package org.aftx.holers.android.xreader.service.fake;

import org.aftx.holers.android.xreader.service.binder.DbBinder;
import org.aftx.holers.android.xreader.service.binder.LogBinder;

import android.content.Context;
import android.os.IBinder;

public class LogLayer {
    private DbBinder         dbBinder = null;
    private LogBinder        binder   = null;
    private DbLayer          db;

    public void setBinder(IBinder binder) {
        dbBinder = (DbBinder) binder;
    }

    public IBinder GetBind() {
        return binder;
    }

    public LogLayer(Context context) {
        db = new DbLayer(context);
        setBinder(db.GetBind());
        binder = new LogBinder(dbBinder);
        while (dbBinder == null) {
        }
    }

    public void Close() {
        if (db != null) db.Close();
        if (dbBinder != null) dbBinder = null;
        if (binder != null) binder = null;
    }
}