package com.example.touch_me.pfe_project;

import android.util.Log;
import android.view.View;

import java.util.List;

public interface MyCustomObjectListener {


  void onOptionButtonReady(View v, int position);
  void onObjectReady();
  void notifyForRemove(CustomAdapter ca, WidgetItem wItem, boolean add, int dummiesToDelete);

}

