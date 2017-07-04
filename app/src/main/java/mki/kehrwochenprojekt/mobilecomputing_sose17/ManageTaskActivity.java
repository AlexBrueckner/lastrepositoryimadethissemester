package mki.kehrwochenprojekt.mobilecomputing_sose17;

import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.Task;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.DataHolder;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.DateParser;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies.TaskDelete;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies.TaskGet;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies.TaskPatch;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.KehrwochenArrayAdapter;

public class ManageTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_task);

        String taskToRead = getIntent().getStringExtra("data");
        final Task tlocal = new Task();
        tlocal.setTaskId(taskToRead);
        String requestedTask = DataHolder.executeRequest("/app/task", TaskGet.getRequest(tlocal), "GET");
        System.out.println("DEBUG: Requested Task = " + requestedTask);

        final TextView name, creationDate, deadline, guideline, comments, state;
        name = (TextView) findViewById(R.id.displayTaskName);
        creationDate = (TextView) findViewById(R.id.displayTaskCreationDate);
        deadline = (TextView) findViewById(R.id.displayTaskDeadline);
        comments = (TextView) findViewById(R.id.displayTaskComments);
        guideline = (TextView) findViewById(R.id.displayTaskGuideline);
        state = (TextView) findViewById(R.id.displayTaskState);

        Button update, delete;
        update = (Button) findViewById(R.id.updateTaskButton);
        delete = (Button) findViewById(R.id.deleteTaskButton);


        try {
            JSONObject parsedTask = new JSONObject(requestedTask);
            name.setText(parsedTask.getString("name"));
            tlocal.setName(parsedTask.getString("name"));
            creationDate.setText(DateParser.parseDate(parsedTask.getString("creationDate")).toString());
            if(parsedTask.has("deadline")){
            deadline.setText(DateParser.parseDate(parsedTask.getString("deadline")).toString());
            }

            JSONArray commentsJson = parsedTask.getJSONArray("comments");
            if (commentsJson != null && commentsJson.length() > 0) {
                String commentString = "";
                for (int i = 0; i < commentsJson.length(); i++) {
                    commentString += commentsJson.get(i).toString() + "-";
                }
                comments.setText(commentString);
            }

            state.setText(parsedTask.getString("state"));
            guideline.setText(parsedTask.getString("guideline"));

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Task t = new Task();
                    t.setTaskId(tlocal.getTaskId());
                    t.setName(name.getText().toString());
                    t.setDeadline(DateParser.parseDate(deadline.getText().toString()));
                    t.setCreationDate(DateParser.parseDate(creationDate.getText().toString()));
                    t.setState(Task.State.fromValue(state.getText().toString()));
                    TaskPatch tp = new TaskPatch(t.getTaskId(), t.getName(), t.getDeadline(), t.getGuideline());
                    System.out.println("Attempting to patch data: " + tp.getRequest(t));
                    DataHolder.executeRequest("/app/task/", tp.getRequest(t), "PATCH");
                    System.out.println("Task updated!");
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Task t = new Task();
                    t.setTaskId(tlocal.getTaskId());
                    System.out.println("Attempting to delete task");
                    System.out.println("Sending: " + TaskDelete.getRequest(t));
                    DataHolder.executeRequest("/app/task/", TaskDelete.getRequest(t), "DELETE");
                    System.out.println("Task Deletion completed");
                    Toast.makeText(ManageTaskActivity.this, "Task deleted!", Toast.LENGTH_LONG).show();
                    System.out.println("Purging adapter argument: "
                            +KehrwochenArrayAdapter.toArgumentString(tlocal.getName(),
                            tlocal.getTaskId()));
                    ManageMyTasks.getAdapterArgs().remove(KehrwochenArrayAdapter.toArgumentString(
                            tlocal.getName(),tlocal.getTaskId()));
                    if(SelectTaskToConfirmActivity.getAdapter() != null){
                    SelectTaskToConfirmActivity.getAdapterArgs().remove(KehrwochenArrayAdapter.
                            toArgumentString(tlocal.getName(),tlocal.getTaskId()));}
                    DataHolder.getKehrwochenAdapters().get("MyTasks").notifyDataSetChanged();
                    finish();
                }
            });

        } catch (JSONException jsone) {

            jsone.printStackTrace();
            System.err.println("Your Task seems to be malformed. Check the format!");
        }

    }
}
