package com.example.prueba_mapas.helpers.dialog;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;

public class DialogHelper {

    private static ProgressDialog progressDialog;

    public static void dismissLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public static void error(Context context, String title, String message, String positiveButtonText, boolean isCancelable, DialogCallback callback) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setCancelable(isCancelable);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(positiveButtonText,
                (dialog, which) -> {
                    dialog.dismiss();
                    if (callback != null) {
                        callback.onPositive();
                    }
                });
        alertDialog.show();
    }

    public static void info(Context context, String title, String message, String positiveButtonText, boolean isCancelable, DialogCallback callback) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setCancelable(isCancelable);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(positiveButtonText,
                (dialog, which) -> {
                    dialog.dismiss();
                    if (callback != null) {
                        callback.onPositive();
                    }
                });
        alertDialog.show();
    }

    public static void question(Context context, String title, String message, String positiveButtonText, String negativeButtonText, DialogCallback callback) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setCancelable(false);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(positiveButtonText,
                (dialog, which) -> {
                    dialog.dismiss();
                    if (callback != null) {
                        callback.onPositive();
                    }
                });
        alertDialog.setNegativeButton(negativeButtonText,
                (dialog, which) -> {
                    dialog.dismiss();
                    if (callback != null) {
                        callback.onNegative();
                    }
                });
        alertDialog.show();
    }

    public static void showLoading(Context context, String message, boolean isCancelable) {
        dismissLoading();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(isCancelable);
        progressDialog.show();
    }

    public static void warning(Context context, String title, String message, String positiveButtonText, boolean isCancelable, DialogCallback callback) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setCancelable(isCancelable);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(positiveButtonText,
                (dialog, which) -> {
                    dialog.dismiss();
                    if (callback != null) {
                        callback.onPositive();
                    }
                });
        alertDialog.show();
    }
}
