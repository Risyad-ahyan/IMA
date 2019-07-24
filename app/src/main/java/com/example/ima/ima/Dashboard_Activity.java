package com.example.ima.ima;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;

import android.view.View;

import com.example.ima.Constant.MessageText;
import com.example.ima.Constant.RequestNumber;
import com.example.ima.Utils.RequestPermission;

public class Dashboard_Activity extends AppCompatActivity implements RequestNumber, MessageText
{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_layout);


        CardView CV_MachineData = (CardView) findViewById(R.id.cardViewMachineData);
        CardView scan = (CardView) findViewById(R.id.cardViewScan);

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(Dashboard_Activity.this, Member_service_Activity.class));
                doCheckPermission();
            }
        });

        CV_MachineData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Dashboard_Activity.this, MachineData_Activity.class));

            }
        });
    }

    private void doCheckPermission() {

        String[] PERMISSIONS = RequestPermission.hasPermissions(Dashboard_Activity.this, Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (PERMISSIONS.length > 0) {
            ActivityCompat.requestPermissions(Dashboard_Activity.this, PERMISSIONS, REQUEST_CAMERA);

        } else {
            startActivity(new Intent(Dashboard_Activity.this, ScannerViewActivity.class));
        }

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}
