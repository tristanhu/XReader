package org.aftx.holers.android.xreader.ui.activity.base;

import org.aftx.holers.android.xreader.db.model.Book;
import org.aftx.holers.android.xreader.db.model.Collection;
import org.aftx.holers.android.xreader.db.model.History;
import org.aftx.holers.android.xreader.service.binder.LogBinder;
import org.aftx.holers.android.xreader.service.fake.LogLayer;
import org.aftx.holers.android.xreader.ui.utils.BaseList;

import roboguice.activity.RoboFragmentActivity;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.google.inject.Inject;

public class BaseReadActivity extends RoboFragmentActivity implements
        ActionBar.TabListener {

    @Inject
    protected BaseList<Book> bookList;
    @Inject
    protected BaseList<Collection> collectionList;
    @Inject
    protected BaseList<History> historyList;

    @Inject
    protected LogLayer    logic;
    @Inject
    protected LogBinder   binder;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    protected ViewPager mViewPager;

    protected Fragment  currentFragment;

    protected ActionBar actionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
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