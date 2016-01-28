package com.example.admin.popupwindowsample;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.text.format.DateFormat;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Date;

public class FileUtils {

    public static File createNewTempProfileFile(Context context, String type) {
        File dir = new File(context.getExternalCacheDir(), "profile");
        dir.mkdir();
        File f = new File(dir, "tmp_" + type + ".png");
        return f;
    }

    public static File createNewTempAttachmentFile(Context context) {
        CharSequence dateFormat = DateFormat.format("MMddyy_hhmmss", new Date().getTime());
        File dir = new File(context.getExternalCacheDir(), "attachments/images");
        dir.mkdir();
        File f = new File(dir, "Img_" + dateFormat + ".png");
        return f;
    }


    public static void deleteTempProfiles(Context context) {
        File f = new File(context.getExternalCacheDir(), "profile");
        f.mkdir();
        for (File image : f.listFiles()) {
            image.delete();
        }
    }

    public static void copyStream(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
    }


    public static File createNewTempAttachment(Context context, String fileType) {
        File attachmentDir = new File(context.getExternalCacheDir(), "attachments");
        attachmentDir.mkdir();
        File f = null;
        CharSequence dateFormat = DateFormat.format("MMddyy_hhmmss", new Date().getTime());
        if (fileType.equals("images")) {
            File imageDir = new File(attachmentDir + File.separator + "images" + File.separator);
            imageDir.mkdir();
            int size = imageDir.listFiles().length;
            f = new File(imageDir + File.separator, "Img_" + dateFormat.toString() + ".png");

        }
        return f;
    }


    public static void deleteParticularImageAttachment(String filePathToDelete) {
        File attachmentDir = new File(filePathToDelete);
        if(attachmentDir.isFile() && attachmentDir.exists())



            attachmentDir.delete();

    }

    public static String getLatestFileFromDir(String dirPath) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return null;
        }

        File lastModifiedFile = files[0];
        for (int i = 1; i < files.length; i++) {
            if (lastModifiedFile.lastModified() < files[i].lastModified()) {
                lastModifiedFile = files[i];
            }
        }
        return lastModifiedFile.getAbsolutePath();
    }

    public static Bitmap getVideoThumbnail(String path) {
        Bitmap thumb = ThumbnailUtils.createVideoThumbnail(path,
                MediaStore.Video.Thumbnails.MINI_KIND);
        return thumb;
    }

    public static String getFileSize(long size) {
        if (size <= 0)
            return "0";
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    public static void deleteTempAttachments(File pathToDelete) {
        if (pathToDelete.exists() && pathToDelete.isDirectory()) {
            File[] files = pathToDelete.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    for (File filesOfChild : file.listFiles()) {
                        filesOfChild.delete();
                    }
                }
            }
        } else if (pathToDelete.exists() && pathToDelete.isFile()) {
            pathToDelete.delete();
        }
    }

    public static int getFilesCount(File file) {
        int count = 0;
        if(file.exists()) {
            File[] files = file.listFiles();
            if(files.length > 0) {
                for (File f : files)
                    if (f.isDirectory())
                        count += getFilesCount(f);
                    else
                        count++;
            }
        }

        return count;
    }

}
