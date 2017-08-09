package asutosh.image.chooser.Util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;


public class RuntimePermissions {

    private Context mContext;
    private String[] permissions;


    public RuntimePermissions(String[] permissions, Context context){

        this.mContext = context;
        this.permissions = permissions;

        int PERMISSION_ALL = 1;

        if(!hasPermissions()){
            ActivityCompat.requestPermissions((Activity) mContext, permissions, PERMISSION_ALL);
        }
    }


    public boolean hasPermissions() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && mContext != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(mContext, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

}
