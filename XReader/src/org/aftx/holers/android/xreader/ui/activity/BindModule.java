package org.aftx.holers.android.xreader.ui.activity;

import org.aftx.holers.android.xreader.ui.activity.base.GetHandler;
import org.aftx.holers.android.xreader.ui.handler.UiHandler;
import org.aftx.holers.android.xreader.ui.interfaces.GetIBookAction;
import org.aftx.holers.android.xreader.ui.interfaces.GetICollectionAction;
import org.aftx.holers.android.xreader.ui.interfaces.IBookAction;
import org.aftx.holers.android.xreader.ui.interfaces.ICollectionAction;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class BindModule extends AbstractModule {
    @Provides
    @GetIBookAction
    @Inject
    IBookAction provideIBookList(Activity context) {
        return (IBookAction) context;
    }

    @Provides
    @GetICollectionAction
    @Inject
    ICollectionAction provideICollectionList(Context context) {
        return (ICollectionAction) context;
    }

    @Override
    protected void configure() {
        bind(Handler.class).annotatedWith(GetHandler.class).to(UiHandler.class)
                .in(Singleton.class);
    }
}
