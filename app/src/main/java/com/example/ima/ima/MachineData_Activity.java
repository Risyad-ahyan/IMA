package com.example.ima.ima;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ima.Model.MachineData;
import com.example.ima.Model.MachineImage;
import com.example.ima.Utils.TypefaceUtil;
import com.example.ima.adapter.MachineDataAdapter;
import com.example.ima.database.QueryDatabase;

import java.util.ArrayList;

public class MachineData_Activity extends AppCompatActivity {

    private Button BtnAddMachineData;
    private MachineDataAdapter defaultAdapter;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_data_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        declareId();

        final ArrayList<MachineData> getAllMachineData = QueryDatabase.GetAllMachineData(getApplicationContext());

        defaultAdapter = new MachineDataAdapter(this, getAllMachineData);

        lv = (ListView) findViewById(R.id.include_listview);
        lv.setAdapter(defaultAdapter);

        int id = 0;
        if (getAllMachineData.size() == 0) {
            id = 0;
        } else {
            id = getAllMachineData.get(0).getMachine_ID();
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

          @Override
          public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                  long arg3) {

              TextView txtview1 = (TextView) arg1.findViewById(R.id.textId);
              System.out.println("idnya " + txtview1.getText().toString());
              final ArrayList<MachineImage> tempImage;
              tempImage=QueryDatabase.GetUriImage(getApplicationContext(),Integer.parseInt(txtview1.getText().toString()));
              ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
              if(!tempImage.isEmpty()) {
                  for (int i = 0; i < tempImage.size(); i++) {

                      mArrayUri.add(Uri.parse(tempImage.get(i).getUri_Image()));
                  }
              }
              Intent i = new Intent(getBaseContext(), DetailMachineData_Activity.class);
              i.putExtra("id", Integer.parseInt(txtview1.getText().toString()));
              i.putExtra("header", "Detail Machine Data");
              i.putExtra("posisi", position);

              startActivity(i);

          }

        });
        setOnClick();


    }

    private void initView(){
        // change type face
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/HelveticaNeueLTPro-MdCn.otf");
    }

    private void setOnClick(){

        BtnAddMachineData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    startActivity(new Intent(MachineData_Activity.this, AddMachineData_Activity.class));

            }
        });
    }

    private void declareId(){

        BtnAddMachineData = (Button) findViewById(R.id.btnFormMachineData);

    }

}
