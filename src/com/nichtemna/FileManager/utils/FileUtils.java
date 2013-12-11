package com.nichtemna.FileManager.utils;

import android.content.Context;
import android.webkit.MimeTypeMap;
import com.nichtemna.FileManager.model.Folder;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Shishova Galina
 * nichtemna@gmail.com
 */
public class FileUtils {
    public static final String MIME_TYPE_IMAGE = "image/";
    public static final String MIME_TYPE_ALL = "*/*";
    public static final String PREFIX = ".";

    public static List<String> getFileList(String path) {
        List<String> files = new ArrayList<String>();
        for (File one : new File(path).listFiles()) {
            if (one.isFile()) {
                if (isFileImage(one.getName())) {
                    files.add(one.getAbsolutePath());
                }
            } else {
                files.addAll(getFileList(one.getAbsolutePath()));
            }
        }
        return files;
    }

    public static List<Folder> getFoldersWithFiles(String path) {
        List<Folder> folders = new ArrayList<Folder>();
        List<String> filesInDefault = new ArrayList<String>();
        for (File one : new File(path).listFiles()) {
            if (one.isDirectory() && !one.getName().startsWith(PREFIX)) {
                List<String> list = getFileList(one.getAbsolutePath());
                if (list.size() > 0) {
                    folders.add(new Folder(one.getAbsolutePath(), list));
                }
            } else if (one.isFile() && !one.getName().startsWith(PREFIX)) {
                filesInDefault.add(one.getAbsolutePath());
            }
        }

        if (filesInDefault.size() > 0) {
            folders.add(new Folder(path, filesInDefault));
        }
        return folders;
    }


    public static String getMimeType(String fileName) {
        String type = null;
        String extention = MimeTypeMap.getFileExtensionFromUrl(fileName);
        if (extention != null && extention.length() > 0) {
            MimeTypeMap map = MimeTypeMap.getSingleton();
            type = map.getMimeTypeFromExtension(extention);
        }
        if (type == null) type = MIME_TYPE_ALL;
        return type;
    }

    public static boolean isFileImage(String fileName) {
        return !fileName.startsWith(PREFIX) && getMimeType(fileName).contains(MIME_TYPE_IMAGE);
    }
}
