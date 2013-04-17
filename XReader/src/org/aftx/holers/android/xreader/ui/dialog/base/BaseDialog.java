package org.aftx.holers.android.xreader.ui.dialog.base;

import org.aftx.holers.android.xreader.ui.handler.MsgDefine;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;

public class BaseDialog extends AlertDialog.Builder implements 
        MsgDefine {
    protected AlertDialog dialog;

    protected Handler     handler;

    public BaseDialog(Context context, Handler handler) {
        super(context);
        this.handler = handler;
    }
}