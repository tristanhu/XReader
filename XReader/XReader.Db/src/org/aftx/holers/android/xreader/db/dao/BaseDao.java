package org.aftx.holers.android.xreader.db.dao;

import org.aftx.holers.android.xreader.db.DbHelper;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class BaseDao {
    @Inject
    @Named("ProvideDbHelper")
    protected DbHelper helper;
}
