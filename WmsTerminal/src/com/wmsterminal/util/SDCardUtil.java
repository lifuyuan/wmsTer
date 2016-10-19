package com.wmsterminal.util;

import java.io.BufferedInputStream;


import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.wmsterminal.base.Config;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;

/**
 * @author LiuSiQing
 * @Package com.mngwyhouhzmb.util
 * @Title SDCardUtil
 * @Description SD¿¨²Ù×÷Àà
 * @Time 2015Äê1ÔÂ5ÈÕÏÂÎç12:02:30
 */
public class SDCardUtil {

    private static final String TAG = SDCardUtil.class.getSimpleName();

    /**
     * @Title: checkSDCardIsExit
     * @Description: ÅÐ¶ÏSD¿¨ÊÇ·ñ´æÔÚ
     * @author: LiuSiQing
     * @date: 2015-4-8 ÏÂÎç1:35:01
     */
    public static boolean checkSDCardIsExit() {
        return Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    /**
     * TODO ÎÄ¼þÃû´¦Àí
     */
    /**
     * @Title: getFileNameData
     * @Description: »ñµÃÈÕÆÚµÄÎÄ¼þÃû
     * @author: LiuSiQing
     * @date: 2015-4-2 ÏÂÎç2:37:17
     */
    public static String getFileNameData() {
        return DateUtil.getSysDate() + DateUtil.getSysTime();
    }

    /**
     * @Title: getFileNameDataJPG
     * @Description: »ñµÃÈÕÆÚµÄÎÄ¼þÃû .jpg ºó×º
     * @author: LiuSiQing
     * @date: 2015-4-2 ÏÂÎç2:37:17
     */
    public static String getFileNameDataJPG() {
        return getFileNameData() + ".jpg";
    }

    /**
     * @Title: fileNameAddEndJPG
     * @Description: ÎªÎÄ¼þÃûÌí¼ÓJPGµÄºó×º
     * @author: LiuSiQing
     * @date: 2015-4-8 ÏÂÎç1:50:16
     */
    public static String fileNameAddEndJPG(String photoName) {
        if (null == photoName)
            return photoName;
        String name = photoName;
        if (!name.endsWith(".jpg"))
            name = name + ".jpg";
        return name;
    }

    /**
     * resolveFileNameForPath ´ÓÎÄ¼þÂ·¾¶½âÎö³öÎÄ¼þÃû
     */
    public static String resolveFileNameForPath(String filePath) {
        int index = 0;
        String fileName = "";
        if ((index = filePath.lastIndexOf("/")) != -1)
            fileName = filePath.substring(index + 1);
        return fileName.contains(".") ? fileName : "";
    }

    /**
     * TODO ÎÄ¼þµØÖ·´¦Àí
     */
    /**
     * @return Aug 1, 2014 4:16:36 PM
     * @throws IOException
     * @Title: getFilePath
     * @Description: µÃµ½ÎÄ¼þÂ·¾¶
     */
    public static String getFilePath() {
        if (checkSDCardIsExit())
            return Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
        return Environment.getRootDirectory().getAbsolutePath() + "/";
    }

//	/**
//	 * @Title: getFilePathOfAuName
//	 * @Description: au_nameµÄfilepath
//	 * @author: LiuSiQing
//	 * @date: 2015-6-29 ÏÂÎç2:21:06
//	 */
//	public static String getFilePathOfAuName(Context context) {
//		String fileName = SharedUtil.getUserinfo(context, "nickname");
//		return getFilePath() + fileName + "/";
//	}
//	
//	public static String getFilePathOfuserId(Context context) {
//		String fileName = SharedUtil.getUserinfo(context, "userid");
//		return getFilePath() + fileName + "/";
//	}

    /**
     * @Title: filePathToFrescoPathOfUri
     * @Description: filePath to frescoPath of Uri
     * @author: LiuSiQing
     * @date: 2015-6-30 ÏÂÎç3:10:54
     */
    // public static Uri filePathToFrescoPathOfUri(String filePath) {
    // if( ObjectUtil.isEmpty(filePath) )
    // return null;
    // return Uri.parse(filePathToFrescoPath(filePath));
    // }

    /**
     * @Title: filePathToFrescoPath
     * @Description: filePath to frescoPath
     * @author: LiuSiQing
     * @date: 2015-6-29 ÏÂÎç2:06:46
     */
    public static String filePathToFrescoPath(String filePath) {
        if (ObjectUtil.isEmpty(filePath))
            return filePath;
        if (filePath.startsWith("http"))
            return filePath;
        else if (filePath.startsWith("/uploaded") || filePath.startsWith("/cspplat") || filePath.startsWith("/image"))
            return Config.BASE_URL + filePath;
        else if (filePath.startsWith("/"))
            return Config.FILE + filePath;
        else
            return filePath;
    }

    /**
     * @Title: getFilePathOfPhoto
     * @Description: Æ´½ÓÍ¼Æ¬µÄÈ«Â·¾¶£¨°üÀ¨Ãû³ÆºÍºó×º£©
     * @author: LiuSiQing
     * @date: 2015-4-8 ÏÂÎç1:49:35
     */
    public static String getFilePathOfPhoto(String photoPath, String photoName) {
        return filePathAddEndSprit(photoPath) + fileNameAddEndJPG(photoName);
    }


    /**
     * @Title: getFilePathOfAuName
     * @Description: au_nameµÄfilepath
     * @author: LiuSiQing
     * @date: 2015-6-29 ÏÂÎç2:21:06
     */
    public static String getFilePathOfAuName(Context context) {
//		String fileName = SharedUtil.getUser(context, "au_name");
        String fileName = "com.datahub.activity/cache/img";
        return getFilePath() + fileName + "/";
    }

    /**
     * @Title: filePathAddEndSprit
     * @Description: ÎªÎÄ¼þÂ·¾¶Ìí¼ÓÐ±¸Ü
     * @author: LiuSiQing
     * @date: 2015-4-8 ÏÂÎç1:49:56
     */
    public static String filePathAddEndSprit(String filePath) {
        if (null != filePath && !filePath.endsWith("/"))
            return filePath + "/";
        return filePath;
    }

    /**
     * TODO ´ÓÊý¾Ý¿â»ñµÃÎÄ¼þµØÖ·
     */
    /**
     * @param activity
     * @return
     * @Title getFilePathFromDBFirst
     * @Description »ñµÃandroidÏµÍ³Êý¾Ý¿âÖÐµÚÒ»¸öÍ¼Æ¬µÄµØÖ·
     */
    public static String getFilePathFromDBFirst(Activity activity) {
        Cursor cursor = null;
        try {
            cursor = activity.getContentResolver().query(Media.EXTERNAL_CONTENT_URI,
                    null,
                    null,
                    null,
                    Media.DATE_MODIFIED + " desc");
            if (null == cursor)
                return null;
            if (cursor.moveToFirst())
                return cursor.getString(cursor.getColumnIndex(Media.DATA)); // _data£ºÎÄ¼þµÄ¾ø¶ÔÂ·¾¶ Media.DATA='_data'
        } catch (Exception e) {
            DebugUtil.e(TAG, e.toString());
        } finally {
            CloseUtil.close(cursor);
        }
        return null;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getImageAbsolutePath(Activity context, Uri imageUri) {
        if (context == null || imageUri == null)
            return null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT
                && DocumentsContract.isDocumentUri(context, imageUri)) {
            if (isExternalStorageDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(imageUri)) {
                String id = DocumentsContract.getDocumentId(imageUri);
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getFilePathFromDBByUri(context, contentUri, null, null);
            } else if (isMediaDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = new String[]{split[1]};
                return getFilePathFromDBByUri(context, contentUri, selection, selectionArgs);
            }
        } // MediaStore (and general)
        else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(imageUri))
                return imageUri.getLastPathSegment();
            return getFilePathFromDBByUri(context, imageUri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
            return imageUri.getPath();
        }
        return imageUri.getPath();
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    /**
     * @Title: getFilePathFromDBByUriPath
     * @Description: Í¨¹ýuriµØÖ·×ª»»ÎªfileµØÖ·
     * @author: LiuSiQing
     * @date: 2015-5-4 ÏÂÎç3:52:22
     */
    public static String getFilePathFromDBByUriPath(Context context, String path) {
        try {
            return getFilePathFromDBByUri(context, Uri.parse(path));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @return
     * @Title: getFilePathFromDBByUri
     * @Description: »ñµÃ¶ÔÓ¦URIµÄµØÖ·
     * @author: LiuSiQing
     * @date: 2015-6-25 ÏÂÎç3:27:45
     */
    public static String getFilePathFromDBByUri(Context context, Uri uri) {
        return getFilePathFromDBByUri(context, uri, null, null);
    }

    /**
     * @param activity
     * @param data
     * @return Sep 25, 2014 11:08:57 AM
     * @Title: getFilePathFromDBByUri
     * @Description: »ñµÃ¶ÔÓ¦URIµÄµØÖ·
     */
    public static String getFilePathFromDBByUri(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        try {
            String[] projection = new String[]{MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor.moveToFirst())
                return cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
        } catch (Exception e) {
            DebugUtil.e(TAG, e.toString());
        } finally {
            CloseUtil.close(cursor);
        }
        return null;
    }

    /**
     * TODO ´´½¨ÎÄ¼þ
     */
    /**
     * @Title: createFile
     * @Description: ´´½¨ÎÄ¼þ
     * @author: LiuSiQing
     * @date: 2015-4-8 ÏÂÎç1:23:57
     */
    public static boolean createFile(File file) {
        if (!file.exists())
            return file.mkdirs();
        return true;
    }

    /**
     * @Title: creatFileOfPath
     * @Description: ¸ù¾ÝµØÖ·´´½¨ÎÄ¼þ
     * @author: LiuSiQing
     * @date: 2015-4-8 ÏÂÎç1:24:12
     */
    public static boolean createFileOfPath(String filePath) {
        return createFile(new File(filePath));
    }

    /**
     * @Title: creatFileByInputStream
     * @Description: ´´½¨ÎÄ¼þ
     * @author: LiuSiQing
     * @date: 2015-4-8 ÏÂÎç1:37:07
     */
    public static void creatFileByInputStream(InputStream inputStream, String filePath) {
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        try {
            createFileOfPath(filePath);
            in = new BufferedInputStream(inputStream);
            out = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            byte array[] = new byte[2048];
            int num = 0;
            while ((num = in.read(array)) != -1)
                out.write(array, 0, num);
            out.flush();
        } catch (IOException e) {
            DebugUtil.e(TAG, e.toString());
        } finally {
            CloseUtil.close(out);
            CloseUtil.close(in);
        }
    }

    /**
     * @param file
     * @return Sep 23, 2014 10:30:56 PM
     * @Title: readFile
     * @Description: TODO ¶ÁÈ¡file
     */
    public static Object readFile(File file) {
        Object obj = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            obj = in.readObject();
            CloseUtil.close(in);
        } catch (Exception e) {
            DebugUtil.e(TAG, e.toString());
        }
        return obj;
    }

    /**
     * @Title: findFileFromSDCard
     * @Description: SD¿¨µÄpathÄ¿Â¼ÏÂÊÇ·ñ´æÔÚnameµÄÎÄ¼þ
     * @author: LiuSiQing
     * @date: 2015-4-8 ÏÂÎç2:22:08
     */
    public static boolean findFileFromSDCard(String path, String name) {
        if (!SDCardUtil.checkSDCardIsExit())
            return false;
        File file = new File(path);
        if (!file.exists())
            return false;
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++)
            if (StringUtil.equals(files[i].getName().split("\\.")[0], name))
                return true;
        return false;
    }

    /**
     * @param file
     * @param obj  Sep 23, 2014 10:31:07 PM
     * @Title: saveFile
     * @Description: TODO ±£´æfile
     */
    public static void saveFile(File file, Object obj) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(obj);
            out.flush();
            CloseUtil.close(out);
        } catch (Exception e) {
            DebugUtil.e(TAG, e.toString());
        }
    }

    /**
     * @return ·µ»ØÎÄ¼þÃû³Æ
     * @Title: saveCatchInfo2File
     * @Description: ±£´æ´íÎóÐÅÏ¢µ½ÎÄ¼þÖÐ
     * @author: LiuSiQing
     * @date: 2015-9-15 ÉÏÎç11:25:54
     */
    @SuppressLint("SimpleDateFormat")
    public static String saveCatchInfo2File(Throwable ex, String filePath) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String sb = writer.toString();
        FileOutputStream fos = null;
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            String time = formatter.format(new Date());
            String fileName = time + ".txt";
            System.out.println("fileName:" + fileName);
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                if (!createFileOfPath(filePath)) { // ´´½¨Ä¿Â¼Ê§°Ü: Ò»°ãÊÇÒòÎªSD¿¨±»°Î³öÁË
                    return "";
                }
                DebugUtil.e(TAG, "filePath + fileName:" + filePath + fileName);
                fos = new FileOutputStream(filePath + fileName);
                fos.write(sb.getBytes());
                fos.flush();
                // ÎÄ¼þ±£´æÍêÁËÖ®ºó,ÔÚÓ¦ÓÃÏÂ´ÎÆô¶¯µÄÊ±ºòÈ¥¼ì²é´íÎóÈÕÖ¾,·¢ÏÖÐÂµÄ´íÎóÈÕÖ¾,¾Í·¢ËÍ¸ø¿ª·¢Õß
            }
            return fileName;
        } catch (Exception e) {
            DebugUtil.e(TAG, "an error occured while writing file..." + e.getMessage());
        } finally {
            CloseUtil.close(fos);
        }
        return null;
    }

    /**
     * @Title: File2byte
     * @Description: File×ªbyte[]
     * @author: LiuSiQing
     * @date: 2015-4-22 ÏÂÎç1:58:54
     */
    public static byte[] File2byte(String path) {
        byte[] bytes = null;
        try {
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            bos.flush();
            CloseUtil.close(bos);
            CloseUtil.close(fis);
            bytes = bos.toByteArray();
            System.gc();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * TODO É¾³ýÎÄ¼þ
     */
    /**
     * Delete the image from SD card É¾³ýÂ·¾¶ÏÂËùÓÐÎÄ¼þ deleteAllFromPath
     *
     * @param context
     * @param path    file:///sdcard/temp.jpg
     */
    public static void deleteAllFromPath(String path) {
        File folder = new File(path);
        if (null != folder) {
            File[] files = folder.listFiles();
            for (File file : files)
                file.delete();
        }
    }

    /**
     * @param path
     * @param fileName Jul 21, 2014 10:41:20 AM
     * @Title: deleteFromPathByName
     * @Description: ¸ù¾ÝÂ·¾¶ºÍÎÄ¼þÃûÉ¾³ýÎÄ¼þ
     */
    public static boolean deleteFromPathByName(String path, String fileName) {
        File folder = new File(path);
        if (!folder.exists())
            return false;
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.getName().equals(fileName))
                return file.delete();
        }
        return true;
    }

    /**
     * @param path Jul 21, 2014 10:40:55 AM
     * @Title: deleteFromPath
     * @Description: É¾³ýÎÄ¼þ»òÎÄ¼þ¼Ð
     */
    public static void deleteFromPath(String path) {
        File file = new File(path);
        if (null != file && file.exists())
            file.delete();
    }

    /**
     * @param path Jul 21, 2014 10:40:55 AM
     * @Title: deleteFromPath
     * @Description: É¾³ýÎÄ¼þ»òÎÄ¼þ¼Ð
     */
    public static void deleteFromFile(File file) {
        if (null != file && file.exists())
            file.delete();
    }


}
