package com.example.touch_me.pfe_project;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.touch_me.pfe_project.helper.OnStartDragListener;
import com.example.touch_me.pfe_project.widgets.Thermometer;
import com.github.lzyzsd.circleprogress.ArcProgress;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.thekhaeng.pushdownanim.PushDownAnim;
import com.transitionseverywhere.Rotate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yalantis.com.sidemenu.util.ViewAnimator;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS;
import static java.sql.Types.NULL;


public class WelcomeScreen extends AppCompatActivity implements OnStartDragListener, CatAdapter.RecyclerViewOnClickListener, ServiceDataReceiver.Receiver {

  private DrawerLayout drawerLayout;
  private LinearLayout bigdady;
  private LinearLayout linearLayout;
  private ArrayList<String> itemList = new ArrayList<String>();
  private ViewAnimator viewAnimator;
  private ActionBarDrawerToggle drawer;
  private Button itemAddBtn;
  private DrawerLayout.DrawerListener dl;
  private Toolbar toolbar;
  private TextView toolbarTitle;
  private View portal;

  private ItemTouchHelper mItemTouchHelper;
  RecyclerView rvFoodItems;

  ArrayList<GreenLand> foodList;
  RecyclerView recyclerView;
  TextView testTV;
  private FlexboxLayoutManager flexboxLayoutManager;

  RecyclerView.Adapter catAdapter;
  /**
   * <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
   */
  ImageButton btn_Settings;
  Button btn_add;
  //  Thermometer thermo;
  DatabaseReference myRef;
  private Query queryObj;
  boolean FireShot = false;
  //  private TextView liveData;
  private int _selected;
  int tempCounter = 0;
  List<WidgetItem> CAT_IMAGE_IDS = new ArrayList<>();
  int testInt = 0;
  boolean firstTime = true;
  CatAdapter ca;
  CustomAdapter ca2;
  private List<List<String>> devicesName = new ArrayList<>();
  private int holyRow = 0;
  int nbrItems = 0; //number of items in the list (not counting button and dummies)
  boolean isRotated = false;
  ViewGroup the_holder;
  boolean add = true;
  Realm realm = null;
  boolean addButtonClicked = false;
  boolean settingsClicked = false;
  boolean saveClicked = false;
  public ServiceDataReceiver mReceiver;
  Dialog settingsDialog;
  Boolean cantStopMeNow = true;
  ScheduledThreadPoolExecutor scheduler;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_welcome_screen);


    setStatusBarTrasparent();
//    setupWindowAnimations();
    overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

//    drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    View portal = findViewById(R.id.portal);
    bigdady = (LinearLayout) findViewById(R.id.big_dady);

//    toolbar = (Toolbar) findViewById(R.id.toolbar);
//    toolbarTitle = (TextView) findViewById(R.id.toolbar_title);

    testTV = (TextView) findViewById(R.id.testTV);
    recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    btn_add = (Button) findViewById(R.id.btn_add);
//    liveData = (TextView) findViewById(R.id.liveData);
    btn_Settings = (ImageButton) findViewById(R.id.settingButon);
    the_holder = (ViewGroup) findViewById(R.id.the_holder);


/**************************WORKS******************************/
//    rvFoodItems = (RecyclerView) findViewById(R.id.rvFoodItems);
//
//    rvFoodItems.setLayoutManager(new LinearLayoutManager(this));
//
//    foodAdapter = new RecyclerListAdapter(foodList, this);
//    rvFoodItems.setAdapter(foodAdapter);
//
//    ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(foodAdapter);
//    mItemTouchHelper = new ItemTouchHelper(callback);
//    mItemTouchHelper.attachToRecyclerView(rvFoodItems);
/***********************WORKS*************************/
    ca2 = new CustomAdapter(WelcomeScreen.this, recyclerView);
//    ca2 = new CustomAdapter(this, recyclerView.getWidth());
//
//
    flexlayout(ca2);
    demoHolder();
//
//
//    addingWidget(ca2, null);
//
//    ca2.setCustomObjectListener(new MyCustomObjectListener() {
//      @Override
//      public void onOptionButtonReady(View v, int position) {
//        Log.wtf("tag", "BUTTON PRESSED AT  POSITIoN::" + position);
//      }
//
//      @Override
//      public void onObjectReady() {
//        Log.wtf("tag", "INITIATE LISTENER ONCE???????????");
//        genesis(ca2);
//      }
//    });


//    networkthingy();

//    Realm.init(this);
//
//
//    RealmConfiguration realmConfig = new RealmConfiguration.Builder()
//      .name("test1.realm")
//      .schemaVersion(0)
////      .deleteRealmIfMigrationNeeded()
//      .build();
//
//    Realm.setDefaultConfiguration(realmConfig);
//
////    Realm realm = null;
//    realm = Realm.getInstance(realmConfig);


//    realm.executeTransaction(new Realm.Transaction() {
//      @Override
//      public void execute(Realm realm) {
//        final GreenLand wo = realm.createObject(GreenLand.class);
//        wo.setTemperature(000);
//        wo.setHumidity(0000f);
//        realm.insertOrUpdate(wo);
//        realm.deleteAll();//in case of deletion
//      }
//    });

//
//    RealmResults<GreenLand> result = realm.where(GreenLand.class)
//      .findAll();
//
//    Log.wtf("tag", "WHAT WE got here??" + result);

//    RealmResults<GreenLand> result = realm.where(GreenLand.class).findAll();
//
//
//    Log.wtf("tag", "WHAT WE got here??" + result);


//    realm.addChangeListener(new RealmChangeListener<Realm>() {
//      @Override
//      public void onChange(Realm realm) {
////        Log.wtf("tag", "DATABSE CHANGEDDDDD");
//        RealmResults<GreenLand> result = realm.where(GreenLand.class).findAll();
//
//
//        Log.wtf("tag", "WHAT WE got here??" + result);
//      }
//    });

    //initializing the service receiver
    mReceiver = new ServiceDataReceiver(new Handler());
    mReceiver.setReceiver(this);


    firebaseConf();
//    firebaseStaticConfig();

    btn_Settings.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Thread thread = new Thread(new Runnable() {
          @Override
          public void run() {
            TransitionManager.beginDelayedTransition(the_holder, new Rotate());
            btn_Settings.setRotation(isRotated ? 0 : 50);
            if (isRotated)
              isRotated = false;
            else
              isRotated = true;
            if (!settingsClicked)
              toggleSettings();
          }
        });
        thread.start();


      }
    });


    //======================OLD===================================
//    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//
//      @Override
//      public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//        moveItem(viewHolder.getAdapterPosition(), target.getAdapterPosition());
//        return true;
//      }
//
//      @Override
//      public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//        deleteItem(viewHolder.getAdapterPosition());
//      }
//
//    });
//    itemTouchHelper.attachToRecyclerView(rvFoodItems);
//=========================OLD===================================
  }

  @Override
  protected void onDestroy() {
    Log.wtf("tag", "BYEEEEEEEEEE");
    if (settingsDialog != null)
      settingsDialog.dismiss();
    super.onDestroy();
  }

  @Override
  protected void onPause() {
    Log.wtf("tag", "PAUSEEEEE");
    if (settingsDialog != null)
      settingsDialog.dismiss();
    super.onPause();
  }

  @Override
  protected void onStart() {
    Log.wtf("tag", "ONSTART");
    super.onStart();
    final Handler handler = new Handler();
    recyclerView.post(new Runnable() {
      @Override
      public void run() {
        Log.wtf("tag", "RECYCELER  LIST MOFO::" + recyclerView.getWidth());

        if (firstTime)
          addingWidget(ca2, null);

        ca2.setCustomObjectListener(new MyCustomObjectListener() {
          @Override
          public void onOptionButtonReady(View v, int position) {
            Log.wtf("tag", "BUTTON PRESSED AT  POSITIoN::" + position);
          }

          @Override
          public void onObjectReady() {
            Log.wtf("tag", "INITIATE LISTENER ONCE???????????");
            if (!addButtonClicked) {
              genesis(ca2);
              addButtonClicked = true;
            }

          }

          @Override
          public void notifyForRemove(CustomAdapter ca, WidgetItem wItem, boolean add, int dummiesToDelete) {
            someMagic(ca, wItem, false, dummiesToDelete);
          }
        });


      }


    });
  }

  @Override
  public void onReceiveResult(int resultCode, Bundle resultData) {
    Log.wtf("tag", "here we go:" + resultData.getString("offlineMode"));
  }

  public void toggleSettings() {
    new Handler(Looper.getMainLooper()).post(new Runnable() {
      @Override
      public void run() {
        settingsClicked = true;

        settingsDialog = new Dialog(WelcomeScreen.this);
        settingsDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        settingsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));
        settingsDialog.setContentView(R.layout.settings_body);
        settingsDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
          @Override
          public void onDismiss(DialogInterface dialog) {
            if (scheduler != null)
              scheduler.shutdown();
            TransitionManager.beginDelayedTransition(the_holder, new Rotate());
            btn_Settings.setRotation(isRotated ? 0 : 70);
            if (isRotated) {
              isRotated = false;
            } else {
              isRotated = true;
            }
            settingsClicked = false;

          }
        });
        settingsDialog.show();
        final CheckBox offlineFunc = settingsDialog.findViewById(R.id.offlineFunc);
        SharedPreferences mPrefs = getSharedPreferences("forSettings", 0);
        Boolean boo = mPrefs.getBoolean("offlineFunc", false);
        if (boo.equals(true)) {
          offlineFunc.setChecked(true);
        } else {
          offlineFunc.setChecked(false);
        }
        final Button saveButton = settingsDialog.findViewById(R.id.saveButton);
        final ViewGroup settingGroupView = (ViewGroup) saveButton.getParent();

        saveButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
//            if(!saveClicked){
            Log.wtf("tag", "earth just got saved");
            settingsClicked = false;
            if (offlineFunc.isChecked()) {
              SharedPreferences mPrefs = getSharedPreferences("forSettings", 0);
              SharedPreferences.Editor editor = mPrefs.edit();
              editor.putBoolean("offlineFunc", true);
              editor.commit();
              //handling intent for Firebase service
              Intent networkIntent = new Intent(WelcomeScreen.this, FirebasePullService.class);
              Bundle b = new Bundle();
              b.putParcelable("offlineMode", mReceiver);
              b.putString("identifier", "static");

              networkIntent.putExtras(b);

              WelcomeScreen.this.startService(networkIntent);

              cantStopMeNow = false;
              saveClicked = true;
              colorChange(settingGroupView, saveButton);
              saveButton.setClickable(false);
            } else {
              SharedPreferences mPrefs = getSharedPreferences("forSettings", 0);
              SharedPreferences.Editor editor = mPrefs.edit();
              editor.putBoolean("offlineFunc", false);
              editor.commit();
            }
          }
//          }
        });


      }
    });

  }

  public void genesis(CustomAdapter ca) {

    final Dialog dialog = new Dialog(this);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));

//    dialog.setTitle("LOL  WTH");
//    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//    dialog.setTitle("LOL  WTH");
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
    ArcProgress cp = new ArcProgress(WelcomeScreen.this);
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

//      if (size == 2)
//        holyRow = holyRow + 2;
//      else
//        holyRow++;

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


    Log.wtf("tag", "LIST SIZE:WS:::" + CAT_IMAGE_IDS.size());
    if (CAT_IMAGE_IDS.size() == 5) {
      Log.wtf("tag", "0 NAME??::" + "::" + CAT_IMAGE_IDS.get(0).getTilte());
      Log.wtf("tag", "1 NAME??::" + "::" + CAT_IMAGE_IDS.get(1).getTilte());
      Log.wtf("tag", "2 NAME??::" + "::" + CAT_IMAGE_IDS.get(2).getTilte());
      Log.wtf("tag", "3 NAME??::" + "::" + CAT_IMAGE_IDS.get(3).getTilte());
      Log.wtf("tag", "4 NAME??::" + "::" + CAT_IMAGE_IDS.get(4).getTilte());
    }
    if (CAT_IMAGE_IDS.size() == 6) {
      Log.wtf("tag", "0 NAME??::" + "::" + CAT_IMAGE_IDS.get(0).getTilte());
      Log.wtf("tag", "1 NAME??::" + "::" + CAT_IMAGE_IDS.get(1).getTilte());
      Log.wtf("tag", "2 NAME??::" + "::" + CAT_IMAGE_IDS.get(2).getTilte());
      Log.wtf("tag", "3 NAME??::" + "::" + CAT_IMAGE_IDS.get(3).getTilte());
      Log.wtf("tag", "4 NAME??::" + "::" + CAT_IMAGE_IDS.get(4).getTilte());
      Log.wtf("tag", "5 NAME??::" + "::" + CAT_IMAGE_IDS.get(5).getTilte());
    }
    if (CAT_IMAGE_IDS.size() == 7) {
      Log.wtf("tag", "0 NAME??::" + "::" + CAT_IMAGE_IDS.get(0).getTilte());
      Log.wtf("tag", "1 NAME??::" + "::" + CAT_IMAGE_IDS.get(1).getTilte());
      Log.wtf("tag", "2 NAME??::" + "::" + CAT_IMAGE_IDS.get(2).getTilte());
      Log.wtf("tag", "3 NAME??::" + "::" + CAT_IMAGE_IDS.get(3).getTilte());
      Log.wtf("tag", "4 NAME??::" + "::" + CAT_IMAGE_IDS.get(4).getTilte());
      Log.wtf("tag", "5 NAME??::" + "::" + CAT_IMAGE_IDS.get(5).getTilte());
      Log.wtf("tag", "6 NAME??::" + "::" + CAT_IMAGE_IDS.get(6).getTilte());
    }
    if (CAT_IMAGE_IDS.size() == 8) {
      Log.wtf("tag", "0 NAME??::" + "::" + CAT_IMAGE_IDS.get(0).getTilte());
      Log.wtf("tag", "1 NAME??::" + "::" + CAT_IMAGE_IDS.get(1).getTilte());
      Log.wtf("tag", "2 NAME??::" + "::" + CAT_IMAGE_IDS.get(2).getTilte());
      Log.wtf("tag", "3 NAME??::" + "::" + CAT_IMAGE_IDS.get(3).getTilte());
      Log.wtf("tag", "4 NAME??::" + "::" + CAT_IMAGE_IDS.get(4).getTilte());
      Log.wtf("tag", "5 NAME??::" + "::" + CAT_IMAGE_IDS.get(5).getTilte());
      Log.wtf("tag", "6 NAME??::" + "::" + CAT_IMAGE_IDS.get(6).getTilte());
      Log.wtf("tag", "7 NAME??::" + "::" + CAT_IMAGE_IDS.get(7).getTilte());
    }
    if (CAT_IMAGE_IDS.size() == 9) {
      Log.wtf("tag", "0 NAME??::" + "::" + CAT_IMAGE_IDS.get(0).getTilte());
      Log.wtf("tag", "1 NAME??::" + "::" + CAT_IMAGE_IDS.get(1).getTilte());
      Log.wtf("tag", "2 NAME??::" + "::" + CAT_IMAGE_IDS.get(2).getTilte());
      Log.wtf("tag", "3 NAME??::" + "::" + CAT_IMAGE_IDS.get(3).getTilte());
      Log.wtf("tag", "4 NAME??::" + "::" + CAT_IMAGE_IDS.get(4).getTilte());
      Log.wtf("tag", "5 NAME??::" + "::" + CAT_IMAGE_IDS.get(5).getTilte());
      Log.wtf("tag", "6 NAME??::" + "::" + CAT_IMAGE_IDS.get(6).getTilte());
      Log.wtf("tag", "7 NAME??::" + "::" + CAT_IMAGE_IDS.get(7).getTilte());
      Log.wtf("tag", "8 NAME??::" + "::" + CAT_IMAGE_IDS.get(8).getTilte());
    }
    if (CAT_IMAGE_IDS.size() == 10) {
      Log.wtf("tag", "0 NAME??::" + "::" + CAT_IMAGE_IDS.get(0).getTilte());
      Log.wtf("tag", "1 NAME??::" + "::" + CAT_IMAGE_IDS.get(1).getTilte());
      Log.wtf("tag", "2 NAME??::" + "::" + CAT_IMAGE_IDS.get(2).getTilte());
      Log.wtf("tag", "3 NAME??::" + "::" + CAT_IMAGE_IDS.get(3).getTilte());
      Log.wtf("tag", "4 NAME??::" + "::" + CAT_IMAGE_IDS.get(4).getTilte());
      Log.wtf("tag", "5 NAME??::" + "::" + CAT_IMAGE_IDS.get(5).getTilte());
      Log.wtf("tag", "6 NAME??::" + "::" + CAT_IMAGE_IDS.get(6).getTilte());
      Log.wtf("tag", "7 NAME??::" + "::" + CAT_IMAGE_IDS.get(7).getTilte());
      Log.wtf("tag", "8 NAME??::" + "::" + CAT_IMAGE_IDS.get(8).getTilte());
      Log.wtf("tag", "9 NAME??::" + "::" + CAT_IMAGE_IDS.get(9).getTilte());
    }
    if (CAT_IMAGE_IDS.size() == 11) {
      Log.wtf("tag", "0 NAME??::" + "::" + CAT_IMAGE_IDS.get(0).getTilte());
      Log.wtf("tag", "1 NAME??::" + "::" + CAT_IMAGE_IDS.get(1).getTilte());
      Log.wtf("tag", "2 NAME??::" + "::" + CAT_IMAGE_IDS.get(2).getTilte());
      Log.wtf("tag", "3 NAME??::" + "::" + CAT_IMAGE_IDS.get(3).getTilte());
      Log.wtf("tag", "4 NAME??::" + "::" + CAT_IMAGE_IDS.get(4).getTilte());
      Log.wtf("tag", "5 NAME??::" + "::" + CAT_IMAGE_IDS.get(5).getTilte());
      Log.wtf("tag", "6 NAME??::" + "::" + CAT_IMAGE_IDS.get(6).getTilte());
      Log.wtf("tag", "7 NAME??::" + "::" + CAT_IMAGE_IDS.get(7).getTilte());
      Log.wtf("tag", "8 NAME??::" + "::" + CAT_IMAGE_IDS.get(8).getTilte());
      Log.wtf("tag", "9 NAME??::" + "::" + CAT_IMAGE_IDS.get(9).getTilte());
      Log.wtf("tag", "10 NAME??::" + "::" + CAT_IMAGE_IDS.get(10).getTilte());
    }
    if (CAT_IMAGE_IDS.size() == 12) {
      Log.wtf("tag", "0 NAME??::" + "::" + CAT_IMAGE_IDS.get(0).getTilte());
      Log.wtf("tag", "1 NAME??::" + "::" + CAT_IMAGE_IDS.get(1).getTilte());
      Log.wtf("tag", "2 NAME??::" + "::" + CAT_IMAGE_IDS.get(2).getTilte());
      Log.wtf("tag", "3 NAME??::" + "::" + CAT_IMAGE_IDS.get(3).getTilte());
      Log.wtf("tag", "4 NAME??::" + "::" + CAT_IMAGE_IDS.get(4).getTilte());
      Log.wtf("tag", "5 NAME??::" + "::" + CAT_IMAGE_IDS.get(5).getTilte());
      Log.wtf("tag", "6 NAME??::" + "::" + CAT_IMAGE_IDS.get(6).getTilte());
      Log.wtf("tag", "7 NAME??::" + "::" + CAT_IMAGE_IDS.get(7).getTilte());
      Log.wtf("tag", "8 NAME??::" + "::" + CAT_IMAGE_IDS.get(8).getTilte());
      Log.wtf("tag", "9 NAME??::" + "::" + CAT_IMAGE_IDS.get(9).getTilte());
      Log.wtf("tag", "10 NAME??::" + "::" + CAT_IMAGE_IDS.get(10).getTilte());
      Log.wtf("tag", "11 NAME??::" + "::" + CAT_IMAGE_IDS.get(11).getTilte());
    }
    if (CAT_IMAGE_IDS.size() == 13) {
      Log.wtf("tag", "0 NAME??::" + "::" + CAT_IMAGE_IDS.get(0).getTilte());
      Log.wtf("tag", "1 NAME??::" + "::" + CAT_IMAGE_IDS.get(1).getTilte());
      Log.wtf("tag", "2 NAME??::" + "::" + CAT_IMAGE_IDS.get(2).getTilte());
      Log.wtf("tag", "3 NAME??::" + "::" + CAT_IMAGE_IDS.get(3).getTilte());
      Log.wtf("tag", "4 NAME??::" + "::" + CAT_IMAGE_IDS.get(4).getTilte());
      Log.wtf("tag", "5 NAME??::" + "::" + CAT_IMAGE_IDS.get(5).getTilte());
      Log.wtf("tag", "6 NAME??::" + "::" + CAT_IMAGE_IDS.get(6).getTilte());
      Log.wtf("tag", "7 NAME??::" + "::" + CAT_IMAGE_IDS.get(7).getTilte());
      Log.wtf("tag", "8 NAME??::" + "::" + CAT_IMAGE_IDS.get(8).getTilte());
      Log.wtf("tag", "9 NAME??::" + "::" + CAT_IMAGE_IDS.get(9).getTilte());
      Log.wtf("tag", "10 NAME??::" + "::" + CAT_IMAGE_IDS.get(10).getTilte());
      Log.wtf("tag", "11 NAME??::" + "::" + CAT_IMAGE_IDS.get(11).getTilte());
      Log.wtf("tag", "12 NAME??::" + "::" + CAT_IMAGE_IDS.get(12).getTilte());
    }
    if (CAT_IMAGE_IDS.size() == 14) {
      Log.wtf("tag", "0 NAME??::" + "::" + CAT_IMAGE_IDS.get(0).getTilte());
      Log.wtf("tag", "1 NAME??::" + "::" + CAT_IMAGE_IDS.get(1).getTilte());
      Log.wtf("tag", "2 NAME??::" + "::" + CAT_IMAGE_IDS.get(2).getTilte());
      Log.wtf("tag", "3 NAME??::" + "::" + CAT_IMAGE_IDS.get(3).getTilte());
      Log.wtf("tag", "4 NAME??::" + "::" + CAT_IMAGE_IDS.get(4).getTilte());
      Log.wtf("tag", "5 NAME??::" + "::" + CAT_IMAGE_IDS.get(5).getTilte());
      Log.wtf("tag", "6 NAME??::" + "::" + CAT_IMAGE_IDS.get(6).getTilte());
      Log.wtf("tag", "7 NAME??::" + "::" + CAT_IMAGE_IDS.get(7).getTilte());
      Log.wtf("tag", "8 NAME??::" + "::" + CAT_IMAGE_IDS.get(8).getTilte());
      Log.wtf("tag", "9 NAME??::" + "::" + CAT_IMAGE_IDS.get(9).getTilte());
      Log.wtf("tag", "10 NAME??::" + "::" + CAT_IMAGE_IDS.get(10).getTilte());
      Log.wtf("tag", "11 NAME??::" + "::" + CAT_IMAGE_IDS.get(11).getTilte());
      Log.wtf("tag", "12 NAME??::" + "::" + CAT_IMAGE_IDS.get(12).getTilte());
      Log.wtf("tag", "13 NAME??::" + "::" + CAT_IMAGE_IDS.get(13).getTilte());
    }
    if (CAT_IMAGE_IDS.size() == 15) {
      Log.wtf("tag", "0 NAME??::" + "::" + CAT_IMAGE_IDS.get(0).getTilte());
      Log.wtf("tag", "1 NAME??::" + "::" + CAT_IMAGE_IDS.get(1).getTilte());
      Log.wtf("tag", "2 NAME??::" + "::" + CAT_IMAGE_IDS.get(2).getTilte());
      Log.wtf("tag", "3 NAME??::" + "::" + CAT_IMAGE_IDS.get(3).getTilte());
      Log.wtf("tag", "4 NAME??::" + "::" + CAT_IMAGE_IDS.get(4).getTilte());
      Log.wtf("tag", "5 NAME??::" + "::" + CAT_IMAGE_IDS.get(5).getTilte());
      Log.wtf("tag", "6 NAME??::" + "::" + CAT_IMAGE_IDS.get(6).getTilte());
      Log.wtf("tag", "7 NAME??::" + "::" + CAT_IMAGE_IDS.get(7).getTilte());
      Log.wtf("tag", "8 NAME??::" + "::" + CAT_IMAGE_IDS.get(8).getTilte());
      Log.wtf("tag", "9 NAME??::" + "::" + CAT_IMAGE_IDS.get(9).getTilte());
      Log.wtf("tag", "10 NAME??::" + "::" + CAT_IMAGE_IDS.get(10).getTilte());
      Log.wtf("tag", "11 NAME??::" + "::" + CAT_IMAGE_IDS.get(11).getTilte());
      Log.wtf("tag", "12 NAME??::" + "::" + CAT_IMAGE_IDS.get(12).getTilte());
      Log.wtf("tag", "13 NAME??::" + "::" + CAT_IMAGE_IDS.get(13).getTilte());
      Log.wtf("tag", "14 NAME??::" + "::" + CAT_IMAGE_IDS.get(14).getTilte());
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

  public void dummyNbr(CatAdapter ca) {
    WidgetItem wi = new WidgetItem(true);
    List<WidgetItem> theList = ca.CAT_IMAGE_IDS;
    int existingDummies = 0;
    int counter = 1;
    int neededDummies = 0;
    int nbrNoDummy = 0;

//    popup(wi, (CatAdapter) catAdapter);


    if (theList.size() == 0) {
//      ca.CAT_IMAGE_IDS.add(wi);
//      ca.CAT_IMAGE_IDS.add(wi);
//      ca.CAT_IMAGE_IDS.add(wi);
      neededDummies = 1;
    }


    for (int i = 0; i < theList.size(); i++) {
      Log.wtf("tag", "is 0 a dummy::" + theList.get(0).getDummy());
      Log.wtf("tag", "is 1 a dummy::" + theList.get(1).getDummy());
      if (theList.get(0).getDummy())
        existingDummies++;
      if (existingDummies > 2)
        existingDummies = existingDummies - 2;
    }


    Log.wtf("tag", "THE LIST SIZE::" + theList.size());
    for (int i = 2; i <= theList.size() - existingDummies; i++) {
      switch (counter) {
        case 1: {
          neededDummies = 3;
        }
        break;
        case 2: {
          neededDummies = 2;
        }
        break;
        case 3: {
          neededDummies = 1;
        }
      }
      counter++;
    }
    ca.dummyNbr = neededDummies;
//    Log.wtf("tag","number of dummies in ADAPTERRRRRRRRRRRRR"+neededDummies);

//    ca.CAT_IMAGE_IDS.add(wi);
//
    for (int i = 0; i < theList.size() - 1; i++) {
      if (theList.get(i).getDummy() == false) {
        nbrNoDummy++;
        Log.wtf("tag", "NUMBER OF  ITEM (NO  DUMMIES)" + nbrNoDummy);
      }
//        existingDummies++;
//      if(existingDummies>2)
//        existingDummies=existingDummies-2;
    }
//
//    if(existingDummies!=neededDummies)
//      dummyNbr(ca);

    Log.wtf("tag", "number of dummies:LAST:" + existingDummies);
    Log.wtf("tag", "number of dummies needed::" + neededDummies);
  }

  public void firebaseStaticConfig() {
//    final GreenLand[] wo = {null};
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef2 = database.getReference().child("Green_Land");

    Realm.init(WelcomeScreen.this);

    RealmConfiguration realmConfig = new RealmConfiguration.Builder()
      .name("test1.realm")
      .schemaVersion(0)
//      .deleteRealmIfMigrationNeeded()
      .build();

    Realm.setDefaultConfiguration(realmConfig);

    realm = Realm.getInstance(realmConfig);
//
    realm.executeTransaction(new Realm.Transaction() {
      @Override
      public void execute(Realm realm) {
//                  for(int i = 0; i<10;i++){
//                    final GreenLand wo = realm.createObject(GreenLand.class);
//                    wo.setTemperature(i+i);
//                    wo.setHumidity(i+5f);
//                    realm.insertOrUpdate(wo);
//                  }

//        realm.deleteAll();//in case of deletion
      }
    });

//
    RealmResults<GreenLand> result = realm.where(GreenLand.class).findAll();
    Log.wtf("tag", "WHAT WE got here UI THREAD??" + result.size());


    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {

        myRef2.addListenerForSingleValueEvent(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            Log.wtf("tag", "STATIC  MOFOOOOOOOOOO:::" + dataSnapshot.getChildrenCount());
            for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {
//          devicesName.add(snapshot.getKey());

//          List<String> list = new ArrayList<String>(2);
//          list.add(snapshot.getKey());
//          devicesName.add(list);

//              realm.executeTransaction(new Realm.Transaction() {
//                @Override
//                public void execute(Realm realm) {
//                  final GreenLand wo = realm.createObject(GreenLand.class);
//                  wo.setTemperature(000);
//                  wo.setHumidity(0000f);
//                  realm.insertOrUpdate(wo);
////                  realm.deleteAll();//in case of deletion
//                }
//              });


              try {
                realm.executeTransaction(new Realm.Transaction() {
                  @Override
                  public void execute(Realm realm) {
                    GreenLand wo = new GreenLand();


                    if (snapshot.child("Time").toString() != null && snapshot.child("Temperature") != null) {

//                      wo.setTime(snapshot.child("Time").getValue().toString());
//                      wo.setHumidity(Float.valueOf(snapshot.child("Humidity").getValue().toString()));
//                      wo.setTemperature(Float.valueOf(snapshot.child("Temperature").getValue().toString()));
//                      wo.setBattery(Double.valueOf(snapshot.child("Battery_Level").getValue().toString()));


//                wo.setTemperature(69);
//                      realm.insertOrUpdate(wo);
                    }
//                    realm.deleteAll();//in case of deletion
                  }
                });

//                RealmResults<GreenLand> result = realm.where(GreenLand.class).findAllAsync();
//                Log.wtf("tag", "WHAT WE got here??" + result.size());

//                if (wo[0].getTime().equals("15 19 46 2019 4 20"))
//                  Log.wtf("tag", "TIME value????  " + wo[0].getTime());
              } catch (Exception e) {
                e.printStackTrace();
              }
            }

//        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//          Log.wtf("tag", "keyValue::" + snapshot.getKey());
//          try{
//          String time = snapshot.child("rxInfo/0/time").getValue().toString();
//
//          Log.wtf("tag", "time ::" + "" + "::" + time);
//          }catch (NullPointerException e){
//            Log.wtf("tag","NUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUULLLLLLLLLLLLLLLL"+snapshot.getKey());
//          }
//        }
          }

          @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.wtf("tag", "WHO  CANCELED  ITTTTTTTT????????,," + databaseError);
          }
        });

      }

    });
    thread.start();

//    realm.addChangeListener(new RealmChangeListener<Realm>() {
//      @Override
//      public void onChange(Realm realm) {
//        Log.wtf("tag", "DATABSE CHANGEDDDDD");
//      }
//    });


  }

  public void firebaseConf() {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
//    DatabaseReference myRef = database.getReference("END_DEVICE_1/-LayOAdQUV042yC6hfPE/object");
    DatabaseReference timeRef = database.getReference();
    DatabaseReference ref_GreenLand = database.getReference().child("Green_Land");

    Query query1 = ref_GreenLand.limitToLast(1);


//    Log.wtf("tag", "size::" + myRef.getKey());


//    myRef.addValueEventListener(new ValueEventListener() {

//    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
//      @Override
//      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//          Log.wtf("tag", "keyValue::" + snapshot.getKey());
//          try{
//          String time = snapshot.child("rxInfo/0/time").getValue().toString();
//
//          Log.wtf("tag", "time ::" + "" + "::" + time);
//          }catch (NullPointerException e){
//            Log.wtf("tag","NUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUULLLLLLLLLLLLLLLL"+snapshot.getKey());
//          }
////          i++;
//        }
//      }
//
//      @Override
//      public void onCancelled(@NonNull DatabaseError databaseError) {
//
//      }
//    });


//    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
//        @Override
//        public void onDataChange(DataSnapshot dataSnapshot) {
//          int i = 0;
//*************************************************************************
//        GreenLand wo = dataSnapshot.getValue(GreenLand.class);
//        Log.wtf("tag", "something???"+wo.getHumidity());
//        for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
//          childDataSnapshot.child("object");
//          GreenLand wo = dataSnapshot.getValue(GreenLand.class);
//          Log.wtf("tag","something:battery:"+wo.getBattery());
//          Log.wtf("tag","something::"+childDataSnapshot.child("-LayOAdQUV042yC6hfPE").child("object").getKey());
//        }
//*************************************************************************
//          Log.wtf("tag", "size:OLD WAYAAAA:" + dataSnapshot.getChildrenCount());
//          for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//            Log.wtf("tag", "keyValue:OLD WAYAAAA:" + snapshot.getKey());
//            GreenLand wo = snapshot.child("object").getValue(GreenLand.class);
//            Log.wtf("tag", "humidity:OLD WAYAAAA:" + i + "::" + wo.getHumidity());
//            i++;
//          }
//
//        }
//
//        @Override
//        public void onCancelled(DatabaseError databaseError) {
//          Log.wtf("tag", "CANCELLLED"+databaseError.getMessage());
//        }
//      });

    /*myRef.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        Log.wtf("tag","NUMBEEEEEEEEEEEEEEEEER ONE????????????????????????????????????????????????????????????????????????????????????????????????");
//        Log.wtf("tag","count"+dataSnapshot.getChildrenCount());
//        GreenLand wo = dataSnapshot.getValue(GreenLand.class);
        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//            Log.wtf("tag", "count and it should be 4::" + snapshot.child("object").getChildrenCount());

          Log.wtf("tag", "time::" + snapshot.child("rxInfo/0/time").getValue());
//            GreenLand wo = snapshot.child("object").getValue(GreenLand.class);
//            Log.wtf("tag", "humidity::" +   "::" + wo.getHumidity());

        }
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });*/


    /**
     * For retrieving date+time data from each device
     */
    timeRef.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        Log.wtf("tag", "it should be 4::" + dataSnapshot.getChildrenCount());


        for (final DataSnapshot childNode : dataSnapshot.getChildren()) {
          DatabaseReference childRef = FirebaseDatabase.getInstance().getReference().child(childNode.getKey());
          Query query = childRef.limitToLast(1);
          query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
              try {
                //for the sake of maintaining only one copy of each of the devices inside the list, the old value must be removed
                for (int i = 0; i < devicesName.size(); i++) {
                  if (devicesName.get(i).get(0) == childNode.getKey()) {
                    devicesName.remove(i);
                  }
                }

                //creating a list each time new data get added, therefore the newly created list will contain the node's name and the "Time" value inside of it
                List<String> listQuery = new ArrayList<>();
                listQuery.add(0, childNode.getKey());
                listQuery.add(1, dataSnapshot.child("Time").getValue().toString());

                devicesName.add(listQuery);
                Log.wtf("tag", "DEVICE NAME SIZE::" + devicesName.size());

                //creating new WidgetItem and insert the big list inside of it
                for (int i = 0; i < ca2.getObjectList().size(); i++) {
                  if (ca2.getObjectList().get(i).getType() == "Info") {
                    WidgetItem wi = ca2.getObjectList().get(i);
                    wi.setInfo(devicesName);
                    ca2.updateOneObject(i, wi);
                    try {

//                      Log.wtf("tag", "WHAT  We Got iNSIde::" + wi.getInfo());
                    } catch (IndexOutOfBoundsException e) {
                      Log.wtf("tag", "SOMETHING NOT RIGHT!!!");
                    }
                  }
                }

              } catch (NullPointerException e) {
                Log.wtf("tag", "NO TIME  DATA");
              }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
          });
        }
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });

    query1.addChildEventListener(new ChildEventListener() {
      @Override
      public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Log.wtf("tag", "ADDED");

        try {
          Log.wtf("tag", "key::" + dataSnapshot.getKey());
          Log.wtf("tag", "CA2::" + ca2.getItemCount());
//          liveData.setText(dataSnapshot.child("Time").getValue().toString());

          for (int i = 0; i < ca2.getObjectList().size(); i++) {
            Log.wtf("tat", "INSDIE  THE FOR LOOP");
            if ((ca2.getObjectList().get(i).getDisplayValue() == "Temperature" && ca2.getObjectList().get(i).getType() == "Widget_G")) {
              Log.wtf("tat", "NOW  WE  CHAnGE  THE WIdGET  DATA");
              ArcProgress cp = (ArcProgress) ca2.getObjectList().get(i).getWidget();
              cp.setBottomText("LMBAO");
              cp.setProgress(6666);
              WidgetItem wi = ca2.getObjectList().get(i);
              wi.setWidget(cp);
              ca2.updateOneObject(i, wi);
//              ca.notifyItemInserted(0);
//              Log.wtf("tag", "WE GOT THEMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM:::" + i);
            }
          }

//          try {
//            listQuery1.remove(1);
//          } catch (Exception e) {
//            Log.wtf("tag", "there's nothing to delete in this position");
//          }
//
//          listQuery1.add(1, dataSnapshot.child("Time").getValue().toString());
//          devicesName.add(listQuery1);

//          Log.wtf("tag", "WHAT WE GOT INSDIE???::" + listQuery1);

//          }
        } catch (NullPointerException e) {
          Log.wtf("tag", "NYULLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
        }


      }

      @Override
      public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Log.wtf("tag", "CHANGED");
      }

      @Override
      public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
        Log.wtf("tag", "REMOVED");
      }

      @Override
      public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Log.wtf("tag", "MOVED");
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {
        Log.wtf("tag", "CANCELLLED" + databaseError.getMessage());
      }
    });

  }


  public void popup(WidgetItem wi, CatAdapter ca) {
    int existingDummies = 0;
    ca.dammyFirst = false;
    Log.wtf("tag", "size in meth launch::" + ca.CAT_IMAGE_IDS.size());
//    CatAdapter ca = (CatAdapter) catAdapter;
//    ca.CAT_IMAGE_IDS.add(R.drawable.ic_menu_camera);
//    if (tempCounter == 0) {
//      ca.CAT_IMAGE_IDS.add(0, wi);
//      Log.wtf("tag", "size after adding in i==0::" + ca.CAT_IMAGE_IDS.size());
//    }
//    if (tempCounter == 1) {
//      ca.CAT_IMAGE_IDS.add(0,wi);
//      Log.wtf("tag", "size after adding in i==1::" + ca.CAT_IMAGE_IDS.size());
//    }
//    if (tempCounter == 2) {
//      ca.CAT_IMAGE_IDS.add(0,wi);
//      Log.wtf("tag", "size after adding in i==2::" + ca.CAT_IMAGE_IDS.size());
//    }
    ca.CAT_IMAGE_IDS.add(0, wi);
    tempCounter++;
    for (int i = 0; i < ca.CAT_IMAGE_IDS.size(); i++) {

      if (ca.CAT_IMAGE_IDS.get(0).getDummy())
        existingDummies++;
      if (existingDummies > 2)
        existingDummies = existingDummies - 2;
    }
    Log.wtf("tag", "nbr OF DUMMMMMMMMMMMMIESSS::" + existingDummies);
//    Log.wtf("tag", "is 1 a dummy::" + ca.CAT_IMAGE_IDS.get(1).getDummy());
//    dummyNbr(ca);
    ca.notifyItemInserted(ca.CAT_IMAGE_IDS.size());
  }

  public void createCard(final CatAdapter ca) {
    int i = 0;

    final Dialog dialog = new Dialog(this);
    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dailog_shape);
//    View v = dialog.getWindow().getDecorView();
//    v.setBackground(dialog.getContext().getResources().getDrawable(R.drawable.dailog_shape));
//
//    firstDialogLayout(dialog, ca);


    dialog.show();
//


//   ca.CAT_IMAGE_IDS.add(R.drawable.ic_menu_camera);
//   ca.notifyItemInserted(ca.CAT_IMAGE_IDS.size() -1);

//    obj1.setImgId(R.drawable.ic_menu_camera);
//    obj1.setFoodItem("Camera");
//    GreenLand obj2 = new GreenLand();
//    obj2.setFoodItem("Share");
//    obj2.setImgId(R.drawable.ic_menu_share);
//    foodList.add(""+i);
//    foodList.add(""+i+1);
    i = i + 1;
//    Log.wtf("tag", "list::size::" + foodList.size());

//    foodList.add(obj2);
//    foodAdapter.notifyItemInserted(foodList.size() - 1);
//    Log.wtf("tag", "adapter size??::" + foodAdapter.getItemCount());
  }

  public void firstDialogLayout(final Dialog dialog, final CustomAdapter ca) {
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
        }

        if (v.equals(card2)) {
          _selected = 2;
          dialog.setContentView(R.layout.popup_body2);
          secondDialogLayout(dialog, ca);
        }
        if (v.equals(card3)) {
          _selected = 3;
//          dialog.setContentView(R.layout.popup_body2);
//          secondDialogLayout(dialog, ca);
        }

        if (v.equals(card4)) {
          _selected = 4;
          dialog.setContentView(R.layout.popup_body_info);
          infoDialogLayout(dialog, ca);
        }


      }
    };
    card1.setOnClickListener(clickAble);
    card2.setOnClickListener(clickAble);
    card3.setOnClickListener(clickAble);
    card4.setOnClickListener(clickAble);

    th.noAnimation();
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public void infoDialogLayout(final Dialog dialog, final CustomAdapter ca) {
    ImageButton btnBack = (ImageButton) dialog.findViewById(R.id.btnBack);
    Button btn_add = dialog.findViewById(R.id.btn_add);
    LinearLayout boxHolder = dialog.findViewById(R.id.box_holder);
    btnBack.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        dialog.setContentView(R.layout.popup_body);
        firstDialogLayout(dialog, ca);
      }
    });
//    firebaseStaticConfig();
//
//    if (devicesName != null && devicesName.size() > 0) {
//      for (List list : devicesName) {
//        Log.wtf("tag", "name" + list);
//      }
//      Log.wtf("tag","INSIDE THE LOOP");
    for (List deviceList : devicesName) {
//        Log.wtf("tag", "THE REAL  DEAL??"+deviceList.get(0));
      CheckBox name = new CheckBox(this);

      name.setTextColor(getResources().getColor(R.color.lightBrown));
      int states[][] = {{android.R.attr.state_checked}, {}};
      int colors[] = {getResources().getColor(R.color.lightBrown), Color.GRAY};
      name.setButtonTintList(new ColorStateList(states, colors));

      name.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
      name.setText(deviceList.get(0).toString());
      boxHolder.addView(name);
      View v = new View(this);
      LinearLayout ln = new LinearLayout(this);
      ln.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, 5));
      ln.setBackgroundColor(Color.GRAY);
      v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 5));
      boxHolder.addView(ln);

    }

    btn_add.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        WidgetItem wi = new WidgetItem();
        wi.setType("Info");
        wi.isWidget(true);
        wi.setSize(2);

        addingWidget(ca, wi);

//        for (String)
      }
    });

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

        picker.setWrapSelectorWheel(true);
        vals1[0] = SWR;
        vals2 = endDevies;
      }
      break;
      case 2: {
        vals1[0] = TPHB;
        vals2 = endDevies;
      }

    }

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
            Thermometer thermo = new Thermometer(WelcomeScreen.this);
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
            ArcProgress cp = new ArcProgress(WelcomeScreen.this);
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

  public void flexlayout(CustomAdapter ca2) {
    flexboxLayoutManager = new FlexboxLayoutManager(WelcomeScreen.this);
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
    ca2.setHasStableIds(true);
    /*for performance*/
    recyclerView.setAdapter(ca2);
//    Log.wtf("tag","adapter sizzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzze::::"+recyclerView.getChildCount());

//    ca.notifyDataSetChanged();
  }


  public void networkthingy() {
/*************************************/
    GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
//    Call<List<GreenLand>> call = service.getAllUsers("13t9mgcgqozdvv1axunet59v91o189ta68hchsuwz88063aqjkhttp://192.168.148.1");
    Call<GreenLand> call = service.getObject();
    call.enqueue(new Callback<GreenLand>() {


      //Handle a successful response//
      //      public void onResponse(Call<List<GreenLand>> call, Response<List<GreenLand>> response) {
      @Override
      public void onResponse(Call<GreenLand> call, Response<GreenLand> response) {
        if (response.isSuccessful()) {
//          testTV.setText("Successful");

          Log.wtf("tag", "code::" + response.code());
          Log.wtf("tag", "RAWW::" + response.raw());
          GreenLand testObj = response.body();
          //the below line should be working, but the object class is just modified
//          Log.wtf("tag", "temp" + testObj.getTemp());
          Log.wtf("tag", "pressure" + testObj.getPressure());
        } else {
          testTV.setText("not successful!!!");
//        loadDataList(response.body());
          Log.wtf("tag", "error::" + response.raw());

        }

      }

      @Override
      public void onFailure(Call<GreenLand> call, Throwable throwable) {
//        testTV.setText("FAILED");
        Log.wtf("tag", "FAILED CAUSE::" + throwable.getCause());
        Log.wtf("tag", "error message::" + throwable.getMessage());
      }
    });


  }

  public void demoHolder() {
    //make the status bar text color grey(to be visible with light background)
    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.grey));
      getWindow().setNavigationBarColor(getResources().getColor(R.color.darkBlue));
      getWindow().getDecorView().setSystemUiVisibility(FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS /*| View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR */ | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);

    }

    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//      getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);

    }

//    setSupportActionBar(toolbar);
//    getSupportActionBar().setTitle(null);
//    toolbarTitle.setText("REEEE??");
//    toolbar.setBackgroundColor(getResources().getColor(R.color.white_ish));

  }


  @Override
  public void onBackPressed() {
    this.finish();
    startActivity(new Intent(WelcomeScreen.this, LaunchScreen.class));

  }

  public void setStatusBarTrasparent() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      Window w = getWindow();
      w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
  }


  @Override
  public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
    mItemTouchHelper.startDrag(viewHolder);
  }


  @Override
  public void onItemClicked(View view, int position) {
    Log.wtf("tag", "detect clickkkkkkkkkkkkkkkkkkkkkkkkkk");
  }

  public void colorChange(final ViewGroup tc, final Button btn) {

    btn.setText("SAVING");
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        if (cantStopMeNow) {
          btn.setTextColor(btn.getResources().getColor(R.color.white_ish));
          btn.setBackground(new ColorDrawable(btn.getResources().getColor(R.color.orange)));
          cantStopMeNow = false;
        } else {
          btn.setTextColor(btn.getResources().getColor(R.color.orange));
          btn.setBackground(new ColorDrawable(btn.getResources().getColor(R.color.white_ish)));
          cantStopMeNow = true;
        }

      }
    };

    scheduler = new ScheduledThreadPoolExecutor(1);
    scheduler.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
  }


}


