package com.example.gsdharmesh.medicare;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by gsdharmesh on 10/25/16.
 */
public class docAdapter extends RecyclerView.Adapter<docAdapter.MyViewHolder> {
    private List<doctors> docList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,spec,status;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            spec = (TextView) view.findViewById(R.id.spec);
            status = (TextView) view.findViewById(R.id.status);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent INT=new Intent(itemView.getContext(),Message.class);
                    doctors p=docList.get(getLayoutPosition());
                    INT.putExtra("pid", ((Activity)itemView.getContext()).getIntent().getExtras().getString("id") );
                    INT.putExtra("did",  p.getName());
                    itemView.getContext().startActivity(INT);

                }
            });


        }

    }


    public docAdapter(List<doctors> docsList) {
        this.docList = docsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sliding_images, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        doctors docs = docList.get(position);
        holder.name.setText(docs.getName());
        holder.spec.setText(docs.getSpec());
        holder.status.setText(docs.getStatus());




    }

    @Override
    public int getItemCount() {
        return docList.size();
    }



}
