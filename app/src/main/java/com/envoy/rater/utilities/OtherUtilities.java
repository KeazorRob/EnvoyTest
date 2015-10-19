package com.envoy.rater.utilities;

import android.view.Gravity;
import android.widget.EditText;
import android.widget.Toast;

import com.envoy.rater.controller.EnvoyApp;

public class OtherUtilities {

    public static float dpFromPx(final float px) {
        return px / EnvoyApp.getApp().getResources().getDisplayMetrics().density;
    }

    public static float pxFromDp(final float dp) {
        return dp * EnvoyApp.getApp().getResources().getDisplayMetrics().density;
    }

    public static void showMessage(String message) {
        Toast msg = Toast.makeText(EnvoyApp.getApp(), message, Toast.LENGTH_SHORT);
        msg.setGravity(Gravity.CENTER, 0, 0);
        msg.show();
    }

    public static String getText(EditText field) {
        return field.getText().toString().trim();
    }

    public static boolean isEmpty(EditText field, String msg) {
        boolean isEmpty = field.getText().toString().trim().equals("");
        if (isEmpty) {
            OtherUtilities.showMessage(msg);
        }
        return isEmpty;
    }
}
