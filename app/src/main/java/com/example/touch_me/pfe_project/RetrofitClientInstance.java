package com.example.touch_me.pfe_project;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

  private static Retrofit retrofit;
  private static final String BASE_URL = "http://192.168.148.1:8080/";

  public static Retrofit getRetrofitInstance() {
    if (retrofit == null) {
      retrofit = new retrofit2.Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL)

        .addConverterFactory(GsonConverterFactory.create())

        .build();
    }
    return retrofit;
  }



  static OkHttpClient client = new OkHttpClient.Builder()
    .addInterceptor(new BasicAuthInterceptor("admin", "changeit"))//this thing is irrelevant not the whole line, but only the strings?
    .build();

  public static class BasicAuthInterceptor implements Interceptor {

    private String credentials;

    public BasicAuthInterceptor(String user, String password) {
      this.credentials = Credentials.basic(user, password);


    }

    @Override
    public Response intercept(Chain chain) throws IOException {
      Request request = chain.request();
      Request authenticatedRequest = request.newBuilder()
        .addHeader("Authorization", "Basic YWRtaW46Y2hhbmdlaXQ=")
//        .addHeader("Authorization", "admin:changeit")
        .addHeader("cache-control", "no-cache")
//        .header("Authorization", credentials)
        .build();
      return chain.proceed(authenticatedRequest);
    }

  }
}
