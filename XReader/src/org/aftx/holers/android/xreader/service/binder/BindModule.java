package org.aftx.holers.android.xreader.service.binder;

import android.os.IBinder;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class BindModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IBinder.class).annotatedWith(GetDbBinder.class).to(DbBinder.class)
                .in(Singleton.class);
        bind(IBinder.class).annotatedWith(GetLogBinder.class)
                .to(LogBinder.class).in(Singleton.class);
    }
}