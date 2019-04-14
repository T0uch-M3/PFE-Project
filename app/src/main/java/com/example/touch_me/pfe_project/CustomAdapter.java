package com.example.touch_me.pfe_project;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

  private MyCustomObjectListener listener;
  private Context context;
  private List<WidgetItem> mListObjects;
  private boolean clickState = false;

  public CustomAdapter(@NonNull Context context) {
    this.context = context;
  }

  @Override
  public void onClick(View v) {

  }

  @Override
  public int getItemViewType(int position) {

    switch (mListObjects.get(position).getType()) {
      case "Dummy":
        return 0;
      case "Button":
        return 1;
      case "Widget_G":
        return 2;
      case "Widget_T":
        return 3;
      default:
        return -1;
    }
  }

  public void setCustomObjectListener(MyCustomObjectListener listener) {
    this.listener = listener;
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    Log.wtf("tag", "inside onCREATEHOLDER");
    ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_holder, parent, false);
    LinearLayout ln = new LinearLayout(parent.getContext());
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(230, 250, Gravity.CENTER);
    ln.setLayoutParams(params);

    switch (viewType) {//thermometer
      case 3: {
        viewGroup.addView(ln);
        return new ViewHolderDummy(viewGroup);
      }
      case 2: {//Gauge
        /**
         * Setting up the TextView for widget title
         */
        TextView tv = new TextView(parent.getContext());
        tv.setBackgroundColor(Color.YELLOW);
        LinearLayout.LayoutParams paramsTV = new LinearLayout.LayoutParams(199, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsTV.gravity = Gravity.START;
//      paramsTV.rightMargin = 8;
        tv.setLayoutParams(paramsTV);

        LinearLayout lnH = new LinearLayout(parent.getContext());
        LinearLayout lnV = null;
        LinearLayout.LayoutParams paramsH = new LinearLayout.LayoutParams(230, 250);
//      paramsH.bottomMargin = -50;
//      paramsH.topMargin = -50;
        lnH.setLayoutParams(paramsH);
        lnH.setOrientation(LinearLayout.HORIZONTAL);
        lnH.setBackgroundColor(Color.GRAY);

        lnV = new LinearLayout(parent.getContext());
        LinearLayout.LayoutParams paramsLnv = new LinearLayout.LayoutParams(150, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        lnV.setBackgroundColor(lnV.getContext().getResources().getColor(R.color.white_ish));
//        params.b = -150;
        params.rightMargin = -30;
        lnV.setLayoutParams(paramsLnv);
        lnV.setOrientation(LinearLayout.VERTICAL);
        lnV.addView(tv);
//        lnV.addView(wi.getWidget());

        lnH.addView(lnV);
//        FloatingActionsMenu fMenu = (FloatingActionsMenu) LayoutInflater.from(parent.getContext()).inflate(R.layout.circular_button, parent, false);
        ViewGroup optionButtonHolder = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.circular_button, parent, false);
        FloatingActionsMenu fMenu = (FloatingActionsMenu) optionButtonHolder.getChildAt(0);
//        ln.addView(optionButtonHolder);
        final FloatingActionButton testButton = new FloatingActionButton(fMenu.getContext());
        final FloatingActionButton testButton2 = new FloatingActionButton(fMenu.getContext());
        fMenu.addButton(testButton);
        fMenu.addButton(testButton2);
        lnH.addView(optionButtonHolder);

        viewGroup.addView(lnH);
        return new ViewHolderDummy(viewGroup);
      }
      case 1: {//Button
        ln.setGravity(Gravity.CENTER);
        ln.addView(LayoutInflater.from(parent.getContext()).inflate(R.layout.add_button, parent, false));
        viewGroup.addView(ln);
        return new ViewHolderBtn(viewGroup);
      }
      case 0: {//Dummy
        viewGroup.addView(ln);
        return new ViewHolderDummy(viewGroup);
      }
      default:
        return null;

    }
  }

  @Override
  public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
    Log.wtf("tag", "inside onBINDVIRWHILDERRR");
    final Object nonCastObject = mListObjects.get(position);
    Log.wtf("tag", "position ==>>==" + position + "===Item title HERE=>>=" + mListObjects.get(position).getTilte());

    if (nonCastObject == null) {
      Log.wtf("tag", "null cast object?");
    }

    WidgetItem wi = (WidgetItem) nonCastObject;
//    switch (holder.getItemViewType()) {
    switch (wi.getType()) {
      case "Widget_T": {

        ViewHolderDummy viewHolder = (ViewHolderDummy) holder;
        if (wi.getWidget().getParent() != null)
          ((ViewGroup) wi.getWidget().getParent()).removeView(wi.getWidget());
        viewHolder.getHolder().addView(wi.getWidget());
      }
      break;
      case "Widget_G": {

        ViewHolderDummy viewHolder = (ViewHolderDummy) holder;
        if (wi.getWidget().getParent() != null)
          ((ViewGroup) wi.getWidget().getParent()).removeView(wi.getWidget());

        Log.wtf("tag", "count" + viewHolder.getvGroup().getChildCount());
        ViewGroup downLvl = (ViewGroup) viewHolder.getvGroup().getChildAt(0);

//        downLvl.addView(LayoutInflater.from(downLvl.getContext()).inflate(R.layout.circular_button, downLvl, false));
//        ViewGroup optionButtonHolder = (ViewGroup) downLvl.getChildAt(1);
//        FloatingActionsMenu fMenu = (FloatingActionsMenu) optionButtonHolder.getChildAt(0);
        ViewGroup downLvl2 = (ViewGroup) downLvl.getChildAt(0);
        Log.wtf("tag", "CLASSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSsS" + downLvl.getChildAt(0).getClass());
        downLvl2.addView(wi.getWidget());
        TextView title = (TextView) downLvl2.getChildAt(0);
        title.setText(wi.getTilte());
        final FloatingActionsMenu optionBtn = ((ViewHolderDummy) holder).getOptionButton();
//        final FloatingActionButton testButton = new FloatingActionButton(optionBtn.getContext());
//        final FloatingActionButton testButton2 = new FloatingActionButton(optionBtn.getContext());
//        optionBtn.addButton(testButton);
//        optionBtn.addButton(testButton2);
//        viewHolder.getvGroup().addView(wi.getWidget());
        if (((ViewHolderDummy) holder).getOptionButton() != null) {
//          final FloatingActionsMenu optionBtn = ((ViewHolderDummy) holder).getOptionButton();
          if (optionBtn != null) {

            optionBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                optionBtn.expand();

                listener.onOptionButtonReady(((ViewHolderDummy) holder).getOptionButton(), position);
              }
            });
          }
        }
      }
      break;
      case "Button": {
        ViewHolderBtn viewHolderBtn = (ViewHolderBtn) holder;
        viewHolderBtn.getButton().setOnClickListener(new View.OnClickListener() {
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
      break;
      case "Dummy": {
        ViewHolderDummy viewHolderDummy = (ViewHolderDummy) holder;
//        viewHolderDummy.getHolder().setVisibility(View.INVISIBLE);
      }
      break;

    }


  }

  @Override
  public int getItemCount() {
//    return 0;
    return ((this.mListObjects == null) ? 0 : this.mListObjects.size());
  }

  class ViewHolder extends RecyclerView.ViewHolder {


    public CardView cv;

    public ViewHolder(View itemView) {
      super(itemView);
      this.cv = itemView.findViewById(R.id.holder);


    }


    public CardView getLayout() {
      return cv;
    }
  }

  class ViewHolderBtn extends RecyclerView.ViewHolder {

    public Button addingButtton;

    public ViewHolderBtn(View itemView) {
      super(itemView);
      this.addingButtton = (Button) itemView.findViewById(R.id.button);

    }

    public Button getButton() {
      return addingButtton;
    }
  }

  class ViewHolderDummy extends RecyclerView.ViewHolder {
    private TextView title;
    public CardView cView;
    private LinearLayout Llayout;
    private ViewGroup vGroup;
    private FloatingActionsMenu optionButton;

    public ViewHolderDummy(View itemView) {
      super(itemView);
      this.vGroup = itemView.findViewById(R.id.holder);
      this.optionButton = itemView.findViewById(R.id.optionButton);
//      this.Llayout = (LinearLayout)itemView.findViewById(R.id.holder);
//      this.cView = (CardView) itemView.findViewById(R.id.holder);

    }

    public CardView getHolder() {
      return cView;
    }

    public LinearLayout getLayout() {
      return Llayout;
    }

    public ViewGroup getvGroup() {
      return vGroup;
    }

    public FloatingActionsMenu getOptionButton() {
      return optionButton;
    }
  }

  public void addOneObject(int position, WidgetItem mObject) {
    if (mObject == null) {
      return;
    }
    if (position < 0 || position > getItemCount()) {
      //Out of bounds
      return;
    }
    if (this.mListObjects == null) {
      this.mListObjects = new ArrayList<>();
    }
    this.mListObjects.add(position, mObject);
    Log.wtf("tag", "WE IN  BOYS");
    this.notifyItemChanged(position);
  }

  public void setListObjects(List<WidgetItem> mListObjects) {
    this.mListObjects = mListObjects;
    this.notifyDataSetChanged();
  }

  public void removeOneObject(int position) {
    if (this.mListObjects != null) {
      if ((position >= 0) && (position <= getItemCount())) {
        this.mListObjects.remove(position);
        this.notifyItemChanged(position);
      }
    }
  }

  public List<WidgetItem> getObjectList() {
    return mListObjects;
  }
}
