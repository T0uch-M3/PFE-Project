package com.example.touch_me.pfe_project;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.github.lzyzsd.circleprogress.ArcProgress;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.ChangeBounds;
import androidx.transition.TransitionManager;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

  private MyCustomObjectListener listener;
  private Context context;
  private List<WidgetItem> mListObjects;
  private boolean clickState = false;
  private RecyclerView recyclerView;
  boolean textClicked1 = false;
  boolean textClicked2 = false;
  boolean cantStopMeNow = true;


  public CustomAdapter(@NonNull Context context) {
    this.context = context;
  }

  public CustomAdapter(@NonNull Context context, RecyclerView recyclerView) {
    this.context = context;
    this.recyclerView = recyclerView;
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
    final LinearLayout ln = new LinearLayout(parent.getContext());
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((recyclerView.getWidth() / 3) - 15, 250, Gravity.CENTER);
    params.setMargins(0, 0, 0, 0);
//    RecyclerView.LayoutParams listParams = new RecyclerView.LayoutParams(230,250);
//    listParams.setMargins(0,0,0,0);
//    viewGroup.setLayoutParams(listParams);
    ln.setLayoutParams(params);

    switch (viewType) {
      case 5: {/************************TempHolder*/
        ln.setBackgroundColor(Color.BLACK);
        viewGroup.addView(ln);
        return new ViewHolderDummy(viewGroup);
      }
      case 4: {/************************Info*/
        ln.setBackgroundColor(Color.GREEN);
        NestedScrollView devicesContainer = (NestedScrollView) LayoutInflater.from(parent.getContext()).inflate(R.layout.info_item, parent, false);
        ScrollView.LayoutParams listParams = new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        devicesContainer.setLayoutParams(listParams);
//        ln.addView(holder);
//        params.width = 467;
        params.width = ((recyclerView.getWidth() / 3) * 2) - 23;

        TextView tv = new TextView(parent.getContext());
        tv.setBackgroundColor(Color.YELLOW);
        LinearLayout.LayoutParams paramsTV = new LinearLayout.LayoutParams(300, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsTV.gravity = Gravity.CENTER | Gravity.LEFT;
        paramsTV.leftMargin = 10;
//      paramsTV.rightMargin = 8;
        tv.setLayoutParams(paramsTV);

        final LinearLayout lnH = new LinearLayout(parent.getContext());
        LinearLayout lnV = null;
        LinearLayout.LayoutParams paramsH = new LinearLayout.LayoutParams(((recyclerView.getWidth() / 3) * 2) - 23, 250);


        lnH.setLayoutParams(paramsH);
        lnH.post(new Runnable() {
          @Override
          public void run() {
            Log.wtf("tag", "result of calculation ::" + (recyclerView.getWidth() - (lnH.getWidth() * 3)) / 5);
          }
        });

//        paramsH.leftMargin=30;

        lnH.setOrientation(LinearLayout.HORIZONTAL);
        lnH.setBackgroundColor(lnH.getContext().getResources().getColor(R.color.white_ish));

        lnV = new LinearLayout(parent.getContext());
        LinearLayout.LayoutParams paramsLnv = new LinearLayout.LayoutParams(150, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER);

//        lnV.setBackgroundColor(lnV.getContext().getResources().getColor(R.color.orange));
        paramsLnv.rightMargin = -30;
        lnV.setLayoutParams(paramsLnv);
        lnV.setOrientation(LinearLayout.VERTICAL);
        lnV.addView(tv);
        lnV.addView(devicesContainer);

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
//      viewGroup.addView(ln);
        Log.wtf("tag", "TESTiNG  CLICKING  ");
        return new ViewHolderDummy(viewGroup);
      }
      case 3: {/************************Thermometer*/
        /************************************||==>lnVh*$$$****
         ********************VG==>lnH==>lnV=||*************
         **********************************||==>tv******/
        TextView tv = new TextView(parent.getContext());
//        tv.setBackgroundColor(Color.YELLOW);
        LinearLayout.LayoutParams paramsTV = new LinearLayout.LayoutParams(199, 40);
        paramsTV.gravity = Gravity.CENTER;

        ViewGroup rightPanel = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        ViewGroup group2 = (ViewGroup) rightPanel.getChildAt(0);
        final ViewGroup group3 = (ViewGroup) group2.getChildAt(0);

        final ViewGroup group32 = (ViewGroup) group2.getChildAt(2);
//        Log.wtf("tag","THE  CLASS"+group32.getChildAt(0).getId());

        final LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        imgParams.gravity = Gravity.CENTER;
        imgParams.rightMargin = -10;
        ImageView img = new ImageView(context);
        img.setLayoutParams(imgParams);

        final TextView upperText = (TextView) group3.getChildAt(0);
        final TextView lowerText = (TextView) group32.getChildAt(0);
        upperText.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Log.wtf("tag", "CLICKED");
            TransitionManager.beginDelayedTransition(group3, new ChangeBounds());
            LinearLayout.LayoutParams textParams = (LinearLayout.LayoutParams) upperText.getLayoutParams();
            if (textClicked1) {
              upperText.setTextSize(40);
              lowerText.setTextSize(40);
              imgParams.topMargin = 0;
              textClicked1 = false;
              Log.wtf("tag", "50");
            } else {
              upperText.setTextSize(55);
              lowerText.setTextSize(20);
              imgParams.topMargin = -20;
              textClicked1 = true;
              Log.wtf("tag", "80");

            }
            upperText.setLayoutParams(textParams);
          }
        });

        lowerText.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Log.wtf("tag", "CLICKED");
            TransitionManager.beginDelayedTransition(group3, new ChangeBounds());
            LinearLayout.LayoutParams textParams = (LinearLayout.LayoutParams) upperText.getLayoutParams();
            if (textClicked2) {
              lowerText.setTextSize(40);
              upperText.setTextSize(40);
              imgParams.topMargin = 0;
              textClicked2 = false;
              Log.wtf("tag", "50");
            } else {
              lowerText.setTextSize(55);
              upperText.setTextSize(20);
              imgParams.topMargin = 20;
              textClicked2 = true;
              Log.wtf("tag", "80");

            }
            upperText.setLayoutParams(textParams);
          }
        });

        paramsTV.leftMargin = -10;
//      paramsTV.rightMargin = 8;
        tv.setLayoutParams(paramsTV);
        tv.setPadding(10, 0, 0, 0);

        LinearLayout lnH = new LinearLayout(parent.getContext());
        LinearLayout lnV = null;
        LinearLayout.LayoutParams paramsH = new LinearLayout.LayoutParams((recyclerView.getWidth() / 3) - 15, 250);
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
        lnVh.addView(img);
        lnVh.addView(rightPanel);
        lnV.addView(lnVh);

        lnH.addView(lnV);
        //setting up the option button and what's under it
        ViewGroup optionButtonHolder = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.circular_button, parent, false);
        FloatingActionsMenu fMenu = (FloatingActionsMenu) optionButtonHolder.getChildAt(0);


        final FloatingActionButton testButton = new FloatingActionButton(fMenu.getContext());
        testButton.setColorNormal(testButton.getContext().getResources().getColor(R.color.orange));
        testButton.setColorPressed(testButton.getContext().getResources().getColor(R.color.grey));
        ShapeDrawable drawable = new ShapeDrawable(new RectShape());
        drawable.getPaint().setColor(Color.WHITE);
        testButton.setIconDrawable(drawable);

        final FloatingActionButton testButton2 = new FloatingActionButton(fMenu.getContext());
        testButton2.setColorNormal(testButton.getContext().getResources().getColor(R.color.company_name));
        testButton2.setColorPressed(testButton.getContext().getResources().getColor(R.color.grey));
        ShapeDrawable drawable2 = new ShapeDrawable(new OvalShape());

        drawable2.getPaint().setColor(Color.WHITE);
        testButton2.setIconDrawable(drawable2);

        fMenu.addButton(testButton2);
        fMenu.addButton(testButton);
        lnH.addView(optionButtonHolder);

        viewGroup.addView(lnH);
        return new ViewHolderDummy(viewGroup);
      }
      case 2: {/************************Gauge*/
        /**
         * Setting up the TextView for widget title
         */

        Log.wtf("tag", "recycler view width ::" + recyclerView.getWidth());
//        Log.wtf("tag","LMBAO::"+viewGroup.);
        TextView tv = new TextView(parent.getContext());
//        tv.setBackgroundColor(Color.YELLOW);
        LinearLayout.LayoutParams paramsTV = new LinearLayout.LayoutParams(199, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsTV.gravity = Gravity.CENTER;
        paramsTV.leftMargin = 10;
//      paramsTV.rightMargin = 8;
        tv.setLayoutParams(paramsTV);

        final LinearLayout lnH = new LinearLayout(parent.getContext());
        LinearLayout lnV = null;
        LinearLayout.LayoutParams paramsH = new LinearLayout.LayoutParams((recyclerView.getWidth() / 3) - 15, 250);

        LinearLayout.LayoutParams listParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 440);
        lnH.setLayoutParams(paramsH);
        lnH.post(new Runnable() {
          @Override
          public void run() {
            Log.wtf("tag", "result of calculation ::" + (recyclerView.getWidth() - (lnH.getWidth() * 3)) / 5);
          }
        });

//        paramsH.leftMargin=30;

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
        testButton.setColorNormal(testButton.getContext().getResources().getColor(R.color.optionButton));
        final FloatingActionButton testButton2 = new FloatingActionButton(fMenu.getContext());
        testButton2.setColorNormal(testButton.getContext().getResources().getColor(R.color.optionButton));
        fMenu.addButton(testButton);
        fMenu.addButton(testButton2);
        lnH.addView(optionButtonHolder);

        viewGroup.addView(lnH);
        return new ViewHolderDummy(viewGroup);
      }
      case 1: {/************************Button*/
        ln.setGravity(Gravity.CENTER);
        ln.addView(LayoutInflater.from(parent.getContext()).inflate(R.layout.add_button, parent, false));
        viewGroup.addView(ln);
        return new ViewHolderBtn(viewGroup);
      }
      case 0: {/************************Dummy*/
        ln.setBackgroundColor(Color.CYAN);
        ln.post(new Runnable() {
          @Override
          public void run() {
            recyclerView.setPadding(((recyclerView.getWidth() - (ln.getWidth() * 3)) / 5) + 2, 0, (recyclerView.getWidth() - (ln.getWidth() * 3)) / 5, 0);
          }
        });
//        params.width =(recyclerView.getWidth()/3)-20 ;
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

    final WidgetItem wi = (WidgetItem) nonCastObject;
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
        final ViewGroup downLvl3 = (ViewGroup) downLvl2.getChildAt(1);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        params.gravity = Gravity.RIGHT | Gravity.CENTER;
        params.rightMargin = -20;
        params.bottomMargin = -10;

        View widget = wi.getWidget();
        widget.setLayoutParams(params);

        ImageView iv = (ImageView) downLvl3.getChildAt(0);
        iv.setImageDrawable(iv.getContext().getResources().getDrawable(R.drawable.farenheit));

        TextView title = (TextView) downLvl2.getChildAt(0);
        title.setText(wi.getTilte());
        final FloatingActionsMenu optionBtn = ((ViewHolderDummy) holder).getOptionButton();

        if (((ViewHolderDummy) holder).getOptionButton() != null) {
          if (optionBtn != null) {/****Option Button Click  EVENT **/
            optionBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                optionBtn.expand();
                listener.onOptionButtonReady(((ViewHolderDummy) holder).getOptionButton(), position);
              }
            });
          }
        }

        ViewGroup underDogs = optionBtn;
        FloatingActionButton deleteButton = (FloatingActionButton) underDogs.getChildAt(1);
        deleteButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {/*****Delete Button Click EVENT  ***/
            removeOneObject(position);
            Log.wtf("tag", "should be TEMPHOLDER" + mListObjects.get(position + 1).getType());

            if (mListObjects.get(position).getType() == "TempHolder") {//the item removed got a holder in front of it
              removeOneObject(position);
              listener.notifyForRemove(CustomAdapter.this, wi, false, 1);
            }
            if (mListObjects.get(position + 1).getType() == "TempHolder") {//the item removed got a holder in front of the item next to it
              removeOneObject(position + 1);
              listener.notifyForRemove(CustomAdapter.this, wi, false, 1);//first check
            }

            listener.notifyForRemove(CustomAdapter.this, wi, false, 1);//a second check won't hurt
            optionBtn.collapse();
          }
        });
        FloatingActionButton infoButton = (FloatingActionButton) underDogs.getChildAt(0);
        infoButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Log.wtf("tag", "INFO MOFO");
          }
        });

        ViewGroup downLvl4 = (ViewGroup) downLvl3.getChildAt(1);
        ViewGroup downLvl5 = (ViewGroup) downLvl4.getChildAt(0);
        ViewGroup downLvl6 = (ViewGroup) downLvl5.getChildAt(0);
        Log.wtf("tag", "SHOULD  BE  TextVIEW::" + downLvl6.getChildAt(0));
        final TextView upperText = (TextView) downLvl6.getChildAt(0);
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
        final ViewHolderBtn viewHolderBtn = (ViewHolderBtn) holder;
        final ViewGroup btnViewGroup = (ViewGroup) viewHolderBtn.getButton().getParent();
        PushDownAnim.setPushDownAnimTo(viewHolderBtn.getButton())
          .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if (!clickState) {

                clickState = true;
//                colorChange(btnViewGroup, viewHolderBtn.getButton());
                if (listener != null)
                  listener.onObjectReady();

              } else {
                clickState = false;

                if (listener != null)
                  listener.onObjectReady();
              }
            }

          }).setScale(PushDownAnim.MODE_STATIC_DP, 1);
      }
      break;
      case "Dummy": {
        ViewHolderDummy viewHolderDummy = (ViewHolderDummy) holder;
//        viewHolderDummy.getHolder().setVisibility(View.INVISIBLE);
      }
      break;
      case "Info": {
        final List<Integer> shapeSaver = new ArrayList<>();

        final ViewHolderDummy viewHolder = (ViewHolderDummy) holder;
        final ViewGroup downLvl = (ViewGroup) viewHolder.getvGroup().getChildAt(0);
        ViewGroup downLvl2 = (ViewGroup) downLvl.getChildAt(0);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        params.gravity = Gravity.BOTTOM;
        params.setMargins(15, 5, 15, 5);
        TextView title = (TextView) downLvl2.getChildAt(0);
        final NestedScrollView scrollViewList = (NestedScrollView) downLvl2.getChildAt(1);
        ViewGroup devicesHolder = (ViewGroup) scrollViewList.getChildAt(0);
//        devicesHolder.removeAllViews();

        List<List<String>> bigDaddy = wi.getInfo();//TODO: The list is start with get filled gradually (1 then 2, 3...) due to being connected directly to db, change the input source to realm, than future update from firebase

        /************************/
        if(devicesHolder.getChildCount()==4)
          devicesHolder.removeAllViews();



        Log.wtf("tag", "holder size::" + devicesHolder.getChildCount());
        Log.wtf("tag", "info display::" + bigDaddy);
        if (wi.getInfo() != null){
          Collections.reverse(bigDaddy);//Just to keep the unchanged value at the bottom (when you became what you hate)
          for (int i = 0; i < bigDaddy.size(); i++) {
            shapeSaver.add(0);//the list responsible of the values for the time/date switch events
          }
        if (bigDaddy.size() > 1)//for testing if the user worth of a gift or not (picking just one device)
          for (int i = 0; i < bigDaddy.size(); i++) {
            Log.wtf("tag", "countingggggg" + i);
            final String deviceName = bigDaddy.get(i).get(0);
            final String timeAndDate = bigDaddy.get(i).get(1);
            String[] splitter = timeAndDate.split(" ");
            //Due to how cooperative the backend team was, i had to add the "0" in front of every one digit number
            for (int h = 0; h < splitter.length; h++) {
              if (splitter[h].length() == 1) {
                splitter[h] = "0" + splitter[h];
              }
            }
            final String time = splitter[0] + " : " + splitter[1] + " : " + splitter[2];
            final String date = splitter[3] + "/" + splitter[4] + "/" + splitter[5];

            //Creating the textView's holder
            LinearLayout device = new LinearLayout(this.context);
            device.setOrientation(LinearLayout.HORIZONTAL);
            device.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            //the textViews...
            final TextView leftSide = new TextView(this.context);
            final TextView rightSide = new TextView(this.context);
            TextView etc = new TextView(this.context);

            leftSide.setTextSize(19);
            rightSide.setTextSize(19);

            leftSide.setSingleLine(true);
            rightSide.setSingleLine(true);

            leftSide.setText(deviceName);
            rightSide.setText(date);

            leftSide.setBackgroundColor(Color.GREEN);
            rightSide.setBackgroundColor(Color.YELLOW);

            leftSide.setLayoutParams(new LinearLayout.LayoutParams(240, ViewGroup.LayoutParams.WRAP_CONTENT));
            rightSide.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            rightSide.setGravity(Gravity.CENTER);
            device.addView(leftSide);

            if (leftSide.length() > 12) {
              etc.setText("...");
              etc.setTextSize(19);
              device.addView(etc);
            }
            device.addView(rightSide);
            devicesHolder.addView(device);

            leftSide.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {//TODO: make the hold to show the full name of the device
                if(leftSide.getLayoutParams().width==450)
                  leftSide.setLayoutParams(new LinearLayout.LayoutParams(220, ViewGroup.LayoutParams.WRAP_CONTENT));
                else
                  leftSide.setLayoutParams(new LinearLayout.LayoutParams(450, ViewGroup.LayoutParams.WRAP_CONTENT));
              }
            });
            final int finalI = i;

            //switching between date and time
            rightSide.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
//                if (shapeSaver.get(finalI) == 0) {
//                  rightSide.setText(time);
//                  shapeSaver.set(finalI, 1);
//                } else {
//                  rightSide.setText(date);
//                  shapeSaver.set(finalI, 0);
//                }
                if(rightSide.getText().toString().contains(":"))
                  rightSide.setText(date);
                else
                  rightSide.setText(time);
              }
            });
            etc.setOnTouchListener(new View.OnTouchListener() {
              @Override
              public boolean onTouch(View v, MotionEvent event) {
                RecyclerView recView = (RecyclerView) downLvl.getParent().getParent();
                recView.requestDisallowInterceptTouchEvent(true);
                return false;
              }

            });
            leftSide.setOnTouchListener(new View.OnTouchListener() {
              @Override
              public boolean onTouch(View v, MotionEvent event) {
                RecyclerView recView = (RecyclerView) downLvl.getParent().getParent();
                recView.requestDisallowInterceptTouchEvent(true);
                return false;
              }

            });
            rightSide.setOnTouchListener(new View.OnTouchListener() {
              @Override
              public boolean onTouch(View v, MotionEvent event) {
                RecyclerView recView = (RecyclerView) downLvl.getParent().getParent();
                recView.requestDisallowInterceptTouchEvent(true);
                return false;
              }

            });
          }
        if (bigDaddy.size() == 1) {

          final String deviceName = bigDaddy.get(0).get(0);
          final String timeAndDate = bigDaddy.get(0).get(1);
          String[] splitter = timeAndDate.split(" ");
          //Due to how cooperative the backend team was, i had to add the "0" in front of every one digit number
          for (int i = 0; i < splitter.length; i++) {
            if (splitter[i].length() == 1) {
              splitter[i] = "0" + splitter[i];
            }
          }
          final String time = "" + splitter[0] + " : " + splitter[1] + " : " + splitter[2];
          final String date = splitter[3] + "/" + splitter[4] + "/" + splitter[5];

          final TextView topText = new TextView(this.context);
          final TextView timeText = new TextView(this.context);
          final TextView dateText = new TextView(this.context);
          final TextView nameText = new TextView(this.context);

          topText.setTextSize(16);
          timeText.setTextSize(30);
          dateText.setTextSize(15);
          nameText.setTextSize(15);

          topText.setText("Last update:");
          timeText.setText(time);
          dateText.setText(date);
          nameText.setText(deviceName);

          timeText.setGravity(Gravity.CENTER);
          dateText.setGravity(Gravity.CENTER);
          nameText.setGravity(Gravity.CENTER);

          nameText.setPadding(0, 3, 0, 1);

          devicesHolder.addView(topText);
          devicesHolder.addView(timeText);
          devicesHolder.addView(dateText);
          devicesHolder.addView(nameText);

        }
        //disable the scroll of the recyclerViw in favor of the scrollView inside the cards when the number of items is more than what the card can hold
        if (scrollViewList.getChildCount() > 4)
          scrollViewList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
              RecyclerView recView = (RecyclerView) downLvl.getParent().getParent();
              recView.requestDisallowInterceptTouchEvent(true);
              return false;
            }
          });

//        LinearLayout lv1 = scrollViewList.getChildAt(0);
        title.setText("HONOLULU");
      }
      }
      break;
      case "tempHolder": {
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
    recyclerView.getRecycledViewPool().clear();
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

  public void updateOneObject(int position, WidgetItem mObject) {
    if (mObject == null) {
      Log.wtf("tag", "NULL  UPDATE");
      return;
    }
//    if (position >= 0 && position <= getItemCount()) {
//      return;
//    }
//    Log.wtf("tag", "WE ARE  UPDATING  BOYS" + mObject.getInfo());
    this.mListObjects.set(position, mObject);

    this.notifyItemChanged(position);
  }

}
