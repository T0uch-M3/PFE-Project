package com.example.touch_me.pfe_project;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.model.SlideMenuItem;
import yalantis.com.sidemenu.util.ViewAnimator;


public class WelcomeScreen extends AppCompatActivity implements yalantis.com.sidemenu.util.ViewAnimator.ViewAnimatorListener {

  private DrawerLayout drawerLayout;
  private LinearLayout linearLayout;
  private List<SlideMenuItem> list = new ArrayList<>();
  private ContentFragment contentFragment;
  private ViewAnimator viewAnimator;
  private ActionBarDrawerToggle drawer;
  private Button btnPullDrawer;
  private DrawerLayout.DrawerListener dl;
  private Toolbar toolbar;
  private TextView toolbarTitle;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.nav_layout);

    setStatusBarTrasparent();
//    setupWindowAnimations();
    overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    contentFragment = ContentFragment.newInstance(R.drawable.content_music);
    getSupportFragmentManager().beginTransaction()
      .replace(R.id.content_frame, contentFragment)
      .commit();
    drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

    drawerLayout.setScrimColor(Color.TRANSPARENT);
    linearLayout = (LinearLayout) findViewById(R.id.left_drawer);
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbarTitle = (TextView) findViewById(R.id.toolbar_title);


    linearLayout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        drawerLayout.closeDrawers();
      }
    });
    setActionBar();
    createMenuList();

    viewAnimator = new ViewAnimator<>(this, list, contentFragment, drawerLayout, this);
    demoHolder();
  }

  public void demoHolder() {
    //make the status bar text color grey(to be visible with light background)
    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle(null);
    toolbarTitle.setText("<<Title>>");
  }

  private void setActionBar() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//    setSupportActionBar(toolbar);
//    getSupportActionBar().setHomeButtonEnabled(true);
//    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    dl = new DrawerLayout.DrawerListener() {
      //    drawer = new ActionBarDrawerToggle(WelcomeScreen.this, drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close ){
      @Override
      public void onDrawerSlide(View drawerView, float slideOffset) {
//        super.onDrawerSlide(drawerView, slideOffset);
        if (slideOffset > 0.6 && linearLayout.getChildCount() == 0)
          viewAnimator.showMenuContent();
      }

      @Override
      public void onDrawerOpened(@NonNull View drawerView) {

      }

      public void onDrawerClosed(View view) {
//        super.onDrawerClosed(view);
        linearLayout.removeAllViews();
        linearLayout.invalidate();
      }

      @Override
      public void onDrawerStateChanged(int newState) {

      }
    };
    drawerLayout.addDrawerListener(dl);
  }

  @Override
  public void onBackPressed() {

    startActivity(new Intent(WelcomeScreen.this, LaunchScreen.class));

  }

  public void setStatusBarTrasparent() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      Window w = getWindow();
      w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
  }

  private void createMenuList() {
    SlideMenuItem menuItem0 = new SlideMenuItem(ContentFragment.CLOSE, R.drawable.icn_close);
    list.add(menuItem0);
    SlideMenuItem menuItem = new SlideMenuItem(ContentFragment.BUILDING, R.drawable.icn_close);
    list.add(menuItem);
    SlideMenuItem menuItem2 = new SlideMenuItem(ContentFragment.BOOK, R.drawable.icn_close);
    list.add(menuItem2);
//    SlideMenuItem menuItem3 = new SlideMenuItem(ContentFragment.PAINT, R.drawable.icn_3);
//    list.add(menuItem3);
//    SlideMenuItem menuItem4 = new SlideMenuItem(ContentFragment.CASE, R.drawable.icn_4);
//    list.add(menuItem4);
//    SlideMenuItem menuItem5 = new SlideMenuItem(ContentFragment.SHOP, R.drawable.icn_5);
//    list.add(menuItem5);
//    SlideMenuItem menuItem6 = new SlideMenuItem(ContentFragment.PARTY, R.drawable.icn_6);
//    list.add(menuItem6);
//    SlideMenuItem menuItem7 = new SlideMenuItem(ContentFragment.MOVIE, R.drawable.icn_7);
//    list.add(menuItem7);
  }


  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
//    drawerToggle.syncState();
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
//    drawerToggle.onConfigurationChanged(newConfig);
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
//    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (super.onOptionsItemSelected(item)) {
      return true;
    }
    switch (item.getItemId()) {
      case R.id.action_settings:
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }

  }

  private ScreenShotable replaceFragment(ScreenShotable screenShotable, int topPosition) {
//    this.res = this.res == R.drawable.content_music ? R.drawable.content_films : R.drawable.content_music;
//    View view = findViewById(R.id.content_frame);
//    int finalRadius = Math.max(view.getWidth(), view.getHeight());
//    SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
//    animator.setInterpolator(new AccelerateInterpolator());
//    animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);

    findViewById(R.id.content_overlay).setBackgroundDrawable(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
//    animator.start();
//    ContentFragment contentFragment = ContentFragment.newInstance(this.res);
    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, contentFragment).commit();
    return contentFragment;
  }

  @Override
  public ScreenShotable onSwitch(Resourceble slideMenuItem, ScreenShotable screenShotable, int position) {
    switch (slideMenuItem.getName()) {
      case ContentFragment.CLOSE:
        return screenShotable;
      default:
        return replaceFragment(screenShotable, position);
    }
  }

  @Override
  public void disableHomeButton() {
//    getSupportActionBar().setHomeButtonEnabled(false);

  }

  @Override
  public void enableHomeButton() {
//    getSupportActionBar().setHomeButtonEnabled(true);
    drawerLayout.closeDrawers();

  }

  @Override
  public void addViewToContainer(View view) {
    linearLayout.addView(view);
  }
}

