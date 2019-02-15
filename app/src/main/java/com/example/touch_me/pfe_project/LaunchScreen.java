package com.example.touch_me.pfe_project;

import android.content.Intent;

/*import android.support.transition.Transition;*/
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;
import androidx.transition.*;
import androidx.appcompat.app.AppCompatActivity;
/*import android.support.v7.app.AppCompatActivity;*/
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import com.transitionseverywhere.extra.Scale;

import org.w3c.dom.Text;

import java.io.ObjectInput;
import java.util.Objects;

public class LaunchScreen extends AppCompatActivity {

  TextView tvNewUser, tvTest;
  Button btnLogIn, btnSignUp;
  ImageView imageV;
//  ViewGroup transitionsContainer;
  Boolean isReturnAnimation = true;
  Boolean isReturnAnimation2 = true;
  Boolean visible = false;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_launch_screen);

    final ViewGroup transitionsContainer = (ViewGroup) findViewById(R.id.transitions_container);
    tvNewUser = (TextView) findViewById(R.id.tvNewUser);
    btnLogIn = (Button) findViewById(R.id.btnLogIn);
    btnSignUp = (Button) findViewById(R.id.btnSignUp);
    tvTest = (TextView) findViewById(R.id.tvTest);
    imageV = (ImageView) findViewById(R.id.imagev);

    btnLogIn.setOnClickListener(new View.OnClickListener(){

      @Override
      public void onClick(View v) {
        loginMeth(v, transitionsContainer, false);
      }
    });
    btnSignUp.setOnClickListener(new View.OnClickListener(){

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

  public void loginMeth(View view, ViewGroup tc,boolean visible){
//    startActivity(new Intent(LaunchScreen.this, LoginAct.class));
/////////////////////////////////////////////PRAISE BE TO THE LORD////////////////////////////////////////////////////////////////

      TransitionSet set = new TransitionSet()
        .addTransition(new Scale(0.7f))
        .addTransition(new Fade())
        .setInterpolator(visible ? new LinearOutSlowInInterpolator() : new FastOutLinearInInterpolator());
      TransitionManager.beginDelayedTransition(tc, set);
      tvNewUser.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);



  }
  public void signupMeth(final View v, final ViewGroup tc){
    animateIt(btnSignUp, 800, tc, Gravity.RIGHT, Gravity.LEFT);
//    animateIt(tvNewUser, 800, tc, Gravity.RIGHT, Gravity.LEFT);
//    fadeIt(tvNewUser,200, tc);
//    animateIt(imageV, 800, tc, Gravity.BOTTOM, Gravity.TOP);
    animateIt(tvTest, 800, tc, Gravity.LEFT, Gravity.RIGHT);
//    fadeIt2(imageV, tc);
//    hideWithFadeView(true, tc,imageV);

    final Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        // Do something after 5s = 5000ms

//        animateIt2(btnLogIn, 800, tc, Gravity.RIGHT, Gravity.LEFT);
      }
    }, 1000);

    }

    public void animateIt(View obj, int time, ViewGroup tc, int startPoint, int endPoint){
    Boolean state = true;
      Transition CB = new ChangeBounds();
      CB.setPathMotion(new ArcMotion());
      CB.setDuration(time);
      TransitionManager.beginDelayedTransition(tc, CB);
      FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) obj.getLayoutParams();
      if (state && params.gravity!=startPoint){
        params.gravity = (startPoint);
        state = false;
      }
      else{
        params.gravity = (endPoint);
        state = true;
      }

      obj.setLayoutParams(params);

    }

    public void fadeIt(View obj, int time, ViewGroup tc){
      TransitionSet set = new TransitionSet().addTransition(new Scale(0.7f))


        .setInterpolator(new FastOutLinearInInterpolator());

//      .setInterpolator(visible ? new LinearOutSlowInInterpolator() :
//        new FastOutLinearInInterpolator());

      TransitionManager.beginDelayedTransition(tc, set);
//      obj.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }
  public void animateIt2(View obj, int time, ViewGroup tc, int startPoint, int endPoint){
    Transition CB = new ChangeBounds();
    CB.setPathMotion(new ArcMotion());
    CB.setDuration(time);
    TransitionManager.beginDelayedTransition(tc, CB);
    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) obj.getLayoutParams();
    if (isReturnAnimation){
      params.gravity = (startPoint);
      isReturnAnimation2 = false;
    }
    else{
      params.gravity = (endPoint);
      isReturnAnimation2 = true;
    }

    obj.setLayoutParams(params);

  }

  public void fadeIt2(View obj, ViewGroup tc){

      TransitionSet transition = (TransitionSet) new TransitionSet().addTransition(new Fade(Fade.OUT)).addTransition(new ChangeBounds()).addTransition(new Fade(Fade.IN)).setDuration(1000);
      TransitionManager.beginDelayedTransition(tc, transition);
      obj.setVisibility(View.VISIBLE);

  }
  public static void hideWithFadeView(boolean on, ViewGroup rootView, View view) {

      Fade fade = new Fade();
      fade.setDuration(5000);

      ChangeBounds changeBounds = new ChangeBounds();
      changeBounds.setDuration(5000);

      TransitionSet transitionSet = new TransitionSet();
      transitionSet.addTransition(fade);
      transitionSet.addTransition(changeBounds);
      transitionSet.setOrdering(TransitionSet.ORDERING_TOGETHER);
      TransitionManager.beginDelayedTransition(rootView, transitionSet);

    view.setVisibility(on ? View.GONE : View.VISIBLE);
  }


  }

