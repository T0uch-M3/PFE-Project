package com.example.touch_me.pfe_project;

import com.google.firebase.database.PropertyName;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmModel;
import io.realm.RealmObject;

public class GreenLand extends RealmObject{
  @PropertyName("Moisture")
  private Float moisture;
  @PropertyName("temperature")
  private float temperature;
  @PropertyName("pressure")
  private Double pressure;
  @PropertyName("Battery_Level")
  private Double batteryLevel;
  @PropertyName("Time")
  private String time;
  @PropertyName("soilTemperature")
  private float soilTemperature;
  @PropertyName("WaterSensor")
  private float waterSensor;

  public GreenLand() {
  }


  public Float getMoisture() {
    return moisture;
  }

  public void setMoisture(Float moisture) {
    this.moisture = moisture;
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

  public Double getBatteryLevel() {
    return batteryLevel;
  }

  public void setBatteryLevel(Double batteryLevel) {
    this.batteryLevel = batteryLevel;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public float getSoilTemperature() {
    return soilTemperature;
  }

  public void setSoilTemperature(float soilTemperature) {
    this.soilTemperature = soilTemperature;
  }

  public float getWaterSensor() {
    return waterSensor;
  }

  public void setWaterSensor(float waterSensor) {
    this.waterSensor = waterSensor;
  }
}
