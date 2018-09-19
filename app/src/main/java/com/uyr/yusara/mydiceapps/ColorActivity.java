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

import org.w3c.dom.Text;

import java.util.Random;

public class ColorActivity extends AppCompatActivity {

    View view;
    TextView displayColour;
    Button randomAnswer;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        view = this.getWindow().getDecorView();

        displayColour = (TextView) findViewById(R.id.displayColour);
        randomAnswer = (Button) findViewById(R.id.randomAnswer);

        //Custom Font
        Typeface mycustomfont = Typeface.createFromAsset(getAssets(),"fonts/OratorStd.otf");
        displayColour.setTypeface(mycustomfont);
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

    public void goRandomColor(View v)
    {
        final int[] colorArray = new int[]
                {
                        R.color.red,
                        R.color.aqua,
                        R.color.white,
                        R.color.silver,
                        R.color.black,
                        R.color.maroon,
                        R.color.yellow,
                        R.color.olive,
                        R.color.lime,
                        R.color.green,
                        R.color.teal,
                        R.color.blue,
                        R.color.navy,
                        R.color.fuchsia,
                        R.color.purple
                };
       // view.setBackgroundResource(R.color.red);

        Log.d("Niceee", "Button Pressed");

        Random randomColorGenerator = new Random();

        int color = randomColorGenerator.nextInt(14);
        Log.d("Color", "The random color is" + color);
        view.setBackgroundResource(colorArray[color]);

        if(color == 0)
        {
            displayColour.setText("Red: #F42B00");
        }
        if(color == 1)
        {
            displayColour.setText("Aqua: #00FFFF");
        }
        if(color == 2)
        {
            displayColour.setText("White: #FFFFFF");
        }
        if(color == 3)
        {
            displayColour.setText("Silver: #C0C0C0");
        }

        if(color == 4)
        {
            displayColour.setText("Black: #000000");
        }

        if(color == 5)
        {

            displayColour.setText("Maroon: #800000");
        }
        if(color == 6)
        {
            displayColour.setText("Yellow: #FFFF00");
        }
        if(color == 7)
        {
            displayColour.setText("Olive: #808000");
        }
        if(color == 8)
        {
            displayColour.setText("Lime: #00FF00");
        }
        if(color == 9)
        {
            displayColour.setText("Green: #008000");
        }
        if(color == 10)
        {
            displayColour.setText("Teal: #008080");
        }
        if(color == 11)
        {
            displayColour.setText("Blue: #0000FF");
        }
        if(color == 12)
        {
            displayColour.setText("Navy: #000080");
        }
        if(color == 13)
        {
            displayColour.setText("Fuchsia: #FF00FF");
        }
        if(color == 14)
        {
            displayColour.setText("Purple: #800080");
        }

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.srapy);
        mp.start();

        randomAnswer.clearAnimation();

    }
}