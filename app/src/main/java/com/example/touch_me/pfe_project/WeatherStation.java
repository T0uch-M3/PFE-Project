package com.example.touch_me.pfe_project;

import com.google.firebase.database.PropertyName;

import io.realm.RealmModel;
import io.realm.RealmObject;

public class WeatherStation extends RealmObject{
  @PropertyName("RH")
  private Float relativeHumidity;
  @PropertyName("Temperature")
  private float temperature;
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

  public WeatherStation() {
  }


  public Float getRelativeHumidity() {
    return relativeHumidity;
  }

  public void setRelativeHumidity(Float relativeHumidity) {
    this.relativeHumidity = relativeHumidity;
  }

  public float getTemperature() {
    return temperature;
  }

  public void setTemperature(float temperature) {
    this.temperature = temperature;
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
}
