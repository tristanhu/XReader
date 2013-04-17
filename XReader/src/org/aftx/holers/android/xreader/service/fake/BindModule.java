package org.aftx.holers.android.xreader.service.fake;

import org.aftx.holers.android.xreader.service.binder.DbBinder;
import org.aftx.holers.android.xreader.service.binder.LogBinder;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.name.Named;

public class BindModule extends AbstractModule {
    @Provides
    @Named("GetDbBinder")
    @Inject
    DbBinder GetDbBinder(DbLayer dbLayer) {
        return (DbBinder) dbLayer.GetBind();
    }
    
    @Provides
    @Named("GetLogBinder")
    @Inject
    LogBinder GetLogBinder(LogLayer logLayer) {
        return (LogBinder) logLayer.GetBind();
    }

    @Override
    protected void configure() {
        // TODO Auto-generated method stub
    }
}
