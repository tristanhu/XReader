package org.aftx.holers.android.xreader.service.conn;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

public class SvrConn implements ServiceConnection {
    private ISetBinder service;

    public SvrConn(ISetBinder service) {
        this.service = service;
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        this.service.setBinder(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        service.setBinder(null);
    }

}
