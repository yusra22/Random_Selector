package com.uyr.yusara.mydiceapps;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Random;

public class QuestionActivity extends AppCompatActivity {

    TextView displayAnswer;
    Button randomAnswer;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        displayAnswer = (TextView) findViewById(R.id.displayAnswer);
        randomAnswer = (Button) findViewById(R.id.randomAnswer);

        //Custom Font
        Typeface mycustomfont = Typeface.createFromAsset(getAssets(),"fonts/OratorStd.otf");
        displayAnswer.setTypeface(mycustomfont);
        randomAnswer.setTypeface(mycustomfont);

        Animation anim = AnimationUtils.loadAnimation(this,R.anim.blink_anim);
        randomAnswer.startAnimation(anim);

        interstitialAd = new InterstitialAd(this);
        //pkai unit id interstialad dkt addmob
        interstitialAd.setAdUnitId("ca-app-pub-2786613293502305/7429637348");
        interstitialAd.loadAd(new AdRequest.Builder().build());

    }

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

    public void btnAnswer(View view)
    {



        final int[] answerArray = new int[]
                {
                        R.string.answer1,
                        R.string.answer2,
                        R.string.answer3,
                        R.string.answer4,
                        R.string.answer5,
                        R.string.answer6,
                        R.string.answer7,
                        R.string.answer8,
                        R.string.answer9,
                        R.string.answer10,
                        R.string.answer11
                };

        Log.d("Niceee", "Button Pressed");

        Random randomAnswers = new Random();

        int answer = randomAnswers.nextInt(10);
        Log.d("Color", "The random color is" + answer);

        displayAnswer.setText(answerArray[answer]);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.answer);
        mp.start();

        randomAnswer.clearAnimation();
    }

}
