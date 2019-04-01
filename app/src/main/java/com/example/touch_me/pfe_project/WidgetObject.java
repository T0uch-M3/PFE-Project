package com.example.touch_me.pfe_project;

import com.google.firebase.database.PropertyName;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WidgetObject {
  @PropertyName("humidity")
  private Float humidity;
  @PropertyName("temperature")
  private int temperature;
  @PropertyName("pressure")
  private Double pressure;
  @PropertyName("battery")
  private Double battery;

  public WidgetObject() {
  }


  public Float getHumidity() {
    return humidity;
  }

  public void setHumidity(Float humidity) {
    this.humidity = humidity;
  }

  public int getTemperature() {
    return temperature;
  }

  public void setTemperature(int temperature) {
    this.temperature = temperature;
  }

  public Double getPressure() {
    return pressure;
  }

  public void setPressure(Double pressure) {
    this.pressure = pressure;
  }

  public Double getBattery() {
    return battery;
  }

  public void setBattery(Double battery) {
    this.battery = battery;
  }


  //  @SerializedName("humidity")
//  @Expose
//  private String humidity;
//  @SerializedName("temprerature")
//  @Expose
//  private String temp;
//  @SerializedName("pressure")
//  @Expose
//  private String pressure;
//
//  public String getHumidity() {
//    return humidity;
//  }
//
//  public void setHumidity(String humidity) {
//    this.humidity = humidity;
//  }
//
//  public String getTemp() {
//    return temp;
//  }
//
//  public void setTemp(String temp) {
//    this.temp = temp;
//  }
//
//  public String getPressure() {
//    return pressure;
//  }
//
//  public void setPressure(String pressure) {
//    this.pressure = pressure;
//  }


}
