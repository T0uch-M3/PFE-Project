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
import com.github.lzyzsd.circleprogress.ArcProgress;
import com.github.lzyzsd.circleprogress.CircleProgress;
import com.thekhaeng.pushdownanim.PushDownAnim;

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
      case "Info":
        return 4;
      case "TempHolder":
        return 5;
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
//    Log.wtf("tag", "inside onCREATEHOLDER");
    ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_holder, parent, false);
    LinearLayout ln = new LinearLayout(parent.getContext());
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(230, 250, Gravity.CENTER);
    ln.setLayoutParams(params);

    switch (viewType) {
      case 5:{
        ln.setBackgroundColor(Color.BLACK);
        viewGroup.addView(ln);
        return new ViewHolderDummy(viewGroup);
      }
      case 4: {
        ln.setBackgroundColor(Color.GREEN);
        params.width = 460;
        viewGroup.addView(ln);
//        Log.wtf("tag","TESTiNG  CLICKING  ");
        return new ViewHolderDummy(viewGroup);
      }
      case 3: {//thermometer
        /************************************||==>lnVh*$$$****
         ********************VG==>lnH==>lnV=||*************
         **********************************||==>tv******/
        TextView tv = new TextView(parent.getContext());
//        tv.setBackgroundColor(Color.YELLOW);
        LinearLayout.LayoutParams paramsTV = new LinearLayout.LayoutParams(199, 40);
        paramsTV.gravity = Gravity.CENTER;

        ViewGroup rightPanel = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);

        paramsTV.leftMargin = -10;
//      paramsTV.rightMargin = 8;
        tv.setLayoutParams(paramsTV);
        tv.setPadding(10, 0, 0, 0);

        LinearLayout lnH = new LinearLayout(parent.getContext());
        LinearLayout lnV = null;
        LinearLayout.LayoutParams paramsH = new LinearLayout.LayoutParams(230, 250);
        lnH.setLayoutParams(paramsH);
        lnH.setOrientation(LinearLayout.HORIZONTAL);
        lnH.setBackgroundColor(lnH.getContext().getResources().getColor(R.color.white_ish));

        lnV = new LinearLayout(parent.getContext());
        LinearLayout.LayoutParams paramsLnv = new LinearLayout.LayoutParams(150, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER);

        LinearLayout lnVh = new LinearLayout(parent.getContext());
        lnVh.setOrientation(LinearLayout.HORIZONTAL);
//        lnVh.setBackgroundColor(Color.GREEN);
        lnVh.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        TextView tempValue = new TextView(parent.getContext());
        tempValue.setText("WHAT??");
        tempValue.setBackgroundColor(Color.MAGENTA);

//        lnV.setBackgroundColor(lnV.getContext().getResources().getColor(R.color.colorPrimary));
        paramsLnv.rightMargin = -30;
        lnV.setLayoutParams(paramsLnv);
        lnV.setOrientation(LinearLayout.VERTICAL);
        lnV.addView(tv);
//        lnVh.addView(tempValue);
        lnVh.addView(rightPanel);
        lnV.addView(lnVh);

        lnH.addView(lnV);
        //setting up the option button and what's under it
        ViewGroup optionButtonHolder = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.circular_button, parent, false);
        FloatingActionsMenu fMenu = (FloatingActionsMenu) optionButtonHolder.getChildAt(0);
        final FloatingActionButton testButton = new FloatingActionButton(fMenu.getContext());
        testButton.setColorNormal(testButton.getContext().getResources().getColor(R.color.optionButton));
        final FloatingActionButton testButton2 = new FloatingActionButton(fMenu.getContext());
        testButton2.setColorNormal(testButton.getContext().getResources().getColor(R.color.optionButton));
        fMenu.addButton(testButton);
        fMenu.addButton(testButton2);
        lnH.addView(optionButtonHolder);

        viewGroup.addView(lnH);
        return new ViewHolderDummy(viewGroup);
      }
      case 2: {//Gauge
        /**
         * Setting up the TextView for widget title
         */
        TextView tv = new TextView(parent.getContext());
//        tv.setBackgroundColor(Color.YELLOW);
        LinearLayout.LayoutParams paramsTV = new LinearLayout.LayoutParams(199, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsTV.gravity = Gravity.CENTER;
        paramsTV.leftMargin = 10;
//      paramsTV.rightMargin = 8;
        tv.setLayoutParams(paramsTV);

        LinearLayout lnH = new LinearLayout(parent.getContext());
        LinearLayout lnV = null;
        LinearLayout.LayoutParams paramsH = new LinearLayout.LayoutParams(230, 250);
//        paramsH.leftMargin=30;
        lnH.setLayoutParams(paramsH);
        lnH.setOrientation(LinearLayout.HORIZONTAL);
        lnH.setBackgroundColor(lnH.getContext().getResources().getColor(R.color.white_ish));

        lnV = new LinearLayout(parent.getContext());
        LinearLayout.LayoutParams paramsLnv = new LinearLayout.LayoutParams(150, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER);

        lnV.setBackgroundColor(lnV.getContext().getResources().getColor(R.color.white_ish));
        paramsLnv.rightMargin = -30;
        lnV.setLayoutParams(paramsLnv);
        lnV.setOrientation(LinearLayout.VERTICAL);
        lnV.addView(tv);

        lnH.addView(lnV);
        //setting up the option button and what's under it
        ViewGroup optionButtonHolder = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.circular_button, parent, false);
        FloatingActionsMenu fMenu = (FloatingActionsMenu) optionButtonHolder.getChildAt(0);
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
        ln.setBackgroundColor(Color.CYAN);
        viewGroup.addView(ln);
        return new ViewHolderDummy(viewGroup);
      }
      default:
        return null;

    }
  }

  @Override
  public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
//    Log.wtf("tag", "inside onBINDVIRWHILDERRR");
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
        //just to bypass an error i was getting
        if (wi.getWidget().getParent() != null)
          ((ViewGroup) wi.getWidget().getParent()).removeView(wi.getWidget());

//        Log.wtf("tag", "count" + viewHolder.getvGroup().getChildCount());
        ViewGroup downLvl = (ViewGroup) viewHolder.getvGroup().getChildAt(0);

        ViewGroup downLvl2 = (ViewGroup) downLvl.getChildAt(0);
        ViewGroup downLvl3 = (ViewGroup) downLvl2.getChildAt(1);
//        Log.wtf("tag", "CLASSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSsS" + downLvl.getChildAt(0).getClass());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        params.gravity = Gravity.RIGHT;
//        params.leftMargin = -80;
        params.rightMargin = -20;
        params.bottomMargin = -10;
//        params.setMargins(20,5,20,5);
        View widget = wi.getWidget();
//        widget.setBackgroundColor(Color.RED);
        widget.setLayoutParams(params);
        downLvl3.addView(widget, 0);
        TextView title = (TextView) downLvl2.getChildAt(0);
        title.setText(wi.getTilte());
        final FloatingActionsMenu optionBtn = ((ViewHolderDummy) holder).getOptionButton();
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
      case "Widget_G": {
        ViewHolderDummy viewHolder = (ViewHolderDummy) holder;
        //just to bypass an error i was getting
        if (wi.getWidget().getParent() != null)
          ((ViewGroup) wi.getWidget().getParent()).removeView(wi.getWidget());

//        Log.wtf("tag", "count" + viewHolder.getvGroup().getChildCount());
        ViewGroup downLvl = (ViewGroup) viewHolder.getvGroup().getChildAt(0);

        ViewGroup downLvl2 = (ViewGroup) downLvl.getChildAt(0);
//        Log.wtf("tag", "CLASSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSsS" + downLvl.getChildAt(0).getClass());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        params.gravity = Gravity.BOTTOM;
        params.setMargins(15, 5, 15, 5);
//        View widget = wi.getWidget();
        ArcProgress widget = (ArcProgress) wi.getWidget();
        widget.setLayoutParams(params);
        downLvl2.addView(widget);
        TextView title = (TextView) downLvl2.getChildAt(0);
        title.setText(wi.getTilte());
        final FloatingActionsMenu optionBtn = ((ViewHolderDummy) holder).getOptionButton();
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
        PushDownAnim.setPushDownAnimTo(viewHolderBtn.getButton())
          .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

          }).setScale(PushDownAnim.MODE_STATIC_DP, 2);
//        viewHolderBtn.getButton().setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View v) {
//
//          }
//        });
      }
      break;
      case "Dummy": {
        ViewHolderDummy viewHolderDummy = (ViewHolderDummy) holder;
//        viewHolderDummy.getHolder().setVisibility(View.INVISIBLE);
      }
      case "Info":{
        ViewHolderDummy viewHolderDummy = (ViewHolderDummy) holder;
      }
      break;
      case "tempHolder":{
        ViewHolderDummy viewHolderDummy = (ViewHolderDummy) holder;
      }
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
    if (position >= 0 && position <= getItemCount()) {
      //Out of bounds
      return;
    }
    if (this.mListObjects == null) {
      this.mListObjects = new ArrayList<>();
    }
    this.mListObjects.add(position, mObject);
//    Log.wtf("tag", "WE IN  BOYS");
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

  public void updatOneObject(int position, WidgetItem mObject) {
    if (mObject != null) {
      return;
    }
    if (position >= 0 && position <= getItemCount()) {
      return;
    }
    this.mListObjects.set(position, mObject);
    this.notifyItemChanged(position);
  }
}
