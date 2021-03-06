package bd.com.bdwallet.util;

import android.widget.Toast;

import bd.com.bdwallet.app.BdApplication;

public class ToastUtil {

    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_ERROR = -1;
    private static final int TYPE_SUCCESS = 1;
    private static long currentToastTime;
    private static String currentMessage;

    public static void toastNormal(String message) {
        toast(message, Toast.LENGTH_SHORT, TYPE_NORMAL);
    }    

    public static void toastNormal(int message) {
        toast(message, Toast.LENGTH_SHORT, TYPE_NORMAL);
    }

    public static void toastError(String message) {
        toast(message, Toast.LENGTH_SHORT, TYPE_ERROR);
    }

    public static void toastError(int message) {
        toast(message, Toast.LENGTH_SHORT, TYPE_ERROR);
    }

    public static void toastSuccess(String message) {
        toast(message, Toast.LENGTH_LONG, TYPE_SUCCESS);
    }

    public static void toastSuccess(int message) {
        toast(message, Toast.LENGTH_LONG, TYPE_SUCCESS);
    }

    private static void toast(String message, int time, int type) {
        toastCustom(message, time, type);
    }

    private static void toast(int message, int time, int type) {
        toastCustom(BdApplication.getAppInstance().getString(message), time, type);
    }

    private static void toastCustom(String message, int time, int type) {
    }
}
