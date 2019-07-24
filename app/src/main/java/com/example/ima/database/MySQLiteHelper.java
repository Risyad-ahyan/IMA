package com.example.ima.database;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Ahyan on 7/22/2019.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {
    public static final String DB_Name="IMA.db";
    public static final int db_ver=5;


    private static final String DB_CREATE1 =
            "CREATE TABLE MACHINE_DATA (" +
                    "	Machine_ID 		INTEGER PRIMARY KEY   AUTOINCREMENT, " +
                    "	Machine_name		VARCHAR(255), " +
                    "	Machine_type			VARCHAR(255), " +
                    "	Machine_QR_code 		NUMERIC, " +
                    "	Last_Maintenance_Date  	DATE" +
                    ")";

    private static final String DB_CREATE2 =
            "CREATE TABLE MACHINE_IMAGE (" +
                    "	Image_ID 		INTEGER PRIMARY KEY   AUTOINCREMENT, " +
                    "	Machine_ID		INTEGER, " +
                    "	Uri_Image			VARCHAR(255) " +
                    ")";

    public MySQLiteHelper (Context context){
        super(context,DB_Name,null,db_ver);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(DB_CREATE1);
        db.execSQL(DB_CREATE2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS MACHINE_DATA");
        db.execSQL("DROP TABLE IF EXISTS MACHINE_IMAGE");

        onCreate(db);
    }
}
