package com.example.gsdharmesh.medicare;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class showChatList extends AppCompatActivity {
     static List<doctors> docList = new ArrayList<>();
    private RecyclerView recyclerView;
    private docAdapter dAdapter,dd;
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_chat_list);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        dAdapter = new docAdapter(docList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(dAdapter);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                prepareDocData();
                dAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        prepareDocData();
        dAdapter.notifyDataSetChanged();
    }
    private void prepareDocData() {
        docList.clear();
        new HttpRequestTask().execute();
        dAdapter.notifyDataSetChanged();
    }
    class HttpRequestTask extends AsyncTask<String, Void, String>  {
        int ch=1;
        @Override
        protected String doInBackground(String... a) {
            String x="",p1,p2,p3;

            try {
                String url = "http://10.0.2.2:8080/try1/try/ec/DList";

                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String line;
                int i = Integer.parseInt(in.readLine());
                for(int j=0;j<i;j++)
                {
                    p1=in.readLine();
                    p2=in.readLine();
                    p3=in.readLine();

                    doctors doc = new doctors(p1,p2,p3);
                    if(!(docList.contains(doc)))
                    docList.add(doc);

                }
                in.close();

            } catch (Exception e) {

                }
            return "";
            }




        @Override
        protected void onPostExecute(String x) {


        }}}
