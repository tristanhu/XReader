package org.aftx.holers.android.xreader.ui.utils;

import org.aftx.holers.android.xreader.db.model.*;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public class BindModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(new TypeLiteral<BaseList<Book>>() {
        }).toInstance(new BookList());
        bind(new TypeLiteral<BaseList<Collection>>() {
        }).toInstance(new CollectionList());
        bind(new TypeLiteral<BaseList<History>>() {
        }).toInstance(new HistoryList());
    }
}
