package com.example.course;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Locale;

public class Lib {

    int[][] ids1 = {
            {},
            {},
            {1},
            {2, 3, 4},
            {5, 6, 7, 8, 9},
            {10, 11, 12},
            {13, 14, 15, 16},
            {17, 18, 19, 20},
            {21, 22, 23, 24},
            {25, 26, 27, 28, 29}
    };

    int[][] rate1 = {
            {},
            {},
            {1},
            {1, 1, 2},
            {1, 3, 1, 10, 7},
            {2, 2, 6},
            {3, 2, 10, 6},
            {2, 6, 10, 17},
            {8, 10, 17, 22},
            {4, 10, 8, 10, 35}
    };

    static void initActivity(Activity ctx, int layout)
    {
        ctx.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ctx.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ctx.setContentView(layout);
        MobileAds.initialize(ctx, ctx.getResources().getString(R.string.banner_ad_id_lambda));
        AdView mAdView = ctx.findViewById(R.id.adView);
        AdRequest.Builder builder = new AdRequest.Builder();
        mAdView.loadAd(builder.build());
    }

    static void setLang(String lang, Context ctx)
    {
        Locale locale = new Locale(lang);
        Resources res = ctx.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = locale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(ctx, MainActivity.class);
        refresh.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        ctx.startActivity(refresh);
    }
}
