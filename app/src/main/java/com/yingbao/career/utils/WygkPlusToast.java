package com.yingbao.career.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class WygkPlusToast {

    private static Toast toast = null;

    public static void makeText(Context context, CharSequence text, int duration) {
        if (toast != null) {
            toast.cancel();
        }

        if (null != text && context != null) {
            toast = Toast.makeText(context.getApplicationContext(), text, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    public static void makeText(Context context, CharSequence text) {
        makeText(context, text, Toast.LENGTH_SHORT);
    }
}
