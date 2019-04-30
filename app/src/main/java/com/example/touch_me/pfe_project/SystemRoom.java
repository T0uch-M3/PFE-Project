package com.example.touch_me.pfe_project;

import com.google.firebase.database.PropertyName;

import io.realm.RealmModel;
import io.realm.RealmObject;

public class SystemRoom extends RealmObject {
  @PropertyName("humidity")
  private Float humidity;
  @PropertyName("temperature")
  private float temperature;
  @PropertyName("pressure")
  private Double pressure;
  @PropertyName("battery")
  private Double battery;
  @PropertyName("Time")
  private String time;
  private String deviceName;

  public SystemRoom() {
  }

  public String getDeviceName() {
    return deviceName;
  }

  public void setDeviceName(String deviceName) {
    this.deviceName = deviceName;
  }

  public Float getHumidity() {
    return humidity;
  }

  public void setHumidity(Float humidity) {
    this.humidity = humidity;
  }

  public float getTemperature() {
    return temperature;
  }

  public void setTemperature(float temperature) {
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

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }
}
