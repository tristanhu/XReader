package org.aftx.holers.android.xreader.ui.fragment;

import org.aftx.holers.android.xreader.ui.handler.MsgDefine;
import org.aftx.holers.android.xreader.ui.utils.BaseList;
import android.app.Fragment;

public class BaseListFragment extends Fragment implements MsgDefine {
    protected BaseList<?> list;

    public void setList(BaseList<?> list) {
        this.list = list;
    }
}