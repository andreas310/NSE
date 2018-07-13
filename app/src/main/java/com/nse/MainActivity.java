package com.nse;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textdes;
    private ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textdes = findViewById(R.id.textdes);
        icon = findViewById(R.id.icon);
        Animation splashtransition = AnimationUtils.loadAnimation(this, R.anim.splashtransition);
        textdes.startAnimation(splashtransition);
        icon.startAnimation(splashtransition);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pindah();
            }
        }, 2000);
    }

    private void pindah() {
        overridePendingTransition(R.anim.splashtransition, R.anim.splashtransition2);
        startActivity(new Intent(MainActivity.this, HomeActivity.class));
        finish();
    }
}
