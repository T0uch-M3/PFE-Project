package com.example.touch_me.pfe_project;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.flexbox.FlexboxLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

public class CatViewHolder extends RecyclerView.ViewHolder {

  private ImageView mImageView;
  CatViewHolder(ViewGroup itemView) {

    super(itemView);
//    mImageView = (ImageView) itemView.findViewById(R.id.icon_view);
  }

  void bindTo(Drawable drawable) {
//    mImageView.setImageDrawable(drawable);
//    ViewGroup.LayoutParams lp = mImageView.getLayoutParams();
//    if (lp instanceof FlexboxLayoutManager.LayoutParams) {
//      FlexboxLayoutManager.LayoutParams flexboxLp = (FlexboxLayoutManager.LayoutParams)lp;
//      flexboxLp.setFlexGrow(1.0f);
//    }
  }
}
