package org.aftx.holers.android.xreader.ui.activity.base;

import org.aftx.holers.android.xreader.db.model.*;
import org.aftx.holers.android.xreader.ui.utils.BaseList;

import com.google.inject.Inject;

import roboguice.activity.RoboActivity;

public class BaseActivity extends RoboActivity{
    @Inject
    protected BaseList<Book> bookList;
    @Inject
    protected BaseList<Collection> collectionList;
    @Inject
    protected BaseList<History> historyList;

}
