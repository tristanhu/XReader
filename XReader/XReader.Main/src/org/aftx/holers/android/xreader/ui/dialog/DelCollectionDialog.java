package org.aftx.holers.android.xreader.ui.dialog;

import org.aftx.holers.android.xreader.ui.dialog.base.BaseDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class DelCollectionDialog extends BaseDialog {
    public DelCollectionDialog(Context context, final Handler handler,
            final int id) {
        super(context, handler);
        setTitle("确定删除当前目录？");
        setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Bundle data = new Bundle();
                        data.putInt("Id", id);
                        Message msg = new Message();
                        msg.what = DELCOLLECTION;
                        msg.setData(data);
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });

        dialog = this.create();
        dialog.show();
    }
}