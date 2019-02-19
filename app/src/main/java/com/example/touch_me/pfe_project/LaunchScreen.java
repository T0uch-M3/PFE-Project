package com.example.touch_me.pfe_project;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
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
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


import com.thekhaeng.pushdownanim.PushDownAnim;
import com.transitionseverywhere.extra.Scale;

import org.w3c.dom.Text;

import java.io.ObjectInput;
import java.util.Objects;

public class LaunchScreen extends AppCompatActivity {

  TextView tvNewUser, tvTest;
  Button btnLogIn, btnSignUp, btnConLogIn;
  FrameLayout topShelf, mainContainer;
  ImageView imageV;

  //ViewGroup transitionsContainer;
  Boolean isReturnAnimation = true;
  Boolean isReturnAnimation2 = true;
  Boolean visible = false;
  Boolean inLaucher = true;
  Boolean cantStopMeNow = true;
  int wiidth;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_launch_screen);

    final ViewGroup transitionsContainer = (ViewGroup) findViewById(R.id.transitions_container);


    VideoView videoview = (VideoView) findViewById(R.id.videoView);
    Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.test);
    videoview.setVideoURI(uri);
    videoview.start();

    setStatusBarTrasparent();
    colorChange(transitionsContainer);/////////////////<<<<<<<<<<<<<<<<<<<<<<<<<<<
//    setStatusBarColored(LaunchScreen.this);
    Button button = findViewById( R.id.btnLogIn );

    PushDownAnim.setPushDownAnimTo( button)
        .setOnClickListener( new View.OnClickListener(){
      @Override
      public void onClick( View view ){
        Toast.makeText( LaunchScreen.this, "PUSH DOWN !!", Toast.LENGTH_SHORT ).show();
      }

    }).setScale( PushDownAnim.MODE_STATIC_DP, 8  ) ;

    tvNewUser = (TextView) findViewById(R.id.tvNewUser);
    btnLogIn = (Button) findViewById(R.id.btnLogIn);
    btnSignUp = (Button) findViewById(R.id.btnSignUp);
    tvTest = (TextView) findViewById(R.id.tvTest);
    imageV = (ImageView) findViewById(R.id.imagev);
    topShelf = (FrameLayout) findViewById(R.id.topShelf);
    btnConLogIn = (Button) findViewById(R.id.btnConLogIn);
//    mainContainer = (FrameLayout) findViewById(R.id.transitions_container);

    btnLogIn.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        loginMeth(v, transitionsContainer, false);
      }
    });
    btnSignUp.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(final View v) {


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
          @Override
          public void run() {
            // Do something after 5s = 5000ms
            signupMeth(v, transitionsContainer);
          }
        }, 1000);


      }
    });
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
  public void setStatusBarTrasparent(){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      Window w = getWindow();
      w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
  }

  public void loginMeth(View view, ViewGroup tc, boolean visible) {
    animateIt(btnLogIn, 800, tc, Gravity.TOP | Gravity.CENTER, Gravity.BOTTOM | Gravity.CENTER);
    animateIt(btnSignUp, 1000, tc, Gravity.TOP | Gravity.CENTER, Gravity.BOTTOM | Gravity.CENTER);
    animateIt(tvNewUser, 1000, tc, Gravity.TOP | Gravity.CENTER, Gravity.BOTTOM | Gravity.CENTER);
    animateIt(topShelf, 800, tc, Gravity.END, Gravity.START);
    animateIt(btnConLogIn, 800, tc, Gravity.BOTTOM | Gravity.CENTER, Gravity.TOP | Gravity.CENTER);

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

    final Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        // Do something after 5s = 5000ms
      }
    }, 1000);

  }

  public void superFade(ViewGroup tc, View v) {
    TransitionSet set = new TransitionSet()
      .addTransition(new Scale(0.7f))
      .addTransition(new Fade())
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


  public void animateIt2(View obj, int time, ViewGroup tc, int startPoint, int endPoint) {
    Transition CB = new ChangeBounds();
    CB.setPathMotion(new ArcMotion());
    CB.setDuration(time);
    TransitionManager.beginDelayedTransition(tc, CB);
    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) obj.getLayoutParams();
    if (isReturnAnimation) {
      params.gravity = (startPoint);
      isReturnAnimation2 = false;
    } else {
      params.gravity = (endPoint);
      isReturnAnimation2 = true;
    }

    obj.setLayoutParams(params);

  }


  public void colorChange(final ViewGroup tc) {
    final TransitionDrawable transition = (TransitionDrawable) tc.getBackground();


//    while(cantStopMeNow){
      transition.startTransition(5000);


    final Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        // Do something after 5s = 5000ms
        transition.reverseTransition(5000);
      }
    }, 5000);

  }

//  }

  @Override
  public void onBackPressed() {
    if (inLaucher) {
      finish();
    } else {
      final ViewGroup tc = (ViewGroup) findViewById(R.id.transitions_container);

      animateIt(btnLogIn, 800, tc, Gravity.TOP | Gravity.CENTER, Gravity.BOTTOM | Gravity.CENTER);
      animateIt(btnSignUp, 1000, tc, Gravity.TOP | Gravity.CENTER, Gravity.BOTTOM | Gravity.CENTER);
      animateIt(tvNewUser, 1000, tc, Gravity.TOP | Gravity.CENTER, Gravity.BOTTOM | Gravity.CENTER);
      animateIt(topShelf, 800, tc, Gravity.END, Gravity.START);
      animateIt(btnConLogIn, 800, tc, Gravity.BOTTOM | Gravity.CENTER, Gravity.TOP | Gravity.CENTER);
      inLaucher = true;
    }
  }

  public void playVid() {
//    VideoView videoview = (VideoView) findViewById(R.id.videoView);
//    Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+res.raw.demoVid);
//    videoview.setVideoURI(uri);
//    videoview.start();
  }




}

