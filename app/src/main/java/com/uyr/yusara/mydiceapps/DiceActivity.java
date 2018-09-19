package com.uyr.yusara.mydiceapps;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Random;

public class DiceActivity extends AppCompatActivity {

    Button rollButton,backButton;
    ImageView leftDice;
    ImageView rightDice;
    TextView view;
    private InterstitialAd interstitialAd;

    @Override
    public void onBackPressed() {
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
            interstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    finish();
                }
            });
        }else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        view = (TextView) findViewById(R.id.titledice);
        rollButton = (Button) findViewById(R.id.rollButton);
        //backButton = (Button) findViewById(R.id.backButton);

        leftDice = (ImageView) findViewById(R.id.leftDice);
        rightDice = (ImageView) findViewById(R.id.rightDice);

        //Custom Font
        Typeface mycustomfont = Typeface.createFromAsset(getAssets(),"fonts/OratorStd.otf");
        view.setTypeface(mycustomfont);
        rollButton.setTypeface(mycustomfont);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.roll);

        final int[] diceArray = new int[]
                {
                        R.drawable.dice1,
                        R.drawable.dice2,
                        R.drawable.dice3,
                        R.drawable.dice4,
                        R.drawable.dice5,
                        R.drawable.dice6
                };

        Animation anim = AnimationUtils.loadAnimation(this,R.anim.blink_anim);
        rollButton.startAnimation(anim);

        interstitialAd = new InterstitialAd(this);
        //pkai unit id interstialad dkt addmob
        interstitialAd.setAdUnitId("ca-app-pub-2786613293502305/7429637348");
        interstitialAd.loadAd(new AdRequest.Builder().build());


        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("Diceee", "Button Pressed");

                Random randomNumberGenerator = new Random();

                int number = randomNumberGenerator.nextInt(6);
                Log.d("Diceee", "The random number is" + number);
                leftDice.setImageResource(diceArray[number]);

                int number2 = randomNumberGenerator.nextInt(6);
                Log.d("Dicee", "The second random number is" + number2);
                rightDice.setImageResource(diceArray[number2]);

                mp.start();

                rollButton.clearAnimation();

                //Test beb hahahaha

            }
        });

/*        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiceActivity.this, MainActivity.class);
                startActivity(intent);
                DiceActivity.this.finish();
                onRestart();
            }
        });*/

    }
}