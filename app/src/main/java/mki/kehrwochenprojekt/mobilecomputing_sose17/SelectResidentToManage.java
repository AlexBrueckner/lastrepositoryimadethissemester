package mki.kehrwochenprojekt.mobilecomputing_sose17;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.Task;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.User;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.DataHolder;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies.UserGet;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies.UserGetByID;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.KehrwochenArrayAdapter;

public class SelectResidentToManage extends AppCompatActivity {

    private static ArrayList<String> adapterArgs;
    private static KehrwochenArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_resident_to_manage);
        adapterArgs = new ArrayList<String>();

        System.out.println("Current Flat = " + DataHolder.getCurrentFlat());
        System.out.println("Current Flat REsident number: " + DataHolder.getCurrentFlat().
                getResidents().size());

        for (Iterator<String> userIterator = DataHolder.getCurrentFlat().getResidents().iterator();
             userIterator.hasNext();) {
            User temp = new User();
            String s = userIterator.next();
            temp.setId(s);
            String userResponse = DataHolder.executeRequest("/app/user/id", UserGetByID.getRequest(temp),
                    "GET");
            try{

                JSONObject tempJson = new JSONObject(userResponse);
                temp.setUserName(tempJson.getString("userName"));
                //Avoid duplicates -> Can't use a Set because that would need me changin EVERYTHING
                // AGIAN
                if(!adapterArgs.contains(KehrwochenArrayAdapter.toArgumentString(temp.getUserName(),
                        temp.getId()))){
                    adapterArgs.add(KehrwochenArrayAdapter.toArgumentString(temp.getUserName(),
                            temp.getId()));
                }

            }
            catch(JSONException e){
                e.printStackTrace();
            }



        }

        adapter = new KehrwochenArrayAdapter(SelectResidentToManage.this, adapterArgs,
                ManageSingleResident.class);
        DataHolder.getKehrwochenAdapters().put("MyUsers",adapter);

        ListView residentsToManageList = (ListView) findViewById(R.id.residentsToManageList);
        residentsToManageList.setAdapter(adapter);

    }

    public static KehrwochenArrayAdapter getAdapter(){
        return adapter;
    }

    public static ArrayList<String> getAdapterArgs(){
        return adapter.getArgs();
    }
}
