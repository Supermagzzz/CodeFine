package com.example.course;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Article extends AppCompatActivity {

    WebView myBrowser;
    int id;
    ClipboardManager clipboardManager;
    int courseId;

    private boolean doubleClick = false;

    @Override
    @SuppressLint("JavascriptInterface")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Lib.initActivity(this, R.layout.activity_article);

        myBrowser = findViewById(R.id.my_browser);
        WebSettings webSettings = myBrowser.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        myBrowser.addJavascriptInterface(new WebAppInterface(this), "Android");
        Intent intent = getIntent();
        courseId = intent.getIntExtra("courseId", 0);
        myBrowser.loadUrl("file:///android_asset/" + getString(R.string.lang) + "/" + Lib.courseName[courseId] + "/" + intent.getStringExtra("site"));
        id = intent.getIntExtra("id", 0);
        clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        myBrowser.post(new Runnable() {
            @Override
            public void run() {
                int h = myBrowser.getHeight() + findViewById(R.id.Header).getHeight();
                myBrowser.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, h));
            }
        });
    }

    public class WebAppInterface
    {
        Context myContext;
        FrameLayout header;
        float lastY;
        float curY;

        WebAppInterface(Context c)
        {
            header = findViewById(R.id.Header);
            myContext = c;
            lastY = 0;
            curY = 0;
        }

        @JavascriptInterface
        public void goToTask()
        {
            if(!doubleClick) {
                doubleClick = true;
                Intent nextIntent = new Intent(Article.this, TaskActivity.class);
                nextIntent.putExtra("id", id);
                nextIntent.putExtra("courseId", courseId);
                startActivity(nextIntent);
            }
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

        @JavascriptInterface
        public void scroll(float y)
        {
            curY -= y - lastY;
            lastY = y;
            curY = Math.min(curY, 0);
            curY = Math.max(curY, -header.getHeight());
            header.setY(curY);
            myBrowser.setY(header.getHeight() + curY);
        }
    }

    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.settings:
                if(!doubleClick) {
                    doubleClick = true;
                    startActivity(new Intent(this, SettingsActivity.class));
                    break;
                }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        doubleClick = false;
    }
}