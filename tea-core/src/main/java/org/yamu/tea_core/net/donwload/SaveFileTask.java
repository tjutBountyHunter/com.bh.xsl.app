package org.yamu.tea_core.net.donwload;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import org.yamu.tea_core.app.Tea;
import org.yamu.tea_core.net.callback.IRequest;
import org.yamu.tea_core.net.callback.ISuccess;
import org.yamu.tea_core.util.file.Fileutil;

import java.io.File;
import java.io.InputStream;

import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;


/**
 * Created by 马杰涛 on 2018/10/1
 */

public class SaveFileTask extends AsyncTask<Object, Void, File> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    public SaveFileTask(IRequest REQUEST, ISuccess SUCCESS) {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
    }

    @Override
    protected File doInBackground(Object... params) {
        String downloadDir = (String) params[0];
        String extension = (String) params[1];
        final ResponseBody body = (ResponseBody) params[2];
        final InputStream is = body.byteStream();
        final String name = (String) params[3];
        if ((downloadDir == null) || downloadDir.equals("")) {
            downloadDir = "down_loads";
        }
        if (extension == null || extension.equals("")) {
            extension = "";
        }
        if (name == null) {
            return Fileutil.writeToDisk(is, downloadDir, extension.toUpperCase(), extension);
        } else {
            return Fileutil.writeToDisk(is, downloadDir, name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESS != null)
            SUCCESS.onSuccess(file.getPath());
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        autoInstallApk(file);
    }

    private void autoInstallApk(File file) {
        if (Fileutil.getExtension(file.getPath()).equals("apk")) {
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Tea.getAppliactionContext().startActivity(install);
        }
    }
}
