package com.example.touch_me.pfe_project;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WidgetObject {
//  @SerializedName("_id")
//  @Expose
//  private Id id;
  @SerializedName("name")
  @Expose
  private String name;
//  @SerializedName("_etag")
//  @Expose
//  private Etag etag;


//  private int imgId;

  public WidgetObject(String name){
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
