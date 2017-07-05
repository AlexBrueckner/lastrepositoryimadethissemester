package mki.kehrwochenprojekt.mobilecomputing_sose17;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/***
 * MainActivity
 * Redirects to other activities, depending on depressed button
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Wubba lubba dub dub!!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myTasks = (Button) findViewById(R.id.myTasksButton);
        myTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ManageMyTasks.class);
                startActivity(i);
            }
        });

        Button createFlat = (Button) findViewById(R.id.createFlatButton);
        Button manageFlat = (Button) findViewById(R.id.myFlatButton);
        Button confirmTask = (Button) findViewById(R.id.confirmTaskButton);
        Button createTask = (Button) findViewById(R.id.createTaskButton);

        createTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CreateTaskActivity.class);
                startActivity(i);
            }
        });
        createFlat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddGroupActivity.class);
                startActivity(i);
            }
        });

        manageFlat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ManageGroupActivity.class);
                startActivity(i);
            }
        });

        confirmTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SelectTaskToConfirmActivity.class);
                startActivity(i);
            }
        });


    }
}
