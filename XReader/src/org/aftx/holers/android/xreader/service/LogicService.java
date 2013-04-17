package org.aftx.holers.android.xreader.service;

import org.aftx.holers.android.xreader.intent.Action;
import org.aftx.holers.android.xreader.service.binder.DbBinder;
import org.aftx.holers.android.xreader.service.binder.GetDbBinder;
import org.aftx.holers.android.xreader.service.binder.GetLogBinder;
import org.aftx.holers.android.xreader.service.conn.SvrConn;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class LogicService extends Service implements Action {
    @Inject
    @GetDbBinder
    private DbBinder         dbBinder ;

    @Inject
    @GetLogBinder
    private IBinder          binder;
    
    public ServiceConnection conn     = null;

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Intent service = new Intent();
        service.setAction(DBSERVICE_ACTION);
        conn = new SvrConn();
        bindService(service, conn, BIND_AUTO_CREATE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (dbBinder != null) dbBinder = null;
        if (binder != null) binder = null;
    }
}