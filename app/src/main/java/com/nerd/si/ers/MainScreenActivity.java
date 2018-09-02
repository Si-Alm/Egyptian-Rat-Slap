package com.nerd.si.ers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen_layout);

        Button b1 = findViewById(R.id.fourPlay);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fourP = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(fourP);
            }
        });

        Button b2 = findViewById(R.id.rule);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ht = new Intent(getApplicationContext(),HowTo.class);
                startActivity(ht);
            }
        });

        Button b3 = findViewById(R.id.onePlay);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent op = new Intent(getApplicationContext(),OnePlayerActivity.class);
                startActivity(op);
            }
        });

        Button b4 = findViewById(R.id.twoPlay);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tp = new Intent(getApplicationContext(),TwoPlayerActivity.class);
                startActivity(tp);
            }
        });

        Button b5 = findViewById(R.id.threePlay);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent thp = new Intent(getApplicationContext(),ThreePlayerActivity.class);
                startActivity(thp);
            }
        });
    }


}
