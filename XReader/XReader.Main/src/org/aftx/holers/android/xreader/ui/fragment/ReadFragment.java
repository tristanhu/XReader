package org.aftx.holers.android.xreader.ui.fragment;

import org.aftx.holers.android.xreader.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ReadFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.read_fragment,
                container, false);
        TextView dummyTextView = (TextView) rootView
                .findViewById(R.id.BookContent);
        dummyTextView.setText(getArguments().getString("Content"));
        return rootView;
    }
}