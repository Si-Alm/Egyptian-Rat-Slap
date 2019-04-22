package com.nerd.si.ers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainScreenActivity extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen_layout);



        MobileAds.initialize(this, "ca-app-pub-5219500977208314~5698985133");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-5219500977208314~5698985133");
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());



        Button b1 = findViewById(R.id.fourPlay);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                Intent fourP = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(fourP);
            }
        });

        Button b2 = findViewById(R.id.rule);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                Intent ht = new Intent(getApplicationContext(),HowTo.class);
                startActivity(ht);
            }
        });
        /*
        One player mode will not be included in this first release
        //TODO: Create a one player mode XD
        Button b3 = findViewById(R.id.onePlay);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent op = new Intent(getApplicationContext(),OnePlayerActivity.class);
                startActivity(op);
            }
        });*/

        Button b4 = findViewById(R.id.twoPlay);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                Intent tp = new Intent(getApplicationContext(),TwoPlayerActivity.class);
                startActivity(tp);
            }
        });

        Button b5 = findViewById(R.id.threePlay);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                Intent thp = new Intent(getApplicationContext(),ThreePlayerActivity.class);
                startActivity(thp);
            }
        });
    }


}
