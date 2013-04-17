package org.aftx.holers.android.xreader.ui.dialog;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.aftx.holers.android.xreader.R;
import org.aftx.holers.android.xreader.ui.handler.MsgDefine;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class FileSelectView extends ListView implements OnItemClickListener,
        MsgDefine {
    private static String               sRoot;
    private static String               sParent;
    private static String               sFolder;
    private static String               sEmpty;
    private static Map<String, Integer> images;

    static {
        sRoot = "/";
        sParent = "..";
        sFolder = ".";
        sEmpty = "";
        images = new HashMap<String, Integer>();
        // 下面几句设置各文件类型的图标， 需要你先把图标添加到资源文件夹
        images.put(sRoot, R.drawable.file_dialog_root); // 根目录图标
        images.put(sParent, R.drawable.file_dialog_folder_up); // 返回上一层的图标
        images.put(sFolder, R.drawable.file_dialog_folder); // 文件夹图标
        images.put("wav", R.drawable.file_dialog_wavfile); // wav文件图标
        images.put(sEmpty, R.drawable.file_dialog_file);
    }

    private static String getSuffix(String filename) {
        int dix = filename.lastIndexOf('.');
        if (dix < 0) {
            return "";
        } else {
            return filename.substring(dix + 1);
        }
    }

    private static int getImageId(String s) {
        if (images == null) {
            return 0;
        } else if (images.containsKey(s)) {
            return images.get(s);
        } else if (images.containsKey(sEmpty)) {
            return images.get(sEmpty);
        } else {
            return 0;
        }
    }

    private String      currentPath;

    private AlertDialog dialog;

    public void setDialog(AlertDialog dialog) {
        this.dialog = dialog;
    }

    public FileSelectView(Context context) {
        super(context);

        this.currentPath = sRoot;

        this.setOnItemClickListener(this);
        refreshFileList(currentPath);
    }

    private static List<Map<String, Object>> list = null;

    private static String                    suffix;
    static {
        suffix = ".txt;";
    }

    private static List<Map<String, Object>> GetFileList(String path) {
        // 刷新文件列表
        File[] files;
        files = new File(path).listFiles();

        list = new ArrayList<Map<String, Object>>(files.length);

        // 用来先保存文件夹和文件夹的两个列表
        ArrayList<Map<String, Object>> folderList;
        ArrayList<Map<String, Object>> fileList;
        folderList = new ArrayList<Map<String, Object>>();
        fileList = new ArrayList<Map<String, Object>>();

        if (!path.equals(sRoot)) {
            // 添加根目录 和 上一层目录
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", sRoot);
            map.put("path", sRoot);
            map.put("img", getImageId(sRoot));
            list.add(map);

            map = new HashMap<String, Object>();
            map.put("name", sParent);
            map.put("path", path);
            map.put("img", getImageId(sParent));
            list.add(map);
        }

        for (File file : files) {
            if (file.isDirectory() && file.listFiles() != null) {
                // 添加文件夹
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("name", file.getName());
                map.put("path", file.getPath());
                map.put("img", getImageId(sFolder));
                folderList.add(map);
            } else if (file.isFile()) {
                
                Locale l = Locale.getDefault();
                // 添加文件
                String sf = getSuffix(file.getName()).toLowerCase(l);

                if (sf.length() > 0 && suffix.indexOf("." + sf + ";") >= 0) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("name", file.getName());
                    map.put("path", file.getPath());
                    map.put("img", getImageId(sf));
                    fileList.add(map);
                }
            }
        }

        // 先添加文件夹，确保文件夹显示在上面
        list.addAll(folderList);
        // 再添加文件
        list.addAll(fileList);

        return list;
    }

    private int refreshFileList(String path) {
        List<Map<String, Object>> list = GetFileList(path);
        SimpleAdapter adapter = new SimpleAdapter(getContext(), list,
                R.layout.file_dialog_item,
                new String[] { "img", "name", "path" }, new int[] {
                        R.id.filedialogitem_img, R.id.filedialogitem_name,
                        R.id.filedialogitem_path });
        this.setAdapter(adapter);

        return list.size();
    }

    private Handler handler;

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        // 条目选择
        String pt = (String) list.get(position).get("path");
        String fn = (String) list.get(position).get("name");
        if (fn.equals(sRoot) || fn.equals(sParent)) {
            // 如果是更目录或者上一层
            File fl = new File(pt);
            String ppt = fl.getParent();
            if (ppt != null) {
                // 返回上一层
                currentPath = ppt;
            } else {
                // 返回更目录
                currentPath = sRoot;
            }
        } else {
            File fl = new File(pt);
            if (fl.isFile()) {
                // 如果是文件

                // 设置回调的返回值
                final Message msg = new Message();
                msg.what = ADDBOOK;
                Bundle bundle = new Bundle();
                bundle.putString("name", fn);
                bundle.putString("path", pt);
                msg.setData(bundle);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendMessage(msg);
                    }
                }).start();

                dialog.dismiss(); // 让文件夹对话框消失

                return;
            } else if (fl.isDirectory()) {
                // 如果是文件夹
                // 那么进入选中的文件夹
                currentPath = pt;
            }
        }
        refreshFileList(currentPath);
    }
}
