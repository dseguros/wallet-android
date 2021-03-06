package bd.com.appupdate.util;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;


import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadLargeFileListener;
import com.liulishuo.filedownloader.FileDownloader;

import java.io.File;


public class DownloadAppUtils {

    private static final String TAG = DownloadAppUtils.class.getSimpleName();
    public static long downloadUpdateApkId = -1;//Apk Id
    public static String downloadUpdateApkFilePath;//Apk    

    /**
     * APK
     *
     * @param context
     * @param url
     */
    public static void downloadForWebView(Context context, String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
  
    public static void download(final Context context, String url, final String serverVersionName, final DownLoadCallBack callBack) {

        String packageName = context.getPackageName();
        String filePath = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {//
            filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            Log.i(TAG, "SD");
            return;
        }

        String apkLocalPath = filePath + File.separator + packageName + "_" + serverVersionName + ".apk";

        downloadUpdateApkFilePath = apkLocalPath;

        FileDownloader.setup(context);
    
        FileDownloader.getImpl().create(url)
                .setPath(apkLocalPath)
                .setListener(new FileDownloadLargeFileListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, long soFarBytes, long totalBytes) {
                        if (callBack != null) {
                            callBack.pending(task, soFarBytes, totalBytes);
                        }
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, long soFarBytes, long totalBytes) {
                        if (callBack != null) {
                            callBack.progress(task, (int) (soFarBytes * 100.0 / totalBytes), totalBytes);
                        }
                        send(context, (int) (soFarBytes * 100.0 / totalBytes), serverVersionName);
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, long soFarBytes, long totalBytes) {
                        if (callBack != null) {
                            callBack.paused(task, soFarBytes, totalBytes);
                        }
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        if (callBack != null) {
                            callBack.completed(task);


                        }
                        send(context, 100, serverVersionName);
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        callBack.error(task, e);
                        Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {
                        callBack.warn(task);
                    }
                }).start();
    }
   
    private static void send(Context context, int progress, String serverVersionName) {
        Intent intent = new Intent("teprinciple.update");
        intent.putExtra("progress", progress);
        intent.putExtra("title", serverVersionName);
        context.sendBroadcast(intent);
    }

}
