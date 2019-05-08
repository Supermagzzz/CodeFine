package com.example.course;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    String lang;
    Locale locale;
    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);

        sPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        lang = sPref.getString("lang", "default");

        ImageView en = findViewById(R.id.english);
        ImageView ru = findViewById(R.id.russian);

        if(getIntent().getIntExtra("first", 0) == 1)
        {
            en.setImageResource(R.drawable.en_flag);
            ru.setImageResource(R.drawable.ru_flag);
        }
        else
        {
            en.setImageResource(R.drawable.en_flag_inactive);
            ru.setImageResource(R.drawable.ru_flag_inactive);
            if(lang.equals("default"))
            {
                lang = getResources().getString(R.string.lang);
            }
            if(lang.equals("ru")) ru.setImageResource(R.drawable.ru_flag);
            if(lang.equals("en")) en.setImageResource(R.drawable.en_flag);
        }
    }

    public void onClick(View view)
    {
        boolean flag = true;
        switch (view.getId())
        {
            case R.id.russian:
                if (lang.equals("ru")) flag = false;
                lang = "ru";
                sPref.edit().putString("lang", "ru").apply();
                break;
            case R.id.english:
                if (lang.equals("en")) flag = false;
                lang = "en";
                sPref.edit().putString("lang", "en").apply();
                break;
        }
        if(flag)
        {
            Lib.setLang(lang, this);
            finish();
        }
    }
}
