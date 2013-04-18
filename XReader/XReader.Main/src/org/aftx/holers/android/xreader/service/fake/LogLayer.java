package org.aftx.holers.android.xreader.service.fake;

import org.aftx.holers.android.xreader.intent.Action;
import org.aftx.holers.android.xreader.service.binder.GetLogBinder;

import android.os.IBinder;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class LogLayer implements Action {
    @Inject
    private DbLayer          dbLayer;

    @Inject
    @GetLogBinder
    private IBinder          binder;

    public IBinder GetBind() {
        return binder;
    }

    public void Close() {
        if (dbLayer != null) dbLayer.Close();
        if (binder != null) binder = null;
    }
}