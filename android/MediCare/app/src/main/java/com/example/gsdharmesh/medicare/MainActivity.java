package com.example.gsdharmesh.medicare;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.AsyncTask;
import android.app.Dialog;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.DataOutputStream;



public class MainActivity extends AppCompatActivity {



    static  EditText username=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button doc = (Button) findViewById(R.id.doclogin);
        Button pat = (Button) findViewById(R.id.patlogin);


        username = (EditText) findViewById(R.id.et_email);


        final EditText passwd;
        passwd = (EditText) findViewById(R.id.et_pass);



        final Button reg = (Button) findViewById(R.id.reg);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent INT=new Intent(MainActivity.this,register.class);

                startActivity(INT);
            }
        });

        pat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String p1,p2;
                p1=username.getText().toString();
                p2=passwd.getText().toString();
                String ss[]={"1",p1,p2};
                new HttpRequestTask().execute(ss);

            }
        });

        doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p1,p2;
                p1=username.getText().toString();
                p2=passwd.getText().toString();
                String ss[]={"2",p1,p2};
                new HttpRequestTask().execute(ss);
            }
        });


    }


    class HttpRequestTask extends AsyncTask<String, Void, String> {
        int ch=1;
        @Override
        protected String doInBackground(String... ps) {
            String x="";

            try {
                String url = "http://10.0.2.2:8080/try1/try/ec/Plogin";
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("POST");
                String p1=ps[0] ,p2=ps[1],p3=ps[2];
                if(p1.equals("2"))
                    ch=2;
                con.setDoOutput(true);
                con.setRequestProperty("Content-Type",
                        "text/plain");
                OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
                writer.write(p1 + "\n" + p2+ "\n" + p3);
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
            return x;}

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("yes"))
            {
                //       Intent INT1;
                if(ch==1) {
                    Intent INT1 = new Intent(MainActivity.this, homePatient.class);
                    INT1.putExtra("id", username.getText().toString());
                    startActivity(INT1);
                }
                else
                {
                    Intent INT1 = new Intent(MainActivity.this, homeDoctor.class);
                    INT1.putExtra("id", username.getText().toString());
                    startActivity(INT1);
                }
            }

            else
            {
                Toast.makeText(getApplicationContext(), "invalid id or password!!" , Toast.LENGTH_SHORT).show();
            }}}



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
