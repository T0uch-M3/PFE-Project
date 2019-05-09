package com.example.touch_me.pfe_project;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import com.example.touch_me.pfe_project.widgets.Thermometer;
import com.github.lzyzsd.circleprogress.ArcProgress;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import static java.sql.Types.NULL;

public class CustomPagerAdapter extends PagerAdapter implements ServiceDataReceiver.Receiver {
  private Context mContext;
  private List<RecyclerView> nameList = new ArrayList<>();
  public boolean firstTime;
  public List<WidgetItem> CAT_IMAGE_IDS;
  private int nbrItems;
  private boolean addButtonClicked;
  private int _selected;
  public int holyRow;
  public List<View> recyclerViewList = new ArrayList<>();
  public List<WidgetSettings> settingsList = new ArrayList<>();
  public int currentPosition = 0;
  public CustomAdapter customAdapter;
  public FlexboxLayoutManager flexboxLayoutManager;
  String testString;
//  RecyclerView recyclerView;
//  List<WidgetItem> CAT_IMAGE_IDS = new ArrayList<>();


  public class WidgetSettings {
    boolean firstTime;
    List<WidgetItem> CAT_IMAGE_IDS;
    int nbrItems;
    int _selected;
    int holyRow;
    boolean addButtonClicked;
    int position;
    CustomAdapter customAdapter;
    FlexboxLayoutManager flexboxLayoutManager;

    public WidgetSettings(int position) {
      Log.wtf("tag", "CONSTRUCTOOOORRR");
      firstTime = true;
      CAT_IMAGE_IDS = new ArrayList<>();
      nbrItems = 0;
      addButtonClicked = false;
      holyRow = 0;
      _selected = 0;
      this.position = position;
      customAdapter = new CustomAdapter(mContext, (RecyclerView) recyclerViewList.get(position));
      flexboxLayoutManager = new FlexboxLayoutManager(mContext);
    }
  }

  public CustomPagerAdapter(Context context, List list) {
    mContext = context;
//    this.recyclerView = (RecyclerView) recyclerView;
    this.nameList = list;
  }

  public void manageObjectList(String action, int position, RecyclerView v) {
    switch (action) {
      case "SWIPE": {
        currentPosition = position;

//        manageObjectList("SAVE", currentPosition, null);
//        RecyclerView rv = (RecyclerView) recyclerViewList.get(currentPosition);
//        List s = (List) rv.getTag();
//        Log.wtf("tag", "current position recyclerView tag::" + s.get(0));

        Log.wtf("tag", "swipe position::" + position);
        Log.wtf("tag", "swipe currentPosition::" + currentPosition);
        manageObjectList("APPLY", currentPosition, v);
      }
      break;
      case "NEW": {
        Log.wtf("tag", "new created object in::" + position);
        WidgetSettings ws = new WidgetSettings(position);
        settingsList.add(ws);
//        if (getCount()==1)
//          manageObjectList("APPLY", currentPosition);
//        firstTime = true;
//        CAT_IMAGE_IDS = new ArrayList<>();
//        nbrItems = 0;
//        addButtonClicked = false;
//        holyRow = 0;
//        _selected = 0;
      }
      break;
      case "APPLY": {//run twice, one when creating new page (run for both first and any page is getting created), two when moving to another page to search and apply it's settings
        for (WidgetSettings ws : settingsList) {
          if (ws.position == position) {
            Log.wtf("tag", "APPLY::" + position);
            firstTime = ws.firstTime;
            CAT_IMAGE_IDS = ws.CAT_IMAGE_IDS;
            nbrItems = ws.nbrItems;
            _selected = ws._selected;
            holyRow = ws.holyRow;
            addButtonClicked = ws.addButtonClicked;
            customAdapter = ws.customAdapter;
            flexboxLayoutManager = ws.flexboxLayoutManager;
          }
        }

      }
      break;
      case "SAVE": {
        Log.wtf("tag", "INCOMING  POSITION  VALUE " + position);
//        Log.wtf("tag", "settingsList.get(1) " + settingsList.get(1).position);
        for (int i = 0; i < settingsList.size(); i++) {
          WidgetSettings ws = settingsList.get(i);
//          Log.wtf("tag", "settingsList.get(0) "+settingsList.get(0));
//          Log.wtf("tag", "settingsList.get(1) "+settingsList.get(1));
          Log.wtf("tag", "HOLY ROW  IN  SAVE: " + settingsList.get(i).holyRow+" i  "+i);
          if (ws.position == position) {
            Log.wtf("tag", "***SAVE*** i " + i);
            Log.wtf("tag", "currentPosition " + currentPosition);
            Log.wtf("tag", "ws.position " + ws.position);
//            Log.wtf("tag", "***SAVE***");


            settingsList.get(i).firstTime = firstTime;
//            ws.CAT_IMAGE_IDS.clear();
            Log.wtf("tag", "LIST size in SAVE::" + settingsList.get(i).CAT_IMAGE_IDS.size());
            settingsList.get(i).CAT_IMAGE_IDS = CAT_IMAGE_IDS;
            Log.wtf("tag", "LIST size in SAVE::" + settingsList.get(i).CAT_IMAGE_IDS.size());
            settingsList.get(i).nbrItems = nbrItems;
            settingsList.get(i)._selected = _selected;
            settingsList.get(i).holyRow = holyRow;
            settingsList.get(i).addButtonClicked = addButtonClicked;
            settingsList.get(i).customAdapter = customAdapter;
            settingsList.get(i).flexboxLayoutManager = flexboxLayoutManager;
//            settingsList.set(i,ws);
          }
        }
//        for (WidgetSettings ws : settingsList) {
//          if (ws.position == currentPosition) {
//            Log.wtf("tag", "SAVE::" + currentPosition);
//            Log.wtf("tag", "LIST size in SAVE::" + CAT_IMAGE_IDS.size());
//            ws.firstTime = firstTime;
////            ws.CAT_IMAGE_IDS.clear();
//            ws.CAT_IMAGE_IDS = CAT_IMAGE_IDS;
//            ws.nbrItems = nbrItems;
//            ws._selected = _selected;
//            ws.holyRow = holyRow;
//            ws.addButtonClicked = addButtonClicked;
//
//          }
//        }
      }
      break;
    }

  }

  @Override
  public Object instantiateItem(ViewGroup collection, int position) {
//    firstTime = true;
////    CAT_IMAGE_IDS = new ArrayList<>();
//    nbrItems = 0;
//    addButtonClicked = false;
//    holyRow = 0;
////    _selected = 0;
    final LayoutInflater[] inflater = {LayoutInflater.from(mContext)};
    Log.wtf("tag", "PoSIIIIIIIIIIIITion::" + position);

//    CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];

//    Button btn = new Button(mContext);
//    ViewGroup layout = (ViewGroup) inflater.inflate(customPagerEnum.getLayoutResId(), collection, false);
//    ViewGroup cl = (ViewGroup) recyclerView.getParent();
//    RecyclerView recyclerView = new RecyclerView(mContext);

//    CustomAdapter ca2 = new CustomAdapter(mContext, recyclerView);

//    recyclerView.addView(btn);
//    cl.removeView(recyclerView);


    RecyclerView v = (RecyclerView) recyclerViewList.get(position);
    try {
      ViewGroup vg = (ViewGroup) v.getParent();
//      vg.removeAllViews();
//      if(position==0)
//        v.setBackgroundColor(Color.BLACK);
//      if(position==1)
//      v.setBackgroundColor(Color.GRAY);
//      if(position==2)
//        v.setBackgroundColor(Color.GREEN);
    } catch (Exception e) {
      Log.wtf("tag", "EXXXXXXXXXXXXXXXEPTION");
      e.printStackTrace();
    }


//    collection.addView (v);
//    final RecyclerView recyclerView;
//    final List<WidgetItem> CAT_IMAGE_IDS = new ArrayList<>();
//    recyclerView = new RecyclerView(mContext);
//    v = recyclerView;
/**
 * ADAPTER  NEED  TO  MOOOOOVE
 */
//    customAdapter = new CustomAdapter(mContext,v);


    if (getCount() == 1) {

      Log.wtf("tag", "RUN ONCE WITH FIRST ADD");
      manageObjectList("NEW", position, v);

      manageObjectList("APPLY", position, v);
      flexlayout(customAdapter, v);

    } else {
      manageObjectList("SAVE", currentPosition, v);
          Log.wtf("tag", "CAT sizeeeee:pos 0:" + settingsList.get(0).CAT_IMAGE_IDS.size());
          Log.wtf("tag", "CAT sizeeeee:local: " + currentPosition + " : " + CAT_IMAGE_IDS.size());
      manageObjectList("NEW", position, v);
         Log.wtf("tag", "***************************");
         Log.wtf("tag", "CAT sizeeeee:pos 0:" + settingsList.get(0).CAT_IMAGE_IDS.size());
         Log.wtf("tag", "CAT sizeeeee:pos 1:" + settingsList.get(1).CAT_IMAGE_IDS.size());
         Log.wtf("tag", "CAT sizeeeee:local: " + currentPosition + " : " + CAT_IMAGE_IDS.size());
//
      manageObjectList("APPLY", position, v);
//      Log.wtf("tag", "CAT sizeeeee:3:"+CAT_IMAGE_IDS.size());
      flexlayout(customAdapter, v);
//      manageObjectList("APPLY", currentPosition, v);
          Log.wtf("tag", "***************************");
         Log.wtf("tag", "CAT sizeeeee:pos 0:" + settingsList.get(0).CAT_IMAGE_IDS.size());
         Log.wtf("tag", "CAT sizeeeee:pos 1:" + settingsList.get(1).CAT_IMAGE_IDS.size());
         Log.wtf("tag", "CAT sizeeeee:local: " + currentPosition + " : " + CAT_IMAGE_IDS.size());
    }

    addingWidget(customAdapter, null);
//    Log.wtf("tag", "CAT LIST SIZE::" + CAT_IMAGE_IDS.size());
    v.setTag(CAT_IMAGE_IDS);
    collection.addView(v);


    customAdapter.setCustomObjectListener(new MyCustomObjectListener() {
      @Override
      public void onOptionButtonReady(View v, int position) {
        Log.wtf("tag", "BUTTON PRESSED AT  POSITIoN::" + position);
      }

      @Override
      public void onObjectReady() {
        Log.wtf("tag", "INITIATE LISTENER ONCE???????????");
//        if (!addButtonClicked) {
        genesis(customAdapter);
////          addButtonClicked = true;
//        }

      }

      @Override
      public void notifyForRemove(CustomAdapter ca, WidgetItem wItem, boolean add, int dummiesToDelete) {
//        someMagic(ca, wItem, false, dummiesToDelete);
      }
    });

//    TextView view = new TextView(mContext);
//    view.setText(nameList.get(position));

//    ((ViewPager) collection).addView(recyclerView);
//    return recyclerView;

//    View v = recyclerViewList.get (position);
//    collection.addView (v);

//    manageObjectList("APPLY", position);

    try {
      Log.wtf("tag", "***************************");
      Log.wtf("tag", "CAT sizeeeee:pos 0:" + settingsList.get(0).CAT_IMAGE_IDS.size());
      Log.wtf("tag", "CAT sizeeeee:local: " + currentPosition + " : " + CAT_IMAGE_IDS.size());
      Log.wtf("tag", "CAT sizeeeee:pos 1:" + settingsList.get(1).CAT_IMAGE_IDS.size());

    } catch (Exception e) {
    }

    Log.wtf("tag", "POSITION::" + position);
    Log.wtf("tag", "CURRENT POSITION::" + currentPosition);
    //TODO:Require revision for that is intended to start the app with at least one page initialized
    //Will not enter on first creation
    if (position != currentPosition){
      manageObjectList("SAVE", position, null);
      loadOldValues();
    }



    return v;
  }

  public void loadOldValues() {
    Log.wtf("tag", "***LOADING**");
    manageObjectList("APPLY", currentPosition, null);
    flexlayout(customAdapter, (RecyclerView) recyclerViewList.get(currentPosition));
    Log.wtf("tag", "CAT sizeeeee:pos current:" + settingsList.get(currentPosition).CAT_IMAGE_IDS.size());
    Log.wtf("tag", "CAT sizeeeee:local: " + currentPosition + " : " + CAT_IMAGE_IDS.size());
    Log.wtf("tag", "CAT sizeeeee:pos next:" + settingsList.get(currentPosition+1).CAT_IMAGE_IDS.size());
  }


  public void genesis(CustomAdapter ca) {

    final Dialog dialog = new Dialog(mContext);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(dialog.getContext().getResources().getColor(R.color.transparent)));

    firstDialogLayout(dialog, ca);

    dialog.show();

    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
      @Override
      public void onDismiss(DialogInterface dialog) {
//            addButtonClicked = false;
      }
    });


  }


  public void addingWidget(CustomAdapter ca, WidgetItem wItem) {
    int addingPos = 0;
    boolean foundRightPost = false;
    Log.wtf("tag", "addingWidget ENTERED");
//    ca.i = 0;
    /**********************************/
    ArcProgress cp = new ArcProgress(mContext);
    cp.setBottomText("Type1");
    cp.setProgress(10);
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(180, 180);
    params.gravity = Gravity.CENTER;
    cp.setLayoutParams(params);
    /**********************************/
    WidgetItem item = new WidgetItem(cp, "test123");
//    WidgetItem item2 = new WidgetItem(cp2, "test000");
    /**************THE  BACK  BONE***************/
    if (firstTime) {
//    if(CAT_IMAGE_IDS!=null)
//    if (CAT_IMAGE_IDS.size() == 0) {
      Log.wtf("tag", "ENTERED  THE FIRST TIME >>>>>>>>>>>>>>>>");
      WidgetItem dummy = new WidgetItem();
      dummy.setTilte("dummy69");
      dummy.setType("Dummy");


      WidgetItem btnHolder = new WidgetItem();
      btnHolder.setType("Button");
      btnHolder.setTilte("btnHolder");


      CAT_IMAGE_IDS.add(0, btnHolder);
      CAT_IMAGE_IDS.add(0, dummy);

      ca.setListObjects(CAT_IMAGE_IDS);
      Log.wtf("tag", "SETTING FIRSTTIME TO  FALSE " + firstTime);
      firstTime = false;
      Log.wtf("tag", "SETTING FIRSTTIME TO  FALSE " + firstTime);
    }
    /**********************************/
    else {
      Log.wtf("tag", "ENTERED  THE SECOND TIME >>>>>>>>>>>>>>>>");


      someMagic(ca, wItem, true, NULL);

//      Log.wtf("tag", "number of items::BEFORE LOOP"+nbrItems);
//      for (int i = 0; i < ca.getItemCount(); i++) {
//        Log.wtf("tag", "ITEM TYPE::" + i + " :: " + ca.getObjectList().get(i).getType());
//        if (ca.getObjectList().get(i).isWidget())
//          nbrItems++;
////          addingPos = i - 1;
////        foundRightPost = true;
//      }
//      Log.wtf("tag", "number of items::AFTER LOOP"+nbrItems);
//      if(addingPos<0)
//      CAT_IMAGE_IDS.add(ca.getItemCount()-2, wItem);
//      else
//        CAT_IMAGE_IDS.add(addingPos, wItem);
//      ca.setListObjects(CAT_IMAGE_IDS);


      Log.wtf("tag", "number of items::BEFORE ADDING  ITEMS" + nbrItems);
      CAT_IMAGE_IDS.add(nbrItems, wItem);
      ca.setListObjects(CAT_IMAGE_IDS);
      Log.wtf("tag", "CAT SIZEEE::" + CAT_IMAGE_IDS.size());


/*********************************************************************************/
//      if (testInt == 0) {
//        Log.wtf("tag", "number of items::" + nbrItems);
//        CAT_IMAGE_IDS.add(0, wItem);
//        ca.setListObjects(CAT_IMAGE_IDS);
//      }
//      if (testInt == 1) {
//        Log.wtf("tag", "number of items::" + nbrItems);
//
//        CAT_IMAGE_IDS.add(1, wItem);
//        ca.setListObjects(CAT_IMAGE_IDS);
//      }
//      if (testInt == 2) {
//        Log.wtf("tag", "number of items::" + nbrItems);
//
//        CAT_IMAGE_IDS.add(3, wItem);
//        ca.setListObjects(CAT_IMAGE_IDS);
//      }
//      if (testInt == 3) {/*************/
//        Log.wtf("tag", "number of items::" + nbrItems);
//
//        CAT_IMAGE_IDS.add(4, wItem);
//        ca.setListObjects(CAT_IMAGE_IDS);
//
//      }
//      testInt++;
//      if (testInt == 4) {
//        CAT_IMAGE_IDS.remove(CAT_IMAGE_IDS.size() - 2);
//        ca.notifyItemRemoved(CAT_IMAGE_IDS.size() - 2);
//        recyclerView.getRecycledViewPool().clear();
//
//        CAT_IMAGE_IDS.add(0, wItem);
//        ca.notifyItemInserted(0);
//      }
//      if (testInt == 5) {
//        CAT_IMAGE_IDS.remove(CAT_IMAGE_IDS.size() - 2);
//        ca.notifyItemRemoved(CAT_IMAGE_IDS.size() - 2);
//        recyclerView.getRecycledViewPool().clear();
//
//        CAT_IMAGE_IDS.add(0, wItem);
//        ca.notifyItemInserted(0);
//      }
//      if (testInt == 6) {
//        CAT_IMAGE_IDS.add(0, wItem);
//        ca.notifyItemInserted(0);
//      }
//      if (testInt == 7) {
//        CAT_IMAGE_IDS.add(0, wItem);
//        ca.notifyItemInserted(0);
//      }
//      testInt++;


//      Collections.reverse(CAT_IMAGE_IDS);
//      ca.notifyItemInserted(CAT_IMAGE_IDS.size()-2);
//      ca.notifyDataSetChanged();
    }

    addButtonClicked = false;

  }

  public void someMagic(CustomAdapter ca, WidgetItem wItem, boolean add, int dummies2Delete) {
    int nbrDummy = 0;
    int addPos = 0;
    nbrItems = 0;
    int weSayJump = 0;
    int foundDummy = 0;

    if (!add) {//in the case of removal
      Log.wtf("tag", "IN CASE  OF  REMOVAL");
      WidgetItem dummy = new WidgetItem();
      dummy.setType("Dummy");
      CAT_IMAGE_IDS.add(ca.getItemCount() - 2, dummy);
      ca.setListObjects(CAT_IMAGE_IDS);

      for (int i = 0; i < ca.getItemCount(); i++) {
        if (ca.getObjectList().get(i).getType() == "Dummy")
          nbrDummy++;
      }
      if (nbrDummy > 3) {
        for (int i = 0; i < 3; i++) {
          ca.removeOneObject(ca.getItemCount() - 2);
        }
      }


      if (wItem.getSize() == 2) {
        holyRow = holyRow - 2;
      }
      if (wItem.getSize() == 1) {
        holyRow--;
      }


    }
    if (add) {

      for (int i = 0; i < ca.getItemCount(); i++) {
        Log.wtf("tag", ":::size inside loop::" + ca.getItemCount());

        if (ca.getObjectList().get(i).getType() == "Dummy")
          nbrDummy++;

        if (ca.getObjectList().get(i).isWidget() && ca.getObjectList().get(i).getSize() == 1)
          nbrItems++;
        if (ca.getObjectList().get(i).getSize() == 2)
          nbrItems++;
        if (ca.getObjectList().get(i).getType() == "TempHolder")
          nbrItems++;
      }
      Log.wtf("tag", "HOLY  ROW:" + holyRow);

      if (holyRow == 2 && wItem.getSize() == 2) {
        WidgetItem tempHolder = new WidgetItem();
        tempHolder.setType("TempHolder");
        CAT_IMAGE_IDS.add(nbrItems, tempHolder);
        ca.setListObjects(CAT_IMAGE_IDS);
        nbrItems++;
      }


//    Log.wtf("tag", "number oooooof ITEMS:BEFORE  CHANGE:" + nbrItems);

//    if(size == 2 )//in the case of large widget
//      nbrItems++;

//    if (nbrItems % 3 == 0) {
      if (holyRow == 0 || holyRow == 3) {

//        for (int i = 0; i < (2 - Math.sin(Math.PI / wItem.getSize())); i++) {
        for (int i = 0; i < 3; i++) {
          WidgetItem dummy = new WidgetItem();
          dummy.setType("Dummy");
          Log.wtf("tag", "ADDDDDDDDDDDDDING DUMMIES");
          dummy.setTilte("dummy" + i + nbrItems);
          CAT_IMAGE_IDS.add(ca.getItemCount() - 2, dummy);
          ca.setListObjects(CAT_IMAGE_IDS);
        }
      }


      if (wItem.getSize() == 2 && (holyRow == 0 || holyRow == 1 || holyRow == 3)) {
        for (int i = 0; foundDummy != 2; i++) {
          if (ca.getObjectList().get(i).getType() == "Dummy") {
            Log.wtf("tag", "000000000DUMMY  REMOVED YEY AT::" + i);
            ca.removeOneObject(ca.getItemCount() - 2);
            foundDummy++;
          }
        }
      }
      if (wItem.getSize() == 1) {
        for (int i = 0; foundDummy != 1; i++) {
          if (ca.getObjectList().get(i).getType() == "Dummy") {
            Log.wtf("tag", "000000000DUMMY  REMOVED YEY AT::" + i);
            ca.removeOneObject(ca.getItemCount() - 2);
            foundDummy++;
          }
        }
      }
      /**
       * BEHOLD..
       */
      if (wItem.getSize() == 2) {
        holyRow = holyRow + 2;
      }

      if (wItem.getSize() == 1)
        holyRow++;
      /**
       *
       */

      if (holyRow > 3) {
        holyRow = wItem.getSize();

      }
    }
//    if ((nbrDummy == 3) || (nbrDummy == 2) || nbrDummy == 1)
////      for (int h = 0; h < size; h++) {
//        for (int i = 0; !foundDummy; i++) {
//          if (ca.getObjectList().get(i).getType() == "Dummy") {
//            Log.wtf("tag", "000000000DUMMY  REMOVED YEY AT::" + i);
//            ca.removeOneObject(ca.getItemCount() - 2);
//            foundDummy = true;
////          }
//
//        }

//      }
//    nbrItems = 0;
//    for (int i = 0; i < ca.getItemCount(); i++) {
//
//      if (ca.getObjectList().get(i).isWidget())
//        nbrItems++;
//    }

//    Log.wtf("tag", "number ooooof dummy:AFTER  CHANGE:" + nbrDummy);
  }


  public void firstDialogLayout(final Dialog dialog, final CustomAdapter ca) {
    final boolean[] exitLoop = {false};
    dialog.setContentView(R.layout.popup_body);

    Thermometer th = dialog.findViewById(R.id.thermo);
    final CardView card1 = dialog.findViewById(R.id.card1);
    final CardView card2 = dialog.findViewById(R.id.card2);
    final CardView card3 = dialog.findViewById(R.id.card3);
    final CardView card4 = dialog.findViewById(R.id.card4);
    PushDownAnim.setPushDownAnimTo(card1, card2, card3, card4);
    View.OnClickListener clickAble = new View.OnClickListener() {
      @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
      @Override
      public void onClick(View v) {
        if (v.equals(card1)) {
          _selected = 1;
//          dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
          dialog.setContentView(R.layout.popup_body2);
          secondDialogLayout(dialog, ca);
//              firebaseStaticConfig();//TODO:ACTIVE  THIS
        }

        if (v.equals(card2)) {
          _selected = 2;
          dialog.setContentView(R.layout.popup_body2);
          secondDialogLayout(dialog, ca);
        }
        if (v.equals(card3)) {
          _selected = 3;
//          dialog.setContentView(R.layout.popup_body2);
          graphDialogLayout(dialog, ca);
        }

        if (v.equals(card4)) {
//              _selected[0] = 4;
//              if (!startOffline) {//if the "startOffline" field in the settings is marked as !true (false) which is mean start in online mode
//                Log.wtf("tag", "Fetching data from online database");
//                if (devicesNnT != null && devicesNnT.size() > 0) {
//                  dialog.setContentView(R.layout.popup_body_info);
//                  infoDialogLayout(dialog, ca);
//                } else {
//                  final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//                  Runnable runnable = new Runnable() {
//                    @Override
//                    public void run() {
//                      Log.wtf("tag", "entered while loop once");
////              if (!exitLoop[0]) {
//                      Log.wtf("tag", "DEIVCE NUMBER**" + devicesNumber);
//                      Log.wtf("tag", "DEVICE NAME  SIZE**" + devicesNnT.size());
//                      if (devicesNumber == devicesNnT.size() && devicesNumber > 0) {
//                        Log.wtf("tag", "condition check is true");
//
//                        Runnable r = new Runnable() {
//                          @Override
//                          public void run() {
//                            dialog.setContentView(R.layout.popup_body_info);
//                            infoDialogLayout(dialog, ca);
//                          }
//                        };
//                        new Handler(Looper.getMainLooper()).post(r);
//                        scheduler.shutdown();
//                      }
//                    }
//                  };
//                  scheduler.scheduleAtFixedRate(runnable, 2, 2, TimeUnit.SECONDS);
//                }
//
//
//              } else {
//                //If an offline data reading already been done than there will be 0 waiting time
//                Log.wtf("tag", "Fetching data from offline database");
//                if (devicesNnT != null && devicesNnT.size() > 0) {
//                  dialog.setContentView(R.layout.popup_body_info);
//                  infoDialogLayout(dialog, ca);
//                } else {
//                  Intent networkIntent = new Intent(mContext, FirebasePullService.class);
//                  Bundle b = new Bundle();
//                  b.putParcelable("offlineMode", mReceiver);
//                  b.putString("identifier", "deviceList");
//                  networkIntent.putExtras(b);
//                  mContext.startService(networkIntent);
//
//                  final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//                  Runnable runnable = new Runnable() {
//                    @Override
//                    public void run() {
//                      if (devicesNnT != null && devicesNnT.size() > 0) {
//                        Runnable r = new Runnable() {
//                          @Override
//                          public void run() {
//                            dialog.setContentView(R.layout.popup_body_info);
//                            infoDialogLayout(dialog, ca);
//                          }
//                        };
//                        new Handler(Looper.getMainLooper()).post(r);
//                        scheduler.shutdown();
//                      }
//                    }
//                  };
//                  scheduler.scheduleAtFixedRate(runnable, 3, 1, TimeUnit.SECONDS);
//                }
//              }
        }

      }
    };
    card1.setOnClickListener(clickAble);
    card2.setOnClickListener(clickAble);
    card3.setOnClickListener(clickAble);
    card4.setOnClickListener(clickAble);

    th.noAnimation();
  }

  public void graphDialogLayout(final Dialog dialog, final CustomAdapter ca) {
    WidgetItem wi = new WidgetItem();
    wi.setType("Graph");
    wi.isWidget(true);
    wi.setSize(6);

    addingWidget(ca, wi);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public void infoDialogLayout(final Dialog dialog, final CustomAdapter ca) {
//        checkedDevices.clear();
    final boolean[] foundIt = {false};
    ImageButton btnBack = (ImageButton) dialog.findViewById(R.id.btnBack);
    final Button btn_add = dialog.findViewById(R.id.btn_add);
    final EditText title = dialog.findViewById(R.id.title);
    btn_add.setEnabled(false);//Enable it only when there's some checkboxes selected in the vicinity ;)
    LinearLayout boxHolder = dialog.findViewById(R.id.box_holder);
    btnBack.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        dialog.setContentView(R.layout.popup_body);
        firstDialogLayout(dialog, ca);
      }
    });


//        for (List deviceList : devicesNnT) {
    final CheckBox name = new CheckBox(mContext);

//          name.setTextColor(getResources().getColor(R.color.lightBrown));
//          int states[][] = {{android.R.attr.state_checked}, {}};
//          int colors[] = {getResources().getColor(R.color.lightBrown), Color.GRAY};
//          name.setButtonTintList(new ColorStateList(states, colors));
//
//          name.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
//          name.setText(deviceList.get(0).toString());
//          boxHolder.addView(name);
//          View v = new View(this);
//          LinearLayout ln = new LinearLayout(this);
//          ln.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, 5));
//          ln.setBackgroundColor(Color.GRAY);
//          v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 5));
//          boxHolder.addView(ln);
//
    name.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

//              if (isChecked) {
//                checkedDevices.add((String) name.getText());
//                Log.wtf("tag", "checked devices name::" + name.getText());
//              }
//              if (!isChecked)
//                for (int i = 0; i < checkedDevices.size(); i++) {
//                  if (checkedDevices.get(i).equals(name.getText()))
//                    checkedDevices.remove(i);
//                }
//              if (checkedDevices.size() > 0/* && title.getText().length() > 0*/)
//                btn_add.setEnabled(true);
//              else
//                btn_add.setEnabled(false);
      }
    });
//        }

    btn_add.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Boolean found = false;
        WidgetItem wi = new WidgetItem();
        wi.setType("Info");
        wi.isWidget(true);
        wi.setSize(2);
        wi.setTilte(title.getText().toString());

        List<List<String>> temp = new ArrayList<>();

//            for (List list : devicesNnT) {
//              found = false;
//              for (int j = 0; j < checkedDevices.size(); j++) {
//                if (list.get(0).equals(checkedDevices.get(j))) {
//                  found = true;
//                }
//                if (!list.get(0).equals(checkedDevices.get(j)) && found == false) {
//                  found = false;
//                }
//              }
//              if (!found)
//                Log.wtf("tag", "this should be removed::" + list.get(0));
//              else
//                temp.add(list);
//            }
        wi.setSelectedDevices(temp);
        wi.setSelectedDevicesNumber((long) temp.size());
//        }
        addingWidget(ca, wi);
//        Log.wtf("tag", "checked devices list::" + checkedDevices);
      }
    });

  }

  //This method should take care of trimming the first list to look like the second list and return it back
  public List removeUnnecessaryData(List<String> theSmallList) {
    Boolean found = false;
    List<List<String>> temp = new ArrayList<>();

//        for (List list : devicesNnT) {
//          found = false;
//          for (int j = 0; j < theSmallList.size(); j++) {
//            if (list.get(0).equals(theSmallList.get(j))) {
//              found = true;
////              Log.wtf("tag", "::FOUND ==TRUE::");
//            }
//            if (!list.get(0).equals(theSmallList.get(j)) && found == false) {
////              Log.wtf("tag", "::FOUND ==FALSE::");
//              found = false;
//            }
//          }
////          Log.wtf("tag", "there's something utterly wrong here::");
//          if (!found)
//            Log.wtf("tag", "this should be removed::" + list.get(0));//but no, this will stay and add the missing value to a new list "else" bellow
//          else
//            temp.add(list);
//        }
    return temp;
  }

  public void secondDialogLayout(final Dialog dialog, final CustomAdapter ca) {
    final WidgetItem[] wi = new WidgetItem[1];
    final String[] displayValue = new String[1];
    final String[][] vals1 = {{""}};
    String[] vals2 = {""};
    final String[] TPHB = new String[]{"Temperature", "Pressure", "Humidity", "Battery"};
    final String[] SWR = new String[]{"Speed", "Wind", "Rain", "??!!?!"};
    final String[] endDevies = new String[]{"END_DEVICE1", "END_DEVICE2", "END_DEVICE3", "??!!?!"};
    final NumberPicker picker = dialog.findViewById(R.id.picker1);
    NumberPicker picker2 = dialog.findViewById(R.id.picker2);

    final Handler handler = new Handler();
    final Button btnAdd = (Button) dialog.findViewById(R.id.btn_add);
    final EditText editText = (EditText) dialog.findViewById(R.id.editText);
    ImageButton btnBack = (ImageButton) dialog.findViewById(R.id.btnBack);

    btnBack.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        dialog.setContentView(R.layout.popup_body);
        firstDialogLayout(dialog, ca);
      }
    });

    picker.setMaxValue(3);
    picker.setMinValue(0);
    picker2.setMaxValue(3);
    picker2.setMinValue(0);

    switch (_selected) {
      case 1: {
        Log.wtf("tag", "1111111111&");
        picker.setWrapSelectorWheel(true);
        vals1[0] = SWR;
        vals2 = endDevies;
      }
      break;
      case 2: {
        Log.wtf("tag", "2222222222222");
        vals1[0] = TPHB;
        vals2 = endDevies;
      }

    }
//Log.wtf("tag","");
    picker.setDisplayedValues(vals1[0]);
    picker2.setDisplayedValues(vals2);
    final String[] finalVals = vals2;

    picker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
      @Override
      public void onValueChange(final NumberPicker picker2, int oldVal, final int newVal) {
        Log.wtf("tag", "picked::" + finalVals[newVal]);

        Runnable spinn = new Runnable() {
          @Override
          public void run() {
            if (finalVals[newVal] == "END_DEVICE1")
              picker.setDisplayedValues(TPHB);
            if (finalVals[newVal] == "END_DEVICE2")
              picker.setDisplayedValues(SWR);
            Log.wtf("tag", "testCounterRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR ");
          }
        };

        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(spinn, 1000);
//        }
      }
    });

    btnAdd.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        switch (_selected) {
          case 1: {
            Thermometer thermo = new Thermometer(mContext);
            thermo.setThermometerColor(Color.rgb(167, 156, 147));
            thermo.setInnerPaint(Color.rgb(30, 144, 255));
            thermo.setScaleX(.9f);
            thermo.setScaleY(.7f);

            thermo.setOuterLinePaint(Color.rgb(235, 235, 235));

//            thermo.noAnimation();
            wi[0] = new WidgetItem(thermo, editText.getText().toString());
//            wi[0].setWidgetType("Thermometer");
            wi[0].setType("Widget_T");
            wi[0].isWidget(true);

          }
          break;
          case 2: {
            ArcProgress cp = new ArcProgress(mContext);
            cp.setBottomText("Type1");
            cp.setProgress(10);
            cp.setUnfinishedStrokeColor(Color.rgb(167, 156, 147));//gray
            cp.setFinishedStrokeColor(Color.rgb(30, 144, 255));//blue
            cp.setTextColor(Color.rgb(30, 144, 255));//blue
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(180, 180);
            params.gravity = Gravity.CENTER;
            cp.setLayoutParams(params);
            wi[0] = new WidgetItem(cp, editText.getText().toString());
//            wi[0].setWidgetType("ArcProgress");
            wi[0].setType("Widget_G");
            wi[0].isWidget(true);
          }

        }


        wi[0].setDisplayValue("Temperature");//temp or  pressure or humidity....
        addingWidget(ca, wi[0]);
      }
    });
  }


  //***************************OLD ERA*********************

  public void flexlayout(CustomAdapter ca2, RecyclerView recyclerView) {
//    flexboxLayoutManager = new FlexboxLayoutManager(mContext);
    flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
    flexboxLayoutManager.setAlignItems(AlignItems.STRETCH);
    flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
    flexboxLayoutManager.setMaxLine(20);
//    flexboxLayoutManager.setJustifyContent(JustifyContent.CENTER);
    recyclerView.setLayoutManager(flexboxLayoutManager);
//    recyclerView.setHasFixedSize(true);
//    catAdapter = ca;
    /*for performance??????*/
    recyclerView.setItemViewCacheSize(20);
    recyclerView.setDrawingCacheEnabled(true);
    recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    if (!ca2.hasObservers())
      ca2.setHasStableIds(true);
    /*for performance*/
    recyclerView.setAdapter(ca2);
//    Log.wtf("tag","adapter sizzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzze::::"+recyclerView.getChildCount());

//    ca.notifyDataSetChanged();
  }


  @Override
  public void destroyItem(ViewGroup collection, int position, Object view) {
    Log.wtf("tag", "REMOOOOOOOOOOOOOOOOOOOOOOOOOOOOVE  IT  ");
//    ((ViewPager) collection).removeView((View) view);
    collection.removeView(recyclerViewList.get(position));

  }

  @Override
  public int getCount() {
    return recyclerViewList.size();
  }

  @Override
  public int getItemPosition(Object object) {
//    return POSITION_NONE;
    int index = recyclerViewList.indexOf(object);
    if (index == -1)
      return POSITION_NONE;
    else
      return index;
  }

  @Override
  public boolean isViewFromObject(View view, Object object) {
//    return view == object;
    return view == object;
  }

  @Override
  public CharSequence getPageTitle(int position) {
//    CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
    return "hohoHAHA";
  }

  @Override
  public void onReceiveResult(int resultCode, Bundle resultData) {

  }

  @Override
  public Parcelable saveState() {
    return null;
  }

  @Override
  public void restoreState(Parcelable state, ClassLoader loader) {

  }

  public int addView(View v) {
    return addView(v, recyclerViewList.size());
  }

  public int addView(View v, int position) {
    recyclerViewList.add(position, v);
    return position;
  }
//  @Override
//  public void finishUpdate(ViewGroup container) {
//    super.finishUpdate(container);
//    this.notifyDataSetChanged();
//  }


}
