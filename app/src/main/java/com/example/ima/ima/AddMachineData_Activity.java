package com.example.ima.ima;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ima.database.MainDatabase;

public class AddMachineData_Activity extends AppCompatActivity {

    private Button BtnAddMachineData;
    private EditText EditTextMachineName,EditTextMachineType,EditTextMachineQR,EditTextMachineLastMaintenance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_machine_data_);
        declareId();

        BtnAddMachineData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validasi()){

                    String machineName  = EditTextMachineName.getText().toString();
                    String machineType  = EditTextMachineType.getText().toString();
                    String machineQR  = EditTextMachineQR.getText().toString();
                    String machineLastMaintenance  = EditTextMachineLastMaintenance.getText().toString();

                    insertMachineData(machineName,machineType,Integer.parseInt(machineQR),machineLastMaintenance);

                    startActivity(new Intent(AddMachineData_Activity.this, MachineData_Activity.class));
                }
            }
        });

    }

    private boolean validasi(){

        boolean isValid =  false;
        String machineName  = EditTextMachineName.getText().toString();
        String machineType  = EditTextMachineType.getText().toString();
        String machineQR  = EditTextMachineQR.getText().toString();
        String machineLastMaintenance  = EditTextMachineLastMaintenance.getText().toString();



        if(TextUtils.isEmpty(machineName)){

            Toast.makeText(this, "Machine Name harus diisi", Toast.LENGTH_SHORT).show();

        }else if(TextUtils.isEmpty(machineType)){

            Toast.makeText(this, "Machine Type harus diisi", Toast.LENGTH_SHORT).show();

        }else if(TextUtils.isEmpty(machineQR)){

            Toast.makeText(this, "machine QR Code harus diisi", Toast.LENGTH_SHORT).show();

        }else if(TextUtils.isEmpty(machineLastMaintenance)){

            Toast.makeText(this, "machine Last Maintenance harus diisi", Toast.LENGTH_SHORT).show();

        }else {

            isValid = true;
        }

        return isValid;

    }

    private void declareId(){

        BtnAddMachineData = (Button) findViewById(R.id.btnAddMachineData);
        EditTextMachineName = (EditText) findViewById(R.id.editTextMachineName);
        EditTextMachineType = (EditText) findViewById(R.id.editTextMachineType);
        EditTextMachineQR = (EditText) findViewById(R.id.editTextMachineQR);
        EditTextMachineLastMaintenance = (EditText) findViewById(R.id.editTextMachineLastMaintenance);
    }


    public void insertMachineData(String machine_name, String machine_type, int machine_QR_code, String last_Maintenance_Date) {
        MainDatabase mainDatabase = new MainDatabase(this);
        mainDatabase.open();
        mainDatabase.insertMachineData(machine_name, machine_type, machine_QR_code, last_Maintenance_Date );
        mainDatabase.close();
    }

}
