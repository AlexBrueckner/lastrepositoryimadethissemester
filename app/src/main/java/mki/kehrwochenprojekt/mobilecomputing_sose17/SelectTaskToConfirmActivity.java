package mki.kehrwochenprojekt.mobilecomputing_sose17;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;

import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.Task;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.DataHolder;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.KehrwochenArrayAdapter;

public class SelectTaskToConfirmActivity extends AppCompatActivity {
    private static KehrwochenArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_task_to_confirm);
        ArrayList<String> adapterArgs = new ArrayList<String>();
        System.out.println("Number of residents in current flat:" +
                DataHolder.getCurrentFlat().getResidents().size());
        System.out.println("Current Number of user tasks " +
                DataHolder.getCurrentUser().getTasks().size());
        for (Iterator<Task> taskIterator = DataHolder.getCurrentUser().getTasks().iterator();
             taskIterator.hasNext(); ) {

            Task t = taskIterator.next();
            String taskName, taskId;
            taskName = t.getName();
            taskId = t.getTaskId();

            adapterArgs.add(KehrwochenArrayAdapter.toArgumentString(taskName, taskId));
        }

        adapter = new KehrwochenArrayAdapter(SelectTaskToConfirmActivity.this, adapterArgs,
                ConfirmTaskActivity.class);

        ListView taskList = (ListView) findViewById(R.id.selectTaskToConfirmList);
        taskList.setAdapter(adapter);
    }

    public static KehrwochenArrayAdapter getAdapter(){
        return adapter;
    }
    public static ArrayList<String> getAdapterArgs() {
        return adapter.getArgs();
    }
    }
