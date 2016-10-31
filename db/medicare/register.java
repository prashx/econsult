package com.example.gsdharmesh.medicare;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class register extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button sub = (Button) findViewById(R.id.btn_submit);
        final EditText username;
        username = (EditText) findViewById(R.id.etr_email);


        final EditText passwd;
        passwd = (EditText) findViewById(R.id.etr_pass);


        final EditText ph;
        ph = (EditText) findViewById(R.id.etr_ph);

        final EditText degrees;
        degrees = (EditText) findViewById(R.id.etr_deg);

        final EditText spl;
        spl = (EditText) findViewById(R.id.etr_spe);


        final CheckBox cb=(CheckBox)findViewById(R.id.chk_terms);

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb.isChecked()==true)
                {
                    String p1,p2,p3,p4,p5;
                    p1=username.getText().toString();
                    p2=passwd.getText().toString();
                    p3=ph.getText().toString();
                    p4=degrees.getText().toString();
                    p5=spl.getText().toString();

                    String ss[]={p1,p2,p3,p4,p5};
                    new HttpRequestTask().execute(ss);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "kindly accept the terms and conditions" , Toast.LENGTH_SHORT).show();
                }
            }

        });


    }


    class HttpRequestTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... ps) {
            String x="",url="",p1="",p2="",p3="",p4="",p5="";
            try {
                if(ps[3].equals("")) {
                    url = "http://10.0.2.2:8080/try1/try/ec/Pregister";
                }
                else
                    url="http://10.0.2.2:8080/try1/try/ec/Dregister";
                URL obj = new URL(url);

                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("POST");
                p1=ps[0] ;p2=ps[1];p3=ps[2];p4=ps[3];p5=ps[4];
                con.setDoOutput(true);
                con.setRequestProperty("Content-Type",
                        "text/plain");
                OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
                writer.write(p1 + "\n" + p2+ "\n" + p3 +"\n"+ p4 + "\n" + p5);
                writer.flush();
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    x=x+line;
                }
                in.close();

            } catch (Exception e) {
            }
            return x ;}

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("posted"))
            {

                Intent INT1 = new Intent(register.this, MainActivity.class);
                startActivity(INT1);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "something went wrong!!" , Toast.LENGTH_SHORT).show();
            }}}



}
