package com.example.touch_me.pfe_project;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.touch_me.pfe_project.widgets.Thermometer;
import com.github.lzyzsd.circleprogress.ArcProgress;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.material.tabs.TabLayout;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.ArcMotion;
import androidx.transition.ChangeBounds;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static java.sql.Types.NULL;

public class CustomPagerAdapter extends PagerAdapter implements ServiceDataReceiver.Receiver {
  private Context mContext;
  public List<String> nameList = new ArrayList<>();
  public boolean firstTime;
  public List<WidgetItem> CAT_IMAGE_IDS;
  private int nbrItems;
  private boolean addButtonClicked = false;
  private int _selected;
  public int holyRow;
  public List<View> recyclerViewList = new ArrayList<>();
  public List<WidgetSettings> settingsList = new ArrayList<>();
  public int currentPosition = 0;
  public CustomAdapter customAdapter;
  public FlexboxLayoutManager flexboxLayoutManager;
  ViewPager viewPager;
  TabLayout tabLayout;
  String pageTitle = "";
  int posToRemove = 0;
  boolean sameAsBefore = false;
  List<String> oldList = new ArrayList<>();
  List<Devices> devicesList = new ArrayList<>();
  boolean fetchFromLocal = false;

  /******to be fetched maybe********/
  Boolean startOffline = true;
  List<List<String>> devicesNnT = new ArrayList<>();
  int devicesNumber = 0;
  public ServiceDataReceiver mReceiver;
  List<String> checkedDevices = new ArrayList<>();


  public CustomPagerAdapter(Context context, ViewPager viewPager, TabLayout tabLayout) {
    mContext = context;
//    this.recyclerView = (RecyclerView) recyclerView;

    this.viewPager = viewPager;
    this.tabLayout = tabLayout;
  }

  public void manageObjectList(String action, int position, RecyclerView v) {
    switch (action) {
      case "SWIPE": {
        currentPosition = position;

        Log.wtf("tag", "swipe position::" + position);
        Log.wtf("tag", "swipe currentPosition::" + currentPosition);
        manageObjectList("APPLY", currentPosition, v);

        notifyAdapter();


      }
      break;
      case "NEW": {
        Log.wtf("tag", "NEW::" + position);
//        Log.wtf("tag", "new created object in::" + position);
        WidgetSettings ws = new WidgetSettings(position);
        settingsList.add(ws);
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
//            addButtonClicked = ws.addButtonClicked;
            customAdapter = ws.customAdapter;
            flexboxLayoutManager = ws.flexboxLayoutManager;
            pageTitle = ws.pageTitle;
          }
        }

      }
      break;
      case "SAVE": {
        Log.wtf("tag", "SAVE::" + position);
        for (int i = 0; i < settingsList.size(); i++) {
          WidgetSettings ws = settingsList.get(i);
          if (ws.position == position) {

            settingsList.get(i).firstTime = firstTime;
            settingsList.get(i).CAT_IMAGE_IDS = CAT_IMAGE_IDS;
            settingsList.get(i).nbrItems = nbrItems;
            settingsList.get(i)._selected = _selected;
            settingsList.get(i).holyRow = holyRow;
//            settingsList.get(i).addButtonClicked = addButtonClicked;
            settingsList.get(i).customAdapter = customAdapter;
            settingsList.get(i).flexboxLayoutManager = flexboxLayoutManager;
//            settingsList.set(i,ws);
            settingsList.get(i).pageTitle = pageTitle;
          }
        }
      }
      break;
    }

  }


  @Override
  public Object instantiateItem(ViewGroup collection, int position) {
//    Log.wtf("tag", "entered INSTANTIATEITEM");
    RecyclerView v = (RecyclerView) recyclerViewList.get(position);
    try {
      ViewGroup vg = (ViewGroup) v.getParent();
    } catch (Exception e) {
      Log.wtf("tag", "EXXXXXXXXXXXXXXXEPTION");
      e.printStackTrace();
    }

    //This is intended to run only one time (on first page addition)
    if (getCount() == 1) {
//      Log.wtf("tag", "getCount() == 1");
      manageObjectList("NEW", position, v);
      manageObjectList("APPLY", position, v);
      flexlayout(customAdapter, v);

      //Setting up the listener between this adapter and the main activity (for firebase stuff)
      WelcomeScreen ws = (WelcomeScreen) mContext;
      ws.removeCustomObjectListener();
      ws.setCustomObjectListener(new MyCustomObjectListener() {
        @Override
        public void onOptionButtonReady(View v, int position) {

        }

        @Override
        public void onObjectReady() {

        }

        @Override
        public void notifyForRemove(CustomAdapter ca, WidgetItem wItem, boolean add, int dummiesToDelete) {
          Log.wtf("tag", "REMOVE YEAHHHH");
        }

        @Override
        public void sendDataList(List list) {

        }

        @Override
        public void sendDataListX2(List<List<String>> list) {
          devicesNnT = list;
        }

        @Override
        public void sendObjectData(List<Devices> list, int similarityCounter) {
          Log.wtf("tag", "**/////*****///////sendObjectData");
////          for (Devices deviceFromList : list) {
          List<String> nameList = new ArrayList<>();
          List<String> timeList = new ArrayList<>();


          devicesList = list;
          fillDeviceNnT();

          /***************************
           * Fetch from local
           */

//          fillDeviceNnT();
//          if (list != null && list.size() > 0)
//            for (Devices device : list) {
//              List list2 = new ArrayList();
//              list2.add(device.getDeviceName());
//              list2.add(device.getTime());
//              devicesNnT.add(list2);
//            }
//            Log.wtf("tag","deviceNnT:: "+devicesNnT.size());


//          if (!fetchFromLocal) {
//            Log.wtf("tag", "run the fetch test");
//            fillDeviceNnT();
//            fetchFromLocal = true;
//          }

//          for (Devices device : list)
//            Log.wtf("tag", "device list time " + device.getTime());

//          for (Devices device : list) {
//            List<String> nNT = new ArrayList<>();
//            nNT.add(device.getDeviceName());
//            nNT.add(device.getTime());
//            devicesNnT.add(nNT);
//          }

//          for (int j = 0; j < list.size(); j++) {
//
//            nameList.add(0, list.get(j).getDeviceName());
//            timeList.add(1, list.get(j).getTime());

          notifyAdapter();
          /****************************************************************/
//            try {
//              for (int i = 0; i < customAdapter.getObjectList().size(); i++) {
//                if (customAdapter.getObjectList().get(i).getType() == "Info") {
//                  WidgetItem wi = customAdapter.getObjectList().get(i);
////                  Log.wtf("tag", "this item:: " + i + " selected devices:: " + wi.getSelectedDevices());
////                  Log.wtf("tag", "this item:: " + i + " selected devices:: " + wi.getSelectedDevicesNumber());
//
//                  List<String> newNnTList = new ArrayList<>();
//
//                  for (List list2 : wi.getSelectedDevices()) {//fill newNnTList with the names of the checked devices
//                    newNnTList.add(list2.get(0).toString());
//                  }
////                  Log.wtf("tag", "new newNnTList" + removeUnnecessaryData(newNnTList));
////                    removeUnnecessaryData(nameList);
//                  wi.setSelectedDevices(removeUnnecessaryData(newNnTList));
//                  wi.setSelectedDevicesNumber((long) newNnTList.size());
////                    wi.setSelectedDevicesNumber((Long.valueOf(String.valueOf(checkedDevices.size()))));
//                  customAdapter.updateOneObject(i, wi);
//                  try {
//
////                      Log.wtf("tag", "WHAT  We Got iNSIde::" + wi.getSelectedDevices());
//                  } catch (IndexOutOfBoundsException e) {
//                    Log.wtf("tag", "SOMETHING NOT RIGHT!!!");
//                  }
//                }
//              }
//            } catch (Exception e) {
//
//            }
          /*****************************************************************/
//
//          }
//          devicesNnT.clear();

//          newList = timeList;
//          for (int i = 0; i < list.size(); i++) {
//            Devices device = list.get(i);
////            nameList.add(device.getDeviceName());
//            timeList.add(device.getTime());
//          }
//          if(oldList.size()>0){
//            for(String oldDate : oldList){
//              for(String newDate : timeList){
//                if(oldDate.equals(newDate))
//                  similarityCounter++;
//              }
//            }
//          }
//          oldList=timeList;


//          devicesNnT.add(listQuery);
//          for (String name : nameList)
//            Log.wtf("tag", "devicesTime>>>:: " + timeList);
////          for (String time : timeList)
          Log.wtf("tag", "executed>>>:: ");
          Log.wtf("tag", "************************* ");
        }
      });
    } else {
      manageObjectList("SAVE", currentPosition, v);
      manageObjectList("NEW", position, v);
      manageObjectList("APPLY", position, v);
      flexlayout(customAdapter, v);
    }

    addingWidget(customAdapter, null);
//    v.setTag(CAT_IMAGE_IDS);
    collection.addView(v);

//TODO: uncomment below
    /**
     * Listener between the recyclerView adapter and this adapter
     */
    customAdapter.setCustomObjectListener(new MyCustomObjectListener() {
      @Override
      public void onOptionButtonReady(View v, int position) {
        Log.wtf("tag", "BUTTON PRESSED AT  POSITIoN::" + position);
      }

      @Override
      public void onObjectReady() {
        Log.wtf("tag", "INITIATE LISTENER ONCE???????????");
        if (!addButtonClicked) {
          genesis(customAdapter);
          addButtonClicked = true;
        }

      }

      @Override
      public void notifyForRemove(CustomAdapter ca, WidgetItem wItem, boolean add, int dummiesToDelete) {
        Log.wtf("tag", "NOTIFIY  FOR REMOVE  DETEDCTED::");
        someMagic(ca, wItem, add, dummiesToDelete);
      }

      @Override
      public void sendDataList(List list) {
        return;
      }

      @Override
      public void sendDataListX2(List<List<String>> list) {
        return;
      }

      @Override
      public void sendObjectData(List<Devices> list, int counter) {

      }
    });


    //TODO:Require revision for that is intended to start the app with at least one page initialized
    //Will not enter on first creation
//    Log.wtf("tag", "***position** " + position);
//    Log.wtf("tag", "***curent postion** " + currentPosition);
    if (position != currentPosition) {
      manageObjectList("SAVE", position, null);
      loadOldValues();
    }


    return v;
  }

  public void loadOldValues() {
//    Log.wtf("tag", "***LOADING**");

    manageObjectList("APPLY", currentPosition, null);
//    flexlayout(customAdapter, (RecyclerView) recyclerViewList.get(currentPosition));
  }

  public void notifyAdapter() {
    try {
      for (int i = 0; i < customAdapter.getObjectList().size(); i++) {
        if (customAdapter.getObjectList().get(i).getType() == "Info") {
          WidgetItem wi = customAdapter.getObjectList().get(i);
//                  Log.wtf("tag", "this item:: " + i + " selected devices:: " + wi.getSelectedDevices());
//                  Log.wtf("tag", "this item:: " + i + " selected devices:: " + wi.getSelectedDevicesNumber());

          List<String> newNnTList = new ArrayList<>();

          for (List list2 : wi.getSelectedDevices()) {//fill newNnTList with the names of the checked devices
            newNnTList.add(list2.get(0).toString());
          }
//                  Log.wtf("tag", "new newNnTList" + removeUnnecessaryData(newNnTList));
//                    removeUnnecessaryData(nameList);
          wi.setSelectedDevices(removeUnnecessaryData(newNnTList));
          wi.setSelectedDevicesNumber((long) newNnTList.size());
//                    wi.setSelectedDevicesNumber((Long.valueOf(String.valueOf(checkedDevices.size()))));
          customAdapter.updateOneObject(i, wi);
          try {

//                      Log.wtf("tag", "WHAT  We Got iNSIde::" + wi.getSelectedDevices());
          } catch (IndexOutOfBoundsException e) {
            Log.wtf("tag", "SOMETHING NOT RIGHT!!!");
          }
        }
      }
    } catch (Exception e) {

    }
  }


  public void addingWidgetFromOutside() {
    Log.wtf("tag", "viewpager size:size:" + viewPager.getChildCount());
    if (!sameAsBefore) {
      for (int i = 0; i < viewPager.getChildCount(); i++) {
        Log.wtf("tag", "viewpager:: " + i + " " + viewPager.getChildAt(i).getClass());
      }
      RecyclerView rv = (RecyclerView) viewPager.getChildAt(1);
//      viewPager.removeView(rv);
      rv.setVisibility(View.VISIBLE);
      ViewPager.LayoutParams params = new ViewPager.LayoutParams();
      params.height = 100;
      params.width = 100;
      params.gravity = Gravity.CENTER;
      rv.setLayoutParams(params);
      sameAsBefore = true;
    } else {
      viewPager.getAdapter();

//      WidgetItem dummy = new WidgetItem();
//      dummy.setType("Dummy");
//
//      CAT_IMAGE_IDS.add(0, dummy);
//
//      customAdapter.setListObjects(CAT_IMAGE_IDS);
    }

//    tabLayout.getTabAt(0).


    Log.wtf("tag", "recyclerListView:size:" + recyclerViewList.size());
    Log.wtf("tag", "CAT_IMAGE_IDS:size:" + CAT_IMAGE_IDS.size());
    Log.wtf("tag", "settingsList:size:" + settingsList.size());
//    Log.wtf("tag", "viewpager size:size:" + viewPager.getChildCount());
//    Log.wtf("tag", "flexboxLayoutManager.getFlexItemCount()::" + flexboxLayoutManager.getFlexItemCount());
  }

  public void fillDeviceNnT() {
    Log.wtf("tag", "fill deviceNnT:: ");
    devicesNnT.clear();
    for (Devices device : devicesList) {
      List list = new ArrayList();
      list.add(device.getDeviceName());
      list.add(device.getTime());
      devicesNnT.add(list);
    }
//    Log.wtf("tag", "deviceNnT:: " + devicesList.size());
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
        addButtonClicked = false;
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
      firstTime = false;
    }
    /**********************************/
    else {
      Log.wtf("tag", "ENTERED  THE SECOND TIME >>>>>>>>>>>>>>>>");

      someMagic(ca, wItem, true, NULL);

//      Log.wtf("tag", "number of items::BEFORE ADDING  ITEMS" + nbrItems);
      Log.wtf("tag", "CAT SIZEEE:BEFORE:" + CAT_IMAGE_IDS.size());
      CAT_IMAGE_IDS.add(nbrItems, wItem);
      ca.setListObjects(CAT_IMAGE_IDS);
      Log.wtf("tag", "CAT SIZEEE:AFTER:" + CAT_IMAGE_IDS.size());


/*******************hardcode for testing*******************************/
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

  public void someMagic(CustomAdapter ca, WidgetItem wItem, boolean add, int magicNumber) {
    /**
     * holyRow is always pointing to the last row and keeping track of the nbr of items there
     */
    int nbrDummy = 0;
    int addPos = 0;
    nbrItems = 0;
    int weSayJump = 0;
    int foundDummy = 0;

    if (!add) {//This's the second part of the The Removal, the first part happen in the adapter, here is the after party (more or less!)
      Log.wtf("tag", "IN CASE  OF  REMOVAL : " + magicNumber + " type: " + wItem.getType());

      //Adding a dummy here just to cover up for an item (widget) removal,
      //let's say we are removing the last item in 2 items list (yes, it's the second item)
      //when that item get removed we simply want to put a dummy in it's place
      if (magicNumber < 10)
        do {
          WidgetItem dummy = new WidgetItem();
          dummy.setType("Dummy");
          CAT_IMAGE_IDS.add(ca.getItemCount() - 2, dummy);
          ca.setListObjects(CAT_IMAGE_IDS);
          magicNumber--;
        } while (magicNumber != 0);

      if (magicNumber == 22)
        Log.wtf("tag", "we found the magic number");


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
      if (holyRow == 0 && ca.getItemCount() > 0)
        holyRow = 3;


    }
    Log.wtf("tag", "add::" + add + " magicnumber::" + magicNumber);
    if (add && (magicNumber >= 1000)) {//Well... if the number inside the list get above 1k, weird stuff may start happening
      Log.wtf("tag", "ENTERED THE MAGIC NUBMER  CHECK:");
      WidgetItem tempHolder = new WidgetItem();
      tempHolder.setType("TempHolder");
      CAT_IMAGE_IDS.add(magicNumber - 1000, tempHolder);
      ca.setListObjects(CAT_IMAGE_IDS);

    }
    if (add && magicNumber < 1000) {

      for (int i = 0; i < ca.getItemCount(); i++) {
//        Log.wtf("tag", ":::size inside loop::" + ca.getItemCount());

        if (ca.getObjectList().get(i).getType() == "Dummy")
          nbrDummy++;

        if (ca.getObjectList().get(i).isWidget() && ca.getObjectList().get(i).getSize() == 1)
          nbrItems++;
        if (ca.getObjectList().get(i).getSize() == 2)
          nbrItems++;
        if (ca.getObjectList().get(i).getType() == "TempHolder")
          nbrItems++;
      }
//      Log.wtf("tag", "HOLY  ROW:" + holyRow);

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
//          Log.wtf("tag", "ADDDDDDDDDDDDDING DUMMIES");
          dummy.setTilte("dummy" + i + nbrItems);
          CAT_IMAGE_IDS.add(ca.getItemCount() - 2, dummy);
          ca.setListObjects(CAT_IMAGE_IDS);
        }
      }


      if (wItem.getSize() == 2 && (holyRow == 0 || holyRow == 1 || holyRow == 3)) {
        for (int i = 0; foundDummy != 2; i++) {
          if (ca.getObjectList().get(i).getType() == "Dummy") {
//            Log.wtf("tag", "000000000DUMMY  REMOVED YEY AT::" + i);
            ca.removeOneObject(ca.getItemCount() - 2);
            foundDummy++;
          }
        }
      }
      if (wItem.getSize() == 1) {
        for (int i = 0; foundDummy != 1; i++) {
          if (ca.getObjectList().get(i).getType() == "Dummy") {
//            Log.wtf("tag", "000000000DUMMY  REMOVED YEY AT::" + i);
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
    Log.wtf("tag", "HOLY ROWWW:: " + holyRow);
  }

  @Override
  public void onReceiveResult(int resultCode, Bundle resultData) {
    List<String> list = resultData.getStringArrayList("deviceNameList");
    for (int i = 0; i < list.size(); i++) {
      List mainList = new ArrayList();
      mainList.add(list.get(i));
      mainList.add(resultData.getStringArrayList("deviceDateList").get(i));
      devicesNnT.add(mainList);
    }
  }

  public void firstDialogLayout(final Dialog dialog, final CustomAdapter ca) {
    mReceiver = new ServiceDataReceiver(new Handler());
    mReceiver.setReceiver(this);

    final boolean[] exitLoop = {false};
    dialog.setContentView(R.layout.popup_body);
    dialog.getWindow().setLayout((viewPager.getWidth() / 100) * 75, (viewPager.getHeight() / 100) * 35);
//    Thermometer th = dialog.findViewById(R.id.thermo);
    final CardView card1 = dialog.findViewById(R.id.card1);
    final CardView card2 = dialog.findViewById(R.id.card2);
    final CardView card3 = dialog.findViewById(R.id.card3);
    final CardView card4 = dialog.findViewById(R.id.card4);
    final TextView waitingTv1 = dialog.findViewById(R.id.waitingTv1);
    TextView waitingTv2 = dialog.findViewById(R.id.waitingTv2);
    TextView waitingTv3 = dialog.findViewById(R.id.waitingTv3);
    final TextView waitingTv4 = dialog.findViewById(R.id.waitingTv4);
    final ImageView imageView1 = dialog.findViewById(R.id.imageView1);
    ImageView imageView2 = dialog.findViewById(R.id.imageView2);
    ImageView imageView3 = dialog.findViewById(R.id.imageView3);
    final ImageView imageView4 = dialog.findViewById(R.id.imageView4);

    PushDownAnim.setPushDownAnimTo(card1, card2, card3, card4);
    View.OnClickListener clickAble = new View.OnClickListener() {
      @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
      @Override
      public void onClick(View v) {
        //First block clicked
        if (v.equals(card1)) {
          _selected = 1;
          imageView1.setVisibility(View.GONE);
          waitingTv1.setVisibility(View.VISIBLE);
          if (devicesList != null && devicesList.size() > 0) {
            dialog.setContentView(R.layout.popup_body2);
            secondDialogLayout(dialog, ca);
          } else {
            final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            Runnable runnable = new Runnable() {
              @Override
              public void run() {
                if (devicesList.size() > 0) {
                  Runnable r = new Runnable() {
                    @Override
                    public void run() {
                      Log.wtf("tag", "if (devicesNnT.size() > 0) {");
                      dialog.setContentView(R.layout.popup_body2);
                      secondDialogLayout(dialog, ca);

                      imageView1.setVisibility(View.VISIBLE);
                      waitingTv1.setVisibility(View.GONE);
                    }
                  };
                  new Handler(Looper.getMainLooper()).post(r);
                  scheduler.shutdown();
                }
              }
            };
            scheduler.scheduleAtFixedRate(runnable, 2, 1, TimeUnit.SECONDS);
          }

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
          _selected = 4;
          imageView4.setVisibility(View.GONE);
          waitingTv4.setVisibility(View.VISIBLE);
          if (devicesNnT != null && devicesNnT.size() > 0) {
            Log.wtf("tag", "check clicked 1::");
            dialog.setContentView(R.layout.popup_body_info);
            infoDialogLayout(dialog, ca);
          } else {

            Log.wtf("tag", "check clicked 2::" + devicesNnT.size());

            final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            Runnable runnable = new Runnable() {
              @Override
              public void run() {
                Log.wtf("tag", "run the loop " + devicesNnT.size());
                if (devicesList.size() > 0) {
                  Runnable r = new Runnable() {
                    @Override
                    public void run() {
                      Log.wtf("tag", "if (devicesNnT.size() > 0) {");
                      fillDeviceNnT();
                      dialog.setContentView(R.layout.popup_body_info);
                      infoDialogLayout(dialog, ca);

                      imageView4.setVisibility(View.VISIBLE);
                      waitingTv4.setVisibility(View.GONE);
                    }
                  };
                  new Handler(Looper.getMainLooper()).post(r);
                  scheduler.shutdown();
                }
              }
            };
            scheduler.scheduleAtFixedRate(runnable, 2, 1, TimeUnit.SECONDS);
          }
          /********************************/
        }

      }
    };
    card1.setOnClickListener(clickAble);
    card2.setOnClickListener(clickAble);
    card3.setOnClickListener(clickAble);
    card4.setOnClickListener(clickAble);

//    th.noAnimation();
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
    checkedDevices.clear();
    final boolean[] foundIt = {false};
    final ImageButton btnBack = (ImageButton) dialog.findViewById(R.id.btnBack);
    final ImageButton btn_add = dialog.findViewById(R.id.btn_add);
    final EditText title = dialog.findViewById(R.id.title);
    final CardView topCard = dialog.findViewById(R.id.topCard);
    final CardView bottomCard = dialog.findViewById(R.id.thirdCard);
    final ConstraintLayout bh1 = dialog.findViewById(R.id.bh1);
    final LinearLayout topHolder = dialog.findViewById(R.id.topHolder);
    final LinearLayout bottomholder = dialog.findViewById(R.id.bottomHolder);
    final boolean[] readyToAdd = {false};

    btn_add.setAlpha(.5f);
    btn_add.setEnabled(false);

    bh1.post(new Runnable() {
      @Override
      public void run() {
//        topHolder.setLayoutParams(new LinearLayout.LayoutParams(bh1.getWidth()*2,topHolder.getHeight()));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((bh1.getWidth() / 100) * 85, topCard.getHeight());
        params.gravity = Gravity.CENTER;
        topCard.setLayoutParams(params);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams((bh1.getWidth() / 100) * 85, bottomCard.getHeight());
        params2.gravity = Gravity.CENTER | Gravity.RIGHT;

        bottomCard.setLayoutParams(params2);

      }
    });


//    btn_add.setEnabled(false);//Enable it only when there's some checkboxes selected in the vicinity ;)
    LinearLayout boxHolder = dialog.findViewById(R.id.box_holder);
    btnBack.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        dialog.setContentView(R.layout.popup_body);
        firstDialogLayout(dialog, ca);
      }
    });


//    for (List deviceList : devicesNnT) {
    Log.wtf("tag", "deviceNnT size" + devicesNnT.size());
    for (int i = 0; i < devicesNnT.size(); i++) {
      final CheckBox name = new CheckBox(mContext);

      name.setTextColor(name.getResources().getColor(R.color.theNewDark));//TODO: require checking, since it got changed from "getResources" to "name.getResources"
      int states[][] = {{android.R.attr.state_checked}, {}};
      int colors[] = {name.getResources().getColor(R.color.lightBrown), Color.GRAY};//TODO: Doubt this one will even works
      name.setButtonTintList(new ColorStateList(states, colors));

      name.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
      name.setText(devicesNnT.get(i).get(0).toString());
      boxHolder.addView(name);
      View v = new View(mContext);
      LinearLayout ln = new LinearLayout(mContext);
      ln.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, 5));
      ln.setBackgroundColor(Color.GRAY);
      v.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, 5));
      if (i != devicesNnT.size() - 1)
        boxHolder.addView(ln);
//
      name.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

          if (isChecked) {
            checkedDevices.add((String) name.getText());
            Log.wtf("tag", "checked devices name::" + checkedDevices.size());
          }
          if (!isChecked)
            for (int i = 0; i < checkedDevices.size(); i++) {
              Log.wtf("tag", "devices names::" + checkedDevices.get(i));
              if (checkedDevices.get(i).equals(name.getText())) {
                Log.wtf("tag", "remove checked device");
                checkedDevices.remove(i);
                i--;
              }

            }
          Log.wtf("tag", "checked devices name::" + checkedDevices.size());
          if (checkedDevices.size() == 0) {
            btn_add.setAlpha(.5f);
            btn_add.setEnabled(false);
          } else {
            btn_add.setAlpha(1f);
            btn_add.setEnabled(true);
          }
//          if (checkedDevices.size() > 0/* && title.getText().length() > 0*/)
//            btn_add.setEnabled(true);
//          else
//            btn_add.setEnabled(false);
        }
      });
    }

    title.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (String.valueOf(s).trim().length() > 0) {
          btn_add.setAlpha(1f);
          btn_add.setEnabled(true);
          readyToAdd[0] = true;
        } else {
          btn_add.setAlpha(.5f);
          btn_add.setEnabled(false);
          readyToAdd[0] = false;
        }
      }

      @Override
      public void afterTextChanged(Editable s) {

      }
    });

    final boolean[] rightSide = {false};
    btn_add.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (rightSide[0] == false) {
          animateIt(topCard, 500, bh1, Gravity.CENTER, Gravity.LEFT);
          animateIt(bottomCard, 500, bh1, Gravity.CENTER | Gravity.RIGHT, Gravity.CENTER);
          rightSide[0] = true;

          btn_add.setAlpha(.5f);
          btn_add.setEnabled(false);

          btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if (rightSide[0] == true) {
                animateIt(bottomCard, 500, bh1, Gravity.CENTER, Gravity.CENTER | Gravity.RIGHT);
                animateIt(topCard, 500, bh1, Gravity.LEFT, Gravity.CENTER);
                rightSide[0] = false;
              } else if (rightSide[0] == false) {
                dialog.setContentView(R.layout.popup_body);
                firstDialogLayout(dialog, ca);
              }

            }
          });


        } else if (rightSide[0] == true) {
          Boolean found = false;
          WidgetItem wi = new WidgetItem();
          wi.setType("Info");
          wi.isWidget(true);
          wi.setSize(2);
          wi.setTilte(title.getText().toString());

          List<List<String>> rightSide = new ArrayList<>();

          for (List list : devicesNnT) {
            found = false;
            for (int j = 0; j < checkedDevices.size(); j++) {
              if (list.get(0).equals(checkedDevices.get(j))) {
                found = true;
              }
              if (!list.get(0).equals(checkedDevices.get(j)) && found == false) {
                found = false;
              }
            }
            if (!found)
              Log.wtf("tag", "this should be removed::" + list.get(0));
            else
              rightSide.add(list);
          }

          if (readyToAdd[0]) {
            wi.setSelectedDevices(rightSide);
            wi.setSelectedDevicesNumber((long) rightSide.size());
            addingWidget(ca, wi);
            dialog.dismiss();
          }


        }

      }
    });

  }

  /****This method should take care of trimming the first list to look like the second list and return it back*/
  public List removeUnnecessaryData(List<String> theSmallList) {
    Boolean found = false;
    List<List<String>> temp = new ArrayList<>();

    for (List list : devicesNnT) {
      found = false;
      for (int j = 0; j < theSmallList.size(); j++) {
        if (list.get(0).equals(theSmallList.get(j))) {
          found = true;
//              Log.wtf("tag", "::FOUND ==TRUE::");
        }
        if (!list.get(0).equals(theSmallList.get(j)) && found == false) {
//              Log.wtf("tag", "::FOUND ==FALSE::");
          found = false;
        }
      }
//          Log.wtf("tag", "there's something utterly wrong here::");
      if (!found)
        Log.wtf("tag", "this should be removed::" + list.get(0));//but no, this will stay and add the missing value to a new list "else" bellow
      else
        temp.add(list);
    }
    return temp;
  }

  public void secondDialogLayout(final Dialog dialog, final CustomAdapter ca) {
//    dialog.getWindow().setLayout((viewPager.getWidth() / 100) * 75, (viewPager.getHeight() / 100) * 35);
    final WidgetItem[] wi = new WidgetItem[1];
    final String[] displayValue = new String[1];
    final String[][] vals1 = {{""}};
    String[] vals2 = {""};
    final String[] devices = new String[]{"Green_Land", "System_Room_SOFIA", "System_Room_SOFIA2", "Weather_Station_Final"};
    final String[] greenLand = new String[]{"Time", "Temperature", "Humidity", "Battery_Level", "Moisture", "soilTemperature", "WaterSensor"};
    final String[] room = new String[]{"Time", "Temperature", "Pressure", "Humidity", "Battery"};
    final String[] station = new String[]{"RH (Relative Humidity)", "Direction", "Temperature", "Time", "Rainfall", "Rainfall24", "Speed", "Speed5"};
    final String[] SWR = new String[]{"Speed", "Wind", "Rain", "??!!?!"};

    final NumberPicker picker = dialog.findViewById(R.id.picker1);
    final NumberPicker picker2 = dialog.findViewById(R.id.picker2);
    final int[] currentLayoutPos = {0};

    final ImageButton btnAdd = (ImageButton) dialog.findViewById(R.id.btn_add);
    final EditText editText = (EditText) dialog.findViewById(R.id.editText);
    final ImageButton btnBack = (ImageButton) dialog.findViewById(R.id.btnBack);
    final EditText title = dialog.findViewById(R.id.title);
    final CardView firstCard = dialog.findViewById(R.id.firstCard);
    final CardView secondCard = dialog.findViewById(R.id.secondCard);
    final CardView thirdCard = dialog.findViewById(R.id.thirdCard);
    final ConstraintLayout bh1 = dialog.findViewById(R.id.bh1);
    final Handler[] handler = {new Handler()};
    final boolean[] clickedOnce = {false};
    final Handler handler2 = new Handler();


//    btnAdd.setAlpha(.5f);
//    btnAdd.setEnabled(false);

    bh1.post(new Runnable() {
      @Override
      public void run() {
//        topHolder.setLayoutParams(new LinearLayout.LayoutParams(bh1.getWidth()*2,topHolder.getHeight()));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((bh1.getWidth() / 100) * 85, firstCard.getHeight());
        params.gravity = Gravity.CENTER;
        firstCard.setLayoutParams(params);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams((bh1.getWidth() / 100) * 85, secondCard.getHeight());
        params2.gravity = Gravity.CENTER | Gravity.RIGHT;
        secondCard.setLayoutParams(params2);
        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams((bh1.getWidth() / 100) * 85, thirdCard.getHeight());
        params3.gravity = Gravity.CENTER | Gravity.RIGHT;
        thirdCard.setLayoutParams(params3);

      }
    });

    picker.setMaxValue(3);
    picker.setMinValue(0);
    picker.setDisplayedValues(devices);
    picker2.setMaxValue(6);
    picker2.setMinValue(0);
    picker2.setDisplayedValues(greenLand);
    picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
    picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
      @Override
      public void onValueChange(final NumberPicker pickerTemp, int oldVal, final int newVal) {
        Log.wtf("tag", "picked::" + devices[newVal]);

        Runnable runnable = new Runnable() {
          @Override
          public void run() {
            if (devices[newVal] == "Green_Land") {
              picker2.setDisplayedValues(null);
              picker2.setMaxValue(6);
              picker2.setMinValue(0);
              picker2.setDisplayedValues(greenLand);
            }

            if (devices[newVal] == "System_Room_SOFIA") {
              picker2.setDisplayedValues(null);
              picker2.setMaxValue(4);
              picker2.setMinValue(0);
              picker2.setDisplayedValues(room);
            }

            if (devices[newVal] == "System_Room_SOFIA2") {
              picker2.setDisplayedValues(null);
              picker2.setMaxValue(4);
              picker2.setMinValue(0);
              picker2.setDisplayedValues(room);
            }

            if (devices[newVal] == "Weather_Station_Final") {//8
              picker2.setDisplayedValues(null);
              picker2.setMaxValue(7);
              picker2.setMinValue(0);
              picker2.setDisplayedValues(station);
            }

            Log.wtf("tag", "testCounterRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR ");
          }
        };


        handler2.removeCallbacksAndMessages(null);
        handler2.postDelayed(runnable, 1000);
//        }
      }
    });


//    picker2.setDisplayedValues(vals2);


//    btn_add.setEnabled(false);//Enable it only when there's some checkboxes selected in the vicinity ;)
//    LinearLayout boxHolder = dialog.findViewById(R.id.box_holder);

    if (currentLayoutPos[0] == 0)
      btnBack.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          dialog.setContentView(R.layout.popup_body);
          firstDialogLayout(dialog, ca);
        }
      });


    btnAdd.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        Runnable runnable = new Runnable() {
          @Override
          public void run() {

            if (currentLayoutPos[0] == 0) {
              animateIt(firstCard, 500, bh1, Gravity.CENTER, Gravity.LEFT);
              animateIt(secondCard, 500, bh1, Gravity.CENTER | Gravity.RIGHT, Gravity.CENTER);
              currentLayoutPos[0] = 1;


              btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  if (currentLayoutPos[0] == 1) {
                    animateIt(secondCard, 500, bh1, Gravity.CENTER, Gravity.CENTER | Gravity.RIGHT);
                    animateIt(firstCard, 500, bh1, Gravity.LEFT, Gravity.CENTER);
                    currentLayoutPos[0] = 0;
                  } else if (currentLayoutPos[0] == 2) {

                    animateIt(thirdCard, 500, bh1, Gravity.CENTER, Gravity.CENTER | Gravity.RIGHT);
                    animateIt(secondCard, 500, bh1, Gravity.LEFT, Gravity.CENTER);
                    currentLayoutPos[0] = 1;

                  } else if (currentLayoutPos[0] == 0) {
                    dialog.setContentView(R.layout.popup_body);
                    firstDialogLayout(dialog, ca);
                  }

                }
              });

            } else if (currentLayoutPos[0] == 1) {
              animateIt(secondCard, 500, bh1, Gravity.CENTER, Gravity.LEFT);
              animateIt(thirdCard, 500, bh1, Gravity.CENTER | Gravity.RIGHT, Gravity.CENTER);
              currentLayoutPos[0] = 2;


              btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  if (currentLayoutPos[0] == 1) {
                    animateIt(secondCard, 500, bh1, Gravity.CENTER, Gravity.CENTER | Gravity.RIGHT);
                    animateIt(firstCard, 500, bh1, Gravity.LEFT, Gravity.CENTER);
                    currentLayoutPos[0] = 0;
                  } else if (currentLayoutPos[0] == 2) {

                    animateIt(thirdCard, 500, bh1, Gravity.CENTER, Gravity.CENTER | Gravity.RIGHT);
                    animateIt(secondCard, 500, bh1, Gravity.LEFT, Gravity.CENTER);
                    currentLayoutPos[0] = 1;

                  } else if (currentLayoutPos[0] == 0) {
                    dialog.setContentView(R.layout.popup_body);
                    firstDialogLayout(dialog, ca);
                  }

                }
              });

            } else if (currentLayoutPos[0] == 2) {

            }

            clickedOnce[0] = false;
          }
        };
        if (!clickedOnce[0]) {
          clickedOnce[0] = true;
          handler[0].postDelayed(runnable, 500);
        }


      }
    });


//    btnBack.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        dialog.setContentView(R.layout.popup_body);
//        firstDialogLayout(dialog, ca);
//      }
//    });
//
//    picker.setMaxValue(3);
//    picker.setMinValue(0);
//    picker2.setMaxValue(3);
//    picker2.setMinValue(0);
//
//    switch (_selected) {
//      case 1: {
//        Log.wtf("tag", "1111111111&");
//        picker.setWrapSelectorWheel(true);
//        vals1[0] = SWR;
//        vals2 = endDevies;
//      }
//      break;
//      case 2: {
//        Log.wtf("tag", "2222222222222");
//        vals1[0] = TPHB;
//        vals2 = endDevies;
//      }
//
//    }
////Log.wtf("tag","");
//    picker.setDisplayedValues(vals1[0]);
//    picker2.setDisplayedValues(vals2);
//    final String[] finalVals = vals2;
//
//    picker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
//      @Override
//      public void onValueChange(final NumberPicker picker2, int oldVal, final int newVal) {
//        Log.wtf("tag", "picked::" + finalVals[newVal]);
//
//        Runnable spinn = new Runnable() {
//          @Override
//          public void run() {
//            if (finalVals[newVal] == "END_DEVICE1")
//              picker.setDisplayedValues(TPHB);
//            if (finalVals[newVal] == "END_DEVICE2")
//              picker.setDisplayedValues(SWR);
//            Log.wtf("tag", "testCounterRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR ");
//          }
//        };
//
//        handler.removeCallbacksAndMessages(null);
//        handler.postDelayed(spinn, 1000);
////        }
//      }
//    });
//
//    btnAdd.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        switch (_selected) {
//          case 1: {
//            Thermometer thermo = new Thermometer(mContext);
//            thermo.setThermometerColor(Color.rgb(167, 156, 147));
//            thermo.setInnerPaint(Color.rgb(30, 144, 255));
//            thermo.setScaleX(.9f);
//            thermo.setScaleY(.7f);
//
//            thermo.setOuterLinePaint(Color.rgb(235, 235, 235));
//
////            thermo.noAnimation();
//            wi[0] = new WidgetItem(thermo, editText.getText().toString());
////            wi[0].setWidgetType("Thermometer");
//            wi[0].setType("Widget_T");
//            wi[0].isWidget(true);
//
//          }
//          break;
//          case 2: {
//            ArcProgress cp = new ArcProgress(mContext);
//            cp.setBottomText("Type1");
//            cp.setProgress(10);
//            cp.setUnfinishedStrokeColor(Color.rgb(167, 156, 147));//gray
//            cp.setFinishedStrokeColor(Color.rgb(30, 144, 255));//blue
//            cp.setTextColor(Color.rgb(30, 144, 255));//blue
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(180, 180);
//            params.gravity = Gravity.CENTER;
//            cp.setLayoutParams(params);
//            wi[0] = new WidgetItem(cp, editText.getText().toString());
////            wi[0].setWidgetType("ArcProgress");
//            wi[0].setType("Widget_G");
//            wi[0].isWidget(true);
//          }
//
//        }
//
//
//        wi[0].setDisplayValue("Temperature");//temp or  pressure or humidity....
//        addingWidget(ca, wi[0]);
//      }
//    });
  }

  public void animateIt(View obj, int time, ViewGroup tc, int startPoint, int endPoint) {
    Boolean state = true;
    Transition CB = new ChangeBounds();
    CB.setPathMotion(new ArcMotion());
    CB.setDuration(time);
    TransitionManager.beginDelayedTransition(tc, CB);
    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) obj.getLayoutParams();

    if (state && params.gravity != startPoint) {
      params.gravity = (startPoint);
      state = false;
    } else {
      params.gravity = (endPoint);
      state = true;
    }

    obj.setLayoutParams(params);

//    if (obj.equals(btnLogIn)) {
//      inLaucher = false;
//    }

  }

  public void flexlayout(CustomAdapter ca2, RecyclerView recyclerView) {
    flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
    flexboxLayoutManager.setAlignItems(AlignItems.STRETCH);
    flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
    flexboxLayoutManager.setMaxLine(20);

//    flexboxLayoutManager.setJustifyContent(JustifyContent.CENTER);
    recyclerView.setLayoutManager(flexboxLayoutManager);

//    recyclerView.setHasFixedSize(false);
//    catAdapter = ca;
    /*for performance??????*/
    recyclerView.setItemViewCacheSize(20);
    recyclerView.setDrawingCacheEnabled(true);
    recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    if (!ca2.hasObservers())
      ca2.setHasStableIds(true);
    /*for performance*/
    recyclerView.setAdapter(ca2);
//    customAdapter.notifyDataSetChanged();
  }

  /**
   * THE  WEIRD STUFF BELOW
   */

  @Override
  public void destroyItem(ViewGroup collection, int position, Object view) {
    for (int i = 0; i < collection.getChildCount(); i++) {
      Log.wtf("tag", "child at " + i + " " + collection.getChildAt(i).getClass());
    }
    Log.wtf("tag", "position" + position);
//    ((ViewPager) collection).removeView((View) view);

//    collection.removeViewAt(currentPosition);
    collection.removeViewAt(position + 1);

    for (int i = 0; i < collection.getChildCount(); i++) {
      Log.wtf("tag", "child at " + i + " " + collection.getChildAt(i).getClass());
    }
    Log.wtf("tag", "CAT_IMAGE_IDS size: " + CAT_IMAGE_IDS.size());
//    for(View rv : recyclerViewList){
//      RecyclerView rv1 = (RecyclerView) rv;
//      for(int i = 0; i<rv1.getChildCount();i++){
//        Log.wtf("tag","child class: "+ rv1.getChildAt(i).getClass());
//      }
//    }
//    firstTime = true;
//    addingWidget(customAdapter, null);
//    loadOldValues();


//    Runnable r = new Runnable() {
//      @Override
//      public void run() {
//        flexlayout(customAdapter, (RecyclerView) recyclerViewList.get(0));
//      }
//    };
//    new Handler(Looper.getMainLooper()).post(r);

  }

  @Override
  public int getCount() {
//    Log.wtf("tag", "tablayout size:1:" + tabLayout.getTabCount());
    return recyclerViewList.size();
  }

  @Override
  public int getItemPosition(Object object) {
    int index = recyclerViewList.indexOf(object);
    if (index == -1)
      return POSITION_NONE;
    else
      return index;
  }

  @Override
  public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }

  @Override
  public CharSequence getPageTitle(int position) {
//    Log.wtf("tag", "getPAgTitle pos" + position);

//    return ws.pageTitle;
    String s = "";
    try {
      if (nameList.size() > 0 && nameList != null)
        s = nameList.get(position);
    } catch (Exception e) {

    }

    return s;
  }


  @Override
  public Parcelable saveState() {
//    Log.wtf("tag", "tablayout size:4:" + tabLayout.getTabCount());
    return null;
  }

  @Override
  public void restoreState(Parcelable state, ClassLoader loader) {
//    Log.wtf("tag", "tablayout size:5:" + tabLayout.getTabCount());
  }

  public int addView(View v) {
//    Log.wtf("tag", "tablayout size:6:" + tabLayout.getTabCount());
    return addView(v, recyclerViewList.size());
  }

  public int addView(View v, int position) {
//    Log.wtf("tag", "tablayout size:7:" + tabLayout.getTabCount());
    recyclerViewList.add(position, v);
    return position;
  }

  public void removeCurrentPage() {
    Log.wtf("tag", "Current pos: " + currentPosition);
    posToRemove = currentPosition;
    settingsList.remove(currentPosition);
    nameList.remove(currentPosition);
    tabLayout.removeTab(tabLayout.getTabAt(currentPosition));
    recyclerViewList.remove(currentPosition);
    notifyDataSetChanged();
  }

  //  @Override
//  public void finishUpdate(ViewGroup container) {
//    Log.wtf("tag", "tablayout size:8:" + tabLayout.getTabCount());
////    super.finishUpdate(container);
////    this.notifyDataSetChanged();
//  }
  public void setPageTitle(String title) {
    nameList.add(title);
    pageTitle = title;
    TabLayout.Tab tab = tabLayout.getTabAt(getCount() - 1);
    tab.setText(title);
  }

  public void changePageTitle(String title) {
    nameList.set(currentPosition, title);
    TabLayout.Tab tab = tabLayout.getTabAt(currentPosition);
    tab.setText(title);
  }

  public class WidgetSettings {
    boolean firstTime;
    List<WidgetItem> CAT_IMAGE_IDS;
    int nbrItems;
    int _selected;
    int holyRow;
    //    boolean addButtonClicked;
    int position;
    CustomAdapter customAdapter;
    FlexboxLayoutManager flexboxLayoutManager;
    String pageTitle;

    public WidgetSettings(int position) {
//      Log.wtf("tag", "CONSTRUCTOOOORRR");
      firstTime = true;
      CAT_IMAGE_IDS = new ArrayList<>();
      nbrItems = 0;
//      addButtonClicked = false;
      holyRow = 0;
      _selected = 0;
      this.position = position;
      customAdapter = new CustomAdapter(mContext, (RecyclerView) recyclerViewList.get(position));
      flexboxLayoutManager = new FlexboxLayoutManager(mContext);
      pageTitle = "<>";
    }
  }


}
