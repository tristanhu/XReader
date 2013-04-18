package org.aftx.holers.android.xreader.ui.utils;

import java.util.ArrayList;
import java.util.List;

import org.aftx.holers.android.xreader.service.binder.LogBinder;

import android.util.SparseArray;

import com.google.inject.Inject;

public class BaseList<T> {

    @Inject
    protected LogBinder      binder;

    protected List<T>        entities;
    protected List<String>   names;
    protected SparseArray<T> map;

    public T GetEntity(int itemIndex) {
        return map.get(itemIndex);
    }

    public int GetItemIndex(T entity) {
        return map.indexOfValue(entity);
    }

    public void Update() {
        names = new ArrayList<String>();
        map = new SparseArray<T>();
    }

    public List<String> GetData() {
        return names;
    }
}
