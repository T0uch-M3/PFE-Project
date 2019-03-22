package com.example.touch_me.pfe_project;


import retrofit2.Call;
import retrofit2.http.GET;


public interface GetDataService {

  @GET("/db/coll/5c94a195c52facaa1b3cea4a")
  Call<WidgetObject> getAllUsers();
//  Call<List<WidgetObject>> getAllUsers(@Header("Authorization") String myAuthString);
}
