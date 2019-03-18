package com.example.aayushgarg.rtu_old_paper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aayushgarg on 6/12/18.
 */

public class PdfAddapter extends BaseAdapter {

    Context context;
    ArrayList<PdfDoc> list;


    public PdfAddapter(Context context, ArrayList<PdfDoc> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

       if(view==null)
       {
           view= LayoutInflater.from(context).inflate(R.layout.moder,viewGroup,false);
       }


       final PdfDoc pdfDoc=(PdfDoc)this.getItem(i);
      TextView textView=(TextView)view.findViewById(R.id.nameTxt);
        textView.setText(pdfDoc.getName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    openPdf(pdfDoc.getPath());
            }


        });
   return view;

    }    private void openPdf(String path) {
        Intent intent=new Intent(context,Download.class);
        intent.putExtra("PATH", path );
        context.startActivity(intent);

    }
}
