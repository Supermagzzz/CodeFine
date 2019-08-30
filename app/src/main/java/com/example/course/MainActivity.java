package com.example.course;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FragmentManager manager;
    MainFragment mainFragment;
    ArrayList<Fragment> stackFragment = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Lib.initActivity(this, R.layout.activity_main);
        manager = getSupportFragmentManager();
        mainFragment = new MainFragment();
        manager.beginTransaction().add(R.id.fragmentContainer, mainFragment).commit();
        stackFragment.add(mainFragment);
        SharedPreferences sPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (sPref.getString("lang", "default").equals("default")) {
            Intent intent = new Intent(this, SettingsActivity.class);
            intent.putExtra("first", 1);
            startActivity(intent);
            finish();
        } else if (!sPref.getString("lang", "default").equals(getResources().getConfiguration().locale.getLanguage())) {
            Lib.setLang(sPref.getString("lang", "default"), this);
            finish();
        }
        findViewById(R.id.settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    public void showCourse(int course)
    {
        CourseFragment courseFragment = CourseFragment.newInstance(course);
        try {
            manager.beginTransaction()
                    .setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right)
                    .replace(R.id.fragmentContainer, courseFragment)
                    .commit();
            stackFragment.add(courseFragment);
        }
        catch (Exception e){}
    }

    @Override
    public void onBackPressed() {
        if(stackFragment.size() == 1)
        {
            super.onBackPressed();
        }
        else
        {
            stackFragment.remove(stackFragment.size() - 1);
            manager.beginTransaction()
                    .setCustomAnimations(R.animator.e_slide_in_left, R.animator.e_slide_in_right)
                    .replace(R.id.fragmentContainer, stackFragment.get(stackFragment.size() - 1))
                    .commit();
        }
    }
}
