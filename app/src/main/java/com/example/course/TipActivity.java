package com.example.course;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
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
import android.widget.TextView;
import android.widget.Toast;

public class TipActivity extends AppCompatActivity {

    WebView myBrowser;
    int id;
    ClipboardManager clipboardManager;

    @Override
    @SuppressLint("JavascriptInterface")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Lib.initActivity(this, R.layout.activity_tip);

        myBrowser = findViewById(R.id.my_browser);
        Intent intent = getIntent();
        myBrowser.loadUrl("file:///android_asset/" + getString(R.string.lang) + "/Beginning/Tip/" + intent.getStringExtra("path"));
        id = intent.getIntExtra("id", 0);
        clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
    }
}