package org.aftx.holers.android.xreader.service.fake;

import org.aftx.holers.android.xreader.db.DbHelper;
import org.aftx.holers.android.xreader.service.binder.DbBinder;

import android.content.Context;
import android.os.IBinder;

import com.j256.ormlite.android.apptools.OpenHelperManager;

public class DbLayer {
    private IBinder  binder = null;
    private DbHelper helper = null;

    public DbLayer(Context context) {
        helper = OpenHelperManager.getHelper(context, DbHelper.class);
        binder = new DbBinder(helper);
    }

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
