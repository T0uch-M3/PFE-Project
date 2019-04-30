package com.example.touch_me.pfe_project;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Fade;
import android.transition.Slide;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.transition.ArcMotion;
import androidx.transition.ChangeBounds;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class LaunchScreen extends AppCompatActivity {

  TextView tvNewUser, tvTest, tv_S;
  Button btnLogIn, btnSignUp, btnConLogIn;
  FrameLayout topShelf, frameLayout;
  ImageView imageV;
  TableLayout goldenShower;
  EditText et_Pwd, et_Login;
  //ViewGroup transitionsContainer;
  Boolean isReturnAnimation = true;
  Boolean isReturnAnimation2 = true;
  Boolean visible = false;
  Boolean inLaucher = true;
  Boolean cantStopMeNow = true;
  LinearLayout theShower;
  Boolean isDown = false;
  Boolean isDown2 = false;
  TableRow highestShoot;
  Boolean isColorsInverted = true;
  Boolean mColorsInverted = true;
  LinearLayout topbox_holder;
  FrameLayout bottombox;
  private Realm realm;
  private static ArrayList<GreenLand> fireBaseList;
  int wiidth;


  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_launch_screen);
    overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

    final ViewGroup transitionsContainer = (ViewGroup) findViewById(R.id.transitions_container);

    setStatusBarTrasparent();
    colorChange(transitionsContainer);/////////////////<<<<<<<<<<<<<<<<<<<<<<<<<<<

//    tvNewUser = (TextView) findViewById(R.id.tvNewUser);
    btnLogIn = (Button) findViewById(R.id.btnLogIn);
//    btnSignUp = (Button) findViewById(R.id.btnSignUp);
    imageV = (ImageView) findViewById(R.id.imageV);
    topShelf = (FrameLayout) findViewById(R.id.topShelf);
    btnConLogIn = (Button) findViewById(R.id.btnConLogIn);
    goldenShower = (TableLayout) findViewById(R.id.goldenShower);
    theShower = (LinearLayout) findViewById(R.id.theShower);
    highestShoot = (TableRow) findViewById(R.id.highestShit);
    et_Login = (EditText) findViewById(R.id.et_Login);
    et_Pwd = (EditText) findViewById(R.id.et_Pwd);
    tv_S = (TextView) findViewById(R.id.tv_S);
    topbox_holder = findViewById(R.id.topbox_holder);
    bottombox = findViewById(R.id.bottombox);
    frameLayout = findViewById(R.id.frameLayout);


//    mainContainer = (FrameLayout) findViewById(R.id.transitions_container);

    buttonAnimation(btnLogIn);
//    buttonAnimation(btnSignUp);
    buttonAnimation(btnConLogIn);

    btnLogIn.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        loginMeth(v, transitionsContainer, false);
        btnConLogIn.setEnabled(false);
        btnLogIn.setEnabled(false);
      }
    });


    et_Login.addTextChangedListener(new TextWatcher() {
      @Override
      public void afterTextChanged(Editable s) {

      }

      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (et_Login.getText().length() >= 5 && et_Pwd.getText().length() >= 5) {
          btnConLogIn.setEnabled(true);
        }
        if (et_Login.getText().length() < 5 || et_Pwd.getText().length() < 5) {
          btnConLogIn.setEnabled(false);
        }
      }
    });

    et_Pwd.addTextChangedListener(new TextWatcher() {
      @Override
      public void afterTextChanged(Editable s) {

      }

      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (et_Login.getText().length() >= 5 && et_Pwd.getText().length() >= 5) {
          btnConLogIn.setEnabled(true);
        }
        if (et_Login.getText().length() < 5 || et_Pwd.getText().length() < 5) {
          btnConLogIn.setEnabled(false);
        }
      }
    });


    btnConLogIn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        SharedPreferences mPrefs = getSharedPreferences("forSettings", 0);
        Boolean boo = mPrefs.getBoolean("offlineFunc", false);
        if (boo == true) {
//          firebaseStaticConfig();
          Log.wtf("tag", "WAiting While loading data from databse");
        }

        startActivity(new Intent(LaunchScreen.this, WelcomeScreen.class));
      }
    });


    Display display = getWindowManager().getDefaultDisplay();
    DisplayMetrics outMetrics = new DisplayMetrics();
    display.getMetrics(outMetrics);

    float density = getResources().getDisplayMetrics().density;
    float dpHeight = outMetrics.heightPixels / density;
    float dpWidth = outMetrics.widthPixels / density;
    Log.wtf("tag", "WIDTH::" + dpWidth);

    topbox_holder.setLayoutParams(new FrameLayout.LayoutParams((int) dpWidth * 2, WRAP_CONTENT));
    bottombox.setLayoutParams(new FrameLayout.LayoutParams((int) dpWidth * 2, 650, Gravity.BOTTOM));
    FrameLayout.LayoutParams topPrams = new FrameLayout.LayoutParams((int) dpWidth * 2, MATCH_PARENT, Gravity.END);
    topPrams.topMargin = 400;
    topShelf.setLayoutParams(new FrameLayout.LayoutParams(topPrams));
    frameLayout.setLayoutParams(new FrameLayout.LayoutParams((int) dpWidth * 4, 800, Gravity.TOP));

//    FrameLayout.LayoutParams btnParams = new FrameLayout.LayoutParams((int) (dpWidth*2)-150,WRAP_CONTENT,Gravity.CENTER);
//    btnParams.topMargin = -30;
//    btnLogIn.setLayoutParams(btnParams);
  }

  public void firebaseStaticConfig() {
//    final GreenLand[] wo = {null};
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference globRef = database.getReference();
    final DatabaseReference myRef2 = database.getReference().child("Green_Land");

    Realm.init(this);

    final RealmConfiguration realmConfig = new RealmConfiguration.Builder()
      .name("test1.realm")
      .schemaVersion(0)
//      .deleteRealmIfMigrationNeeded()
      .build();

    Realm.setDefaultConfiguration(realmConfig);

    realm = Realm.getInstance(realmConfig);
//
//    realm.executeTransaction(new Realm.Transaction() {
//      @Override
//      public void execute(Realm realm) {
//                  for(int i = 0; i<10;i++){
//                    final GreenLand wo = realm.createObject(GreenLand.class);
//                    wo.setTemperature(i+i);
//                    wo.setHumidity(i+5f);
//                    realm.insertOrUpdate(wo);
//                  }

//        realm.deleteAll();//in case of deletion
//      }
//    });


    RealmResults<GreenLand> result = realm.where(GreenLand.class).findAll();
    Log.wtf("tag", "WHAT WE got here BG THREAD??" + result.size());

//    realm.close();


//    Thread thread = new Thread(new Runnable() {
//      @Override
//      public void run() {

    globRef.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//        realm = Realm.getInstance(realmConfig);
        Log.wtf("tag", "STATIC  MOFOOOOOOOOOO:::" + dataSnapshot.getChildrenCount());
        for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {

//          devicesName.add(snapshot.getKey());

//          List<String> list = new ArrayList<String>(2);
//          list.add(snapshot.getKey());
//          devicesName.add(list);

//              realm.executeTransaction(new Realm.Transaction() {
//                @Override
//                public void execute(Realm realm) {
//                  final GreenLand wo = realm.createObject(GreenLand.class);
//                  wo.setTemperature(000);
//                  wo.setHumidity(0000f);
//                  realm.insertOrUpdate(wo);
////                  realm.deleteAll();//in case of deletion
//                }
//              });


//            realm.executeTransaction(new Realm.Transaction() {
//              @Override
//              public void execute(Realm realm) {
          GreenLand wo = new GreenLand();
          try {

//          if (snapshot.child("Time").getValue().toString() != null && snapshot.child("Temperature") != null) {

            wo.setTime(snapshot.child("Time").getValue().toString());
            wo.setTemperature(Float.valueOf(snapshot.child("Temperature").getValue().toString()));
            wo.setBatteryLevel(Double.valueOf(snapshot.child("Battery_Level").getValue().toString()));

//            Log.wtf("tag", "Time:::" + wo.getTime());
            fireBaseList.add(wo);

//                  wo.setTemperature(69);
//                  realm.insertOrUpdate(wo);
//          }
//                    realm.deleteAll();//in case of deletion
          } catch (Exception e) {
            e.printStackTrace();
          }
//              }
//            });

//                RealmResults<GreenLand> result = realm.where(GreenLand.class).findAllAsync();
//                Log.wtf("tag", "WHAT WE got here??" + result.size());

//                if (wo[0].getTime().equals("15 19 46 2019 4 20"))
//                  Log.wtf("tag", "TIME value????  " + wo[0].getTime());

        }
//        realm.close();

//        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//          Log.wtf("tag", "keyValue::" + snapshot.getKey());
//          try{
//          String time = snapshot.child("rxInfo/0/time").getValue().toString();
//
//          Log.wtf("tag", "time ::" + "" + "::" + time);
//          }catch (NullPointerException e){
//            Log.wtf("tag","NUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUULLLLLLLLLLLLLLLL"+snapshot.getKey());
//          }
//        }
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


  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  private void setupWindowAnimations() {
    Fade fade = new Fade();
    fade.setDuration(1000);
    getWindow().setEnterTransition(fade);

    Slide slide = new Slide();
    slide.setDuration(3000);
    getWindow().setExitTransition(slide);
  }

  public void buttonAnimation(View v) {
    PushDownAnim.setPushDownAnimTo(v)
      .setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        }

      }).setScale(PushDownAnim.MODE_STATIC_DP, 8);
  }

  public void setStatusBarColored(Activity context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      Window w = context.getWindow();
      w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//      int statusBarHeight = getStatusBarHeight(context);

      View view = new View(context);
      view.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//      view.getLayoutParams().height = statusBarHeight;
      ((ViewGroup) w.getDecorView()).addView(view);
//      view.setBackground(context.getResources().getDrawable(R.drawable.navibg));
    }
  }

  public void setStatusBarTrasparent() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      Window w = getWindow();
      w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
  }

  public void loginMeth(final View view, final ViewGroup tc, boolean visible) {
    animateIt(btnLogIn, 800, tc, Gravity.TOP | Gravity.CENTER, Gravity.BOTTOM | Gravity.CENTER);
//    animateIt(btnSignUp, 1000, tc, Gravity.TOP | Gravity.CENTER, Gravity.BOTTOM | Gravity.CENTER);
//    animateIt(tvNewUser, 1000, tc, Gravity.TOP | Gravity.CENTER, Gravity.BOTTOM | Gravity.CENTER);
    animateIt(topShelf, 800, tc, Gravity.END, Gravity.START);
    animateIt(btnConLogIn, 800, tc, Gravity.BOTTOM | Gravity.CENTER, Gravity.TOP | Gravity.CENTER);
//    theShower.setPadding(0,20,0,0);
    animateIt3(imageV, 500, tc, 120, 20);
    animateIt2(highestShoot, 500, tc, 0, -460);//PRAISE THE SUUUUUUUUN \O/

//    superFade(tc, goldenShower);

//    animateIt2(goldenShower, 800, tc,0,40);

//    goldenShower.setPadding(0,190,0,0);


    ///RESIZING STUFF////
//    superFade(tc, imageV);
//    DisplayMetrics display = this.getResources().getDisplayMetrics();
//    wiidth = display.widthPixels;
//    tvNewUser.setText(String.valueOf(imageV.getWidth()));
//    imageV.getLayoutParams().width += ((wiidth - imageV.getWidth()));
//    imageV.requestLayout();


  }

  public void signupMeth(final View v, final ViewGroup tc) {
//    superFade(tc, imageV);

//    final Handler handler = new Handler();
//    handler.postDelayed(new Runnable() {
//      @Override
//      public void run() {
//        // Do something after 5s = 5000ms
//      }
//    }, 1000);
////    animateIt2(goldenShower, 800, tc,0,240);

  }

  public void superFade(ViewGroup tc, View v) {
    //**********************doesn't work anymore
//    TransitionSet set = new TransitionSet()
//      .addTransition(new Scale(0.8f))
//      .addTransition(new Fade())
//      .setDuration(200)
//      .setInterpolator(visible ? new LinearOutSlowInInterpolator() : new FastOutLinearInInterpolator());
//
//
//    TransitionManager.beginDelayedTransition(tc, set);
//    if (v.getVisibility() == View.VISIBLE) {
//      v.setVisibility(View.INVISIBLE);
//    } else {
//      v.setVisibility(View.VISIBLE);
//    }
  }

  public void animateIt(View obj, int time, ViewGroup tc, int startPoint, int endPoint) {
    Boolean state = true;
    Transition CB = new ChangeBounds();
    CB.setPathMotion(new ArcMotion());
    CB.setDuration(time);
    TransitionManager.beginDelayedTransition(tc, CB);
    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) obj.getLayoutParams();

    if (state && params.gravity != startPoint) {
      params.gravity = (startPoint);
      state = false;
    } else {
      params.gravity = (endPoint);
      state = true;
    }

    obj.setLayoutParams(params);

    if (obj.equals(btnLogIn)) {
      inLaucher = false;
    }

  }


  public void animateIt2(View obj, int time, ViewGroup tc, int startPoint, int endPoint) {
//    Boolean state = true;
    Transition CB = new ChangeBounds();
    CB.setPathMotion(new ArcMotion());
    CB.setDuration(time);
    TransitionManager.beginDelayedTransition(tc, CB);
    TableLayout.LayoutParams params = (TableLayout.LayoutParams) obj.getLayoutParams();

    if (!isDown) {
      params.topMargin = endPoint;
      isDown = true;
    } else {
      params.topMargin = startPoint;
      isDown = false;
    }

    obj.setLayoutParams(params);


  }

  public void animateIt3(final View obj, int time, ViewGroup tc, int startPoint, int endPoint) {
    ViewGroup tc2 = (ViewGroup) findViewById(R.id.theShower);
    Transition CB2 = new ChangeBounds();
    CB2.setPathMotion(new ArcMotion());
    CB2.setDuration(time);
    TransitionManager.beginDelayedTransition(tc, CB2);
    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) obj.getLayoutParams();

    if (!isDown2) {
      params.topMargin = endPoint;
      isDown2 = true;
    } else {
      params.topMargin = startPoint;
      isDown2 = false;
    }

    obj.setLayoutParams(params);

  }


  public void colorChange(final ViewGroup tc) {
    final TransitionDrawable transition = (TransitionDrawable) tc.getBackground();

    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        if (cantStopMeNow) {
          transition.startTransition(8000);
          cantStopMeNow = false;
        } else {
          transition.reverseTransition(8000);
          cantStopMeNow = true;
        }

      }
    };

    final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    scheduler.scheduleAtFixedRate(runnable, 5, 7, TimeUnit.SECONDS);

  }

  @Override
  public void onBackPressed() {
    btnLogIn.setEnabled(true);
    Log.wtf("tag", "onBackPresssssssssed");
    if (inLaucher) {
      this.finish();
    } else {
      final ViewGroup tc = (ViewGroup) findViewById(R.id.transitions_container);

      animateIt(btnLogIn, 600, tc, Gravity.TOP | Gravity.CENTER, Gravity.BOTTOM | Gravity.CENTER);
//      animateIt(btnSignUp, 800, tc, Gravity.TOP | Gravity.CENTER, Gravity.BOTTOM | Gravity.CENTER);
//      animateIt(tvNewUser, 800, tc, Gravity.TOP | Gravity.CENTER, Gravity.BOTTOM | Gravity.CENTER);
      animateIt(topShelf, 600, tc, Gravity.END, Gravity.START);
      animateIt(btnConLogIn, 600, tc, Gravity.BOTTOM | Gravity.CENTER, Gravity.TOP | Gravity.CENTER);
//      theShower.setPadding(0,100,0,0);
      animateIt3(imageV, 500, tc, 120, 20);

      animateIt2(highestShoot, 500, tc, 0, -300);


//      goldenShower.setPadding(0,0,0,0);
//      ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) goldenShower.getLayoutParams();
//      params.leftMargin = 0;
//      params.topMargin = 0;
//      goldenShower.invalidate();
//      goldenShower.requestLayout();

      inLaucher = true;
    }
  }


}

