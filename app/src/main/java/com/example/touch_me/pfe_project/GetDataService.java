package com.example.touch_me.pfe_project;


import retrofit2.Call;
import retrofit2.http.GET;


public interface GetDataService {

  @GET("/db/coll/5c94bee7c52facaa1b3cee43")
  Call<GreenLand> getObject();
//  Call<List<GreenLand>> getAllUsers(@Header("Authorization") String myAuthString);
}
