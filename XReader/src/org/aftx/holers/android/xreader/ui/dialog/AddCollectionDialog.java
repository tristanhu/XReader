package org.aftx.holers.android.xreader.ui.dialog;

import org.aftx.holers.android.xreader.R;
import org.aftx.holers.android.xreader.ui.dialog.base.BaseDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class AddCollectionDialog extends BaseDialog {
    private EditText edt;

    public AddCollectionDialog(Context context,
            final Handler handler) {
        super(context, handler);

        setTitle(context.getString(R.string.title_add_collection));
        
        setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        
        LayoutInflater factory = LayoutInflater.from(context);
        // Define view
        final View textEntryView = factory.inflate(
                R.layout.add_collection_dialog, null);
        
        edt = (EditText) textEntryView.findViewById(R.id.AddCollection_Text);
        
        setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String name = edt.getText().toString().trim();

                final Message msg = new Message();
                msg.what = ADDCOLLECTION;
                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                msg.setData(bundle);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendMessage(msg);
                    }
                }).start();
                
                dialog.dismiss();
            }
        });

        dialog = create();

        dialog.setView(textEntryView);
        
        dialog.show();
    }
}