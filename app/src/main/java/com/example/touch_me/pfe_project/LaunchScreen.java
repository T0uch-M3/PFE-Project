package com.example.touch_me.pfe_project;

import android.content.Intent;

/*import android.support.transition.Transition;*/
import androidx.transition.*;
import androidx.appcompat.app.AppCompatActivity;
/*import android.support.v7.app.AppCompatActivity;*/
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;



import org.w3c.dom.Text;

public class LaunchScreen extends AppCompatActivity {

  TextView tvNewUser, tvTest;
  Button btnLogIn, btnSignUp;
  ViewGroup transitionsContainer;
  Boolean isReturnAnimation = false;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_launch_screen);

    final ViewGroup transitionsContainer = (ViewGroup) findViewById(R.id.transitions_container);
    tvNewUser = (TextView) findViewById(R.id.tvNewUser);
    btnLogIn = (Button) findViewById(R.id.btnLogIn);
    btnSignUp = (Button) findViewById(R.id.btnSignUp);
    tvTest = (TextView) findViewById(R.id.tvTest);

    btnLogIn.setOnClickListener(new View.OnClickListener(){

      @Override
      public void onClick(View v) {
        loginMeth(v);
      }
    });
    btnSignUp.setOnClickListener(new View.OnClickListener(){

      @Override
      public void onClick(View v) {
        signupMeth(v);
      }
    });
  }

  public void loginMeth(View view){
    startActivity(new Intent(LaunchScreen.this, LoginAct.class));
  }
  public void signupMeth(View v){
    Transition CB = new ChangeBounds();
    CB.setPathMotion(new ArcMotion());
    CB.setDuration(300);
    TransitionManager.beginDelayedTransition(transitionsContainer, CB);
    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) btnSignUp.getLayoutParams();
    params.gravity = isReturnAnimation ? (Gravity.LEFT | Gravity.TOP) : (Gravity.BOTTOM | Gravity.RIGHT);

    btnSignUp.setLayoutParams(params);

    }
}
