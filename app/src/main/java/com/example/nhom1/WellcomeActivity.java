package com.example.nhom1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class WellcomeActivity extends AppCompatActivity {
    ImageView imgAppName;
    LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);
        imgAppName = findViewById(R.id.imgappname);
        lottieAnimationView = findViewById(R.id.lottieAnimationView);

        imgAppName.animate().translationY(-700).setDuration(2700).setStartDelay(0);
        lottieAnimationView.animate().translationY(600).setDuration(2000).setStartDelay(0);

        Thread time = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(5000);
                    Intent intent = new Intent(WellcomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        time.start();
    }
}