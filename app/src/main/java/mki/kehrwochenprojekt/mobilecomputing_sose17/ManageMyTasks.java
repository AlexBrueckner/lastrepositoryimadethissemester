package mki.kehrwochenprojekt.mobilecomputing_sose17;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.Task;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.DataHolder;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies.TaskGet;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies.TaskUserGet;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.KehrwochenArrayAdapter;

public class ManageMyTasks extends AppCompatActivity {
    private KehrwochenArrayAdapter adapter;
    private static ArrayList<String> adapterArgs = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_my_tasks);

        for (Iterator<Task> taskIterator = DataHolder.getCurrentUser().getTasks().iterator();
             taskIterator.hasNext(); ) {

            Task t = taskIterator.next();
            String taskName, taskId;
            taskName = t.getName();
            taskId = t.getTaskId();
            //Avoid duplicates -> Can't use a Set because that would need me changin EVERYTHING AGIAN
            if(!adapterArgs.contains(KehrwochenArrayAdapter.toArgumentString(taskName, taskId))){
            adapterArgs.add(KehrwochenArrayAdapter.toArgumentString(taskName, taskId));
            }
        }

        adapter = new KehrwochenArrayAdapter(ManageMyTasks.this, adapterArgs,
                ManageTaskActivity.class);
        DataHolder.getKehrwochenAdapters().put("MyTasks",adapter);

        ListView taskList = (ListView) findViewById(R.id.myTasksList);
        taskList.setAdapter(adapter);


    }

    public KehrwochenArrayAdapter getAdapter() {
        return adapter;
    }
    public static ArrayList<String> getAdapterArgs(){
        return adapterArgs;
    }
}
