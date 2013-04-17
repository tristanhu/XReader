package org.aftx.holers.android.xreader.ui.activity;

import org.aftx.holers.android.xreader.ui.activity.base.GetHandler;
import org.aftx.holers.android.xreader.ui.handler.UiHandler;

import android.os.Handler;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class BindModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Handler.class).annotatedWith(GetHandler.class).to(UiHandler.class)
                .in(Singleton.class);
    }
}
