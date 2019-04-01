package com.example.touch_me.pfe_project;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
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

import java.security.cert.PolicyQualifierInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yalantis.com.sidemenu.model.SlideMenuItem;
import yalantis.com.sidemenu.util.ViewAnimator;

import static android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS;


public class WelcomeScreen extends AppCompatActivity implements OnStartDragListener {

  private DrawerLayout drawerLayout;
  private LinearLayout bigdady;
  private LinearLayout linearLayout;
  private List<SlideMenuItem> list = new ArrayList<>();
  private ArrayList<String> itemList = new ArrayList<String>();
  private ContentFragment contentFragment;
  private ViewAnimator viewAnimator;
  private ActionBarDrawerToggle drawer;
  private ImageButton btn_settings;
  private DrawerLayout.DrawerListener dl;
  private Toolbar toolbar;
  private TextView toolbarTitle;
  private View portal;
  private RecyclerListFragment fragment = null;
  private ItemTouchHelper mItemTouchHelper;
  RecyclerView rvFoodItems;
  RecyclerListAdapter foodAdapter;
  ArrayList<WidgetObject> foodList;
  RecyclerView recyclerView;
  TextView testTV;
  private FlexboxLayoutManager flexboxLayoutManager;
  RecyclerView.Adapter catAdapter;
  Button btn_add;
  //  Thermometer thermo;
  DatabaseReference myRef;
  private Query queryObj;
  boolean FireShot = false;
  private TextView liveData;
  private int _selected;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_welcome_screen);

// ...
//    DatabaseReference userDBRef = FirebaseDatabase.getInstance().getReference();
//    userDBRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://url_of_my_database");


    setStatusBarTrasparent();
//    setupWindowAnimations();
    overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

    drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    View portal = findViewById(R.id.portal);
    bigdady = (LinearLayout) findViewById(R.id.big_dady);
    btn_settings = (ImageButton) findViewById(R.id.btn_settings);


    toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbarTitle = (TextView) findViewById(R.id.toolbar_title);

    demoHolder();
    btn_settings.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        createCard((CatAdapter) catAdapter);
        FireShot = true;

      }
    });
    testTV = (TextView) findViewById(R.id.testTV);
//    thermo = (Thermometer) findViewById(R.id.thermo);
    recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    btn_add = (Button) findViewById(R.id.btn_add);
    liveData = (TextView) findViewById(R.id.liveData);


    //**************************************NEW ERA******************


//    rvFoodItems.setHasFixedSize(true);
    //    rvFoodItems.setItemAnimator(new DefaultItemAnimator());
//    foodList = new ArrayList<String>();

/**************************WORKS******************************/
//    rvFoodItems = (RecyclerView) findViewById(R.id.rvFoodItems);
//
//    rvFoodItems.setLayoutManager(new LinearLayoutManager(this));
//
    foodAdapter = new RecyclerListAdapter(foodList, this);
//    rvFoodItems.setAdapter(foodAdapter);
//
//    ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(foodAdapter);
//    mItemTouchHelper = new ItemTouchHelper(callback);
//    mItemTouchHelper.attachToRecyclerView(rvFoodItems);
/***********************WORKS*************************/
    flexlayout(foodAdapter);
    btnAddPositioning((CatAdapter) catAdapter);
//    networkthingy();
//    firebaseConf();


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

  public void btnAddPositioning(CatAdapter ca) {
    int size = ca.CAT_IMAGE_IDS.size();


  }

  public void firebaseConf() {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
//    DatabaseReference myRef = database.getReference("END_DEVICE_1/-LayOAdQUV042yC6hfPE/object");
    DatabaseReference myRef = database.getReference().child("END_DEVICE_1");
    Query query = myRef.limitToLast(1);
    Log.wtf("tag", "size::" + myRef.getKey());


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


    query.addChildEventListener(new ChildEventListener() {
      @Override
      public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Log.wtf("tag", "ADDED");

//                DataSnapshot data1 = dataSnapshot.child(dataSnapshot.getKey()).child("/object/humidity");
////
//        Log.wtf("tag","valueeeeeee::"+data1.getValue().toString());


//        String key = dataSnapshot.getKey();
//        String path = "\""+"/"+key+"/object/humidity"+"\"";
//        Log.wtf("tag","PATH::"+path);

//        Log.wtf("tag", dataSnapshot.child(path).getValue());
//          WidgetObject wo = dataSnapshot.child(path).getValue(WidgetObject.class);
//          Log.wtf("tag", "humidity::" +   "::" + wo.getHumidity());
//
        try {
//          for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//          Log.wtf("tag", "count and it should be 4::" + snapshot.getChildrenCount());
//            WidgetObject wo = dataSnapshot.child("object").getValue(WidgetObject.class);
//            Log.wtf("tag", "humidity::" +   "::" + wo.getBattery());
          Log.wtf("tag", "key::" + dataSnapshot.getKey());
          liveData.setText(dataSnapshot.child("object/temperature").getValue().toString());
          Log.wtf("tag", "key::" + dataSnapshot.child("object/temperature").getValue());

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

  void moveItem(int oldPos, int newPos) {
//    WidgetObject fooditem = foodList.get(oldPos);
//
//    foodList.remove(oldPos);
//    foodList.add(newPos, fooditem);
//    foodAdapter.notifyItemMoved(oldPos, newPos);
  }

  void deleteItem(final int position) {
//    foodList.remove(position);
//    foodAdapter.notifyItemRemoved(position);

  }

  public void popup(WidgetItem wi, CatAdapter ca) {
//    CatAdapter ca = (CatAdapter) catAdapter;
//    ca.CAT_IMAGE_IDS.add(R.drawable.ic_menu_camera);
    ca.CAT_IMAGE_IDS.add(wi);
    ca.notifyItemInserted(ca.CAT_IMAGE_IDS.size());
  }

  public void createCard(final CatAdapter ca) {
    int i = 0;

    final Dialog dialog = new Dialog(this);
    firstDialogLayout(dialog, ca);


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

  public void firstDialogLayout(final Dialog dialog, final CatAdapter ca) {
    dialog.setContentView(R.layout.popup_body);

    Thermometer th = dialog.findViewById(R.id.thermo);
    final CardView card1 = dialog.findViewById(R.id.card1);
    final CardView card2 = dialog.findViewById(R.id.card2);
    CardView card3 = dialog.findViewById(R.id.card3);
    CardView card4 = dialog.findViewById(R.id.card4);
    PushDownAnim.setPushDownAnimTo(card1, card2, card3, card4);
    View.OnClickListener clickAble = new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (v.equals(card1)) {
          _selected = 1;
          dialog.setContentView(R.layout.popup_body2);
          secondDialogLayout(dialog,ca);
        }

        if (v.equals(card2)) {
          _selected = 2;
          dialog.setContentView(R.layout.popup_body2);
          secondDialogLayout(dialog,ca);
        }

      }
    };
    card1.setOnClickListener(clickAble);
    card2.setOnClickListener(clickAble);

    th.noAnimation();
  }

  public void secondDialogLayout(Dialog dialog, final CatAdapter ca) {
    String[] vals1 = {""};
    String[] vals2 = {""};
    final Button btnAdd = (Button) dialog.findViewById(R.id.btn_add);
    btnAdd.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        ArcProgress cp = new ArcProgress(WelcomeScreen.this);
        cp.setBottomText("Type1");
        cp.setProgress(10);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(180, 180);
        params.gravity = Gravity.CENTER;
        cp.setLayoutParams(params);
        WidgetItem wi = new WidgetItem(cp, "Type1", true);

        popup(wi, ca);
      }
    });
    NumberPicker picker = dialog.findViewById(R.id.picker1);
    NumberPicker picker2 = dialog.findViewById(R.id.picker2);
    picker.setMaxValue(3);
    picker.setMinValue(0);
    picker2.setMaxValue(3);
    picker2.setMinValue(0);

      switch (_selected){
        case 1:{

          picker.setWrapSelectorWheel(true);
          vals1 = new String[]{"Speed", "Wind", "Rain", "??!!?!"};
          vals2 = new String[]{"END_DEVICE1", "END_DEVICE2", "END_DEVICE3", "??!!?!"};
        }
        break;
        case 2:{
          vals1 = new String[]{"Temperature", "Pressure", "Humidity", "Battery"};
          vals2 = new String[]{"END_DEVICE1", "END_DEVICE2", "END_DEVICE3", "??!!?!"};
        }

      }

    picker.setDisplayedValues(vals1);
      picker2.setDisplayedValues(vals2);
    final String[] finalVals = vals1;
    picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
      @Override
      public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        Log.wtf("tag", "picked::" + finalVals[newVal]);
      }
    });
  }


  //***************************OLD ERA*********************

  public void flexlayout(RecyclerListAdapter foodAdapter) {
    flexboxLayoutManager = new FlexboxLayoutManager(WelcomeScreen.this);
    flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
    flexboxLayoutManager.setAlignItems(AlignItems.STRETCH);
    flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
    flexboxLayoutManager.setMaxLine(20);
    recyclerView.setLayoutManager(flexboxLayoutManager);
    catAdapter = new CatAdapter(this);

    /*for performance??????*/
    recyclerView.setItemViewCacheSize(20);
    recyclerView.setDrawingCacheEnabled(true);
    recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    catAdapter.setHasStableIds(true);
    /*for performance*/
    recyclerView.setAdapter(catAdapter);
    catAdapter.notifyDataSetChanged();
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
//    catAdapter.getItemViewType(0);
//    ca.innitFooter();
//    ca.getItemCount();
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
}


