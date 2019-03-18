package com.example.aayushgarg.rtu_old_paper;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class folder extends AppCompatActivity {
   ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);
        lv= (ListView) findViewById(R.id.lv);
        lv.setAdapter(new PdfAddapter(folder.this,getPDFs()));
}

    private ArrayList<PdfDoc> getPDFs()

    {
        ArrayList<PdfDoc> pdfDocs=new ArrayList<>();
        //TARGET FOLDER
        File downloadsFolder = new File(Environment.getExternalStorageDirectory() + "/Rtu_paper_Jokerzzz");

        PdfDoc pdfDoc;

        if(downloadsFolder.exists())
        {
            //GET ALL FILES IN DOWNLOAD FOLDER
            File[] files=downloadsFolder.listFiles();

            //LOOP THRU THOSE FILES GETTING NAME AND URI
            for (int i=0;i<files.length;i++)
            {
                File file=files[i];

                if(file.getPath().endsWith("pdf"))
                {
                    pdfDoc=new PdfDoc();
                    pdfDoc.setName(file.getName());
                    pdfDoc.setPath(file.getAbsolutePath());

                    pdfDocs.add(pdfDoc);
                }

            }
        }
else {
        }
        return pdfDocs;
    }
}
