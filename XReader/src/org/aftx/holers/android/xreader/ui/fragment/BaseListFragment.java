package org.aftx.holers.android.xreader.ui.fragment;

import org.aftx.holers.android.xreader.ui.activity.MainActivity;
import org.aftx.holers.android.xreader.ui.handler.MsgDefine;
import org.aftx.holers.android.xreader.ui.utils.BaseList;
import org.aftx.holers.android.xreader.ui.utils.BookList;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Message;

public class BaseListFragment extends Fragment implements MsgDefine {
    protected BaseList<?> list;

    public void setList(BaseList<?> list) {
        this.list = list;
    }

    public void sendDelBook(final int pos) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = DELBOOK;
                Bundle bundle = new Bundle();
                bundle.putInt("Id", ((BookList) list).GetEntity(pos).getId());
                msg.setData(bundle);
                ((MainActivity) getActivity()).handler.sendMessage(msg);
                ((MainActivity) getActivity()).UpdateBookList();
            }
        }).start();
    }
}