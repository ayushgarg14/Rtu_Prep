package com.example.aayushgarg.rtu_old_paper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by aayushgarg on 6/7/18.
 */

public class AddapterPaper extends RecyclerView.Adapter<AddapterPaper.ViewHolder> {

    private List<Keytable> item;
    private Context context;
    private MyFunction myFunction;
   // private InterstitialAd mInterstitialAd;
    public AddapterPaper(List<Keytable> item, Context context) {

        this.item = item;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.paperlist,   parent,false);
        final   ViewHolder viewHolder=new ViewHolder(v);
     try{
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myFunction = MyFunction.getInstance();

                String name = (item.get(viewHolder.getAdapterPosition()).getKey());
                myFunction.setSubname(name);
                Intent intent=new Intent(context,Paperyear.class);
                context.startActivity(intent);
           }
        });
     }
        catch (Exception e)
        {
            Toast.makeText(context,e.toString(),Toast.LENGTH_SHORT).show();
        }
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Keytable listItem =item.get(position);
        holder.t1.setText("Sub  :"+listItem.getKey());
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView t1;
        public ViewHolder(View itemView) {
            super(itemView);
            t1=(TextView) itemView.findViewById(R.id.subjectName);

        }
}}
