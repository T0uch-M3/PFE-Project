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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.ArrayList;
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

  boolean cantStopMeNow = true;
  private List<TextShapeSaver> shapeSaverList = new ArrayList<>();


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
      case "Graph":
        return 6;
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
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((recyclerView.getWidth() / 3) - 6, (recyclerView.getWidth() / 3) + 15, Gravity.CENTER);
    params.setMargins(0, 0, 0, 0);
//    Log.wtf("tag", "list widdth::" + recyclerView.getWidth());
//    RecyclerView.LayoutParams listParams = new RecyclerView.LayoutParams(230,250);
//    listParams.setMargins(0,0,0,0);
//    viewGroup.setLayoutParams(listParams);
    ln.setLayoutParams(params);

    switch (viewType) {
      case 6: {
        ln.setBackgroundColor(ln.getContext().getResources().getColor(R.color.white_ish));
        params.width = (recyclerView.getWidth() - 23);
        params.height = 500;
        LineChart lineChart = new LineChart(context);
        BarChart barChart = new BarChart(context);

        List<Entry> entriesList = new ArrayList<Entry>();

        for (int i = 0; i < 9; i = i + 2) {
          entriesList.add(new Entry(i, i + 3));
        }

//        entriesList.add(new Entry(9,4));
//        entriesList.add(new Entry(5,4));
//        entriesList.add(new Entry(5,2));
//        entriesList.add(new Entry(3,0));

//        LineDataSet dataSet = new LineDataSet(entriesList, "Label");
//        dataSet.setColor(Color.RED);
//        dataSet.setValueTextColor(Color.BLUE);
//        dataSet.setForm(Legend.LegendForm.CIRCLE);
//        dataSet.setFillColor(Color.GREEN);
//        dataSet.setFillDrawable(new DrawableContainer());
//        dataSet.setDrawHighlightIndicators(false);
//        dataSet.setFormSize(5f);
//        dataSet.setLineWidth(2f);
//        dataSet.setDrawCircleHole(false);
//
//        final String[] quarters = new String[] { "Q1", "Q2", "Q3", "Q4" };
//        IndexAxisValueFormatter formatter2 = new IndexAxisValueFormatter();
//        formatter2.setValues(quarters);
//
//        XAxis xAxis = lineChart.getXAxis();
//        lineChart.getAxisLeft().setEnabled(false);
//        xAxis.setValueFormatter(formatter2);
//
//        LineData lineData = new LineData(dataSet);
/********************************************************/
        List<BarEntry> entries = new ArrayList<>();
//        entries.add(new BarEntry(0f, 30f));
//        entries.add(new BarEntry(1f, 80f));
//        entries.add(new BarEntry(2f, 60f));
//        entries.add(new BarEntry(3f, 50f));
//        // gap of 2f
//        entries.add(new BarEntry(5f, 70f));
//        entries.add(new BarEntry(6f, 60f));
//        entries.add(new BarEntry(7f, 40f));
//        entries.add(new BarEntry(8f, 75f));
//        entries.add(new BarEntry(9f, 55f));
        for (int i = 0; i < 40; i = i + 2) {
          entries.add(new BarEntry(i - 2, i));
        }

        BarDataSet set = new BarDataSet(entries, "BarDataSet");

        BarData data = new BarData(set);
        data.setBarWidth(0.9f); // set custom bar width
        barChart.setData(data);
//        barChart.setFitBars(true); // make the x-axis fit exactly all bars
        barChart.setDrawGridBackground(false);
        barChart.setGridBackgroundColor(Color.GREEN);
        barChart.animateX(3000);
//        barChart.setTouchEnabled(false);
        barChart.setScaleEnabled(false);
        barChart.setDrawBorders(false);

        barChart.setDragYEnabled(false);
//        barChart.setPinchZoom(false);
        barChart.invalidate(); // refresh


//        lineChart.setData(barChart);
//        lineChart.invalidate();
        barChart.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        ln.addView(barChart);

        viewGroup.addView(ln);
        return new ViewHolderDummy(viewGroup);
      }
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
//        tv.setBackgroundColor(Color.YELLOW);
        LinearLayout.LayoutParams paramsTV = new LinearLayout.LayoutParams(400, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsTV.gravity = Gravity.CENTER | Gravity.LEFT;
        paramsTV.leftMargin = 10;
//      paramsTV.rightMargin = 8;
        tv.setLayoutParams(paramsTV);

        final LinearLayout lnH = new LinearLayout(parent.getContext());
        LinearLayout lnV = null;
        LinearLayout.LayoutParams paramsH = new LinearLayout.LayoutParams(((recyclerView.getWidth() / 3) * 2) - 12, (recyclerView.getWidth() / 3) + 15);


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

//        lnH.setBackground(ln.getContext().getResources().getDrawable(R.drawable.rect_button_border));

        viewGroup.addView(lnH);
//      viewGroup.addView(ln);
//        Log.wtf("tag", "TESTiNG  CLICKING  ");
        return new ViewHolderDummy(viewGroup);
      }
      case 3: {/************************Thermometer*/
        /************************************||==>lnVh*$$$****
         ********************VG==>lnH==>lnV=||*************
         **********************************||==>tv******/
        TextView tv = new TextView(parent.getContext());
        final boolean[] textClicked1 = {false};
        final boolean[] textClicked2 = {false};
//        tv.setBackgroundColor(Color.YELLOW);
        LinearLayout.LayoutParams paramsTV = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

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
            Log.wtf("tag", "up CLICKED");
            TransitionManager.beginDelayedTransition(group3, new ChangeBounds());
            final LinearLayout.LayoutParams textParams = (LinearLayout.LayoutParams) upperText.getLayoutParams();
            if (textClicked1[0]) {
              Log.wtf("tag", "up text size::" + upperText.getTextSize());
//            if(upperText.getTextSize()==55){
              upperText.setTextSize(40);
              lowerText.setTextSize(40);
              imgParams.topMargin = 0;
              textClicked1[0] = false;
              textClicked2[0] = false;
              Log.wtf("tag", "up 40");
            } else {
              upperText.setTextSize(55);
              lowerText.setTextSize(20);
              imgParams.topMargin = -20;
              textClicked1[0] = true;
              textClicked2[0] = true;
              Log.wtf("tag", "up 55");

            }
            upperText.setLayoutParams(textParams);
          }
        });

        lowerText.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Log.wtf("tag", "down CLICKED");
            TransitionManager.beginDelayedTransition(group3, new ChangeBounds());
            LinearLayout.LayoutParams textParams = (LinearLayout.LayoutParams) upperText.getLayoutParams();
            if (textClicked2[0]) {
//            if(lowerText.getTextSize()==55){
              lowerText.setTextSize(40);
              upperText.setTextSize(40);
              imgParams.topMargin = 0;
              textClicked2[0] = false;
              textClicked1[0] = false;
              Log.wtf("tag", "50");
            } else {
              lowerText.setTextSize(55);
              upperText.setTextSize(20);
              imgParams.topMargin = 20;
              textClicked2[0] = true;
              textClicked1[0] = true;
              Log.wtf("tag", "80");

            }
            upperText.setLayoutParams(textParams);
          }
        });

        paramsTV.leftMargin = -10;
//      paramsTV.rightMargin = 8;
        tv.setLayoutParams(paramsTV);
        tv.setPadding(20, 0, 20, 0);
        tv.setMaxLines(2);

        LinearLayout lnH = new LinearLayout(parent.getContext());
        LinearLayout lnV = null;
        LinearLayout.LayoutParams paramsH = new LinearLayout.LayoutParams((recyclerView.getWidth() / 3) - 6, (recyclerView.getWidth() / 3) + 15);
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
//        lnH.setBackground(lnH.getContext().getResources().getDrawable(R.drawable.rect_button_border));

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
        LinearLayout.LayoutParams paramsH = new LinearLayout.LayoutParams((recyclerView.getWidth() / 3) - 15, (recyclerView.getWidth() / 3) + 15);

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
//        ln.setBackgroundColor(Color.CYAN);
        ln.post(new Runnable() {
          @Override
          public void run() {
//            recyclerView.setPadding(0, 0, 0, 0);
//            recyclerView.setPadding(((recyclerView.getWidth() - (ln.getWidth() * 3)) / 5) + 2, 0, (recyclerView.getWidth() - (ln.getWidth() * 3)) / 5, 0);
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
//    Log.wtf("tag", "position ==>>==" + position + "===Item title HERE=>>=" + mListObjects.get(position).getTilte());

    if (nonCastObject == null) {
      Log.wtf("tag", "null cast object?");
    }
    if (position % 3 == 0 || position == 0) {
      try {
        ViewHolderDummy viewHolderDummy = (ViewHolderDummy) holder;
        final ViewGroup lvl1 = (ViewGroup) viewHolderDummy.getvGroup().getChildAt(0);
        Log.wtf("tag", "position");
        lvl1.setBackground(lvl1.getContext().getResources().getDrawable(R.drawable.right_border));
      } catch (Exception e) {

      }
    }
    if ((position - 1) % 3 == 0) {
      try {
        ViewHolderDummy viewHolderDummy = (ViewHolderDummy) holder;
        final ViewGroup lvl1 = (ViewGroup) viewHolderDummy.getvGroup().getChildAt(0);
        Log.wtf("tag", "position-1");
        lvl1.setBackground(lvl1.getContext().getResources().getDrawable(R.drawable.bottom_border));
      } catch (Exception e) {

      }
    }
    if ((position - 2) % 3 == 0) {
      try {
        ViewHolderDummy viewHolderDummy = (ViewHolderDummy) holder;
        final ViewGroup lvl1 = (ViewGroup) viewHolderDummy.getvGroup().getChildAt(0);
        Log.wtf("tag", "position-2");
        lvl1.setBackground(lvl1.getContext().getResources().getDrawable(R.drawable.left_border));
      } catch (Exception e) {

      }

    }

    final WidgetItem wi = (WidgetItem) nonCastObject;
//    switch (holder.getItemViewType()) {
    switch (wi.getType()) {
      case "Graph": {
        ViewHolderDummy viewHolderDummy = (ViewHolderDummy) holder;
        final ViewGroup lvl1 = (ViewGroup) viewHolderDummy.getvGroup().getChildAt(0);
        ViewGroup lvl2 = (ViewGroup) lvl1.getChildAt(0);
        lvl2.setOnTouchListener(new View.OnTouchListener() {
          @Override
          public boolean onTouch(View v, MotionEvent event) {
            RecyclerView recView = (RecyclerView) lvl1.getParent().getParent();
            recView.requestDisallowInterceptTouchEvent(true);
            return false;
          }
        });
        Log.wtf("tag", "what is the class::" + lvl2.getClass());
      }
      break;
      case "Widget_T": {
        final boolean[] magicChecking = {true}; //this should be responsible of triggering the check through the magic method
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
        iv.setImageDrawable(iv.getContext().getResources().getDrawable(R.drawable.thermometertemp));

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

//            Log.wtf("tag", "pos    :: " + mListObjects.get(position).getType());
//            Log.wtf("tag", "pos + 1:: " + mListObjects.get(position + 1).getType());
//            Log.wtf("tag", "pos + 2:: " + mListObjects.get(position + 2).getType());
//            Log.wtf("tag", "pos + 3:: " + mListObjects.get(position + 3).getType());

//If we remove a widget and there's a tempHolder infront of  it
            if (mListObjects.get(position).getType() == "TempHolder") {//the item removed got a holder in front of it
              List<Integer> posList = new ArrayList<>();
              int jumpCounter = position;
              int holderDeleted = 0;
              Log.wtf("tag", "first if:: ");
              Log.wtf("tag", "position of tempholdder:: " + position);


              while ((mListObjects.get(jumpCounter).getType() == "TempHolder" || mListObjects.get(jumpCounter + 1).getType() == "TempHolder") && holderDeleted < 2) {
                if (mListObjects.get(jumpCounter).getType() == "TempHolder") {
                  removeOneObject(jumpCounter);
                  listener.notifyForRemove(CustomAdapter.this, wi, false, 1);
                  Log.wtf("tag", "FOUND  AND DELETE ");
                  holderDeleted++;
                }

                if (mListObjects.get(jumpCounter + 1).getType() == "TempHolder") {
                  removeOneObject(jumpCounter + 1);
                  listener.notifyForRemove(CustomAdapter.this, wi, false, 1);
                  Log.wtf("tag", "FOUND  AND DELETE + 1");
                  holderDeleted++;
                }

                jumpCounter++;
              }
            }
//            Log.wtf("tag", "pos    :: " + mListObjects.get(position).getType());

            //If we remove a widget and there's a tempHolder infront of the widget after it
            if (mListObjects.get(position + 1).getType() == "TempHolder" && mListObjects.get(position).getType() != "Info") {//the item removed got a holder in front of the item next to it
              List<Integer> posList = new ArrayList<>();
              int jumpCounter = position + 1;
              int holderDeleted = 0;
              Log.wtf("tag", "first if:: ");
              Log.wtf("tag", "position of tempholdder:: " + position);


              while ((mListObjects.get(jumpCounter).getType() == "TempHolder" || mListObjects.get(jumpCounter + 1).getType() == "TempHolder") && holderDeleted < 2) {
                if (mListObjects.get(jumpCounter).getType() == "TempHolder") {
                  removeOneObject(jumpCounter);
                  listener.notifyForRemove(CustomAdapter.this, wi, false, 1);
                  Log.wtf("tag", "FOUND  AND DELETE ");
                  holderDeleted++;
                }

                if (mListObjects.get(jumpCounter + 1).getType() == "TempHolder") {
                  removeOneObject(jumpCounter + 1);
                  listener.notifyForRemove(CustomAdapter.this, wi, false, 1);
                  Log.wtf("tag", "FOUND  AND DELETE + 1");
                  holderDeleted++;
                }

                jumpCounter++;
              }
            }


            int pos = 0;
            boolean foundInfo = false;
            Log.wtf("tag", "pos    :: " + mListObjects.get(position).getType());
            Log.wtf("tag", "pos + 1:: " + mListObjects.get(position + 1).getType());
            Log.wtf("tag", "pos + 2:: " + mListObjects.get(position + 2).getType());
            do {
              Log.wtf("tag", "pos:: " + pos);
              Log.wtf("tag", "REQL  pos:: " + getAbsolutePosition(position));
              Log.wtf("tag", "position:: " + position);

              if (mListObjects.get(position + pos).getType() == "Info") {
                if (getAbsolutePosition(position + pos) % 3 == 0) {
                  Log.wtf("tag", "we got a boogy");
                  magicChecking[0] = false;
                  listener.notifyForRemove(CustomAdapter.this, wi, true, 1000 + position + pos);
                }
//                Log.wtf("tag", "found info at ::" + pos + " " + getAbsolutePosition(position + pos));
                foundInfo = true;
              }
              pos++;
            }
            while (pos < 3 && !foundInfo);//As long as these conditions are met the loop will keep spinning
            /***********************/

            if (mListObjects.get(position).getType() == "Info") {
              if (mListObjects.get(position + 1).getType() == "Info") {
                listener.notifyForRemove(CustomAdapter.this, wi, true, 1000 + position + 1);
                magicChecking[0] = false;
              }
            }

            if (magicChecking[0])
              listener.notifyForRemove(CustomAdapter.this, wi, false, 1);//a second check won't hurt (actually it can)
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
//        Log.wtf("tag", "SHOULD  BE  TextVIEW::" + downLvl6.getChildAt(0));
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
//        Log.wtf("tag", "detected dummy::");
        ViewHolderDummy viewHolderDummy = (ViewHolderDummy) holder;
        try {
//          viewHolderDummy.getHolder().setVisibility(View.INVISIBLE);
//          Log.wtf("tag", "detected dummy:2:");
          final ViewGroup lvl1 = (ViewGroup) viewHolderDummy.getvGroup().getChildAt(0);
//        Log.wtf("tag", "what is the class::" + lvl1.getClass());
          lvl1.setBackground(null);
          lvl1.setBackgroundColor(Color.RED);
        } catch (Exception e) {
          e.printStackTrace();
        }

      }
      break;
      case "Info": {
        List<Integer> shapeSaver = new ArrayList<>();

        final ViewHolderDummy viewHolder = (ViewHolderDummy) holder;
        final ViewGroup downLvl = (ViewGroup) viewHolder.getvGroup().getChildAt(0);
        ViewGroup downLvl2 = (ViewGroup) downLvl.getChildAt(0);
        /********************************/
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
            listener.notifyForRemove(CustomAdapter.this, wi, false, 2);//a second check won't hurt
//            Log.wtf("tag", "pos " + mListObjects.get(position).getType());
//            Log.wtf("tag", "pos + 1 " + mListObjects.get(position +1).getType());
//            Log.wtf("tag", "pos + 2 " + mListObjects.get(position + 2).getType());
//            Log.wtf("tag", "pos + 3 " + mListObjects.get(position + 3).getType());

            if (mListObjects.get(position).getType() == "TempHolder") {//the item removed got a holder in front of it
              listener.notifyForRemove(CustomAdapter.this, mListObjects.get(position), false, 1);
              removeOneObject(position);
            }

//            if (mListObjects.get(position + 1).getType() == "TempHolder") {//the item removed got a holder in front of the item next to it
//              removeOneObject(position + 1);
//              listener.notifyForRemove(CustomAdapter.this, wi, false, 1);//first check
//            }


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
        /*****************************************/

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        params.gravity = Gravity.BOTTOM;
        params.setMargins(15, 5, 15, 5);
        TextView title = (TextView) downLvl2.getChildAt(0);
        final NestedScrollView scrollViewList = (NestedScrollView) downLvl2.getChildAt(1);
        ViewGroup devicesHolder = (ViewGroup) scrollViewList.getChildAt(0);
//        devicesHolder.removeAllViews();

        List<List<String>> bigDaddy = wi.getSelectedDevices();//TODO: The list is start with get filled gradually (1 then 2, 3...) due to being connected directly to db, change the input source to realm, than future update from firebase

        Log.wtf("tag", "wi.getSelectedDevicesNumber" + wi.getSelectedDevicesNumber());
        if ((devicesHolder.getChildCount() + bigDaddy.size()) > wi.getSelectedDevicesNumber())
          devicesHolder.removeAllViews();


        Log.wtf("tag", "holder size::" + devicesHolder.getChildCount());
        Log.wtf("tag", "info display::" + bigDaddy);

        if (wi.getSelectedDevices() != null && wi.getSelectedDevicesNumber() != null) {
          if (bigDaddy.size() == wi.getSelectedDevicesNumber()) {
            Collections.reverse(bigDaddy);//Just to keep the unchanged value at the bottom (or this what i think it should be doing :P)
//            Log.wtf("tag", "YOU BETTER  BE  4::" + wi.getSelectedDevicesNumber());

//            if (shapeSaverList.size() == 0) {

            boolean foundIt = false;
            for (TextShapeSaver tsS : shapeSaverList) {
              if (tsS.devicePos == holder.getAdapterPosition())
                foundIt = true;
//              Log.wtf("tag", "refilling the shapeSaver");
            }
            if (!foundIt) {
//              Log.wtf("tag", "bigdady size" + bigDaddy.size());
              for (int h = 0; h < bigDaddy.size(); h++) {
//                Log.wtf("tag", "refilling the shapeSaver");
                shapeSaver.add(0);//the list responsible of the values for the time/date switch events
              }
              TextShapeSaver tss = new TextShapeSaver();
              tss.devicePos = holder.getAdapterPosition();
              tss.shapeList = shapeSaver;
              shapeSaverList.add(tss);
            }


            if (bigDaddy.size() > 2)//for testing if the user worth of a gift or not (picking just one device)(tfw you are EA)
              for (int i = 0; i < bigDaddy.size(); i++) {
//                Log.wtf("tag", "number of devices detected under BigDaddy: " + i);
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
                final LinearLayout device = new LinearLayout(this.context);
                device.setOrientation(LinearLayout.HORIZONTAL);
                device.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

//the textViews...
                final TextView leftSide = new TextView(this.context);
                final TextView rightSide = new TextView(this.context);
                final TextView etc = new TextView(this.context);

                leftSide.setTextSize(19);
                rightSide.setTextSize(19);

                leftSide.setSingleLine(true);
                rightSide.setSingleLine(true);

                leftSide.setText(deviceName);
                rightSide.setText(date);

//                leftSide.setBackgroundColor(Color.GREEN);
//                rightSide.setBackgroundColor(Color.YELLOW);

                leftSide.setLayoutParams(new LinearLayout.LayoutParams(recyclerView.getWidth() / 3, ViewGroup.LayoutParams.WRAP_CONTENT));
                leftSide.setClickable(true);
                rightSide.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                rightSide.setGravity(Gravity.RIGHT);
                rightSide.setId(i);
                device.addView(leftSide);

                if (leftSide.length() > 12) {
                  etc.setText("...");
                  etc.setTextSize(19);
                  device.addView(etc);
                }
                device.addView(rightSide);
                devicesHolder.addView(device);

//                Log.wtf("tag", "shapeSaverList size::" + shapeSaverList.size());
                for (TextShapeSaver tss2 : shapeSaverList) {
                  if (tss2.devicePos == holder.getAdapterPosition()) {
                    List l = tss2.shapeList;
                    if (l.get(rightSide.getId()).equals(0)) {
                      rightSide.setText(date);
                    } else {
                      rightSide.setText(time);
                    }
                  }
                }
                if (leftSide.length() > 12)
                  leftSide.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                      switch (event.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_DOWN:
                          Log.wtf("tag", "CLICKED  OR  MAYBE TOUCHED");
//                  if(event.getAction()== MotionEvent.ACTION_DOWN){
                          leftSide.setLayoutParams(new LinearLayout.LayoutParams(recyclerView.getWidth() / 2, ViewGroup.LayoutParams.WRAP_CONTENT));
                          if (device.getChildCount() == 3)
                            etc.setVisibility(View.GONE);
                          rightSide.setVisibility(View.GONE);

                          RecyclerView recView = (RecyclerView) downLvl.getParent().getParent();
                          recView.requestDisallowInterceptTouchEvent(true);
                          return true;
                        case MotionEvent.ACTION_MOVE:
                          leftSide.setLayoutParams(new LinearLayout.LayoutParams(recyclerView.getWidth() / 3, ViewGroup.LayoutParams.WRAP_CONTENT));
                          if (device.getChildCount() == 3)
                            etc.setVisibility(View.VISIBLE);
                          rightSide.setVisibility(View.VISIBLE);
                          return true;
                        case MotionEvent.ACTION_CANCEL:
                          leftSide.setLayoutParams(new LinearLayout.LayoutParams(recyclerView.getWidth() / 3, ViewGroup.LayoutParams.WRAP_CONTENT));
                          if (device.getChildCount() == 3)
                            etc.setVisibility(View.VISIBLE);
                          rightSide.setVisibility(View.VISIBLE);
                          return true;
                        case MotionEvent.ACTION_OUTSIDE:
                          leftSide.setLayoutParams(new LinearLayout.LayoutParams(recyclerView.getWidth() / 3, ViewGroup.LayoutParams.WRAP_CONTENT));
                          if (device.getChildCount() == 3)
                            etc.setVisibility(View.VISIBLE);
                          rightSide.setVisibility(View.VISIBLE);
                          return true;
                        case MotionEvent.ACTION_UP:
                          leftSide.setLayoutParams(new LinearLayout.LayoutParams(recyclerView.getWidth() / 3, ViewGroup.LayoutParams.WRAP_CONTENT));
                          if (device.getChildCount() == 3)
                            etc.setVisibility(View.VISIBLE);
                          rightSide.setVisibility(View.VISIBLE);
                          return true;
                      }

//                  }
                      return false;
                    }
                  });

                final int finalI = i;

                //switching between date and time
                rightSide.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                    for (int h = 0; h < shapeSaverList.size(); h++) {
                      if (shapeSaverList.get(h).devicePos == holder.getAdapterPosition()) {
                        List l = shapeSaverList.get(h).shapeList;
                        if (l.get(rightSide.getId()).equals(0)) {
                          rightSide.setText(time);
                          l.set(rightSide.getId(), 1);
                        } else {
                          rightSide.setText(date);
                          l.set(rightSide.getId(), 0);
                        }
                      }
                    }
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

              nameText.setPadding(0, 2, 0, 1);

              devicesHolder.addView(topText);
              devicesHolder.addView(timeText);
              devicesHolder.addView(dateText);
              devicesHolder.addView(nameText);

            }
            if (bigDaddy.size() == 2) {
              for (int i = 0; i < bigDaddy.size(); i++) {
//                Log.wtf("tag", "number of devices detected under BigDaddy: " + i);
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
                final LinearLayout firstHolder = new LinearLayout(this.context);
                final LinearLayout secondHolder = new LinearLayout(this.context);
                firstHolder.setOrientation(LinearLayout.HORIZONTAL);
                firstHolder.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                firstHolder.setGravity(Gravity.CENTER);
                secondHolder.setOrientation(LinearLayout.HORIZONTAL);
                secondHolder.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                secondHolder.setGravity(Gravity.CENTER);

//the textViews...
                final TextView upperTextView = new TextView(this.context);
                final TextView bottomTextViewL = new TextView(this.context);
                final TextView bottomTextViewR = new TextView(this.context);

                bottomTextViewL.setPadding(0, 0, 30, 0);
                bottomTextViewR.setPadding(30, 0, 0, 0);

                upperTextView.setTextSize(19);
                bottomTextViewL.setTextSize(19);
                bottomTextViewR.setTextSize(19);

                upperTextView.setSingleLine(true);
                bottomTextViewL.setSingleLine(true);
                bottomTextViewR.setSingleLine(true);

                upperTextView.setText(deviceName);
                bottomTextViewL.setText(date);
                bottomTextViewR.setText(time);

//                leftSide.setBackgroundColor(Color.GREEN);
//                rightSide.setBackgroundColor(Color.YELLOW);

//                leftSide.setLayoutParams(new LinearLayout.LayoutParams(recyclerView.getWidth() / 3, ViewGroup.LayoutParams.WRAP_CONTENT));
//                leftSide.setClickable(true);
//                rightSide.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                rightSide.setGravity(Gravity.RIGHT);
//                rightSide.setId(i);
                firstHolder.addView(upperTextView);
                secondHolder.addView(bottomTextViewL);
                secondHolder.addView(bottomTextViewR);

                devicesHolder.addView(firstHolder);
                devicesHolder.addView(secondHolder);
              }
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

            title.setText(wi.getTilte());
          }
        }
      }
      break;
      case "TempHolder": {
        ViewHolderDummy viewHolderDummy = (ViewHolderDummy) holder;
        final ViewGroup lvl1 = (ViewGroup) viewHolderDummy.getvGroup().getChildAt(0);
//        Log.wtf("tag", "what is the class::" + lvl1.getClass());
        lvl1.setBackground(null);
        lvl1.setBackgroundColor(Color.BLACK);
      }
    }


  }


  @Override
  public int getItemCount() {
//    return 0;
    int ss = (this.mListObjects == null) ? 0 : this.mListObjects.size();
//    Log.wtf("tag", "ss:: " + ss);
    return ss;
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
//    Log.wtf("tag", "WE IN  BOYS");
//    Log.wtf("tag", "mListObjects :before:" + mListObjects.size());
    recyclerView.getRecycledViewPool().clear();
    this.mListObjects = mListObjects;
//    Log.wtf("tag", "mListObjects :before:" + mListObjects.size());
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
//    Log.wtf("tag", "WE ARE  UPDATING  BOYS" + mObject.getSelectedDevices());
    this.mListObjects.set(position, mObject);

    this.notifyItemChanged(position);
  }

  public class TextShapeSaver {
    List<Integer> shapeList = new ArrayList<>();
    int devicePos = 0;

  }

  public int getAbsolutePosition(int filthyCasuals) {
    int pos = 1;
    for (int i = 0; i < filthyCasuals; i++) {
      if (mListObjects.get(i).getType() != "Info")
        pos++;
      else
        pos = pos + 2;
    }
    return pos;
  }


}
