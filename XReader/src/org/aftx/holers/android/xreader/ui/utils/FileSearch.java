package org.aftx.holers.android.xreader.ui.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSearch {

    public static List<String> searchFile(String path, String name) {
        return searchFile(new File(path), name);
    }

    private static List<String> searchFile(File path, String name) {
        List<String> result = new ArrayList<String>();

        File[] filelist = path.listFiles();

        if (filelist != null) {
            for (File file : filelist) {
                if (file.isDirectory()) {
                    result.addAll(searchFile(file, name));
                } else {
                    if (file.getName().contains(name))
                        result.add(file.getAbsolutePath());
                }
            }
        }

        for (String str : result) {
            if (str.indexOf(".txt") == -1) result.remove(str);
        }

        return result;
    }
}
