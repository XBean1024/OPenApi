package com.binny.openapi.util;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.bean.logger.JJLogger;
import com.vise.log.ViseLog;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 类说明： 文件操作帮助类
 *
 * @author tianshuguang
 * @version 1.0
 * @date 2015-3-30
 */
public class FileUtils {
    private static final String TAG = FileUtils.class.getSimpleName();

    public static long getFileSizes(File f) throws Exception {// 取得文件大小
        long s = 0;
        if (f.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(f);
                if (null != fis) {
                    s = fis.available();
                    fis.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != fis) {
                    fis.close();
                }
            }


        } else {
            f.createNewFile();
            ViseLog.e("文件不存在");
        }
        return s;
    }

    public static String formatFileSize(long fileS) {// 转换文件大小
        DecimalFormat df = new DecimalFormat("0.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        if (fileS == 0) {
            return "0.00M";
        }
        return fileSizeString;
    }

    public static void fileDelete(String path) {
        try {
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static boolean fileIsExists(String path) {
        try {
            File f = new File(path);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {

            return false;
        }
        return true;
    }

    public static void deleteDirWithFile(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDirWithFile(file); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身
    }

    /**
     * * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache) * *
     *
     * @param context
     */
    private static void cleanInternalCache(Context context) {
        deleteFilesByDirectory(getDiskCacheDir(context), false);
    }

    private static File getDiskCacheDir(Context context) {
        File cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir();
        } else {
            cachePath = context.getCacheDir();
        }
        return cachePath;
    }

    /**
     * * 清除本应用所有数据库(/data/data/com.xxx.xxx/databases) * *
     *
     * @param context
     */
    private static void cleanDatabases(Context context) {
        deleteFilesByDirectory(getDBFile(context), false);
    }

    /**
     * 获取数据库文件
     *
     * @param context 上下文
     * @return 数据库文件
     */
    private static File getDBFile(Context context) {
        String path = "/data/data/"
                + context.getPackageName() + "/databases/flowup.db";
        ViseLog.i("path",path);
        return new File(path);
    }

    /**
     * * 清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs) *
     *
     * @param context 上下文
     */
    private static void cleanSharedPreference(Context context) {
        deleteFilesByDirectory(getSPFile(context), false);
    }

    private static File getSPFile(Context context) {

        String p  = "/data/data/"+context.getPackageName()+ "/shared_prefs";
        Log.i("[context]", "getSPFile = " + p);
        return new File(p);
    }

    /**
     * * 清除/data/data/com.xxx.xxx/files下的内容 * *
     *
     * @param context 上下文
     */
    private static void cleanFiles(Context context) {
        deleteFilesByDirectory(getDataFiles(context), false);
    }

    private static File getDataFiles(Context context) {
        return context.getFilesDir();
    }

    /**
     * * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache)
     *
     * @param context 上下文
     */
    private static void cleanExternalCache(Context context) {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            deleteFilesByDirectory(getDiskCacheDir(context), false);
        }
    }

    /**
     * /PostStore/image
     *
     * @param filePath      自定义缓存的根目录
     * @param deleteRootDir 是否删除根目录 PostStore
     */
    public static void cleanCustomCache(String filePath, boolean deleteRootDir) {
        deleteFilesByDirectory(new File(filePath), deleteRootDir);
    }

    /**
     * * 清除本应用所有的数据 * *
     *
     * @param context  上下文
     * @param filepath 自定文件路径
     */
    public static void cleanApplicationData(Context context, String... filepath) {
        cleanInternalCache(context);
        cleanExternalCache(context);
        cleanDatabases(context);
        cleanSharedPreference(context);
        cleanFiles(context);
        if (filepath == null) {
            return;
        }
        for (String filePath : filepath) {
            deleteFilesByDirectory(new File(filePath), true);
        }
    }

    /**
     * @param directory      要删除的文件 或目录
     * @param deleteRootfDir 是否删除根目录
     */
    private static void deleteFilesByDirectory(File directory, boolean deleteRootfDir) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                if (item.isFile()) {
                    item.delete();
                } else {
                    deleteFilesByDirectory(item, deleteRootfDir);
                }
            }
            if (deleteRootfDir) {
                directory.delete();
            }
        }
    }
    /**
     * 获取指定文件夹
     * @param f
     * @return
     * @throws Exception
     *
     */
    private static long getFolderSizes(File f) throws Exception {
        long size = 0;
        File flist[] = f.listFiles();
        if (flist == null) {
            return 0;
        }
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getFolderSizes(flist[i]);
            } else {
                size = size + getFolderSize(flist[i]);
            }
        }
        return size;
    }
    /**
     * 获取指定文件大小
     * @return
     * @throws Exception 　　
     */
    private static long getFolderSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        } else {
            file.createNewFile();
            Log.e("获取文件大小", "文件不存在!");
        }
        return size;
    }
    public static String getTotalCacheSize(Context context, String path) {
        try {
            long sp_size = getFolderSizes(getSPFile(context));
            Log.i("[context, path]", "sp_size = " + sp_size);
            long out_size = 0;
            if (!TextUtils.isEmpty(path)) {
                out_size = getFolderSizes(new File(path));
            }
            Log.i("[context, path]", "out_size = " + out_size);
            long db_size = getFolderSizes(getDBFile(context));
            Log.i("[context, path]", "db_size = " + db_size);
            long cache_size = getFolderSizes(getDiskCacheDir(context));// (/data/data/com.xxx.xxx/cache);
            Log.i("[context, path]", "cache_size = " + cache_size);
            long f_size = getFolderSizes(getDataFiles(context));
            Log.i("[context, path]", "f_size = " + f_size);
            long size = cache_size
                    + db_size
                    + sp_size
                    + f_size
                    + out_size;
            Log.i("[context, path]", "getTotalCacheSize = " + size);
            return formatFileSize(size);
        } catch (Exception e) {
            Log.i("[context, path]", "getTotalCacheSize = " + e.getMessage());
        }
        return "0.00M";
    }
}