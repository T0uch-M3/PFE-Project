package com.example.touch_me.pfe_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

public class WelcomeScreen extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_welcome_screen);
    setupWindowAnimations();
  }

  @SuppressLint("NewApi")
  private void setupWindowAnimations() {
    Fade fade = new Fade();
    fade.setDuration(1000);
      getWindow().setEnterTransition(fade);

  }
  }

