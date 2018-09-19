package com.uyr.yusara.mydiceapps;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Random;

public class NumberActivity extends AppCompatActivity {

    Button generateButton,backNumberButton;
    EditText editnum1,editnum2;
    TextView displayView,title;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        generateButton = (Button) findViewById(R.id.btnNumber);
        //backNumberButton = (Button) findViewById(R.id.backNumberButton);

        editnum1 = (EditText) findViewById(R.id.editnum1);
        editnum2 = (EditText) findViewById(R.id.editnum2);

        displayView = (TextView) findViewById(R.id.displayView);
        title = (TextView)findViewById(R.id.title);

        //Custom Font
        Typeface mycustomfont = Typeface.createFromAsset(getAssets(),"fonts/OratorStd.otf");
        displayView.setTypeface(mycustomfont);
        title.setTypeface(mycustomfont);
        generateButton.setTypeface(mycustomfont);

        Animation anim = AnimationUtils.loadAnimation(this,R.anim.blink_anim);
        generateButton.startAnimation(anim);

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

    public void fnGenerate (View view)
    {
        Random random = new Random();

        String num1,num2;
        int max,min,output;

        num1 = editnum1.getText().toString();
        num2 = editnum2.getText().toString();

        if(num1.equals("") && num2.equals(""))
        {
            Toast.makeText(NumberActivity.this, "Please insert your range", Toast.LENGTH_SHORT).show();
        }
        else
        {
            min = Integer.parseInt(num1);
            max = Integer.parseInt(num2);

            if(max > min)
            {
            output = random.nextInt((max - min) + 1) + min;
            displayView.setText("" + output);
            }
            else
            {
                Toast.makeText(NumberActivity.this, "Please insert correctly", Toast.LENGTH_SHORT).show();
            }

            final MediaPlayer mp = MediaPlayer.create(this, R.raw.number);
            mp.start();

            generateButton.clearAnimation();
        }


    }

    public void fnBack (View view)
    {
        Intent intent = new Intent(NumberActivity.this, MainActivity.class);
        startActivity(intent);
        NumberActivity.this.finish();
    }
}
