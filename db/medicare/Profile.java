package com.example.gsdharmesh.medicare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Button go_home = (Button) findViewById(R.id.go_to_home);
        go_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent INT=new Intent(Profile.this,homePatient.class);
                startActivity(INT);
            }
        });
    }
}
