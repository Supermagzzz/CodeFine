package com.example.course;

import android.app.FragmentManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TaskActivity extends AppCompatActivity implements RewardedVideoAdListener {

    int myId;

    int pointer = 0;
    SharedPreferences sPref;
    int rating;
    Button last, next, tip;
    CheckBox solve;
    TextView textPointer, textSolve;
    WebView myBrowser;
    Intent intent;
    WebSettings webSettings;
    ClipboardManager clipboardManager;
    Lib lib = new Lib();
    private RewardedVideoAd mRewardedVideoAd;
    FrameLayout onLoading;
    boolean stopLoad = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Lib.initActivity(this, R.layout.activity_task);
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();

        myBrowser = findViewById(R.id.my_browser);
        intent = getIntent();
        myId = intent.getIntExtra("id", 0);
        sPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        rating = sPref.getInt("Beginning_rating", 0);
        last = findViewById(R.id.last);
        next = findViewById(R.id.next);
        solve = findViewById(R.id.solve);
        tip = findViewById(R.id.tip);
        sPref.edit().putInt("A", 100).apply();
        textPointer = findViewById(R.id.pointer);
        textSolve = findViewById(R.id.textSolve);
        onLoading = findViewById(R.id.onLoad);

        webSettings = myBrowser.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
        myBrowser.addJavascriptInterface(new TaskActivity.WebAppInterface(this), "Android");

        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointer = (pointer - 1 + lib.ids1[myId].length) % lib.ids1[myId].length;
                updateWebView();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointer = (pointer + 1 + lib.ids1[myId].length) % lib.ids1[myId].length;
                updateWebView();
            }
        });

        textSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solve.callOnClick();
            }
        });

        solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sPref.getBoolean("Beginning_task" + lib.ids1[myId][pointer], false))
                {
                    sPref.edit().putBoolean("Beginning_task" + lib.ids1[myId][pointer], false).apply();
                    rating -= lib.rate1[myId][pointer];
                }
                else
                {
                    sPref.edit().putBoolean("Beginning_task" + lib.ids1[myId][pointer], true).apply();
                    rating += lib.rate1[myId][pointer];
                }
                updateSolve();
                sPref.edit().putInt("Beginning_rating", rating).apply();
            }
        });

        updateWebView();
    }


    public class WebAppInterface
    {
        Context myContext;

        WebAppInterface(Context c)
        {
            myContext = c;
        }

        @JavascriptInterface
        public void copyLink(String link)
        {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, link);
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent,"Поделиться"));
        }
    }

    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case R.id.tip:
                stopLoad = false;
                onLoading.setVisibility(View.VISIBLE);
                waitLoading();
                break;
            case R.id.backLoad:
                stopLoad = true;
                onLoading.setVisibility(View.INVISIBLE);
                break;
        }
    }

    public void waitLoading()
    {
        if(stopLoad)
        {
            stopLoad = false;
            return;
        }
        if(!mRewardedVideoAd.isLoaded()) {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    waitLoading();
                }
            }, 1000);
        }
        else
        {
            mRewardedVideoAd.show();
        }
    }

    @Override
    public void onRewarded(RewardItem reward)
    {

    }

    private void loadRewardedVideoAd()
    {
        mRewardedVideoAd.loadAd(getResources().getString(R.string.banner_ad_id_tip), new AdRequest.Builder().build());
    }

    private void updateSolve()
    {
        solve.setChecked(sPref.getBoolean("Beginning_task" + lib.ids1[myId][pointer], false));
    }

    private void updateWebView()
    {
        myBrowser.loadUrl("file:///android_asset/" + getString(R.string.lang) + "/Beginning/Tasks/" + intent.getIntExtra("id", 0) + "/" + lib.ids1[myId][pointer] + ".html");
        textPointer.setText((pointer + 1) + "/" + lib.ids1[myId].length);
        updateSolve();
    }

    @Override
    public void onRewardedVideoAdLeftApplication() { }
    @Override
    public void onRewardedVideoAdClosed() {
        onLoading.setVisibility(View.INVISIBLE);
        loadRewardedVideoAd();
    }
    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) { }
    @Override
    public void onRewardedVideoAdLoaded() { }
    @Override
    public void onRewardedVideoAdOpened() { }
    @Override
    public void onRewardedVideoStarted() { }
    @Override
    public void onRewardedVideoCompleted() {

    }

}
