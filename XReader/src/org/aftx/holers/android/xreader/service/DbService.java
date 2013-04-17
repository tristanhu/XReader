package org.aftx.holers.android.xreader.service;

import org.aftx.holers.android.xreader.db.DbHelper;
import org.aftx.holers.android.xreader.service.binder.DbBinder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.j256.ormlite.android.apptools.OpenHelperManager;

public class DbService extends Service {
    private IBinder  binder = null;
    private DbHelper helper = null;

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        helper = OpenHelperManager.getHelper(this, DbHelper.class);
        binder = new DbBinder(helper);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /*
         * You'll need this in your class to release the helper when done.
         */
        if (helper != null) {
            OpenHelperManager.releaseHelper();
            helper = null;
        }
        if (binder != null) {
            binder = null;
        }
    }
}
