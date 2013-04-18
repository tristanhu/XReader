package org.aftx.holers.android.xreader.ui.action.implement;

import org.aftx.holers.android.xreader.db.model.Book;
import org.aftx.holers.android.xreader.db.model.Collection;
import org.aftx.holers.android.xreader.db.model.History;
import org.aftx.holers.android.xreader.service.binder.LogBinder;
import org.aftx.holers.android.xreader.ui.utils.BaseList;

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
}
