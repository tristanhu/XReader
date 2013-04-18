package org.aftx.holers.android.xreader.db;

import android.content.Context;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.j256.ormlite.android.apptools.OpenHelperManager;

public class BindModule extends AbstractModule {
    @Provides
    @Named("ProvideDbHelper")
    @Inject
    DbHelper provideDbHelper(Context context) {
        return OpenHelperManager.getHelper(context, DbHelper.class);
    }

    @Override
    protected void configure() {
        // TODO Auto-generated method stub
    }
}
