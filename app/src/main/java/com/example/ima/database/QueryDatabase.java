package com.example.ima.database;

import android.content.Context;

import com.example.ima.Model.MachineData;
import com.example.ima.Model.MachineImage;

import java.util.ArrayList;

/**
 * Created by Ahyan on 7/23/2019.
 */

public class QueryDatabase {

    public static ArrayList<MachineData> GetAllMachineData(Context context){
        MainDatabase mainDatabase = new MainDatabase(context);
        mainDatabase.open();
        return mainDatabase.getAllMachineData();
    }
    public static ArrayList<MachineData> GetAllMachineDataById(Context context,int id){
        MainDatabase mainDatabase = new MainDatabase(context);
        mainDatabase.open();
        return mainDatabase.getAllMachineDataById(id);
    }

    public static ArrayList<MachineData> GetAllMachineDataByQR(Context context,int id){
        MainDatabase mainDatabase = new MainDatabase(context);
        mainDatabase.open();
        return mainDatabase.getByQR(id);
    }
//--image
    public static ArrayList<MachineImage> GetUriImage(Context context, int id){
        MainDatabase mainDatabase = new MainDatabase(context);
        mainDatabase.open();
        return mainDatabase.getUriImage(id);
    }
}
