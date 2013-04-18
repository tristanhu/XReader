package org.aftx.holers.android.xreader.ui.dialog;

import org.aftx.holers.android.xreader.R;
import org.aftx.holers.android.xreader.ui.dialog.base.BaseDialog;

import android.content.Context;
import android.os.Handler;

public class AddBookDialog extends BaseDialog {

    public AddBookDialog(Context context, Handler handler, int collection) {
        super(context, handler);

        setTitle(R.string.title_add_book);
        FileSelectView view = new FileSelectView(this.getContext(), collection);
        view.setHandler(handler);
        setView(view);

        dialog = create();

        view.setDialog(dialog);

        dialog.show();
    }
}
