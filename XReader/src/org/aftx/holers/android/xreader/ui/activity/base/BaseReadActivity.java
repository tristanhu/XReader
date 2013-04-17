package org.aftx.holers.android.xreader.ui.activity.base;

import org.aftx.holers.android.xreader.service.binder.LogBinder;
import org.aftx.holers.android.xreader.service.conn.ISetBinder;
import org.aftx.holers.android.xreader.service.fake.LogLayer;
import org.aftx.holers.android.xreader.ui.utils.BaseList;
import org.aftx.holers.android.xreader.ui.utils.BookList;
import org.aftx.holers.android.xreader.ui.utils.CollectionList;
import org.aftx.holers.android.xreader.ui.utils.HistoryList;

import roboguice.activity.RoboFragmentActivity;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.view.ViewPager;

public class BaseReadActivity extends RoboFragmentActivity implements
        ActionBar.TabListener, ISetBinder {

    protected BaseList<?> bookList, collectionList, historyList;

    protected LogLayer    logic;
    protected LogBinder   binder;

    @Override
    public void setBinder(IBinder binder) {
        this.binder = (LogBinder) binder;
    }

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    protected ViewPager mViewPager;

    protected Fragment  currentFragment;

    protected ActionBar actionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        logic = new LogLayer(this);
        setBinder(logic.GetBind());
        bookList = new BookList();
        bookList.setBinder(binder);
        collectionList = new CollectionList();
        collectionList.setBinder(binder);
        historyList = new HistoryList();
        historyList.setBinder(binder);
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // if (tab.getText().equals(getString(R.string.title_mark))) {
        // Fragment fragment = new MarkFragment();
        // ft.add(fragment, (String) tab.getTag());
        // currentFragment = fragment;
        // }
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        // ft.remove(currentFragment);
    }

}