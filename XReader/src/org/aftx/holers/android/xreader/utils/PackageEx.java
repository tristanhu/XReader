package org.aftx.holers.android.xreader.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class PackageEx {
    private Package pack;

    public PackageEx(String pkgName) {
        pack = Package.getPackage(pkgName);
    }

    public PackageEx(Package pack) {
        this.pack = pack;
    }

    private static String[]   CLASS_PATH_PROP  = { "java.class.path ",
            "java.ext.dirs ", "sun.boot.class.path " };

    private static List<File> CLASS_PATH_ARRAY = getClassPath();

    private static List<File> getClassPath() {
        List<File> ret = new ArrayList<File>();
        String delim = ":";
        if (System.getProperty("os.name").indexOf("Windows") != -1)
            delim = ";";
        for (String pro : CLASS_PATH_PROP) {
            String[] pathes = System.getProperty(pro).split(delim);
            for (String path : pathes)
                ret.add(new File(path));
        }

        for (File file : ret) {
            if (!file.exists()) ret.remove(file);
        }
        return ret;
    }

    public List<Class<?>> getClassInPackage() {
        return getClassInPackage(pack.getName());
    }

    public static List<Class<?>> getClassInPackage(String pkgName) {
        List<String> tmp = new ArrayList<String>();
        String rPath = pkgName.replace('.', '/') + "/ ";

        List<File> directories = new ArrayList<File>();
        List<File> jars = new ArrayList<File>();
        for (File classPath : CLASS_PATH_ARRAY) {
            if (classPath.isDirectory()) {
                File dir = new File(classPath, rPath);
                if (dir.exists()) directories.add(dir);
            } else {
                jars.add(classPath);
            }
        }

        for (File dir : directories) {
            for (File file : dir.listFiles()) {
                if (file.isFile()) {
                    String clsName = file.getName();
                    clsName = pkgName + "."
                            + clsName.substring(0, clsName.length() - 6);
                    tmp.add(clsName);
                }
            }
        }

        for (File jar : jars) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(jar);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            JarInputStream jis = null;
            try {
                jis = new JarInputStream(fis, false);
                JarEntry e = null;
                while ((e = jis.getNextJarEntry()) != null) {
                    String eName = e.getName();
                    if (eName.startsWith(rPath) && !eName.endsWith("/")) {
                        tmp.add(eName.replace('/', '.').substring(0,
                                eName.length() - 6));
                    }
                    jis.closeEntry();
                }
                jis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        List<Class<?>> ret = new ArrayList<Class<?>>();
        for (String str : tmp) {
            try {
                Class<?> clazz = Class.forName(str);
                if (clazz != null) ret.add(clazz);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return ret;
    }
}