package mki.kehrwochenprojekt.mobilecomputing_sose17;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.Flat;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.DataHolder;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies.FlatPatch;

/***
 * ManageGroupActivity
 * Offers functions to manage groups, such as changing values and removing residents
 */
public class ManageGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_group);
       final EditText penalty,numResidents,name;


        Flat temp = DataHolder.getCurrentFlat();

        penalty = (EditText) findViewById(R.id.manageGroupPenalty);
        numResidents = (EditText) findViewById(R.id.manageResidentCount);
        name = (EditText) findViewById(R.id.manageGroupName);

        System.out.println(penalty);
        System.out.println(numResidents);
        System.out.println(name);

        //Fill the TextViews with the CURRENTLY ACTIVE DATA
        penalty.setText(temp.getPenalty());
        numResidents.setText(""+temp.getResidents().size());
        name.setText(temp.getName());
        //Prepare the buttons for redirection to the ManageResidentsACtivity
        Button manageResidents = (Button) findViewById(R.id.buttonManageResidents);
        Button updateGroup = (Button) findViewById(R.id.buttonupdateGroup);
        manageResidents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ManageGroupActivity.this, SelectResidentToManage.class);
                startActivity(i);
            }
        });

        updateGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Flat f = new Flat();
                f.setID(DataHolder.getCurrentFlat().getID());
                f.setName(name.getText().toString());
                f.setPenalty(penalty.getText().toString());
                //Prepare a FlatPatcher instance with the supplied data, then execute a PATCH request
                FlatPatch fp = new FlatPatch(f.getID(),f.getName(),f.getPenalty());
                String response = DataHolder.executeRequest("/app/group/", fp.getRequest(f),"PATCH");
                System.out.println("Response was: " + response);
                finish();
            }
        });




    }
}
