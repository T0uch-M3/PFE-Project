package com.example.touch_me.pfe_project;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.ArcProgress;

import java.util.ArrayList;
import java.util.List;

import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

public class CatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  //  public int[] CAT_IMAGE_IDS = new int[]{
//    R.drawable.ic_menu_share,R.drawable.ic_menu_gallery,R.drawable.ic_menu_manage};
//  List<Integer> CAT_IMAGE_IDS = new ArrayList<>();
  List<WidgetItem> CAT_IMAGE_IDS = new ArrayList<>();
  private Context mContext;
  int i = 0;
  Boolean btn_exist = false;
  private static final int TYPE_FOOTER = 1;
  private static final int TYPE_ITEM = 2;

  CatAdapter(Context context) {
    mContext = context;
  }

  public void innitFooter() {

  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    Log.wtf("tag", "Number of calls::"+i);//for debugging the outofboundary beast
    if (viewType == TYPE_ITEM) {
      WidgetItem wi;
      wi = CAT_IMAGE_IDS.get(i);
      ViewGroup view = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_holder, parent, false);
      if(wi.getDummy()){
        LinearLayout ln = new LinearLayout(parent.getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(230, 250, Gravity.CENTER);
        ln.setLayoutParams(params);
        view.setVisibility(View.INVISIBLE);
        view.addView(ln);
        i++;
      }else {
        TextView tv = new TextView(parent.getContext());
        tv.setText(wi.getTilte());

        LinearLayout ln = new LinearLayout(parent.getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(230, 250, Gravity.CENTER);
        ln.setLayoutParams(params);
        ln.setOrientation(LinearLayout.VERTICAL);
        ln.addView(tv);
        ln.addView(wi.getWidget());
        view.addView(ln);
        Log.wtf("tag", "list count::" + CAT_IMAGE_IDS.get(0).getTilte());
        i++;
      }



//    wi.getWidget().setLayoutParams(new ArcProgress());
//    wi.getWidget().getLayoutParams().height = 100;
//    wi.getWidget().getLayoutParams().width = 100;

      return new ItemViewHolder(view);
    }

//if(!btn_exist){
   else if (viewType == TYPE_FOOTER) {
      //Inflating header view
//      View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_header, parent, false);

//  View btn_add = new Button(mContext);
  ViewGroup bg_btn = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.add_button, parent, false);

  btn_exist = true;
//  return new CatViewHolder((ViewGroup) bg_btn);
      return new FooterViewHolder(bg_btn);
}
//else{
//    WidgetItem wi = new WidgetItem();
//    wi = CAT_IMAGE_IDS.get(i);
//    ViewGroup view = (ViewGroup) LayoutInflater.from(parent.getContext())
//      .inflate(R.layout.widget_holder, parent, false);
//    TextView tv = new TextView(parent.getContext());
//    tv.setText(wi.getTilte());
//    LinearLayout ln = new LinearLayout(parent.getContext());
//    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(230, 250, Gravity.CENTER);
//    ln.setLayoutParams(params);
//    ln.setOrientation(LinearLayout.VERTICAL);
//    ln.addView(tv);
//    ln.addView(wi.getWidget());
//    view.addView(ln);
//    Log.wtf("tag", "list count::" + CAT_IMAGE_IDS.get(0).getTilte());
//    i++;
//    return new ItemViewHolder(view);
//
//}
    else return null;
  }
  @Override
  public int getItemViewType(int position) {
//    Log.wtf("tag","size:BEFORE:"+CAT_IMAGE_IDS.size());
    Log.wtf("tag","position::"+position);
    if (position == CAT_IMAGE_IDS.size()+1) {
      Log.wtf("tag","detected footer");
      return TYPE_FOOTER;
    }
    else{

      return TYPE_ITEM;

    }

  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//    int pos = position % CAT_IMAGE_IDS.size();
//    Drawable drawable = ResourcesCompat.getDrawable(mContext.getResources(),
//      CAT_IMAGE_IDS.get(pos), null);
//    holder.bindTo(drawable);
  }

  @Override
  public int getItemCount() {
    return CAT_IMAGE_IDS.size();
  }

  private class ItemViewHolder extends RecyclerView.ViewHolder {
    TextView itemText;

    public ItemViewHolder(ViewGroup itemView) {
      super(itemView);
      Log.wtf("tag","size yo::"+CAT_IMAGE_IDS.size());
//      itemText = (TextView) itemView.findViewById(R.id.recycler_item_text);
    }
  }
  private class FooterViewHolder extends RecyclerView.ViewHolder {
    TextView footerText;

    public FooterViewHolder(ViewGroup view) {
      super(view);
//      footerText = (TextView) view.findViewById(R.id.footer_text);
    }
  }

}
