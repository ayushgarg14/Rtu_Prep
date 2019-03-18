package com.example.aayushgarg.rtu_old_paper;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.List;

/**
 * Created by aayushgarg on 2/10/19.
 */

public class AdapterSyllabus extends RecyclerView.Adapter<AdapterSyllabus.ViewHolder>  {
    private ProgressDialog progressDialog;
    private List<SyllabusTable> item;
    private Context context;
    private MyFunction myFunction;

    public AdapterSyllabus(List<SyllabusTable> item, Context context) {
        this.item = item;
        this.context = context;
    }

    @Override
    public AdapterSyllabus.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.syllabuslist,   parent,false);
        final AdapterSyllabus.ViewHolder viewHolder=new AdapterSyllabus.ViewHolder(v);



        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(context);


                if(checkInternet()==false)
                    Toast.makeText(context,"Check Your Internet Cnnection",Toast.LENGTH_LONG).show();

                else {

                    progressDialog.setMessage("progress....");
                    progressDialog.show();


                    myFunction = MyFunction.getInstance();

                    String branch = myFunction.getBranch();
                    String Year = myFunction.getYear();
                    final String subname = myFunction.getSubname();
                    final String year = item.get(viewHolder.getAdapterPosition()).getYear();
                    StorageReference storageRef = FirebaseStorage.getInstance().getReference().child(Year).child(branch).child(subname + year);

                    storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {


                        DownloadManager downloadManager = myFunction.getDownloadManager();

                        public void onSuccess(Uri uri) {
                            try {
                                File file = new File(Environment.getExternalStorageDirectory() + "/RTU_PAPER");
                                if (!file.exists())
                                    file.mkdir();

                                DownloadManager.Request request = new DownloadManager.Request(uri);

                                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED).setDestinationInExternalPublicDir("/RTU_PAPER", subname + " " + year + ".pdf");

                                Long ref = downloadManager.enqueue(request);

                                progressDialog.dismiss();
                                Toast.makeText(context, "downloading...", Toast.LENGTH_SHORT).show();


                                // Got the download URL for 'users/me/profile.png'
                            } catch (Exception e) {
                                Toast.makeText(context, "fail.../n" + e.toString(), Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressDialog.dismiss();
                            Toast.makeText(context, "Fail....", Toast.LENGTH_SHORT).show();

                            // Handle any errors
                        }
                    });


                }     }

        });
        return  viewHolder;

    }

    @Override
    public void onBindViewHolder(AdapterSyllabus.ViewHolder holder, int position) {
        SyllabusTable listItem =item.get(position);
        holder.t1.setText("Sub  :"+listItem.getBranchName());
        holder.t2.setText("year "+listItem.getYear());


    }

    @Override
    public int getItemCount() {
        return item.size();
    }


    public boolean checkInternet() {
        ConnectivityManager manager = (ConnectivityManager)context. getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED || manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            return true;
        } else
            return false;


    }




    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView t1;
        public TextView t2;

        public ViewHolder(View itemView) {
            super(itemView);
            t1 = (TextView) itemView.findViewById(R.id.syllabusname2);
            t2 = (TextView) itemView.findViewById(R.id.year2);
        }
    }
}
