package com.example.course;

import android.app.FragmentManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TaskActivity extends AppCompatActivity {

    int[][] ids = {
            {},
            {},
            {1},
            {2, 3, 4},
            {5, 6, 7, 8, 9}
    };

    int[][] rate = {
            {},
            {},
            {1},
            {1, 1, 2},
            {1, 3, 1, 10, 7}
    };

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_task);
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

        webSettings = myBrowser.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
        myBrowser.addJavascriptInterface(new TaskActivity.WebAppInterface(this), "Android");

        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointer = (pointer - 1 + ids[myId].length) % ids[myId].length;
                updateWebView();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointer = (pointer + 1 + ids[myId].length) % ids[myId].length;
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
                if(sPref.getBoolean("Beginning_task" + ids[myId][pointer], false))
                {
                    sPref.edit().putBoolean("Beginning_task" + ids[myId][pointer], false).apply();
                    rating -= rate[myId][pointer];
                }
                else
                {
                    sPref.edit().putBoolean("Beginning_task" + ids[myId][pointer], true).apply();
                    rating += rate[myId][pointer];
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

    private void updateSolve()
    {
        solve.setChecked(sPref.getBoolean("Beginning_task" + ids[myId][pointer], false));
    }

    private void updateWebView()
    {
        myBrowser.loadUrl("file:///android_asset/Beginning/Tasks/" + intent.getIntExtra("id", 0) + "/" + ids[myId][pointer] + ".html");
        textPointer.setText((pointer + 1) + "/" + ids[myId].length);
        updateSolve();
    }
}
