package com.example.gsdharmesh.medicare;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout.*;
import android.widget.*;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InterfaceAddress;
import java.net.URL;

import static com.example.gsdharmesh.medicare.R.drawable.shadows;

public class Message extends AppCompatActivity {
    RelativeLayout my_root;
    TextView t[]=new TextView[20];
    TextView t1,t2;
    RelativeLayout layout;
    RelativeLayout.LayoutParams params[]= new RelativeLayout.LayoutParams[20];
    int x=-10,tc=0,lc=0,tid=1,ch=1;

    ImageView im;
    EditText me;
    String pp1,pp2,m;
    BufferedReader in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        im=(ImageView) findViewById(R.id.send);
        me=(EditText)findViewById(R.id.message);
        me.setTextColor(Color.BLACK);
        pp1=getIntent().getExtras().getString("pid");
        pp2=getIntent().getExtras().getString("did");
        im.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                m=me.getText().toString();
                String pp[]={"2",pp2,pp1,"1","0",m};
                me.setText("");
                new HttpRequestTask().execute(pp);
                if(tid==1)
                    firstmessage(m);
                else
                pat_message(m);
            }
        });
       layout = (RelativeLayout) findViewById(R.id.rlayout1);

        refresh();

    }
    void refresh()
    {
        layout.removeAllViews();
        layout.removeAllViewsInLayout();

        String ss[]={"1",pp1,pp2};
        new HttpRequestTask().execute(ss);
    }

    void firstmessage(String message)
    {

        t[tc] = new TextView(this);
        params[lc] =new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params[lc].setMargins(0, 40, 0, 0);
        t[tc].setBackgroundResource(R.drawable.shadows);
        t[tc].setTextColor(Color.BLUE);
        t[tc].setPadding(200,10,0,0);
        t[tc].setId(Integer.parseInt(""+tid));
        t[tc].setText(message);

        layout.addView(t[tc], params[lc]);
        String pp1,pp2;
        tc++;
        lc++;
        tid++;
    }

    void doc_message(String message)
    {
        t[tc] = new TextView(this);
        t[tc].setBackgroundResource(R.drawable.shadows);
        t[tc].setTextColor(Color.BLACK);
        t[tc].setPadding(20,10,0,0);
        params[lc] = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params[lc].setMargins(0, 40, 0, 0);
        params[lc].addRule(RelativeLayout.BELOW,t[tc-1].getId());
        t[tc].setId(Integer.parseInt(""+tid));
        t[tc].setText(message);
        layout.addView(t[tc], params[lc]);
        tc++;
        lc++;
        tid++;
    }
    void pat_message(String message)
    {

        t[tc] = new TextView(this);
        params[lc] =new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params[lc].setMargins(0, 40, 0, 0);
        params[lc].addRule(RelativeLayout.BELOW,t[tc-1].getId());
        t[tc].setBackgroundResource(R.drawable.shadows);
        t[tc].setTextColor(Color.BLUE);
        t[tc].setPadding(200,10,0,0);
        t[tc].setId(Integer.parseInt(""+tid));
        t[tc].setText(message);

                layout.addView(t[tc], params[lc]);

        tc++;
        lc++;
        tid++;

    }


    class HttpRequestTask extends AsyncTask<String, Void, String> {
        String line;
        @Override
        protected String doInBackground(String... ps) {
            String x="",url="",p1="",p2="",p3="",p4="",p5="";
            try {

               if(ps[0].equals("1")) {


                    url = "http://10.0.2.2:8080/try1/try/ec/getmessage";
                }
            else
             {

                    url = "http://10.0.2.2:8080/try1/try/ec/putmessage";
                ch=0;
                }

                URL obj = new URL(url);

                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("POST");
                p1=ps[1] ;p2=ps[2];
                if(ch==0)
                { p3=ps[3];p4=ps[4];p5=ps[5];}
                con.setDoOutput(true);

                con.setRequestProperty("Content-Type",
                        "text/plain");
                OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
                writer.write(p1 + "\n" + p2+"\n" + p3 + "\n" + p4 + "\n" + p5);
                writer.flush();

                 in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String line;
                if(ch==1)
                {

                while ((line = in.readLine()) != null) {

                    if(in.readLine().equals("1")) {
                        if(tid==1) {
                            firstmessage(line);
                        continue;
                        }pat_message(line);

                    }else
                        doc_message(line);
                }}
                in.close();

            } catch (Exception e) {
            }
            return "" ;}
        @Override
        protected void onPostExecute(String x) {



            if(ch==1)
            {

                try {
                    while ((line = in.readLine()) != null) {

                        if(in.readLine().equals("1")) {
                            if(tid==1) {

                                        firstmessage(line);


                                continue;
                            }

                                    pat_message(line);

                        }else
                            doc_message(line);
                    }
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }}}
