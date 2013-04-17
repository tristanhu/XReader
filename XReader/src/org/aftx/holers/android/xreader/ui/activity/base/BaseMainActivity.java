package org.aftx.holers.android.xreader.ui.activity.base;

import org.aftx.holers.android.xreader.service.binder.LogBinder;
import org.aftx.holers.android.xreader.service.fake.LogLayer;
import org.aftx.holers.android.xreader.ui.handler.MsgDefine;

import android.app.ActionBar;
import android.os.Bundle;
import android.os.Handler;

import com.google.inject.Inject;

public class BaseMainActivity extends BaseActivity implements
        ActionBar.OnNavigationListener, MsgDefine {

    /**
     * The serialization (saved instance state) Bundle key representing the
     * current dropdown position.
     */
    private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";

    protected ActionBar         actionBar;

    @Inject
    protected LogLayer          logic;

    @Inject
    protected LogBinder         binder;

    @Inject
    @GetHandler
    public Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Intent service = new Intent();
        // service.setAction(LOGSERVICE_ACTION);
        // conn = new SvrConn(this);
        // bindService(service, conn, BIND_AUTO_CREATE);
        // while (binder == null) {
        // }
        // logic = new LogLayer(this);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore the previously serialized current dropdown position.
        if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
            getActionBar().setSelectedNavigationItem(
                    savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Serialize the current dropdown position.
        outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar()
                .getSelectedNavigationIndex());
    }

    @Override
    public boolean onNavigationItemSelected(int position, long id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(UPDATEBOOKLIST);
            }
        }).start();
        return true;
    }
}
