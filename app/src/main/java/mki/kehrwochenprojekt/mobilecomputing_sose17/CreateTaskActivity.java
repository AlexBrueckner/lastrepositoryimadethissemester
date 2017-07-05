package mki.kehrwochenprojekt.mobilecomputing_sose17;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.Task;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.User;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.DataHolder;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.DateParser;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies.TaskPost;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.KehrwochenArrayAdapter;

/***
 * Collects Data from the Layout and sends it to the REST API for creation of a Task
 */
public class CreateTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);


        final EditText userName,taskName,taskGuideline,taskDeadline;
        userName = (EditText) findViewById(R.id.taskUserAdd);
        taskName = (EditText) findViewById(R.id.taskNameAdd);
        taskGuideline = (EditText) findViewById(R.id.taskGuidelineAdd);
        taskDeadline = (EditText) findViewById(R.id.taskDeadlineAdd);

        final Button b = (Button) findViewById(R.id.taskSubmitAdd);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task t = new Task();
                User u = new User();
                //Collect data and fill dummy objects for the requests with them
                u.setUserName(userName.getText().toString());
                t.setName(taskName.getText().toString());
                Date d = new Date();
                d.setTime(System.currentTimeMillis());
                t.setCreationDate(DateParser.parseDate(d.toString()));
                d = DateParser.parseDate(taskDeadline.getText().toString());
                t.setDeadline(d);
                t.setGuideline(taskGuideline.getText().toString());
                //Send prepared data and aprse response
                try {
                String response = DataHolder.executeRequest("/app/task/", TaskPost.getRequest(u,t),
                        "POST");
                JSONObject taskGetAsJSON = new JSONObject(response);
                if(u.getUserName().equals(DataHolder.getCurrentUser().getUserName())){
                    System.out.println("Adding this to current user ");
                    boolean success = ManageMyTasks.getAdapterArgs().add(
                            KehrwochenArrayAdapter.toArgumentString(t.getName(),taskGetAsJSON.getString("taskId")));
                    if(SelectTaskToConfirmActivity.getAdapter() != null){
                    SelectTaskToConfirmActivity.getAdapterArgs().add(
                            KehrwochenArrayAdapter.toArgumentString(t.getName(),taskGetAsJSON.getString("taskId")));
                    }
                    String output = success ? "Successfully updated adapater" :
                    "failed to update adapter!";
                    System.err.println(output);
                }


                    JSONObject jsonResp = new JSONObject(response);

                    if(!jsonResp.has("error")){
                        t.setTaskId(jsonResp.getString("taskId"));
                        DataHolder.getCurrentUser().addTask(t);
                        Toast.makeText(CreateTaskActivity.this,"Task created succesfully!",
                                Toast.LENGTH_LONG);
                    }
                    else{
                        Toast.makeText(CreateTaskActivity.this,"Failed to create task!",
                                Toast.LENGTH_LONG);
                    }

                }
                catch(JSONException jsone){
                    Toast.makeText(CreateTaskActivity.this,"Failed to create task!",
                            Toast.LENGTH_LONG);
                    jsone.printStackTrace();
                }
                finally{
                    finish();
                }

            }
        });
    }
}
