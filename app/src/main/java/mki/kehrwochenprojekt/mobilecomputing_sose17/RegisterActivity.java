
package mki.kehrwochenprojekt.mobilecomputing_sose17;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.User;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.DataHolder;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies.UserPost;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        Button regButton = (Button) findViewById(R.id.register);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText username,password,surname,forename;
                username = (EditText) findViewById(R.id.userName);
                password = (EditText) findViewById(R.id.password);
                forename = (EditText) findViewById(R.id.foreName);
                surname = (EditText) findViewById(R.id.surName);

                User u = new User();
                u.setUserName(username.getText().toString());
                u.setPassword(password.getText().toString());
                u.setForeName(forename.getText().toString());
                u.setSurName(surname.getText().toString());
                String response = DataHolder.executeRequest("/app/user/", UserPost.getRequest(u),"POST");
                Toast.makeText(RegisterActivity.this, "Successfully registered. Please login now.",
                        Toast.LENGTH_LONG).show();
                finish();

            }
        });








    }
}
