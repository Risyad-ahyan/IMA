package com.example.ima.ima;

/**
 * Created by Ahyan on 7/22/2019.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ima.Constant.MessageText;
import com.example.ima.Constant.RequestNumber;
import com.example.ima.Model.MachineData;
import com.example.ima.database.QueryDatabase;
import com.google.zxing.Result;

import java.util.ArrayList;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class ScannerViewActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler, RequestNumber, MessageText {

    private ZXingScannerView scannerView;
    private String firstCodeId;
    private String idCode;
    private boolean isFirstScannedDone = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(scannerView);


    }

    private void init() {

        scannerView = new ZXingScannerView(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }


    @Override
    public void handleResult(Result result) {

        scannerView.stopCamera();

        String resultText           = result.getText();
        final String firstScanned   = resultText.substring(0, Math.min(resultText.length(), 2)); // identifying that scanned from user or officer
      //  final String idCodeScanned  = resultText.substring(Math.min(resultText.length(), 3), resultText.length()); // id

        Toast.makeText(this, "firstScanned "+firstScanned, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "idCodeScanned "+idCodeScanned, Toast.LENGTH_SHORT).show();
        String messageScanned = "";
        int QRcode = Integer.parseInt(firstScanned);
        final ArrayList<MachineData> temp;
        temp = QueryDatabase.GetAllMachineDataByQR(getApplicationContext(),QRcode);

        if(temp.isEmpty()){
            messageScanned = "Tidak Ada Data Machine";
        }else {

            System.out.println("QR nya " + QRcode+" id "+temp.get(0).getMachine_ID());
            Intent i = new Intent(getBaseContext(), DetailMachineData_Activity.class);
            i.putExtra("id", temp.get(0).getMachine_ID());
            i.putExtra("header", "Detail Machine Data");

            startActivity(i);
        }

        // checking if first scan is done
       /* if(isFirstScannedDone){

            // checking if first id is same (means that scan with his own QR Code self "disable scan twice with same qr code")
            if(firstCodeId.equalsIgnoreCase(firstScanned)){

                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Scan Result");
                builder.setMessage(messageScanned);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        startCamera();
                        dialog.dismiss();

                    }
                });

                AlertDialog alert = builder.create();
                alert.setCancelable(false);
                alert.show();

            }else {

                // checking that id is same
                if(idCode.equals(idCodeScanned)){

                    // success
                    this.finish();
                    //startActivity(new Intent(ScannerViewActivity.this, ResultScanQRCode.class));

                }else {

                    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Scan Result");
                    builder.setMessage("QR Code tidak cocok!");
                    builder.setPositiveButton("Rescan", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            startCamera();
                            dialog.dismiss();

                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.setCancelable(false);
                    alert.show();

                }

            }


        }else{

            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Scan Result");
            builder.setMessage("Scan QR Code kedua");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    firstCodeId         = firstScanned;
                    idCode              = idCodeScanned;
                    isFirstScannedDone  = true;
                    startCamera();
                    dialog.dismiss();

                }
            });
            builder.setNegativeButton("Rescan", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startCamera();
                    dialog.dismiss();
                }
            });

            AlertDialog alert = builder.create();
            alert.setCancelable(false);
            alert.show();



        }*/
    }

    private void startCamera(){

        scannerView.startCamera();
        scannerView.resumeCameraPreview(ScannerViewActivity.this);

    }
}