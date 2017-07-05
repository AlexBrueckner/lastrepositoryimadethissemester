package mki.kehrwochenprojekt.mobilecomputing_sose17;

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

import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.Flat;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.User;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.DataHolder;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies.FlatGet;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies.FlatPost;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies.FlatPut;

/***
 * Sends a POST to the REST API after Data has been collected through the Edit Texts in the layout.
 */
public class AddGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

    //Find the buttons
        Button flatCreateButton = (Button) findViewById(R.id.flatCreateButton);
      final  EditText penalty, groupName;
        penalty = (EditText) findViewById(R.id.penalty);
        groupName = (EditText) findViewById(R.id.groupName);


        //Set the onclick listener
        flatCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Try to create a Flat through the Rest API
                Flat f = new Flat();
                f.setPenalty(penalty.getText().toString());
                f.setName(groupName.getText().toString());
                String response = DataHolder.executeRequest("/app/group/", FlatPost.getRequest(f),
                        "POST");
                System.out.println("Response was: " + response);
                Toast.makeText(AddGroupActivity.this,"Added group!",Toast.LENGTH_LONG).show();
                try {


                //Add the creator to the Flat and print the Flat to check if it worked
                    JSONObject respJson = new JSONObject(response);
                    f.setID(respJson.getString("flatId"));
                    response = DataHolder.executeRequest("/app/group/",
                            FlatPut.getRequest(DataHolder.getCurrentUser(),f),"PUT");
                    System.out.println("Response was: " + response);
                    response = DataHolder.executeRequest("/app/group",
                            FlatGet.getRequest(DataHolder.getCurrentUser()),"GET");
                    System.out.println("Response was: " + response);
                    respJson = new JSONObject(response);
                    JSONArray currResidents;

                    f.setID(respJson.getString("_id"));
                    //Parse the residents. Did not work with Gson :(
                    currResidents = respJson.getJSONArray("residents");
                    if(currResidents.length() > 0){
                        System.out.println("Flat has residents");
                        for(int i = 0; i<currResidents.length(); i++){
                            JSONObject currElem = currResidents.getJSONObject(i);
                            f.getResidents().add(currElem.toString());
                        }
                        System.out.println("Done adding residents");

                    }
                    DataHolder.setCurrentFlat(f);
                    System.out.println("Current flat updated: " + DataHolder.getCurrentFlat());

                }
                catch(JSONException e){
                    e.printStackTrace();
                }

                finish();

            }
        });


    }
}
