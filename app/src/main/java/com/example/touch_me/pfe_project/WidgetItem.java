package com.example.touch_me.pfe_project;

import android.view.View;

public class WidgetItem {
  public View widget;
  public String tilte;
  private Boolean dummy;
  public WidgetItem() {
  }

  public WidgetItem(View widget, String tilte, Boolean dummy) {
    this.widget = widget;
    this.tilte = tilte;
    this.dummy = dummy;
  }

  public Boolean getDummy() {
    return dummy;
  }

  public void setDummmy(Boolean dummmy) {
    this.dummy = dummmy;
  }

  public View getWidget() {
    return widget;
  }

  public void setWidget(View widget) {
    this.widget = widget;
  }

  public String getTilte() {
    return tilte;
  }

  public void setTilte(String tilte) {
    this.tilte = tilte;
  }


}
