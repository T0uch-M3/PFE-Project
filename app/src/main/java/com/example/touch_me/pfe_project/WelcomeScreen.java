package com.example.touch_me.pfe_project;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
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

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yalantis.com.sidemenu.model.SlideMenuItem;
import yalantis.com.sidemenu.util.ViewAnimator;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS;


public class WelcomeScreen extends AppCompatActivity implements OnStartDragListener, CatAdapter.RecyclerViewOnClickListener {

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

  ArrayList<WidgetObject> foodList;
  RecyclerView recyclerView;
  TextView testTV;
  private FlexboxLayoutManager flexboxLayoutManager;

  RecyclerView.Adapter catAdapter;
  /**
   * <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
   */

  Button btn_add;
  //  Thermometer thermo;
  DatabaseReference myRef;
  private Query queryObj;
  boolean FireShot = false;
  private TextView liveData;
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

    toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbarTitle = (TextView) findViewById(R.id.toolbar_title);

    testTV = (TextView) findViewById(R.id.testTV);
    recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    btn_add = (Button) findViewById(R.id.btn_add);
    liveData = (TextView) findViewById(R.id.liveData);


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
    ca2 = new CustomAdapter(this);


    flexlayout(ca2);
    demoHolder();


    addingWidget(ca2, null);

    ca2.setCustomObjectListener(new MyCustomObjectListener() {
      @Override
      public void onOptionButtonReady(View v, int position) {
        Log.wtf("tag", "BUTTON PRESSED AT  POSITIoN::" + position);
      }

      @Override
      public void onObjectReady() {
        Log.wtf("tag", "INITIATE LISTENER ONCE???????????");
        genesis(ca2);
      }
    });


//    networkthingy();

    firebaseConf();
    firebaseStaticConfig();

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


  public void genesis(CustomAdapter ca) {

    final Dialog dialog = new Dialog(this);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));

//    dialog.setTitle("LOL  WTH");
//    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//    dialog.setTitle("LOL  WTH");
    firstDialogLayout(dialog, ca);

    dialog.show();

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

      someMagic(ca, wItem);

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

  }

  public void someMagic(CustomAdapter ca, WidgetItem wItem) {
    int nbrDummy = 0;
    int addPos = 0;
    nbrItems = 0;
    int weSayJump = 0;
    int foundDummy = 0;




    for (int i = 0; i < ca.getItemCount(); i++) {
      Log.wtf("tag", ":::size inside loop::" + ca.getItemCount());

      if (ca.getObjectList().get(i).getType() == "Dummy")
        nbrDummy++;

      if (ca.getObjectList().get(i).isWidget() && ca.getObjectList().get(i).getSize() == 1)
        nbrItems++;
      if (ca.getObjectList().get(i).getSize() == 2)
        nbrItems++;
      if(ca.getObjectList().get(i).getType()=="TempHolder")
        nbrItems++;
    }
    Log.wtf("tag", "HOLY  ROW:" + holyRow);

    if(holyRow==2 && wItem.getSize()==2){
      WidgetItem tempHolder = new WidgetItem();
      tempHolder.setType("TempHolder");
      CAT_IMAGE_IDS.add(nbrItems,tempHolder);
      ca.setListObjects(CAT_IMAGE_IDS);
      nbrItems++;
    }


//    Log.wtf("tag", "number oooooof ITEMS:BEFORE  CHANGE:" + nbrItems);

//    if(size == 2 )//in the case of large widget
//      nbrItems++;

//    if (nbrItems % 3 == 0) {
    if(holyRow==0 || holyRow==3){

//        for (int i = 0; i < (2 - Math.sin(Math.PI / wItem.getSize())); i++) {
        for (int i = 0; i < 3 ; i++) {
          WidgetItem dummy = new WidgetItem();
          dummy.setType("Dummy");
        Log.wtf("tag", "ADDDDDDDDDDDDDING DUMMIES");
          dummy.setTilte("dummy" + i + nbrItems);
          CAT_IMAGE_IDS.add(ca.getItemCount() - 2, dummy);
          ca.setListObjects(CAT_IMAGE_IDS);
        }
    }




    if (wItem.getSize() == 2 && (holyRow==0 || holyRow==1 || holyRow==3)) {
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
      holyRow = holyRow+2;
    }

    if (wItem.getSize() == 1)
      holyRow ++;
    /**
     *
     */

    if (holyRow > 3){
      holyRow=wItem.getSize();

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
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef2 = database.getReference();

    myRef2.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        Log.wtf("tag", "COUNT  AND IT  SHOULD  BE  5 or something:::" + dataSnapshot.getChildrenCount());
        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//          devicesName.add(snapshot.getKey());

          List<String> list = new ArrayList<String>(2);
          list.add(snapshot.getKey());
          devicesName.add(list);

//          Log.wtf("tag", "name" + snapshot.getValue().toString());
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

      }
    });


  }

  public void firebaseConf() {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
//    DatabaseReference myRef = database.getReference("END_DEVICE_1/-LayOAdQUV042yC6hfPE/object");
    DatabaseReference ref_GreenLand = database.getReference().child("Green_Land");
    DatabaseReference ref_Room1 = database.getReference().child("System_Room_SOFIA");
    DatabaseReference ref_Room2_ = database.getReference().child("System_Room_SOFIA2");
    DatabaseReference ref_Station = database.getReference().child("Weather_Station_Final");

    Query query1 = ref_GreenLand.limitToLast(1);
    Query query2 = database.getReference().child("System_Room_SOFIA").limitToLast(1);
    Query query3 = database.getReference().child("System_Room_SOFIA2").limitToLast(1);
    Query query4 = database.getReference().child("Weather_Station_Final").limitToLast(1);

    final List<String> listQuery1 = new ArrayList<String>(2);
    listQuery1.add(0, "Green_Land");
    List<String> listQuery2 = new ArrayList<String>(2);
    List<String> listQuery3 = new ArrayList<String>(2);
    List<String> listQuery4 = new ArrayList<String>(2);

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
//        WidgetObject wo = dataSnapshot.getValue(WidgetObject.class);
//        Log.wtf("tag", "something???"+wo.getHumidity());
//        for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
//          childDataSnapshot.child("object");
//          WidgetObject wo = dataSnapshot.getValue(WidgetObject.class);
//          Log.wtf("tag","something:battery:"+wo.getBattery());
//          Log.wtf("tag","something::"+childDataSnapshot.child("-LayOAdQUV042yC6hfPE").child("object").getKey());
//        }
//*************************************************************************
//          Log.wtf("tag", "size:OLD WAYAAAA:" + dataSnapshot.getChildrenCount());
//          for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//            Log.wtf("tag", "keyValue:OLD WAYAAAA:" + snapshot.getKey());
//            WidgetObject wo = snapshot.child("object").getValue(WidgetObject.class);
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
//        WidgetObject wo = dataSnapshot.getValue(WidgetObject.class);
        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//            Log.wtf("tag", "count and it should be 4::" + snapshot.child("object").getChildrenCount());

          Log.wtf("tag", "time::" + snapshot.child("rxInfo/0/time").getValue());
//            WidgetObject wo = snapshot.child("object").getValue(WidgetObject.class);
//            Log.wtf("tag", "humidity::" +   "::" + wo.getHumidity());

        }
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });*/


    query1.addChildEventListener(new ChildEventListener() {
      @Override
      public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Log.wtf("tag", "ADDED");

        try {
          Log.wtf("tag", "key::" + dataSnapshot.getKey());
          Log.wtf("tag", "CA2::" + ca2.getItemCount());
          liveData.setText(dataSnapshot.child("Time").getValue().toString());

          for (int i = 0; i < ca2.getObjectList().size(); i++) {
            Log.wtf("tat", "INSDIE  THE FOR LOOP");
            if ((ca2.getObjectList().get(i).getDisplayValue() == "Temperature" && ca2.getObjectList().get(i).getType() == "Widget_G")) {
              Log.wtf("tat", "NOW  WE  CHAnGE  THE WIdGET  DATA");
              ArcProgress cp = (ArcProgress) ca2.getObjectList().get(i).getWidget();
              cp.setBottomText("LMBAO");
              cp.setProgress(6666);
              WidgetItem wi = ca2.getObjectList().get(i);
              wi.setWidget(cp);
              ca2.updatOneObject(i, wi);
//              ca.notifyItemInserted(0);
//              Log.wtf("tag", "WE GOT THEMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM:::" + i);
            }
          }

          try {
            listQuery1.remove(1);
          } catch (Exception e) {
            Log.wtf("tag", "there's nothing to delete in this position");
          }

          listQuery1.add(1, dataSnapshot.child("Time").getValue().toString());
          devicesName.add(listQuery1);

          Log.wtf("tag", "WHAT WE GOT INSDIE???::" + listQuery1);

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
//    WidgetObject obj2 = new WidgetObject();
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

//    if(devicesName!=null && devicesName.size()>0){
//    for(int i=0; i<8;i++){
//      Log.wtf("tag","name"+devicesName.get(0));
//    }
//      Log.wtf("tag","INDIDE THE LOOP");
//    for (String deviceName : devicesName) {
//      CheckBox name = new CheckBox(this);
//      name.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
//      name.setText(deviceName);
//      boxHolder.addView(name);
//      View v = new View(this);
//      LinearLayout ln = new LinearLayout(this);
//      ln.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, 5));
//      ln.setBackgroundColor(Color.GRAY);
//      v.setLayoutParams(new LinearLayout.LayoutParams(
//        LinearLayout.LayoutParams.MATCH_PARENT,
//        5
//      ));
//      boxHolder.addView(ln);
//    }

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
//    Call<List<WidgetObject>> call = service.getAllUsers("13t9mgcgqozdvv1axunet59v91o189ta68hchsuwz88063aqjkhttp://192.168.148.1");
    Call<WidgetObject> call = service.getObject();
    call.enqueue(new Callback<WidgetObject>() {


      //Handle a successful response//
      //      public void onResponse(Call<List<WidgetObject>> call, Response<List<WidgetObject>> response) {
      @Override
      public void onResponse(Call<WidgetObject> call, Response<WidgetObject> response) {
        if (response.isSuccessful()) {
//          testTV.setText("Successful");

          Log.wtf("tag", "code::" + response.code());
          Log.wtf("tag", "RAWW::" + response.raw());
          WidgetObject testObj = response.body();
          //the below line should be working, but the object class is just modified
//          Log.wtf("tag", "temp" + testObj.getTemp());
          Log.wtf("tag", "humidity" + testObj.getHumidity());
          Log.wtf("tag", "pressure" + testObj.getPressure());
        } else {
          testTV.setText("not successful!!!");
//        loadDataList(response.body());
          Log.wtf("tag", "error::" + response.raw());

        }

      }

      @Override
      public void onFailure(Call<WidgetObject> call, Throwable throwable) {
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

    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle(null);
    toolbarTitle.setText("REEEE??");
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
}


