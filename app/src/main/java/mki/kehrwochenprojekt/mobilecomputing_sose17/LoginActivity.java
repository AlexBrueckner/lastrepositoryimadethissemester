package mki.kehrwochenprojekt.mobilecomputing_sose17;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.preference.EditTextPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.Flat;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.Task;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.User;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.DataHolder;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.DateParser;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies.FlatGet;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies.TaskGet;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies.UserGet;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies.UserPost;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies.UserPut;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Button login,register;

        login = (Button) findViewById(R.id.buttonLogin);
        register = (Button) findViewById(R.id.buttonRegister);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    EditText name,password;
                    name = (EditText) findViewById(R.id.inputUsername);
                    password = (EditText) findViewById(R.id.inputPassword);
                    final UserLoginTask ult = new UserLoginTask(name.getText().toString(),
                            password.getText().toString());
                    ult.execute((Void) null);
                }
                catch(IllegalStateException ise){
                    ise.printStackTrace();
                }

            }
        });





    }



    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
        // Defining the variables used for the login
        private final String myUsername;
        private final String myPassword;
        private String response;
        private User u;

        UserLoginTask(String username, String password) {
            myUsername = username;
            myPassword = password;
            u = new User();
            u.setUserName(myUsername);
            u.setPassword(myPassword);
           // myUsernameView.setText(myUsername);
        }

        /**
         * This will parse the user data and either create a Toast saying the user should register,
         * or confirms the login and redirects to the Mainactivity.
         */
        @Override
        protected Boolean doInBackground(Void... params) {



            System.out.println("Inside do in Background - starting request");
            //PUT Checks the login data, parse the response
            String response = DataHolder.executeRequest("/app/user/", UserPut.getRequest(u),"PUT");
            try {
                JSONObject o = new JSONObject(response);
                System.out.println("Created JSON obj from response!");
                switch (o.getString("auth")){
                    case "ok":
                        //This is the kind of code you just cannot be proud of. But I'd rather meet
                        //the deadline. I feel sorry for you trying to decypher this and must say
                        //that writing hit hurt me as much as it hurts you reading it.
                        User u = new User();
                        u.setUserName(myUsername);
                        //Parse the user from the REST API
                        JSONObject newUser = new JSONObject(DataHolder.executeRequest("/app/user", UserGet.getRequest(u),"GET"));
                        u.setId(newUser.getString("_id"));
                        u.setUserName(newUser.getString("userName"));
                        u.setPassword(newUser.getString("password"));
                        u.setForeName(newUser.getString("foreName"));
                        u.setSurName(newUser.getString("surName"));


                        //Couple of Tracer Sysos, now proceed to parse the user tasks
                        System.out.println("Received Response: "   + newUser.toString());
                        JSONArray tasksToParse = newUser.getJSONArray("tasks");
                        System.out.println("Response as JSON "+tasksToParse.toString());
                        int numTasks = tasksToParse.length();
                        Gson parser = new Gson();
                        if(numTasks > 0){
                            for(int currTask = 0; currTask < numTasks; currTask++){
                                //Because even our REST guy makes mistakes, we fetch the tasks
                                //This was meant to be an array of the ACTUAL objects, not their ID
                                //So we have to send some requests...
                                Task t = new Task();
                                t.setTaskId(tasksToParse.get(currTask).toString());
                                String resp = DataHolder.executeRequest("/app/task", TaskGet.getRequest(t),"GET");
                                JSONObject readMeOut = new JSONObject(resp);
                                t.setTaskId(readMeOut.getString("_id"));
                                t.setName(readMeOut.getString("name"));
                                t.setGuideline(readMeOut.getString("guideline"));
                                t.setState(Task.State.fromValue(readMeOut.getString("state")));
                                t.setCreationDate(DateParser.parseDate(readMeOut.getString("creationDate")));
                                t.setDeadline(DateParser.parseDate(readMeOut.getString("deadline")));
                                System.out.println("Added task to current user: " + t);
                                u.addTask(t);
                            }

                        }

                        //Once the dummy is filled, put it into our DataHolder
                        DataHolder.setCurrentUser(u);
                        //Grab the corresponding Flat from the REST API, if there is any.
                        Flat f = new Flat();

                        String flatResponse = DataHolder.executeRequest("/app/group",
                                FlatGet.getRequest(u),"GET");
                        System.out.println("Response from FlatGET: "+flatResponse);
                        f = parser.fromJson(flatResponse,Flat.class);
                        DataHolder.setCurrentFlat(f);
                        System.out.println("Created Flat and saved reference..");
                        System.out.println(DataHolder.getCurrentFlat());
                        System.out.println("Auth OK");
                        return true;
                    case "err":
                        //Authentication failed due to a wrong password
                        System.out.println("auth err");
                        return false;
                    default:
                        System.out.println("inside default, something is wrong");
                        return false;
                }
            }
            catch(JSONException jsone){
                jsone.printStackTrace();
                return false;
            }


        }

        /**
         * Method called after the background task for further execution of the application
         * Starting the MainActivity when successfull and displaying an error when not
         * @param success - return statement from doInBackground()
         */
        @Override
        protected void onPostExecute(final Boolean success) {
            //THis just parses the result. If all went well, we just push up the new activity.
            //Should things go awry, we let the user know that we don't know him, and ask him
            //to register with our service.
            System.out.println("Inside onPostExecute");
            if(success){
                System.out.println("Successfully logged in!");
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }
            else{
                System.out.println("Should display toast now");
                Toast.makeText(LoginActivity.this,"Failed to login! Please register",
                        Toast.LENGTH_LONG).show();
            }

        }

        /**
         * Overwrite to make things look somewhat clean
         */
        @Override
        protected void onCancelled() {
            try{
                //Make it look like work is being done
                Thread.sleep(2000);
            }
            catch(InterruptedException ie){
                ie.printStackTrace();
            }


        }
    }


}
