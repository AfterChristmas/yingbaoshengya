package com.yingbao.career.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;

public class WygkPlusPermissionTools {


    public static boolean checkAndApplyPermissionActivity(Activity activity, String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permissions = checkPermissions(activity, permissions);
            if (permissions != null && permissions.length > 0) {
                ActivityCompat.requestPermissions(activity, permissions, requestCode);
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public static boolean checkAndApplyPermissionFragment(Fragment mFragment, String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permissions = checkPermissions(mFragment.getActivity(), permissions);
            if (permissions != null && permissions.length > 0) {
                if (mFragment.getActivity() != null) {
                    mFragment.requestPermissions(permissions, requestCode);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    private static String[] checkPermissions(Context context, String[] permissions) {
        if (permissions == null || permissions.length == 0) {
            return new String[0];
        }
        ArrayList<String> permissionLists = new ArrayList<>();
        permissionLists.addAll(Arrays.asList(permissions));
        for (int i = permissionLists.size() - 1; i >= 0; i--) {
            if (ContextCompat.checkSelfPermission(context, permissionLists.get(i)) == PackageManager.PERMISSION_GRANTED) {
                permissionLists.remove(i);
            }
        }

        String[] temps = new String[permissionLists.size()];
        for (int i = 0; i < permissionLists.size(); i++) {
            temps[i] = permissionLists.get(i);
        }
        return temps;
    }


    public static boolean checkPermission(int[] grantResults) {
        if (grantResults == null || grantResults.length == 0) {
            return true;
        } else {
            int temp = 0;
            for (int i : grantResults) {
                if (i == PackageManager.PERMISSION_GRANTED) {
                    temp++;
                }
            }
            return temp == grantResults.length;
        }
    }

    public static void showPermissionsToast(Activity activity, @NonNull String[] permissions) {
        if (permissions.length > 0) {
            for (String permission : permissions) {
                showPermissionToast(activity, permission);
            }
        }
    }

    private static void showPermissionToast(Activity activity, @NonNull String permission) {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
            //用户勾选了不再询问,提示用户手动打开权限
            switch (permission) {
                case Manifest.permission.CAMERA:
                    WygkPlusToast.makeText(activity, "相机权限已被禁止，请在应用管理中打开权限或卸载重装");
                    break;
                case Manifest.permission.READ_EXTERNAL_STORAGE:
                case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                    WygkPlusToast.makeText(activity, "文件权限已被禁止，请在应用管理中打开权限或卸载重装");
                    break;
                case Manifest.permission.RECORD_AUDIO:
                    WygkPlusToast.makeText(activity, "录制音频权限已被禁止，请在应用管理中打开权限或卸载重装");
                    break;
                case Manifest.permission.ACCESS_FINE_LOCATION:
                    WygkPlusToast.makeText(activity, "位置权限已被禁止，请在应用管理中打开权限或卸载重装");
                    break;

                default:
                    break;
            }
        } else {
            //用户没有勾选了不再询问,拒绝了权限申请
            switch (permission) {
                case Manifest.permission.CAMERA:
                    WygkPlusToast.makeText(activity, "没有相机权限");
                    break;
                case Manifest.permission.READ_EXTERNAL_STORAGE:
                case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                    WygkPlusToast.makeText(activity, "没有文件读取权限");
                    break;
                case Manifest.permission.RECORD_AUDIO:
                    WygkPlusToast.makeText(activity, "没有录制音频权限");
                    break;
                case Manifest.permission.ACCESS_FINE_LOCATION:
                    WygkPlusToast.makeText(activity, "没有位置权限");
                    break;

                default:
                    break;
            }
        }
    }
}
