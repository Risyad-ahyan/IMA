package com.example.ima.ima;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ima.database.MainDatabase;

public class ShowImageActivity extends AppCompatActivity {


    private ImageView ivGallery;
    private Button btnClose,btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        final Intent intent = getIntent();
        final String path = intent.getStringExtra("path");
        final int id  = intent.getIntExtra("id",0);
        System.out.println("id nya  "+id);


        Bitmap b=BitmapFactory.decodeFile(path);

        ivGallery = (ImageView) findViewById(R.id.imageView);
        ivGallery.setImageBitmap(b);

        btnClose = (Button) findViewById(R.id.btnback);
        btnDelete= (Button) findViewById(R.id.btnDelete);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               deleteMachineImage(id,path);
                Intent i = new Intent(getBaseContext(), DetailMachineData_Activity.class);
                intent.putExtra("id",id);
                startActivity(i);
            }
        });

    }

    public void deleteMachineImage(int id , String path){
        MainDatabase mainDatabase = new MainDatabase(getApplicationContext());
        mainDatabase.open();
        mainDatabase.deleteMachineImg(id,path);
        mainDatabase.close();
    }
}
