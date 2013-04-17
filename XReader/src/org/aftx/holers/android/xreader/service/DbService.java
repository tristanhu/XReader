package org.aftx.holers.android.xreader.service;

import org.aftx.holers.android.xreader.db.DbHelper;
import org.aftx.holers.android.xreader.service.binder.GetDbBinder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.j256.ormlite.android.apptools.OpenHelperManager;

@Singleton
public class DbService extends Service {
    @Inject
    @Named("ProvideDbHelper")
    private DbHelper helper ;
    
    @Inject
    @GetDbBinder
    private IBinder  binder ;

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
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
