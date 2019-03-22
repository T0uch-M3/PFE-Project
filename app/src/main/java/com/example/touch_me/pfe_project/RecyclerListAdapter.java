/*
 * Copyright (C) 2015 Paul Burke
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.touch_me.pfe_project;

import android.content.Context;
import android.graphics.Color;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.touch_me.pfe_project.helper.ItemTouchHelperAdapter;
import com.example.touch_me.pfe_project.helper.ItemTouchHelperViewHolder;
import com.example.touch_me.pfe_project.helper.OnStartDragListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import androidx.core.view.MotionEventCompat;
import androidx.recyclerview.widget.RecyclerView;


public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.ItemViewHolder> implements ItemTouchHelperAdapter {

  List<WidgetObject> foodItemList;
  //+++++++++++++NEW+++++++++++++++++
  private final List<String> mItems = new ArrayList<>();
  private final OnStartDragListener mDragStartListener;

  public RecyclerListAdapter(List<WidgetObject> foodItemList, OnStartDragListener dragStartListener) {
//    this.foodItemList = foodItemList;
    mDragStartListener = dragStartListener;
    mItems.addAll(Arrays.asList("1","2","3","4"));
  }

  @Override
  public boolean onItemMove(int fromPosition, int toPosition) {
    Collections.swap(mItems, fromPosition, toPosition);
    notifyItemMoved(fromPosition, toPosition);
    return true;
  }

  @Override
  public void onItemDismiss(int position) {

  }

  @Override
  public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
    ItemViewHolder viewHolder = new ItemViewHolder(view);
    return viewHolder;
  }

  @Override
  public int getItemCount() {
    if (mItems == null)
      return 0;
    else
      return mItems.size();
  }

  @Override
  public void onBindViewHolder(final ItemViewHolder holder, int position) {
//    holder.textView.setText(mItems.get(position));
//    WidgetObject foodItemBean = foodItemList.get(position);

//    holder.imgFood.setImageResource(foodItemBean.getImgId());
//    holder.txtFood.setText(foodItemBean.getFoodItem());
    // Start a drag whenever the handle view it touched
//    holder.handleView.setOnTouchListener(new View.OnTouchListener() {
//      @Override
//      public boolean onTouch(View v, MotionEvent event) {
//        if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
//          mDragStartListener.onStartDrag(holder);
//        }
//        return false;
//      }
//    });
  }

  public static class ItemViewHolder extends RecyclerView.ViewHolder implements
    ItemTouchHelperViewHolder {


    ImageView imgFood;
    TextView txtFood;

    public ItemViewHolder(View itemView) {
      super(itemView);
      imgFood = (ImageView) itemView.findViewById(R.id.imgFood);
//      txtFood = (TextView) itemView.findViewById(R.id.txtFood);
    }

    @Override
    public void onItemSelected() {
      itemView.setBackgroundColor(Color.LTGRAY);
    }

    @Override
    public void onItemClear() {
      itemView.setBackgroundColor(0);
    }
  }

  //+++++++++++++++++++++++OLD---------------

//  public class ViewHolder extends RecyclerView.ViewHolder {
//
//    ImageView imgFood;
//    TextView txtFood;
//
//    public ViewHolder(View itemView) {
//      super(itemView);
//
//      imgFood = (ImageView) itemView.findViewById(R.id.imgFood);
//      txtFood = (TextView) itemView.findViewById(R.id.txtFood);
//
//    }
//
//  }
//
//  @Override
//  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
//    ViewHolder viewHolder = new ViewHolder(view);
//    return viewHolder;
//  }
//
//  @Override
//  public void onBindViewHolder(RecyclerListAdapter.ViewHolder holder, int position) {
//    WidgetObject foodItemBean = foodItemList.get(position);
//
//    holder.imgFood.setImageResource(foodItemBean.getImgId());
//    holder.txtFood.setText(foodItemBean.getFoodItem());
//  }
//
//  @Override
//  public int getItemCount() {
//    if (foodItemList == null)
//      return 0;
//    else
//      return foodItemList.size();
//
//
//  }
}
