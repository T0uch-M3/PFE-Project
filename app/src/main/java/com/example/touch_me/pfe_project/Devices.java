package com.example.touch_me.pfe_project;

import com.google.firebase.database.PropertyName;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;

import io.realm.RealmObject;

public class Devices extends RealmObject {
  @PropertyName("Battery_Level")
  private Double batteryLevel;
  @PropertyName("Moisture")
  private Float moisture;
  @PropertyName("Humidity")
  private Float humidity;
  @PropertyName("soilTemperature")
  private float soilTemperature;
  @PropertyName("Temperature")
  private float Temperature;
  @PropertyName("Time")
  private String time;
  @PropertyName("WaterSensor")
  private float waterSensor;

  @PropertyName("temperature")
  private float temperature;

  @PropertyName("pressure")
  private Double pressure;

  private String deviceName;
  @PropertyName("battery")
  private Double battery;

  @PropertyName("RH")
  private Float relativeHumidity;

  @PropertyName("rainfall")
  private Double rainFall;
  @PropertyName("rainfall24")
  private Double rainFall24;
  @PropertyName("direction")
  private int direction;
  @PropertyName("speed")
  private float speed;
  @PropertyName("speed5")
  private float speed5;

  public Devices() {
  }

  public Float getRelativeHumidity() {
    return relativeHumidity;
  }

  public void setRelativeHumidity(Float relativeHumidity) {
    this.relativeHumidity = relativeHumidity;
  }

  public Double getRainFall() {
    return rainFall;
  }

  public void setRainFall(Double rainFall) {
    this.rainFall = rainFall;
  }

  public Double getRainFall24() {
    return rainFall24;
  }

  public void setRainFall24(Double rainFall24) {
    this.rainFall24 = rainFall24;
  }

  public int getDirection() {
    return direction;
  }

  public void setDirection(int direction) {
    this.direction = direction;
  }

  public float getSpeed() {
    return speed;
  }

  public void setSpeed(float speed) {
    this.speed = speed;
  }

  public float getSpeed5() {
    return speed5;
  }

  public void setSpeed5(float speed5) {
    this.speed5 = speed5;
  }

  public Double getBattery() {
    return battery;
  }

  public void setBattery(Double battery) {
    this.battery = battery;
  }

  public Float getHumidity() {
    return humidity;
  }

  public void setHumidity(Float humidity) {
    this.humidity = humidity;
  }

  public String getDeviceName() {
    return deviceName;
  }

  public void setDeviceName(String deviceName) {
    this.deviceName = deviceName;
  }

  public Float getMoisture() {
    return moisture;
  }

  public void setMoisture(Float moisture) {
    this.moisture = moisture;
  }

  public float gettemperature() {
    return temperature;
  }

  public void settemperature(float temperature) {
    this.temperature = temperature;
  }
  public float getTemperature() {
    return Temperature;
  }

  public void setTemperature(float Temperature) {
    this.Temperature = Temperature;
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
