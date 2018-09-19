package com.uyr.yusara.mydiceapps;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageButton diceMenu,numberMenu,askMenu,colorMenu;
    Button btnabout,btnshare;
    TextView titlemenu;
    View view;
    AdView mAdview;
    private InterstitialAd interstitialAd;

    //untuk interstitialAd backpress
/*    @Override
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
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titlemenu = (TextView)findViewById(R.id.titlemenu);

        diceMenu = (ImageButton) findViewById(R.id.diceMenu);
        numberMenu = (ImageButton) findViewById(R.id.numberMenu);
        askMenu = (ImageButton) findViewById(R.id.askMenu);
        colorMenu = (ImageButton) findViewById(R.id.colorMenu);

        btnabout = (Button)findViewById(R.id.btnabout);
        btnshare = (Button)findViewById(R.id.btnshare);

        //Custom Font
        Typeface mycustomfont = Typeface.createFromAsset(getAssets(),"fonts/OratorStd.otf");
        titlemenu.setTypeface(mycustomfont);
        btnabout.setTypeface(mycustomfont);
        btnshare.setTypeface(mycustomfont);
        //rollButton.setTypeface(mycustomfont);

        Animation anim = AnimationUtils.loadAnimation(this,R.anim.bounce);

        titlemenu.startAnimation(anim);
        diceMenu.startAnimation(anim);
        numberMenu.startAnimation(anim);
        askMenu.startAnimation(anim);
        colorMenu.startAnimation(anim);

        //Ini apps id dri admobs
        MobileAds.initialize(this, "ca-app-pub-2786613293502305/8250962094");


        mAdview = (AdView)findViewById(R.id.adView);
        //AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdview.loadAd(adRequest);

        interstitialAd = new InterstitialAd(this);
        //pkai unit id interstialad dkt addmob
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAd.loadAd(new AdRequest.Builder().build());

/*        interstitialAd.setAdListener(new AdListener()
        {
            @Override
            public void onAdClosed() {

                startActivity(new Intent(MainActivity.this, DiceActivity.class));
                //sebab ade emulator
                interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
            }
        });*/


    }

    public void btnDice (View view)
    {
/*        if(interstitialAd.isLoaded())
        {
            interstitialAd.show();
        }
        else
        {
            startActivity(new Intent(this, DiceActivity.class));
        }*/

        startActivity(new Intent(this, DiceActivity.class));
    }

    public void btnNumber (View view)
    {
        startActivity(new Intent(this, NumberActivity.class));
    }

    public void btnColor (View view)
    {
        startActivity(new Intent(this, ColorActivity.class));
    }

    public void btnQuestion (View view)
    {
        startActivity(new Intent(this, QuestionActivity.class));
    }

    public void btnShare(View view)
    {
        Intent myIntent = new Intent(Intent.ACTION_SEND);
        myIntent.setType("text/plain");
        String shareBody = "Random Selector";
        String shareSub = "Your Subjecr here";
        myIntent.putExtra(Intent.EXTRA_SUBJECT,shareBody);
        myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
        startActivity(Intent.createChooser(myIntent, "Share Using"));
    }

    public void btnAbout(View view)
    {
        startActivity(new Intent(this, AboutActivity.class));
    }
}
