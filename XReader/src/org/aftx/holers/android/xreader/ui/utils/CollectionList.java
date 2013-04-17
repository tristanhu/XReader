package org.aftx.holers.android.xreader.ui.utils;

import org.aftx.holers.android.xreader.db.model.Collection;

public class CollectionList extends BaseList<Collection> {

    public void Update() {
        super.Update();
        entities = binder.GetAllCollections();
        int i = 0;
        for (Collection coll : entities) {
            map.put(i, coll);
            names.add(coll.getName());
            i++;
        }
    }
}