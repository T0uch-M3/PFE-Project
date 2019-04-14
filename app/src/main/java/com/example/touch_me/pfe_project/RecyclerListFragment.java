

package com.example.touch_me.pfe_project;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.touch_me.pfe_project.helper.OnStartDragListener;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/*
public class RecyclerListFragment extends Fragment implements OnStartDragListener {

    private ItemTouchHelper mItemTouchHelper;
  RecyclerView recyclerView;
  RecyclerListAdapter adapter;

    public RecyclerListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return new RecyclerView(container.getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
      Log.wtf("tag", "ENTERE THE  VOID");
        super.onViewCreated(view, savedInstanceState);

//        RecyclerListAdapter adapter = new RecyclerListAdapter(getActivity(), this);

        recyclerView = (RecyclerView) view;
        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
//        mItemTouchHelper = new ItemTouchHelper(callback);
//        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
    public void addItem(ArrayList<String> list){
//      adapter = new RecyclerListAdapter(getActivity(), this, list);
      recyclerView.setAdapter(adapter);
//      ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
//      mItemTouchHelper = new ItemTouchHelper(callback);
    }
}
*/
