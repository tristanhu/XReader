package org.aftx.holers.android.xreader.ui.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class ErrorDialog extends AlertDialog.Builder {
    public ErrorDialog(Context context) {
        super(context);
        setTitle("��Ϣ��ʾ");
        setMessage("����������~������������~��");
        setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
    }
}