package mki.kehrwochenprojekt.mobilecomputing_sose17;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.Flat;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.User;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.DataHolder;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies.FlatUserDelete;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies.UserGetByID;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.KehrwochenArrayAdapter;
/***
 * Activity called my SelectResidentToManage
 * OFfers Management functions for this single resident instance
 */

public class ManageSingleResident extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Fetch Data
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_single_resident);
        final Button b = (Button) findViewById(R.id.deleteUserFromFlatButton);
        final EditText userName,surName,foreName;

        userName = (EditText) findViewById(R.id.residentEditUsername);
        foreName = (EditText) findViewById(R.id.residentEditForename);
        surName = (EditText) findViewById(R.id.residentEditSurname);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        //Build dummies
                User u = new User();
                Flat f = new Flat();
        //Fill them with the data supplied by the adapter argument
                String arg = getIntent().getStringExtra("data");
                u.setId(arg);
                f.setID(DataHolder.getCurrentFlat().getID());
        //See if the REST guy can find something
                String response = DataHolder.executeRequest("/app/user/id",
                        UserGetByID.getRequest(u),"GET");
                try{
                    //Parse the result, put it into the adapter, and notify him of the change
                    JSONObject jsono = new JSONObject(response);
                    u.setUserName(jsono.getString("userName"));
                    response = DataHolder.executeRequest("/app/group/user/",
                            FlatUserDelete.getRequest(f,u),"DELETE");
                    SelectResidentToManage.getAdapterArgs().remove(
                            KehrwochenArrayAdapter.toArgumentString(
                                    u.getUserName(),u.getId()));
                    DataHolder.getKehrwochenAdapters().get("MyUsers").notifyDataSetChanged();

                }

                catch(JSONException e){
                    e.printStackTrace();
                }

            }
        });

    }
}
