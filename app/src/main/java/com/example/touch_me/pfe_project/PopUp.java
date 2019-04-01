package com.example.touch_me.pfe_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import com.github.lzyzsd.circleprogress.ArcProgress;
import com.github.lzyzsd.circleprogress.CircleProgress;

public class PopUp extends Activity {

  Button btn_add;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.popup_body);
    btn_add = (Button) findViewById(R.id.btn_add);

    btn_add.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        ArcProgress cp = new ArcProgress(PopUp.this);
        cp.setBottomText("Type1");
        cp.setProgress(10);
//        WidgetItem wi = new WidgetItem(cp,"Type1");

//
//        Intent intent=new Intent();
//        intent.putExtra("MESSAGE", (Parcelable) wi);
//        setResult(2,intent);
//        finish();//finishing activity

      }
    });

  }
}
