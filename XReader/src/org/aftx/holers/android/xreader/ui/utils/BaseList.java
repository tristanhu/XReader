package org.aftx.holers.android.xreader.ui.utils;

import java.util.ArrayList;
import java.util.List;

import org.aftx.holers.android.xreader.service.binder.LogBinder;
import org.aftx.holers.android.xreader.service.conn.ISetBinder;

import android.os.IBinder;
import android.util.SparseArray;

public class BaseList<T> implements ISetBinder {
    protected LogBinder binder;

    @Override
    public void setBinder(IBinder binder) {
        this.binder = (LogBinder) binder;
    }

    protected List<T>        entities;
    protected List<String>   names;
    protected SparseArray<T> map;

    public T GetEntity(int itemIndex) {
        return map.get(itemIndex);
    }

    public int GetItemIndex(T entity) {
        return map.indexOfValue(entity);
    }
    
    public void Update()
    {
        names = new ArrayList<String>();
        map = new SparseArray<T>();
    }

    public List<String> GetData() {
        return names;
    }
}
