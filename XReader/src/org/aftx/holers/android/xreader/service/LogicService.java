package org.aftx.holers.android.xreader.service;

import org.aftx.holers.android.xreader.intent.Action;
import org.aftx.holers.android.xreader.service.binder.DbBinder;
import org.aftx.holers.android.xreader.service.binder.LogBinder;
import org.aftx.holers.android.xreader.service.conn.ISetBinder;
import org.aftx.holers.android.xreader.service.conn.SvrConn;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

public class LogicService extends Service implements Action, ISetBinder {

    public ServiceConnection Conn     = null;
    private DbBinder         dbBinder = null;
    private LogBinder        binder   = null;

    public void setBinder(IBinder binder) {
        dbBinder = (DbBinder) binder;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Intent service = new Intent();
        service.setAction(DBSERVICE_ACTION);
        Conn = new SvrConn(this);
        bindService(service, Conn, BIND_AUTO_CREATE);
        binder = new LogBinder(dbBinder);
        while (dbBinder == null) {
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (dbBinder != null) dbBinder = null;
        if (binder != null) binder = null;
    }
}