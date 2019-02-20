package com.example.touch_me.pfe_project;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Intent;

/*import android.support.transition.Transition;*/
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;
import androidx.transition.*;
import androidx.appcompat.app.AppCompatActivity;
/*import android.support.v7.app.AppCompatActivity;*/
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


import com.google.android.material.tabs.TabLayout;
import com.thekhaeng.pushdownanim.PushDownAnim;
import com.transitionseverywhere.Recolor;
import com.transitionseverywhere.extra.Scale;

import org.w3c.dom.Text;

import java.io.ObjectInput;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LaunchScreen extends AppCompatActivity {

  TextView tvNewUser, tvTest, tv_S;
  Button btnLogIn, btnSignUp, btnConLogIn;
  FrameLayout topShelf, mainContainer;
  ImageView imageV;
  TableLayout goldenShower;
  //ViewGroup transitionsContainer;
  Boolean isReturnAnimation = true;
  Boolean isReturnAnimation2 = true;
  Boolean visible = false;
  Boolean inLaucher = true;
  Boolean cantStopMeNow = true;
  LinearLayout theShower;
  Boolean isDown = false;
  Boolean isDown2 = false;
  TableRow highestShoot;
  Boolean isColorsInverted = true;
  int wiidth;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_launch_screen);

    final ViewGroup transitionsContainer = (ViewGroup) findViewById(R.id.transitions_container);


//    VideoView videoview = (VideoView) findViewById(R.id.videoView);
//    Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.test);
//    videoview.setVideoURI(uri);
//    videoview.start();

    setStatusBarTrasparent();
    colorChange(transitionsContainer);/////////////////<<<<<<<<<<<<<<<<<<<<<<<<<<<
//    setStatusBarColored(LaunchScreen.this);
//    Button button = findViewById(R.id.btnLogIn);



    tvNewUser = (TextView) findViewById(R.id.tvNewUser);
    btnLogIn = (Button) findViewById(R.id.btnLogIn);
    btnSignUp = (Button) findViewById(R.id.btnSignUp);
    tvTest = (TextView) findViewById(R.id.tvTest);
    imageV = (ImageView) findViewById(R.id.imageV);
    topShelf = (FrameLayout) findViewById(R.id.topShelf);
    btnConLogIn = (Button) findViewById(R.id.btnConLogIn);
    goldenShower = (TableLayout) findViewById(R.id.goldenShower);
    theShower = (LinearLayout)  findViewById(R.id.theShower);
    highestShoot = (TableRow) findViewById(R.id.highestShit);
    tv_S = (TextView)  findViewById(R.id.tv_S);
//    mainContainer = (FrameLayout) findViewById(R.id.transitions_container);

    buttonAnimation(btnLogIn);
    buttonAnimation(btnSignUp);
    buttonAnimation(btnConLogIn);

    btnLogIn.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        loginMeth(v, transitionsContainer, false);
      }
    });
    btnSignUp.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(final View v) {
        signupMeth(v, transitionsContainer);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
          @Override
          public void run() {
            // Do something after 5s = 5000ms

          }
        }, 1000);
      }
    });


  }
  public void buttonAnimation(View v){
    PushDownAnim.setPushDownAnimTo(v)
      .setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Toast.makeText(LaunchScreen.this, "PUSH DOWN !!", Toast.LENGTH_SHORT).show();
        }

      }).setScale(PushDownAnim.MODE_STATIC_DP, 8);
  }
  public static void setStatusBarColored(Activity context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      Window w = context.getWindow();
      w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//      int statusBarHeight = getStatusBarHeight(context);

      View view = new View(context);
      view.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//      view.getLayoutParams().height = statusBarHeight;
      ((ViewGroup) w.getDecorView()).addView(view);
//      view.setBackground(context.getResources().getDrawable(R.drawable.navibg));
    }
  }

  public void setStatusBarTrasparent() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      Window w = getWindow();
      w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
  }

  public void loginMeth(final View view, final ViewGroup tc, boolean visible) {
    animateIt(btnLogIn, 800, tc, Gravity.TOP | Gravity.CENTER, Gravity.BOTTOM | Gravity.CENTER);
    animateIt(btnSignUp, 1000, tc, Gravity.TOP | Gravity.CENTER, Gravity.BOTTOM | Gravity.CENTER);
    animateIt(tvNewUser, 1000, tc, Gravity.TOP | Gravity.CENTER, Gravity.BOTTOM | Gravity.CENTER);
    animateIt(topShelf, 800, tc, Gravity.END, Gravity.START);
    animateIt(btnConLogIn, 800, tc, Gravity.BOTTOM | Gravity.CENTER, Gravity.TOP | Gravity.CENTER);
//    theShower.setPadding(0,20,0,0);
    animateIt3(imageV,500,tc,120,20);
    animateIt2(highestShoot,500,tc,0,-300);//PRAISE THE SUUUUUUUUN \O/

//    superFade(tc, goldenShower);

//    animateIt2(goldenShower, 800, tc,0,40);

//    goldenShower.setPadding(0,190,0,0);








    ///RESIZING STUFF////
//    superFade(tc, imageV);
//    DisplayMetrics display = this.getResources().getDisplayMetrics();
//    wiidth = display.widthPixels;
//    tvNewUser.setText(String.valueOf(imageV.getWidth()));
//    imageV.getLayoutParams().width += ((wiidth - imageV.getWidth()));
//    imageV.requestLayout();


  }

  public void signupMeth(final View v, final ViewGroup tc) {
    superFade(tc, imageV);

//    final Handler handler = new Handler();
//    handler.postDelayed(new Runnable() {
//      @Override
//      public void run() {
//        // Do something after 5s = 5000ms
//      }
//    }, 1000);
////    animateIt2(goldenShower, 800, tc,0,240);

  }

  public void superFade(ViewGroup tc, View v) {
    TransitionSet set = new TransitionSet()
      .addTransition(new Scale(0.8f))
      .addTransition(new Fade())
      .setDuration(200)
      .setInterpolator(visible ? new LinearOutSlowInInterpolator() : new FastOutLinearInInterpolator());


    TransitionManager.beginDelayedTransition(tc, set);
    if (v.getVisibility() == View.VISIBLE) {
      v.setVisibility(View.INVISIBLE);
    } else {
      v.setVisibility(View.VISIBLE);
    }
  }

  public void animateIt(View obj, int time, ViewGroup tc, int startPoint, int endPoint) {
    Boolean state = true;
    Transition CB = new ChangeBounds();
    CB.setPathMotion(new ArcMotion());
    CB.setDuration(time);
    TransitionManager.beginDelayedTransition(tc, CB);
    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) obj.getLayoutParams();

    if (state && params.gravity != startPoint) {
      params.gravity = (startPoint);
      state = false;
    } else {
      params.gravity = (endPoint);
      state = true;
    }

    obj.setLayoutParams(params);

    if (obj.equals(btnLogIn)) {
      inLaucher = false;
    }

  }


  public void animateIt2(View obj,int time,ViewGroup tc,int startPoint, int endPoint) {
//    Boolean state = true;
    Log.wtf("WHAAAA", "TTHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
    Transition CB = new ChangeBounds();
    CB.setPathMotion(new ArcMotion());
    CB.setDuration(time);
    TransitionManager.beginDelayedTransition(tc, CB);
    TableLayout.LayoutParams params = (TableLayout.LayoutParams) obj.getLayoutParams();

    if (!isDown) {
      params.topMargin = endPoint;
      isDown=true;
    } else {
      params.topMargin = startPoint;
      isDown = false;
    }

    obj.setLayoutParams(params);



  }
  public void animateIt3(final View obj, int time, ViewGroup tc, int startPoint, int endPoint) {
    ViewGroup tc2 = (ViewGroup) findViewById(R.id.theShower);
    Transition CB2 = new ChangeBounds();
    CB2.setPathMotion(new ArcMotion());
    CB2.setDuration(time);
    TransitionManager.beginDelayedTransition(tc, CB2);
    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) obj.getLayoutParams();

    if (!isDown2) {
      params.topMargin = endPoint;
      isDown2=true;
    } else {
      params.topMargin = startPoint;
      isDown2 = false;
    }

    obj.setLayoutParams(params);

  }


  public void colorChange(final ViewGroup tc) {
    final TransitionDrawable transition = (TransitionDrawable) tc.getBackground();

    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        if (cantStopMeNow) {
          transition.startTransition(5000);
          cantStopMeNow = false;
        } else {
          transition.reverseTransition(5000);
          cantStopMeNow = true;
        }

      }
    };

    final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    scheduler.scheduleAtFixedRate(runnable, 5, 5, TimeUnit.SECONDS);

  }

  @Override
  public void onBackPressed() {
    if (inLaucher) {
      finish();
    } else {
      final ViewGroup tc = (ViewGroup) findViewById(R.id.transitions_container);

      animateIt(btnLogIn, 600, tc, Gravity.TOP | Gravity.CENTER, Gravity.BOTTOM | Gravity.CENTER);
      animateIt(btnSignUp, 800, tc, Gravity.TOP | Gravity.CENTER, Gravity.BOTTOM | Gravity.CENTER);
      animateIt(tvNewUser, 800, tc, Gravity.TOP | Gravity.CENTER, Gravity.BOTTOM | Gravity.CENTER);
      animateIt(topShelf, 600, tc, Gravity.END, Gravity.START);
      animateIt(btnConLogIn, 600, tc, Gravity.BOTTOM | Gravity.CENTER, Gravity.TOP | Gravity.CENTER);
//      theShower.setPadding(0,100,0,0);
      animateIt3(imageV,500,tc,120,20);

      animateIt2(highestShoot,500,tc,0,-300);


//      goldenShower.setPadding(0,0,0,0);
//      ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) goldenShower.getLayoutParams();
//      params.leftMargin = 0;
//      params.topMargin = 0;
//      goldenShower.invalidate();
//      goldenShower.requestLayout();

      inLaucher = true;
    }
  }


}

