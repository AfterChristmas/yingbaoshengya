package com.yingbao.career.utils;

/**
 * Created by FH0002571 on 2018/7/3.
 */

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
import java.util.List;

/**
 * Created by Fungo_Xiaoke on 2017/5/4 14:18.
 * eamil:luoxiaoke@yuntutv.net
 * 权限工具类
 */
public class PermissionUtils {


    /**
     * @param activity    activity
     * @param permissions 权限数组
     * @param requestCode 申请码
     * @return true 有权限  false 无权限
     */
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

    /**
     * @param mFragment   fragment
     * @param permissions 权限数组
     * @param requestCode 申请码
     * @return true 有权限  false 无权限
     */
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

    /**
     * @param context     上下文
     * @param permissions 权限数组
     * @return 还需要申请的权限
     */
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


    /**
     * 检查申请的权限是否全部允许
     */
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

    /**
     * 没有获取到权限的提示
     *
     * @param permissions 权限名字数组
     */
    public static void showPermissionsToast(Activity activity, @NonNull String[] permissions) {
        if (permissions.length > 0) {
            for (String permission : permissions) {
                showPermissionToast(activity, permission);
            }
        }
    }

    /**
     * 没有获取到权限的提示
     *
     * @param permission 权限名字
     */
    private static void showPermissionToast(Activity activity, @NonNull String permission) {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
            //用户勾选了不再询问,提示用户手动打开权限
            switch (permission) {
                case Manifest.permission.CAMERA:
                    ToastUtil.showShort(activity, "相机权限已被禁止，请在应用管理中打开权限或卸载重装");
                    break;
                case Manifest.permission.READ_EXTERNAL_STORAGE:
                case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                    ToastUtil.showShort(activity,  "文件权限已被禁止，请在应用管理中打开权限或卸载重装");
                    break;
                case Manifest.permission.RECORD_AUDIO:
                    ToastUtil.showShort(activity,  "录制音频权限已被禁止，请在应用管理中打开权限或卸载重装");
                    break;
                case Manifest.permission.ACCESS_FINE_LOCATION:
                    ToastUtil.showShort(activity,  "位置权限已被禁止，请在应用管理中打开权限或卸载重装");
                    break;

                default:
                    break;
            }
        } else {
            //用户没有勾选了不再询问,拒绝了权限申请
            switch (permission) {
                case Manifest.permission.CAMERA:
                    ToastUtil.showShort(activity,  "没有相机权限");
                    break;
                case Manifest.permission.READ_EXTERNAL_STORAGE:
                case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                    ToastUtil.showShort(activity,  "没有文件读取权限");
                    break;
                case Manifest.permission.RECORD_AUDIO:
                    ToastUtil.showShort(activity,  "没有录制音频权限");
                    break;
                case Manifest.permission.ACCESS_FINE_LOCATION:
                    ToastUtil.showShort(activity,  "没有位置权限");
                    break;

                default:
                    break;
            }
        }
    }
    /**
     * 第一次检查权限，用在打开应用的时候请求应用需要的所有权限
     * @param context
     * @param requestCode   请求码
     * @param permission    权限数组
     * @return
     */
    public static boolean checkPermissionFirst(Context context , int requestCode, String[] permission){
        List<String> permissions = new ArrayList<String>();
        for (String per : permission) {
            int permissionCode = ActivityCompat.checkSelfPermission(context, per );
            if( permissionCode != PackageManager.PERMISSION_GRANTED ) {
                permissions.add(per);
            }
        }
        if( !permissions.isEmpty() ) {
            ActivityCompat.requestPermissions((Activity) context,permissions.toArray( new String[permissions.size()] ), requestCode);
            return  false;
        }else{
            return  true;
        }
    }
}
