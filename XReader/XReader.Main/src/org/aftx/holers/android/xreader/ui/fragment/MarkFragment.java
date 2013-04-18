package org.aftx.holers.android.xreader.ui.fragment;

import org.aftx.holers.android.xreader.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MarkFragment extends Fragment {
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.mark_fragment, container,
                false);

        // ListView view = (ListView) rootView.findViewById(R.id.section_label);
        // view.setAdapter(new ArrayAdapter<String>(view.getContext(),
        // android.R.layout.simple_list_item_1, android.R.id.text1,
        // bookList.GetData()));
        //
        // view.setOnItemClickListener(new OnItemClickListener() {
        // @Override
        // public void onItemClick(AdapterView<?> adapter, View view, int pos,
        // long id) {
        // Intent intent = new Intent(getActivity(), ReadActivity.class);
        // Book book = bookList.GetBook(pos);
        // intent.putExtra("Name", book.getName());
        // intent.putExtra("Path", book.getPath());
        // startActivity(intent);
        // }
        // });

        return rootView;
    }
}
