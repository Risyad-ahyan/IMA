package com.example.ima.Model;

/**
 * Created by Ahyan on 7/22/2019.
 */

public class MachineData {

    private int Machine_ID;
    private String Machine_name;
    private String Machine_type;
    int Machine_QR_code;
    private String Last_Maintenance_Date;

    public MachineData(){}

    public MachineData(int machine_ID, String machine_name, String machine_type, int machine_QR_code, String last_Maintenance_Date) {
        this.Machine_ID = machine_ID;
        this.Machine_name = machine_name;
        this.Machine_type = machine_type;
        this.Machine_QR_code = machine_QR_code;
        this.Last_Maintenance_Date = last_Maintenance_Date;
    }

    public int getMachine_ID() {
        return Machine_ID;
    }

    public void setMachine_ID(int machine_ID) {
        Machine_ID = machine_ID;
    }

    public String getMachine_name() {
        return Machine_name;
    }

    public void setMachine_name(String machine_name) {
        Machine_name = machine_name;
    }

    public String getMachine_type() {
        return Machine_type;
    }

    public void setMachine_type(String machine_type) {
        Machine_type = machine_type;
    }

    public int getMachine_QR_code() {
        return Machine_QR_code;
    }

    public void setMachine_QR_code(int machine_QR_code) {
        Machine_QR_code = machine_QR_code;
    }

    public String getLast_Maintenance_Date() {
        return Last_Maintenance_Date;
    }

    public void setLast_Maintenance_Date(String last_Maintenance_Date) {
        Last_Maintenance_Date = last_Maintenance_Date;
    }
}
