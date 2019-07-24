package com.example.ima.ima;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ima.Model.MachineData;
import com.example.ima.Model.MachineImage;
import com.example.ima.adapter.GalleryAdapter;
import com.example.ima.adapter.GalleryAdapterString;
import com.example.ima.database.MainDatabase;
import com.example.ima.database.QueryDatabase;

import java.util.ArrayList;
import java.util.List;

public class DetailMachineData_Activity extends AppCompatActivity {

    private TextView EditTextMachineName,EditTextMachineType,EditTextMachineQR,EditTextMachineLastMaintenance;
    int id;
    int posisi;
    int PICK_IMAGE_MULTIPLE = 10;
    String imageEncoded;
    List<String> imagesEncodedList;
    private GridView gvGallery;
    private GalleryAdapter galleryAdapter;
    private GridView gvGalleryString;
    private GalleryAdapterString galleryAdapterString;
    private Button btnImage;



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_machine_data_);


        Intent intent = getIntent();
        setId(intent.getIntExtra("id",0));
        posisi=intent.getIntExtra("posisi",0);
        System.out.println("id = "+id);


        final ArrayList<MachineData> temp;
        final ArrayList<MachineImage> tempImage;
        temp = QueryDatabase.GetAllMachineDataById(getApplicationContext(),getId());
        tempImage=QueryDatabase.GetUriImage(getApplicationContext(),getId());

        EditTextMachineName = (TextView) findViewById(R.id.detail_text_machine_name);
        EditTextMachineType = (TextView) findViewById(R.id.detail_text_machine_Type);
        EditTextMachineQR = (TextView) findViewById(R.id.detail_text_machine_QR);
        EditTextMachineLastMaintenance = (TextView) findViewById(R.id.detail_text_machine_LastMaintenance);

        EditTextMachineName.setText(temp.get(0).getMachine_name());
        EditTextMachineType.setText(temp.get(0).getMachine_type());
        EditTextMachineQR.setText(""+temp.get(0).getMachine_QR_code());
        EditTextMachineLastMaintenance.setText(""+temp.get(0).getLast_Maintenance_Date());


        gvGallery = (GridView)findViewById(R.id.gv);
        btnImage = (Button) findViewById(R.id.btnMachineImage);
        String[] filePathColumn = { MediaStore.Images.Media.DATA };

        if(!tempImage.isEmpty()){

            ArrayList<String> mArrayUri = new ArrayList<String>();
            for(int i = 0 ; i<tempImage.size();i++){

                mArrayUri.add(tempImage.get(i).getUri_Image());


                /*Cursor cursor = getContentResolver().query(mArrayUri.get(i), filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imageEncoded  = cursor.getString(columnIndex);
                imagesEncodedList.add(imageEncoded);
                cursor.close();*/

                System.out.println("uri nya adalah = "+mArrayUri.get(i));



            }
            galleryAdapterString = new GalleryAdapterString(getApplicationContext(),mArrayUri,getId());
            gvGallery.setAdapter(galleryAdapterString);
            gvGallery.setVerticalSpacing(gvGallery.getHorizontalSpacing());
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) gvGallery
                    .getLayoutParams();
            mlp.setMargins(0, gvGallery.getHorizontalSpacing(), 0, 0);

        }else{
            System.out.println("masuk else empty");
        }

        gvGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {



                System.out.println(" id text view : "+getId());
                Intent i = new Intent(getBaseContext(), ShowImageActivity.class);
                i.putExtra("id", getId());
                i.putExtra("header", "Show Image");
                i.putExtra("posisi", position);

                startActivity(i);

                //finish();
            }
        });

        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE_MULTIPLE);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            int id = getId();

            // When an Image is picked
            if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                imagesEncodedList = new ArrayList<String>();
                if(data.getData()!=null){

                    Uri mImageUri=data.getData();

                    // Get the cursor
                    Cursor cursor = getContentResolver().query(mImageUri,
                            filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imageEncoded  = cursor.getString(columnIndex);
                    cursor.close();

                    ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                    mArrayUri.add(mImageUri);
                    galleryAdapter = new GalleryAdapter(getApplicationContext(),mArrayUri);
                    gvGallery.setAdapter(galleryAdapter);
                    gvGallery.setVerticalSpacing(gvGallery.getHorizontalSpacing());
                    ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) gvGallery
                            .getLayoutParams();
                    mlp.setMargins(0, gvGallery.getHorizontalSpacing(), 0, 0);

                } else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                        for (int i = 0; i < mClipData.getItemCount(); i++) {

                            ClipData.Item item = mClipData.getItemAt(i);
                            //System.out.println("uri nya adalah "+mArrayUri.get(i));
                            Uri uri = item.getUri();


                            mArrayUri.add(uri);
                            // Get the cursor
                            Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                            // Move to first row
                            cursor.moveToFirst();

                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            imageEncoded  = cursor.getString(columnIndex);
                            imagesEncodedList.add(imageEncoded);
                            cursor.close();


                            System.out.println("uri nya adalah "+uri.getPath()+" image encode "+imageEncoded);

                            //insert uri
                            insertMachineImage(getId(),imageEncoded);


                            galleryAdapter = new GalleryAdapter(getApplicationContext(),mArrayUri);
                            gvGallery.setAdapter(galleryAdapter);
                            gvGallery.setVerticalSpacing(gvGallery.getHorizontalSpacing());
                            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) gvGallery
                                    .getLayoutParams();
                            mlp.setMargins(0, gvGallery.getHorizontalSpacing(), 0, 0);

                        }
                        Log.v("LOG_TAG", "Selected Images" + mArrayUri.size());
                    }
                }
            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void insertMachineImage( int machine_id, String machine_uri) {
        MainDatabase mainDatabase = new MainDatabase(this);
        mainDatabase.open();
        mainDatabase.insertMachineImage(id, machine_uri );
        mainDatabase.close();
    }
}
