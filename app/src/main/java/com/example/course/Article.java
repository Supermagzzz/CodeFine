package com.example.course;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class Article extends AppCompatActivity {

    WebView myBrowser;
    int id;
    ClipboardManager clipboardManager;

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
        myBrowser.loadUrl("file:///android_asset/Beginning/" + intent.getStringExtra("site"));
        id = intent.getIntExtra("id", 0);
        clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
    }

    public class WebAppInterface
    {
        Context myContext;

        WebAppInterface(Context c)
        {
            myContext = c;
        }

        @JavascriptInterface
        public void goToTask()
        {
            Intent nextIntent = new Intent(Article.this, TaskActivity.class);
            nextIntent.putExtra("id", id);
            startActivity(nextIntent);
        }

        @JavascriptInterface
        public void copyLink(String link)
        {
            ClipData data = ClipData.newPlainText("text", link);
            clipboardManager.setPrimaryClip(data);
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.link_copy), Toast.LENGTH_SHORT).show();
        }
    }

    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
        }
    }
}