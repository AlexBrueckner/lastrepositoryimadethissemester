package mki.kehrwochenprojekt.mobilecomputing_sose17;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.gson.Gson;

import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.Task;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.DataHolder;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies.TaskCommentPost;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies.TaskGet;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies.TaskPut;

public class ConfirmTaskActivity extends AppCompatActivity {

    private String state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmtask);


        Button submitRate = (Button) findViewById(R.id.taskEvaluateButton);
         final EditText comment = (EditText) findViewById(R.id.comment);


        submitRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            String taskId = getIntent().getStringExtra("data");
                System.out.println("Got taskID: " + taskId);
                Task t = new Task();
                t.setTaskId(taskId);
                String response = DataHolder.executeRequest("/app/task", TaskGet.getRequest(t),
                        "GET");
                System.out.println("Response was " + response);
                Gson gson = new Gson();
                t.setState(Task.State.fromValue(state));

                response = DataHolder.executeRequest("/app/task/", TaskPut.getRequest(t),"PUT");
                System.out.println("Response was " + response);
                if(comment.getText().toString() != null && comment.getText().toString().length() > 0){
                    response = DataHolder.executeRequest("/app/task/comment/",
                            TaskCommentPost.getRequest(t,comment.getText().toString()),"POST");
                    System.out.println("Response was " + response);
                    Toast.makeText(ConfirmTaskActivity.this,"Updated Task with rating and comment.",
                            Toast.LENGTH_LONG);
                }
                else{
                    Toast.makeText(ConfirmTaskActivity.this,"Updated Task with rating and " +
                                    "without comment.",Toast.LENGTH_LONG);
                }



                finish();
            }
        });
    }

    public void onRadioButtonClicked(View view){
        RadioButton red,yellow,green;
        red = (RadioButton)findViewById(R.id.radioRed);
        yellow = (RadioButton)findViewById(R.id.radioYellow);
        green = (RadioButton)findViewById(R.id.radioGreen);

        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()){
            case R.id.radioRed:
                state = "RED";
                yellow.setChecked(false);
                green.setChecked(false);
                break;
            case R.id.radioYellow:
                green.setChecked(false);
                red.setChecked(false);
                state = "YELLOW";
                break;
            case R.id.radioGreen:
                red.setChecked(false);
                yellow.setChecked(false);
                state = "GREEN";
                break;
        }
        System.out.println("STATE = " + state);
    }
}
