package com.example.ima.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.ima.Model.MachineData;
import com.example.ima.Model.MachineImage;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Ahyan on 7/22/2019.
 */

public class MainDatabase {

    private SQLiteDatabase database;
    private MySQLiteHelper DBHelper;


    public MainDatabase() {

    }
    public SQLiteDatabase getDatabase() {
        return database;
    }
    public void setDatabase(SQLiteDatabase database) {
        this.database = database;
    }

    public MySQLiteHelper getDBHelper() {
        return DBHelper;
    }
    public void setDBHelper(MySQLiteHelper dBHelper) {
        DBHelper = dBHelper;
    }
    public MainDatabase(Context context) {
        DBHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = DBHelper.getWritableDatabase();
    }

    public void close() throws SQLException {
        DBHelper.close();
    }

    public String handleNull(Date dt) {
        if (dt == null) {
            return null;
        } else {
            return dt.toGMTString();
        }
    }


    //---- exec query
    // ----- insert
    public void insertMachineData( String machine_name, String machine_type,
                                  int machine_QR_code, String last_Maintenance_Date) {
        ContentValues values = new ContentValues();
        values.put("Machine_name", machine_name);
        values.put("Machine_type", machine_type);
        values.put("Machine_QR_code", machine_QR_code);
        values.put("Last_Maintenance_Date", last_Maintenance_Date);

        database.insert("MACHINE_DATA", null, values);
    }

    public void insertMachineImage( int machine_id,String machine_uri) {
        ContentValues values = new ContentValues();
        values.put("Machine_ID", machine_id);
        values.put("Uri_Image",machine_uri );
        System.out.println("insert id "+machine_id+ " uri "+machine_uri );
        database.insert("MACHINE_IMAGE", null, values);
    }


    //--------select

    public ArrayList<MachineData> getAllMachineData() {

        String query = "SELECT * FROM MACHINE_DATA";
        System.out.println("query "+query);
        Cursor cursor = database.rawQuery(query, null);
        ArrayList<MachineData> hasil = new ArrayList<MachineData>();

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            hasil.add(new MachineData(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getInt(3), cursor
                    .getString(4)));
        }
        return hasil;
    }

    public ArrayList<MachineData> getAllMachineDataById(int id) {

        String query = "SELECT * FROM MACHINE_DATA WHERE Machine_ID=" + id;
        System.out.println("query "+query);
        Cursor cursor = database.rawQuery(query, null);
        ArrayList<MachineData> hasil = new ArrayList<MachineData>();

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            hasil.add(new MachineData(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getInt(3), cursor
                    .getString(4)));
        }
        return hasil;
    }

    public ArrayList<MachineData> getByQR(int id) {

        String query = "SELECT * FROM MACHINE_DATA WHERE Machine_QR_code=" + id;
        System.out.println("query "+query);
        Cursor cursor = database.rawQuery(query, null);
        ArrayList<MachineData> hasil = new ArrayList<MachineData>();

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            hasil.add(new MachineData(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getInt(3), cursor
                    .getString(4)));
        }
        return hasil;
    }

    public ArrayList<MachineImage> getUriImage(int id) {

        String query = "SELECT * FROM MACHINE_IMAGE WHERE Machine_ID=" + id;
        System.out.println("query "+query);
        Cursor cursor = database.rawQuery(query, null);
        ArrayList<MachineImage> hasil = new ArrayList<MachineImage>();

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            hasil.add(new MachineImage(cursor.getInt(0), cursor.getInt(1),
                    cursor.getString(2)));
        }
        return hasil;
    }

}
