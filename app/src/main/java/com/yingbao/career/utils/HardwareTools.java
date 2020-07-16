package com.yingbao.career.utils;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by FH0002571 on 2018/7/5.
 */

public class HardwareTools {
    public static boolean hasCamera(Context context) {
        PackageManager pm = context.getPackageManager();
        boolean hasCamera = pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)
                || pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT);
        return hasCamera;
    }
}
