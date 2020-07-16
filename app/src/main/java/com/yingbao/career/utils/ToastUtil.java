package com.yingbao.career.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    private static Toast toast=null;
    public static void showShort(Context context, String text){
        if (toast==null){
            toast = Toast.makeText(context,text, Toast.LENGTH_SHORT);
        }else {
            toast.setText(text);
        }
        toast.show();
    }
}
