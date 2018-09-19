package com.uyr.yusara.mydiceapps;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);


        int width = dm.widthPixels;
        int height = dm.widthPixels;

        getWindow().setLayout((int) (width*1), (int) (height*1));

        View view = this.getWindow().getDecorView();

        view.setBackgroundResource(R.color.accent_material_light_1);

        TextView abouttitle = (TextView)findViewById(R.id.abouttitle);
        TextView abouttxt = (TextView)findViewById(R.id.abouttxt);

        //Custom Font
        Typeface mycustomfont = Typeface.createFromAsset(getAssets(),"fonts/OratorStd.otf");
        abouttitle.setTypeface(mycustomfont);
        abouttxt.setTypeface(mycustomfont);

    }
}
