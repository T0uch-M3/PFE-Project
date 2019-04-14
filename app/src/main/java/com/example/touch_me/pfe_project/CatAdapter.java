package com.example.touch_me.pfe_project;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.touch_me.pfe_project.helper.ClickListener;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.github.lzyzsd.circleprogress.ArcProgress;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;


public class CatAdapter extends RecyclerView.Adapter<CatAdapter.ItemViewHolder> implements View.OnClickListener {
  //  public int[] CAT_IMAGE_IDS = new int[]{
//    R.drawable.ic_menu_share,R.drawable.ic_menu_gallery,R.drawable.ic_menu_manage};
//  List<Integer> CAT_IMAGE_IDS = new ArrayList<>();
  List<WidgetItem> CAT_IMAGE_IDS;
  private Context mContext;
  View.OnClickListener addButtonListener;
  int i = 0, j = 0;
  int dummyNbr = 0;
  Boolean btn_exist = false;
  Boolean dammyFirst = true;
  Boolean runOnlyOnce = true;
  private static final int TYPE_HEADER = 0;
  private static final int TYPE_FOOTER = 1;
  private static final int TYPE_ITEM = 2;
  public ViewGroup bg_btn;
  public View Holder;
  boolean clickState = false;
  private MyCustomObjectListener listener;
  boolean fuckingCheat = true;
  boolean fuckingCheat2 = true;
  RecyclerViewOnClickListener mRecyclerInterface;

  //  CatAdapter(Context context) {
//    mContext = context;
//
//  }
  CatAdapter(List<WidgetItem> itemList) {
    this.CAT_IMAGE_IDS = itemList;
//    updateReceiptsList(itemList);
    this.listener = null;


  }

  //  @Override
//  public int getItemViewType(int position) {
//    return position;
//  }
  @Override
  public int getItemCount() {
//      return CAT_IMAGE_IDS.size();
    return CAT_IMAGE_IDS.size();
  }

//  public interface MyCustomObjectListener {
//    public void onObjectReady(List<WidgetItem> list);
//
//  }

  public void setCustomObjectListener(MyCustomObjectListener listener) {
    this.listener = listener;
  }


  public void innitFooter() {

  }

  public void updateReceiptsList(List<WidgetItem> list2) {
    CAT_IMAGE_IDS.clear();
    CAT_IMAGE_IDS.addAll(list2);
    this.notifyDataSetChanged();
  }

  @Override
  public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    Log.wtf("tag", "jjjjjjjjjjjjj IISSS::" + j);

    Log.wtf("tag", "IIIIIII  IIISSSS :::" + i);
    if ((i == 2) && (fuckingCheat)) {
      Log.wtf("tag", "PLAY WITH CHEATS never see again");
      i = 0;
      fuckingCheat = false;
    }
    if (j == 1) {
      i = CAT_IMAGE_IDS.size() - 3;
      j++;
    }


//     bg_btn = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.add_button, parent, false);
//    if (runOnlyOnce) {
    Log.wtf("tag", "DO***YOU***HAVE***A***NAME??::" + i + "::" + CAT_IMAGE_IDS.get(i).getTilte());
    Log.wtf("tag", "CURRREENT SIZEEE??::" + i + "::" + CAT_IMAGE_IDS.size());
    if (i == 1) {
      Log.wtf("tag", ">>>>>>>TAKE a GLANCE>>>>>>>>>>");
      Log.wtf("tag", "0 NAME??::" + i + "::" + CAT_IMAGE_IDS.get(0).getTilte());
      Log.wtf("tag", "1 NAME??::" + i + "::" + CAT_IMAGE_IDS.get(1).getTilte());

    }


    ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_holder, parent, false);
    if (CAT_IMAGE_IDS.get(i).getButton()) {
      Log.wtf("tag", "IT WAS A BUTTON");
      LinearLayout ln = new LinearLayout(parent.getContext());
      LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(230, 250, Gravity.CENTER);
      ln.setLayoutParams(params);


      ln.addView(LayoutInflater.from(parent.getContext()).inflate(R.layout.add_button, parent, false));

      ln.setGravity(Gravity.CENTER);
      viewGroup.addView(ln);

      i++;
      return new ItemViewHolder(viewGroup);
    } else if (CAT_IMAGE_IDS.get(i).getDummy()) {
      Log.wtf("tag", "IT WAS A DUMMY");
      LinearLayout ln = new LinearLayout(parent.getContext());
      LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(230, 250, Gravity.CENTER);
      ln.setLayoutParams(params);
      ln.setBackgroundColor(Color.CYAN);
//              viewGroup.setVisibility(View.INVISIBLE);
      viewGroup.addView(ln);
      i++;
      return new ItemViewHolder(viewGroup);
    } else {
      /***********************ITEM BELLOW *************************/
      Log.wtf("tag", "IT WAS AN ITEM");
      WidgetItem wi = CAT_IMAGE_IDS.get(i);
      Log.wtf("tag", "ITEM TITLE::" + wi.getTilte());
      TextView tv = new TextView(parent.getContext());
      tv.setText(wi.getTilte());
      tv.setBackgroundColor(Color.YELLOW);
      LinearLayout.LayoutParams paramsTV = new LinearLayout.LayoutParams(199, ViewGroup.LayoutParams.WRAP_CONTENT);
      paramsTV.gravity = Gravity.START;
//      paramsTV.rightMargin = 8;
      tv.setLayoutParams(paramsTV);

//      Button btnOption = new Button(parent.getContext());
//      btnOption.setText("x");
//      btnOption.setTag("TAG"+wi.getTilte());
//      btnOption.setGravity(Gravity.TOP | Gravity.RIGHT);
//      btnOption.setLayoutParams(new LinearLayout.LayoutParams(30, 30));
//      Drawable roundDrawable = btnOption.getContext().getResources().getDrawable(R.drawable.round_button);
//      btnOption.setBackground(roundDrawable);

/*******************TOP LAYOUT FOR  TITLE  AND ROUND BUTTON************/
      LinearLayout lnH = new LinearLayout(parent.getContext());
      LinearLayout lnV = null;
      LinearLayout.LayoutParams paramsH = new LinearLayout.LayoutParams(230, 250);
//      paramsH.bottomMargin = -50;
//      paramsH.topMargin = -50;
      lnH.setLayoutParams(paramsH);
      lnH.setOrientation(LinearLayout.HORIZONTAL);
      lnH.setBackgroundColor(Color.GRAY);
//      lnH.addView(tv);
//      lnH.addView(LayoutInflater.from(parent.getContext()).inflate(R.layout.circular_button, parent, false));
/************************THE  MIDDLE  FLEET*****************/
      if (wi.getWidgetType() == "ArcProgress") {
        lnV = new LinearLayout(parent.getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(150, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        lnV.setBackgroundColor(lnV.getContext().getResources().getColor(R.color.white_ish));
//        params.b = -150;
        params.rightMargin = -30;
        lnV.setLayoutParams(params);
        lnV.setOrientation(LinearLayout.VERTICAL);
        lnV.addView(tv);
        lnV.addView(wi.getWidget());

      }
      if (wi.getWidgetType() == "Thermometer") {
        TextView tvTemperature = new TextView(parent.getContext());
        tvTemperature.setText("HOT AF!");
        lnV = new LinearLayout(parent.getContext());
        LinearLayout lnHV = new LinearLayout(parent.getContext());
        LinearLayout.LayoutParams paramsHV = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        lnHV.setLayoutParams(paramsHV);
        lnHV.setOrientation(LinearLayout.HORIZONTAL);

        wi.getWidget().setLayoutParams(new LinearLayout.LayoutParams(100, ViewGroup.LayoutParams.MATCH_PARENT));

        LinearLayout.LayoutParams paramsTVTemp = new LinearLayout.LayoutParams(200, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsTVTemp.gravity = Gravity.RIGHT | Gravity.CENTER;
        tvTemperature.setLayoutParams(paramsTVTemp);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(150, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        lnV.setBackgroundColor(lnV.getContext().getResources().getColor(R.color.red));
        params.rightMargin = -30;
        lnV.setLayoutParams(params);
        lnV.setOrientation(LinearLayout.VERTICAL);

        lnHV.addView(wi.getWidget());
        lnHV.addView(tvTemperature);
        lnV.addView(tv);
        lnV.addView(lnHV);
      }

      lnH.addView(lnV);
      lnH.addView(LayoutInflater.from(parent.getContext()).inflate(R.layout.circular_button, parent, false));
      viewGroup.addView(lnH);


//      i++;
      j++;
      return new ItemViewHolder(viewGroup);
    }


  }


  @Override
  public void onBindViewHolder(@NonNull final ItemViewHolder holder, final int position) {

//    holder.setIsRecyclable(false);
    Log.wtf("tag", "entered onBindViewHolder::" + position);

    if (holder.getOptionButton() != null) {
      final FloatingActionsMenu optionBtn = holder.getOptionButton();
      if (optionBtn != null) {

        final FloatingActionButton testButton = new FloatingActionButton(optionBtn.getContext());
        final FloatingActionButton testButton2 = new FloatingActionButton(optionBtn.getContext());

        optionBtn.addButton(testButton);
        optionBtn.addButton(testButton2);


        optionBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

            optionBtn.expand();

            listener.onOptionButtonReady(holder.getOptionButton(), position);
          }
        });
      }
    }

    if (holder.getAddButton() != null) {
      Button btnAdd = holder.getAddButton();
      if (btnAdd != null)
        btnAdd.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {


            if (!clickState) {

              clickState = true;

              if (listener != null)
                listener.onObjectReady();

            } else {
              clickState = false;

              if (listener != null)
                listener.onObjectReady();
            }
          }
        });


    }

  }

  @Override
  public void onClick(View v) {
    mRecyclerInterface.onItemClicked(v, 5);
  }


  public static class ItemViewHolder extends RecyclerView.ViewHolder implements
    View.OnClickListener {

    public TextView itemText;
    public Button addingButtton;
    public FloatingActionsMenu optionButton;
    public FloatingActionButton buttonTest;

    public ItemViewHolder(ViewGroup itemView) {
      super(itemView);
      addingButtton = (Button) itemView.findViewById(R.id.button);
      optionButton = (FloatingActionsMenu) itemView.findViewById(R.id.optionButton);
//      buttonTest = (FloatingActionButton) itemView.findViewById(R.id.optionButton2);
    }

    public Button getAddButton() {
      return addingButtton;
    }


    public FloatingActionsMenu getOptionButton() {
      return optionButton;
    }

    public FloatingActionButton getButtonTest() {
      return buttonTest;
    }


    @Override
    public void onClick(View v) {
      Log.wtf("tag", "");

    }

  }

  private class FooterViewHolder extends RecyclerView.ViewHolder {
    TextView footerText;

    public FooterViewHolder(ViewGroup view) {
      super(view);
//      footerText = (TextView) view.findViewById(R.id.footer_text);
    }
  }

  public interface RecyclerViewOnClickListener {
    void onItemClicked(View view, int position);
  }

}
