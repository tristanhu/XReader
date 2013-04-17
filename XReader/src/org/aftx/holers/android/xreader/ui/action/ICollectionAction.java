package org.aftx.holers.android.xreader.ui.action;

import org.aftx.holers.android.xreader.ui.action.implement.CollectionAction;

import com.google.inject.ImplementedBy;

@ImplementedBy(CollectionAction.class)
public interface ICollectionAction {
    public void AddCollection(String name);

    public void DelCollection();

    public void UpdateCollectionList();
}
