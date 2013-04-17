package org.aftx.holers.android.xreader.ui.action.implement;

import org.aftx.holers.android.xreader.db.model.Book;
import org.aftx.holers.android.xreader.db.model.Collection;
import org.aftx.holers.android.xreader.db.model.History;
import org.aftx.holers.android.xreader.service.binder.LogBinder;
import org.aftx.holers.android.xreader.ui.activity.MainActivity;
import org.aftx.holers.android.xreader.ui.utils.BaseList;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;

import com.google.inject.Inject;

public class BaseAction {
    @Inject
    protected LogBinder            binder;

    @Inject
    protected BaseList<Book>       bookList;
    @Inject
    protected BaseList<Collection> collectionList;
    @Inject
    protected BaseList<History>    historyList;

    protected Context              context;
    protected ActionBar            actionBar;

    @Inject
    public BaseAction(Activity context) {
        this.context = context;
        actionBar = ((MainActivity) context).getActionBar();
    }
}
