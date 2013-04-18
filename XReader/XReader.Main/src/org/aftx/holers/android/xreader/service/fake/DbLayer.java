package org.aftx.holers.android.xreader.service.fake;

import org.aftx.holers.android.xreader.db.DbHelper;
import org.aftx.holers.android.xreader.service.binder.GetDbBinder;

import android.os.IBinder;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.j256.ormlite.android.apptools.OpenHelperManager;

@Singleton
public class DbLayer {
    @Inject
    @Named("ProvideDbHelper")
    private DbHelper helper;

    @Inject
    @GetDbBinder
    private IBinder  binder;

    public IBinder GetBind() {
        return binder;
    }

    public void Close() {
        if (helper != null) {
            OpenHelperManager.releaseHelper();
            helper = null;
        }
        if (binder != null) {
            binder = null;
        }
    }
}
