package org.aftx.holers.android.xreader.ui.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class ErrorDialog extends AlertDialog.Builder {
    public ErrorDialog(Context context) {
        super(context);
        setTitle("消息提示");
        setMessage("你输入有误~！请重新输入~！");
        setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
    }
}