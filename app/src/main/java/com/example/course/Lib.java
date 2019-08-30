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

    public static int[][][] ids = {
            //--------Первый курс
            {
                    {},
                    {},
                    {1},
                    {2, 3, 4},
                    {5, 6, 7, 8, 9},
                    {10, 11, 12},
                    {13, 14, 15, 16},
                    {17, 18, 19, 20},
                    {21, 22, 23, 24},
                    {25, 26, 27, 28},
                    {30, 31},
                    {32, 33, 34, 35, 36, 37, 38},
                    {39, 40}
            },
            //--------Второй курс
            {
                    {},
                    {},
                    {},
                    {41, 42, 43, 44, 45},
                    {}
            },
            //--------Третий курс
            {
                    {},
                    {}
            }
    };

    public static int[][][] rate = {
            //-------Первый курс
            {
                    {},
                    {},
                    {1},
                    {1, 1, 2},
                    {1, 3, 1, 10, 7},
                    {2, 2, 6},
                    {3, 2, 10, 6},
                    {2, 6, 10, 17},
                    {8, 10, 17, 22},
                    {4, 10, 8, 10},
                    {15, 23},
                    {20, 30, 15, 42, 15, 20, 48},
                    {40, 40}
            },
            //--------Второй курс
            {
                    {},
                    {},
                    {},
                    {10, 15, 20, 30, 47},
                    {}
            },
            //--------Третий курс
            {
                    {},
                    {}
            }
    };

    public static int[][] names =
    {
        //------------Первый курс
        {
            R.string.introduction, R.string.installing, R.string.first_program, R.string.variables, R.string.data_types, R.string.if_else,
            R.string.loops, R.string.arrays, R.string.std_functions, R.string.functions, R.string.vectors, R.string.structures
        },
        //--------Второй курс
        {
            R.string.introduction, R.string.worktime, R.string.sort, R.string.binsearch
        },
        //--------Третий курс
        {}
    };

    public static String[][] fileNames =
    {
            //------------Первый курс
            {
                "1-Introduction.html", "2-Installing_software.html", "3-Simple_programm.html", "4-Variables.html", "5-Data_types.html", "6-Conditionals.html",
                "7-Loops.html", "8-Arrays.html", "9-Standart_functions.html", "10-Functions.html", "11-Vectors.html", "12-Structures.html"
            },
            //--------Второй курс
            {
                "1-Introduction.html", "2-Work_time.html", "3-Sort.html", "4-Binsearch.html"
            },
            //--------Третий курс
            {}
    };

    public static int[][] headers_pos = {
            //------------Первый курс
            {0},
            //--------Второй курс
            {0},
            //--------Третий курс
            {}
    };
    public static int[][] headers_color = {
            //------------Первый курс
            {0},
            //--------Второй курс
            {0},
            //--------Третий курс
            {}
    };
    public static int[][] headers_name = {
            //------------Первый курс
            {R.string.Beginning},
            //--------Второй курс
            {R.string.asymptotic},
            //--------Третий курс
            {}
    };

    public static String[] courseName = {"Beginning", "Algorithm", "Advanced"};

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
