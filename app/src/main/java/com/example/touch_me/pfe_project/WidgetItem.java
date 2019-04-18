package com.example.touch_me.pfe_project;

import android.view.View;

public class WidgetItem {
  public View widget;
  public String tilte;
  private String type;
  private Boolean dummy = false;
  private Boolean isButton = false;
  private String displayValue = "null";
  private boolean isWidget = false;
  private int size = 1;

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public boolean isWidget() {
    return isWidget;
  }

  public void isWidget(boolean widget) {
    isWidget = widget;
  }

  public String getWidgetType() {
    return widgetType;
  }

  public void setWidgetType(String widgetType) {
    this.widgetType = widgetType;
  }

  private String widgetType;

  public void setDummy(Boolean dummy) {
    this.dummy = dummy;
  }


  public WidgetItem() {
  }

  public WidgetItem(View widget, String tilte) {
    this.widget = widget;
    this.tilte = tilte;
  }

  public WidgetItem(Boolean dummy) {
    this.dummy = dummy;
  }

  public Boolean getButton() {
    return isButton;
  }

  public void setButton(Boolean button) {
    isButton = button;
  }


  public Boolean getDummy() {
    return dummy;
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

  public String getDisplayValue() {
    return displayValue;
  }

  public void setDisplayValue(String displayValue) {
    this.displayValue = displayValue;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
