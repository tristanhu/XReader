package org.aftx.holers.android.xreader.db.dao;

import java.util.*;

import com.j256.ormlite.dao.*;

public class Daos {
    private Map<Class<?>, Dao<?, ?>> map;

    public Daos() {
        map = new HashMap<Class<?>, Dao<?, ?>>();
    }

    public void Add(Dao<?, ?> dao) {
        map.put(dao.getDataClass(), dao);
    }

    public void Get(Class<?> clazz) {
        map.get(clazz);
    }
}