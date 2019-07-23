package com.example.ima.Utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;


public class RequestPermission {

    public static String[] hasPermissions(Context context, String... permissions) {

        List<String> retPermission = new ArrayList<>();
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    retPermission.add(permission);
                }
            }
        }
        return retPermission.toArray(new String[retPermission.size()]);

    }

}
