package com.example.touch_me.pfe_project;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class FirebasePullService extends IntentService {
  // TODO: Rename actions, choose action names that describe tasks that this
  // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
  private static final String ACTION_FOO = "com.example.touch_me.pfe_project.action.FOO";
  private static final String ACTION_BAZ = "com.example.touch_me.pfe_project.action.BAZ";
  private Realm realm;
  private static ArrayList<GreenLand> fireBaseList;
  private List<String> deviceName = new ArrayList<>();
  private List<String> deviceDate = new ArrayList<>();
  private List<List<String>> dateAndNames = new ArrayList<>();

  // TODO: Rename parameters
  private static final String EXTRA_PARAM1 = "com.example.touch_me.pfe_project.extra.PARAM1";
  private static final String EXTRA_PARAM2 = "com.example.touch_me.pfe_project.extra.PARAM2";

  public FirebasePullService() {
    super("FirebasePullService");
  }

  /**
   * Starts this service to perform action Foo with the given parameters. If
   * the service is already performing a task this action will be queued.
   *
   * @see IntentService
   */
  // TODO: Customize helper method
  public static void startActionFoo(Context context, String param1, String param2) {
    Intent intent = new Intent(context, FirebasePullService.class);
    intent.setAction(ACTION_FOO);
    intent.putExtra(EXTRA_PARAM1, param1);
    intent.putExtra(EXTRA_PARAM2, param2);
    context.startService(intent);
  }

  /**
   * Starts this service to perform action Baz with the given parameters. If
   * the service is already performing a task this action will be queued.
   *
   * @see IntentService
   */
  // TODO: Customize helper method
  public static void startActionBaz(Context context, String param1, String param2) {
    Intent intent = new Intent(context, FirebasePullService.class);
    intent.setAction(ACTION_BAZ);
    intent.putExtra(EXTRA_PARAM1, param1);
    intent.putExtra(EXTRA_PARAM2, param2);
    context.startService(intent);
  }

  @Override
  protected void onHandleIntent(Intent intent) {
    ResultReceiver resRec = null;
//    Log.wtf("tag","INSIDE THE onHANDLEINTENT");
    if (intent != null) {
      Bundle bundle = intent.getExtras();
      if (bundle.getString("identifier").equals("static")) {
        resRec = intent.getParcelableExtra("offlineMode");
        Log.wtf("tag", "STATIC");
//        new AsyncCaller().execute();
        Log.wtf("tag", "NETWORK  THREAD  JUST  STRATEEDDDD");
        Realm.init(FirebasePullService.this);
        final RealmConfiguration realmConfig = new RealmConfiguration.Builder()
          .name("test1.realm")
          .schemaVersion(0)
//          .deleteRealmIfMigrationNeeded()
          .build();

        Realm.setDefaultConfiguration(realmConfig);

        realm = Realm.getInstance(realmConfig);

        RealmResults<Devices> result = realm.where(Devices.class).equalTo("deviceName", "Green_Land").findAll();
        RealmResults<Devices> result2 = realm.where(Devices.class).equalTo("deviceName", "System_Room_SOFIA").findAll();
        RealmResults<Devices> result3 = realm.where(Devices.class).equalTo("deviceName", "System_Room_SOFIA").findAll();
        RealmResults<Devices> result4 = realm.where(Devices.class).equalTo("deviceName", "Weather_Station_Final").findAll();

        Log.wtf("tag", "greendland size::::" + result.size());
        Log.wtf("tag", "system room 1 size::::" + result2.size());
        Log.wtf("tag", "system room 2 size::::" + result3.size());
        Log.wtf("tag", "weather station size::::" + result4.size());


//        firebaseStaticConfig();

//        final ResultReceiver finalResRec = resRec;


      }
      //sending data back the activity through the same receiver that we got earlier
      if (bundle.getString("identifier").equals("deviceList")) {
        resRec = intent.getParcelableExtra("offlineMode");
        Bundle b = new Bundle();

        Realm.init(FirebasePullService.this);
        final RealmConfiguration realmConfig = new RealmConfiguration.Builder()
          .name("test1.realm")
          .schemaVersion(0)
//          .deleteRealmIfMigrationNeeded()
          .build();
        Realm.setDefaultConfiguration(realmConfig);
        realm = Realm.getInstance(realmConfig);
        RealmResults<Devices> result = realm.where(Devices.class).findAll();
        for (Devices device : result) {
//          Log.wtf("tag","devices name::"+device.getDeviceName());
          if (!deviceName.contains(device.getDeviceName())) {
            deviceName.add(device.getDeviceName());
          }
        }

        for (String name : deviceName) {
//            Log.wtf("tag","name::"+name);
          List<String> list = new ArrayList<>();
          list.add(name);
          dateAndNames.add(list);
        }

        /**IF THIS METHOD NOT GONNA BE USED FOR DEVICE SELECTION THEN THERE'S MORE EFFICIENT METHODS*/

        Long incoming;
        for (String name : deviceName) {
          for (Devices device : result) {
            if (device.getDeviceName().equals(name)) {

              if (device.getTime() != null) {

                String[] splitter = device.getTime().split(" ");

                for (int h = 0; h < splitter.length; h++) {
                  if (splitter[h].length() == 1) {
                    splitter[h] = "0" + splitter[h];
                  }
                }
                String organized = splitter[3] + splitter[4] + splitter[5] + splitter[0] + splitter[1] + splitter[2];
                incoming = Long.valueOf(organized);

                for (List list : dateAndNames) {
                  if (list.get(0).equals(device.getDeviceName())) {

                    if (list.size() == 1){

                      list.add(00000000000000);
                    }

                    else {
                      if (incoming > Long.valueOf(list.get(1).toString()))
                        list.set(1, incoming);
                    }
                  }
                }
              }
            }
          }
        }

        for (List list : dateAndNames) {
          if (list.size() == 1) {
            dateAndNames.get(dateAndNames.indexOf(list)).add("19451116130522");
          }
          deviceDate.add(list.get(1).toString().substring(8, 10) + " " +
            list.get(1).toString().substring(10, 12) + " " +
            list.get(1).toString().substring(12, 14) + " " +
            list.get(1).toString().substring(0, 4) + " " +
            list.get(1).toString().substring(4, 6) + " " +
            list.get(1).toString().substring(6, 8));
        }
//        Log.wtf("tag","NAMEs :::"+deviceName);
//        Log.wtf("tag","Dates :::"+deviceDate);
//        Log.wtf("tag", "devices list::" + dateAndNames);
        Intent i = new Intent();

        b.putStringArrayList("deviceNameList", (ArrayList<String>) deviceName);
        b.putStringArrayList("deviceDateList", (ArrayList<String>) deviceDate);
        resRec.send(0, b);


      }


//      ServiceDataReceiver serviceData = (ServiceDataReceiver) resRec;
//      Bundle dundle = serviceData.getData();


//      if(intent.getStringExtra("offlineMode2").equals("abc")){
////        firebaseStaticConfig();
//        Log.wtf("tag","INSIDE THE handleer");
//      }

//      Log.wtf("tag","INSIDE THE onHANDLEINTENT"+intent.getStringExtra("offlineMode2"));


//      final String action = intent.getAction();
//      if (ACTION_FOO.equals(action)) {
//        final String param1 = intent.getStringExtra(EXTRA_PARAM1);
//        final String param2 = intent.getStringExtra(EXTRA_PARAM2);
//        handleActionFoo(param1, param2);
//      } else if (ACTION_BAZ.equals(action)) {
//        final String param1 = intent.getStringExtra(EXTRA_PARAM1);
//        final String param2 = intent.getStringExtra(EXTRA_PARAM2);
//        handleActionBaz(param1, param2);
//      }
    }
  }


  private static class AsyncCaller extends AsyncTask<Void, Void, Void> {
//    ProgressDialog pdLoading = new ProgressDialog(AsyncExample.this);

    @Override
    protected void onPreExecute() {
      super.onPreExecute();

      //this method will be running on UI thread
//      pdLoading.setMessage("\tLoading...");
//      pdLoading.show();
    }

    @Override
    protected Void doInBackground(Void... params) {

      final FirebaseDatabase database = FirebaseDatabase.getInstance();
      final DatabaseReference myRef2 = database.getReference().child("Green_Land");

      myRef2.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//        realm = Realm.getInstance(realmConfig);
          Log.wtf("tag", "STATIC  MOFOOOOOOOOOO:::" + dataSnapshot.getChildrenCount());
          for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {

            GreenLand wo = new GreenLand();
            try {

//          if (snapshot.child("Time").getValue().toString() != null && snapshot.child("Temperature") != null) {

              wo.setTime(snapshot.child("Time").getValue().toString());
              wo.setTemperature(Float.valueOf(snapshot.child("Temperature").getValue().toString()));
              wo.setBatteryLevel(Double.valueOf(snapshot.child("Battery_Level").getValue().toString()));

//            Log.wtf("tag", "Time:::" + wo.getTime());
              fireBaseList.add(wo);

//          }
//                    realm.deleteAll();//in case of deletion
            } catch (Exception e) {
              e.printStackTrace();
            }


          }
        }


        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
          Log.wtf("tag", "WHO  CANCELED  ITTTTTTTT????????,," + databaseError);
        }
      });


      //this method will be running on background thread so don't update UI frome here
      //do your long running http tasks here,you dont want to pass argument and u can access the parent class' variable url over here


      return null;
    }

    @Override
    protected void onPostExecute(Void result) {
      super.onPostExecute(result);

      //this method will be running on UI thread

//      pdLoading.dismiss();
    }
  }

  private void sendDataBack(String msg) {
    Intent intent = new Intent(getBaseContext(), FirebasePullService.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.putExtra("offlineMode", msg);
    sendBroadcast(intent);
  }

  /**
   * Handle action Foo in the provided background thread with the provided
   * parameters.
   */
  private void handleActionFoo(String param1, String param2) {
    // TODO: Handle action Foo
    throw new UnsupportedOperationException("Not yet implemented");
  }

  /**
   * Handle action Baz in the provided background thread with the provided
   * parameters.
   */
  private void handleActionBaz(String param1, String param2) {
    // TODO: Handle action Baz
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public void realmInflater(final ArrayList list) {
    try (Realm realm = Realm.getDefaultInstance()) {
      realm.executeTransaction(new Realm.Transaction() {
        @Override
        public void execute(Realm realm) {
//          NewsList newsListObj = new NewsList(); // <-- create unmanaged
          RealmList<GreenLand> _newsList = new RealmList<>();
          _newsList.addAll(list);
          realm.insert(list); // <-- insert unmanaged to Realm
        }
      });
    }
  }

  public void firebaseStaticConfig() {
//    final GreenLand[] wo = {null};
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef2 = database.getReference().child("Green_Land");
    DatabaseReference globalRef = database.getReference();
/***********************/
//    Realm.init(this);
//
//    final RealmConfiguration realmConfig = new RealmConfiguration.Builder()
//      .name("test1.realm")
//      .schemaVersion(0)
////      .deleteRealmIfMigrationNeeded()
//      .build();
//
//    Realm.setDefaultConfiguration(realmConfig);
//
//    realm = Realm.getInstance(realmConfig);
    /*******************************/
//
//    realm.executeTransaction(new Realm.Transaction() {
//      @Override
//      public void execute(Realm realm) {
//        for (int i = 0; i < 10; i++) {
//          Devices devices = realm.createObject(Devices.class);
//          devices.setTemperature(i + i);
//          devices.setWaterSensor(i + 5f);
//          devices.setDeviceName("GreenLand");
//          realm.insertOrUpdate(devices);
//        }
//        realm.deleteAll();//in case of deletion
//      }
//    });

//    RealmResults<Devices> result = realm.where(Devices.class).findAll();
////    RealmResults<> result2 = realm.f
//    Log.wtf("tag", "WHAT WE got here BG THREAD->>>" + result);

//    realm.close();

//    Thread thread = new Thread(new Runnable() {
//      @Override
//      public void run() {

    globalRef.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
        Realm.init(FirebasePullService.this);

        final RealmConfiguration realmConfig = new RealmConfiguration.Builder()
          .name("test1.realm")
          .schemaVersion(0)
//          .deleteRealmIfMigrationNeeded()
          .build();

        Realm.setDefaultConfiguration(realmConfig);

        realm = Realm.getInstance(realmConfig);

//        realm.executeTransaction(new Realm.Transaction() {
//          @Override
//          public void execute(Realm realm) {
//            for (int i = 0; i < 10; i++) {
//              Devices devices = realm.createObject(Devices.class);
//              devices.setTemperature(i + i);
//              devices.setWaterSensor(i + 5f);
//              devices.setDeviceName("GreenLand");
//              realm.insertOrUpdate(devices);
//            }
////            realm.deleteAll();//in case of deletion
//          }
//        });
/***************************************************/
//        RealmResults<Devices> result = realm.where(Devices.class).findAll();
//////    RealmResults<> result2 = realm.f
//    Log.wtf("tag", "WHAT WE got here BG THREAD->>>" + result.size());
        /***************************************/


        for (final DataSnapshot childNode : dataSnapshot.getChildren()) {

          DatabaseReference childRef = FirebaseDatabase.getInstance().getReference().child(childNode.getKey());

          childRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
              Log.wtf("tag", "ONE");
              switch (childNode.getKey()) {
                case "Green_Land": {
                  Log.wtf("tag", "TWO");
                  for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    try {
                      realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(final Realm realm) {
//                          Devices greenLand = new Devices();
//
//                          greenLand.setTime(snapshot.child("Time").getValue().toString());
//                          greenLand.setTemperature(Float.valueOf(snapshot.child("Temperature").getValue().toString()));
//                          greenLand.setBatteryLevel(Double.valueOf(snapshot.child("Battery_Level").getValue().toString()));
//                          greenLand.setHumidity(Float.valueOf(snapshot.child("Humidity").getValue().toString()));
//                          greenLand.setMoisture(Float.valueOf(snapshot.child("Moisture").getValue().toString()));
//                          greenLand.setSoilTemperature(Float.valueOf(snapshot.child("soilTemperature").getValue().toString()));
//                          greenLand.setWaterSensor(Float.valueOf(snapshot.child("WaterSensor").getValue().toString()));
//                          greenLand.setDeviceName("Green_Land");

//                          realm.deleteAll();
//                          realm.insertOrUpdate(greenLand);
                        }
                      });
                    } catch (Exception e) {
                      e.printStackTrace();
                    }
                  }
                  Log.wtf("tag", "hhhhhhmmmm:1::: ==" + dataSnapshot.getChildrenCount());
//                  realm.deleteAll();
                  RealmResults<Devices> result = realm.where(Devices.class).equalTo("deviceName", "Green_Land").findAll();
                  Log.wtf("tag", "Green_Land->>>" + result.size());
                  RealmResults<Devices> resultWhole = realm.where(Devices.class).findAll();
                  Log.wtf("tag", "THE WHOLE THINGGGGG->>>" + result.size());
                }
                break;

                case "System_Room_SOFIA": {
                  for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    try {
                      realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(final Realm realm) {
//                          Devices systemRoom1 = new Devices();
//
//                          systemRoom1.setTime(snapshot.child("Time").getValue().toString());
//                          systemRoom1.settemperature(Float.valueOf(snapshot.child("temperature").getValue().toString()));
//                          systemRoom1.setBattery(Double.valueOf(snapshot.child("battery").getValue().toString()));
//                          systemRoom1.setPressure(Double.valueOf(snapshot.child("pressure").getValue().toString()));
//                          systemRoom1.setHumidity(Float.valueOf(snapshot.child("humidity").getValue().toString()));
//                          systemRoom1.setDeviceName("System_Room_SOFIA");
//                          realm.deleteAll();
//                          realm.insertOrUpdate(systemRoom1);

                        }
                      });

                    } catch (Exception e) {
//                      e.printStackTrace();
                    }
                  }
                  Log.wtf("tag", "hhhhhhmmmm:2::: ==" + dataSnapshot.getChildrenCount());
//                                    realm.deleteAll();
                  RealmResults<Devices> result = realm.where(Devices.class).equalTo("deviceName", "System_Room_SOFIA").findAll();

                  Log.wtf("tag", "System_Room_SOFIA SIZE->>>" + result.size());
                }
                break;
                case "System_Room_SOFIA2": {
                  for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    try {
                      realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(final Realm realm) {
//                          Devices systemRoom2 = new Devices();
//
//                          systemRoom2.setTime(snapshot.child("Time").getValue().toString());
//                          systemRoom2.settemperature(Float.valueOf(snapshot.child("temperature").getValue().toString()));
//                          systemRoom2.setBattery(Double.valueOf(snapshot.child("battery").getValue().toString()));
//                          systemRoom2.setPressure(Double.valueOf(snapshot.child("pressure").getValue().toString()));
//                          systemRoom2.setHumidity(Float.valueOf(snapshot.child("humidity").getValue().toString()));
//                          systemRoom2.setDeviceName("System_Room_SOFIA2");
////                          realm.deleteAll();
//                          realm.insertOrUpdate(systemRoom2);

                        }
                      });

                    } catch (Exception e) {
//                      e.printStackTrace();
                    }
                  }
                  Log.wtf("tag", "hhhhhhmmmm:3::: ==" + dataSnapshot.getChildrenCount());
                  //                  realm.deleteAll();
                  RealmResults<Devices> result = realm.where(Devices.class).equalTo("deviceName", "System_Room_SOFIA2").findAll();
                  Log.wtf("tag", "System_Room_SOFIA2 size->>>" + result.size());
                }
                break;
                case "Weather_Station_Final": {
                  for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    try {
                      realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(final Realm realm) {
//                          Devices weatherStation = new Devices();
//
//                          weatherStation.setRelativeHumidity(Float.valueOf(snapshot.child("RH").getValue().toString()));
//                          weatherStation.setTemperature(Float.valueOf(snapshot.child("Temperature").getValue().toString()));
//                          weatherStation.setDirection(Integer.valueOf(snapshot.child("direction").getValue().toString()));
//                          weatherStation.setRainFall(Double.valueOf(snapshot.child("rainfall").getValue().toString()));
//                          weatherStation.setRainFall24(Double.valueOf(snapshot.child("rainfall24").getValue().toString()));
//                          weatherStation.setSpeed(Float.valueOf(snapshot.child("speed").getValue().toString()));
//                          weatherStation.setSpeed5(Float.valueOf(snapshot.child("speed5").getValue().toString()));
//                          weatherStation.setDeviceName("Weather_Station_Final");
//
////                          realm.deleteAll();
//                          realm.insertOrUpdate(weatherStation);

                        }
                      });

                    } catch (Exception e) {
                      e.printStackTrace();
                    }
                  }
                  Log.wtf("tag", "hhhhhhmmmm:4::: ==" + dataSnapshot.getChildrenCount());

                  RealmResults<Devices> result = realm.where(Devices.class).equalTo("deviceName", "Weather_Station_Final").findAll();
                  Log.wtf("tag", "Weather_Station_Final size->>>" + result.size());
                }
                break;
              }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
          });
        }
      }


      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {
        Log.wtf("tag", "WHO  CANCELED  ITTTTTTTT????????,," + databaseError);
      }
    });

//      }

//    });
//    thread.start();

//    realm.addChangeListener(new RealmChangeListener<Realm>() {
//      @Override
//      public void onChange(Realm realm) {
//        Log.wtf("tag", "DATABSE CHANGEDDDDD");
//      }
//    });


  }


}
