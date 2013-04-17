package org.aftx.holers.android.xreader.ui.action;

import org.aftx.holers.android.xreader.ui.action.implement.Action;

import com.google.inject.ImplementedBy;

@ImplementedBy(Action.class)
public interface IAction extends IBookAction, ICollectionAction {

}
