package org.yamu.tea_core.util.file;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.media.MediaScannerConnection;
import android.os.Build;
import android.os.Environment;
import android.webkit.MimeTypeMap;
import android.widget.TextView;

import org.yamu.tea_core.app.Tea;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.http.Body;

/**
 * Created by 马杰涛 on 2018/10/1
 */


public class Fileutil {


    // 格式化的模板
    public static final String TIME_FORMAT = "_yyyyMMdd_HHmmss";
    public static final String SDCARD_DIR =
            Environment.getExternalStorageDirectory().getPath();

    // 默认本地上传图片目录
    public static final String UPLOAD_PHOTO_DIR =
            Environment.getExternalStorageDirectory().getPath() + "/a_upload_photos/";

    // 网页缓存地址
    public static final String WEB_CACHE_DIR =
            Environment.getDownloadCacheDirectory().getPath() + "/app_web_cache/";

    // 系统相机目录
    public static final String CAMERA_PHOTO_DIR =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath();

    public static String getTimeFormatName(String timeFormatheader) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'" + timeFormatheader + "'" + TIME_FORMAT);
        return dateFormat.format(date);
    }


    /**
     * @param timeFormatHeader 格式化的头（除去时间部分）
     * @param extension        后缀名
     * @return 返回时间格式化后的文件名
     */
    public static String getFileNameByTime(String timeFormatHeader, String extension) {
        return getTimeFormatName(timeFormatHeader) + "." + extension;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static File createDir(String sdcardDirName) {
        // 拼接成SD卡中完整的dir
        String dir = SDCARD_DIR + "/" + sdcardDirName + "/";
        File fileDir = new File(dir);
        if (!fileDir.exists())
            fileDir.mkdirs();
        return fileDir;
    }

    public static File createFile(String sdcarDirname, String fileName) {
        return new File(createDir(sdcarDirname), fileName);
    }

    public static File createFileByTime(String sdcardDirName, String timeFormatHeader, String extension) {
        String fileName = getFileNameByTime(timeFormatHeader, extension);
        return createFile(sdcardDirName, fileName);
    }

    /**
     * 获取文件MIME
     *
     * @param filePath
     * @return
     */
    public static String getMimeType(String filePath) {
        String extension = getExtension(filePath);
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
    }

    /**
     * 获取文件的后缀名
     *
     * @param filePath
     * @return
     */
    public static String getExtension(String filePath) {
        String suffix = "";
        File file = new File(filePath);
        String name = file.getName();
        final int idx = name.lastIndexOf(".");
        if (idx > 0) {
            suffix = name.substring(idx + 1);
        }
        return suffix;
    }

    /**
     * 保存Bitmap到SD卡中
     *
     * @param mBitmap
     * @param dir      目录名，只需要写自己的相对目录名即可
     * @param compress 压缩比例，100是不压缩，值越小压缩率越高
     * @return 返回该文件
     */
    public static File saveBitmap(Bitmap mBitmap, String dir, int compress) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED))
            return null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        File fileName = createFileByTime(dir, "DOWN_LOAD", "jpg");
        try {
            fos = new FileOutputStream(fileName);
            bos = new BufferedOutputStream(fos);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, compress, bos);  // 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null)
                    bos.flush();
                if (bos != null)
                    bos.close();
                // 关闭流
                if (fos != null)
                    fos.flush();
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        refreshDICM();
        return fileName;
    }

    public static File writeToDisk(InputStream is, String dir, String name) {
        File file = Fileutil.createFile(dir, name);
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {
            bis = new BufferedInputStream(is);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            byte data[] = new byte[1024 * 4];

            int count;
            while ((count = bis.read(data)) != -1) {
                bos.write(data, 0, count);
            }
            bos.flush();
            fos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null)
                    bos.close();
                if (fos != null)
                    fos.close();
                if (bis != null)
                    bis.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static File writeToDisk(InputStream is, String dir, String prefix, String extension) {
        File file = Fileutil.createFileByTime(dir, prefix, extension);
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {
            bis = new BufferedInputStream(is);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            byte data[] = new byte[1024 * 4];

            int count;
            while ((count = bis.read(data)) != -1) {
                bos.write(data, 0, count);
            }
            bos.flush();
            fos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null)
                    bos.close();
                if (fos != null)
                    fos.close();
                if (bis != null)
                    bis.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 通知系统调用系统相册，使照片展现出来
     */
    private static void refreshDICM() {
//        if (Build.VERSION.SDK_INT >= 19) {
        MediaScannerConnection.scanFile(Tea.getAppliactionContext(),
                new String[]{Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getName()},
                null, null);
//        } else {
        // 扫描整个SD卡来更新系统图库，当文件很多的用户体验不佳，且不适合4.4以上版本
        //TODO   扫描整个SD卡来更新系统图库，当文件很多的用户体验不佳，且不适合4.4以上版本
//            Tea.getAppliactionContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,Environment.getExternalStorageDirectory()));
//        }
    }


    /**
     * 读取raw目录中的文件，并返回为字符串
     *
     * @param id
     * @return
     */
    public static String getRawFile(int id) {
        InputStream is = Tea.getAppliactionContext().getResources().openRawResource(id);
        BufferedInputStream bis = new BufferedInputStream(is);
        InputStreamReader isr = new InputStreamReader(bis);
        BufferedReader br = new BufferedReader(isr);
        StringBuilder stringBuilder = new StringBuilder();
        String str;
        try {
            while ((str = br.readLine()) != null) {
                stringBuilder.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                isr.close();
                bis.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    public static void setIconFont(String path, TextView textView) {
        Typeface typeface = Typeface.createFromAsset(Tea.getAppliactionContext().getAssets(), path);
        textView.setTypeface(typeface);
    }

    /**
     * 读取assets目录下的文件，并返回字符串
     *
     * @param name
     * @return
     */
    public static String getAssetsFile(String name) {
        InputStream is = null;
        BufferedInputStream bis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        StringBuilder stringBuilder = null;
        AssetManager assetManager = Tea.getAppliactionContext().getAssets();
        try {
            is = assetManager.open(name);
            bis = new BufferedInputStream(is);
            isr = new InputStreamReader(bis);
            br = new BufferedReader(isr);
            stringBuilder = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null) {
                stringBuilder.append(str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (isr != null)
                    isr.close();
                if (bis != null)
                    bis.close();
                if (is != null)
                    is.close();
                assetManager.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (stringBuilder != null)
            return stringBuilder.toString();
        else
            return null;
    }


}
