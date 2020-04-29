package com.example.templatesendinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText user_kid;
    EditText user_email;
    EditText user_name;
    String kid, email, name;
    Button send_button;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user_kid = (EditText) findViewById(R.id.KID);
        user_email = (EditText) findViewById(R.id.Email);
        user_name = (EditText) findViewById(R.id.Name);
        send_button = findViewById(R.id.send_button);
        tv = findViewById(R.id.tv);


        send_button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                kid = user_kid.getText().toString();
                email = user_email.getText().toString();
                name = user_name.getText().toString();

                try {
                    LoginTask loginTask = new LoginTask();
                    String msg = String.valueOf(loginTask.execute("http://khprince.com/restaurantApp/login.php", name, email, kid));
                    tv.setText(msg);
                }catch (Exception e){
                    e.printStackTrace();
                    Log.e("tag","In catch");
                }
            }
        });
    }

}


