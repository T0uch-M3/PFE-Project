package com.example.touch_me.pfe_project;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.touch_me.pfe_project.helper.OnStartDragListener;
import com.example.touch_me.pfe_project.helper.SimpleItemTouchHelperCallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.codetail.animation.ViewAnimationUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
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


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.content_test_nav);

    setStatusBarTrasparent();
//    setupWindowAnimations();
    overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
//    contentFragment = ContentFragment.newInstance(R.drawable.content_music);
//    getSupportFragmentManager().beginTransaction()
//      .replace(R.id.content_frame, contentFragment)
//      .commit();
    drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    View portal = findViewById(R.id.portal);
    bigdady = (LinearLayout) findViewById(R.id.big_dady);
    btn_settings = (ImageButton) findViewById(R.id.btn_settings);


    linearLayout = (LinearLayout) findViewById(R.id.left_drawer);
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbarTitle = (TextView) findViewById(R.id.toolbar_title);

    demoHolder();
    btn_settings.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        createCard();
      }
    });
    testTV = (TextView) findViewById(R.id.testTV);


    //**************************************NEW ERA******************


//    foodList = (ArrayList)DataSource.createListItems();


    rvFoodItems = (RecyclerView) findViewById(R.id.rvFoodItems);
//    rvFoodItems.setHasFixedSize(true);
    rvFoodItems.setLayoutManager(new LinearLayoutManager(this));
//    rvFoodItems.setItemAnimator(new DefaultItemAnimator());

//    foodList = new ArrayList<String>();
    foodAdapter = new RecyclerListAdapter(foodList, this);
    rvFoodItems.setAdapter(foodAdapter);

    ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(foodAdapter);
    mItemTouchHelper = new ItemTouchHelper(callback);
    mItemTouchHelper.attachToRecyclerView(rvFoodItems);

    networkthingy2();

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


  public void createCard() {
    int i = 0;
//    obj1.setImgId(R.drawable.ic_menu_camera);
//    obj1.setFoodItem("Camera");
//    WidgetObject obj2 = new WidgetObject();
//    obj2.setFoodItem("Share");
//    obj2.setImgId(R.drawable.ic_menu_share);
//    foodList.add(""+i);
//    foodList.add(""+i+1);
    i = i + 1;
    Log.wtf("tag", "list::size::" + foodList.size());

//    foodList.add(obj2);
    foodAdapter.notifyItemInserted(foodList.size() - 1);
    Log.wtf("tag", "adapter size??::" + foodAdapter.getItemCount());
  }


  //***************************OLD ERA*********************

  public void networkthingy2() {
/*************************************/
    GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
//    Call<List<WidgetObject>> call = service.getAllUsers("13t9mgcgqozdvv1axunet59v91o189ta68hchsuwz88063aqjkhttp://192.168.148.1");
    Call<WidgetObject> call = service.getAllUsers();
    call.enqueue(new Callback<WidgetObject>() {



//Handle a successful response//
      //      public void onResponse(Call<List<WidgetObject>> call, Response<List<WidgetObject>> response) {
      @Override
      public void onResponse(Call<WidgetObject> call, Response<WidgetObject> response) {
        if (response.isSuccessful()) {
          testTV.setText("Successful");

          Log.wtf("tag", "code::" + response.code());
          Log.wtf("tag", "RAWW::" + response.raw());
          WidgetObject fromJSON = response.body();
//          Log.wtf("tag", "name::" + response.body().get);
          Log.wtf("tag", "NNNNNNNNNNNNNAME"+fromJSON.getName());
//          Log.wtf("tag", "0::"+response.body().get(0).getName());
//          Log.wtf("tag", "1::"+response.body().get(1).getName());
//          testTV.setText("successful");
        } else {
          testTV.setText("not successful!!!");
//        loadDataList(response.body());
          Log.wtf("tag", "error::" + response.raw());

        }

      }

      @Override
      public void onFailure(Call<WidgetObject> call, Throwable throwable) {
        testTV.setText("FAILED");
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
      getWindow().getDecorView().setSystemUiVisibility(FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);

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


