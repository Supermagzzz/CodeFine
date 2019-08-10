package com.example.course;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView prbBeginning;
    ImageView prbAlgorithms;
    ImageView prbAdvanced;
    TextView BegRateText;
    TextView AlgoRateText;
    TextView AdvRateText;
    SharedPreferences sPref;
    int[] maxRate = {0, 0, 0};
    String[] courseName = {"Beginning_rating", "Algorithm_rating", "Advanced_rating"};
    ArrayList<ImageView> prbCourses;
    ArrayList<TextView> textCourses;
    private boolean doubleClick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        for (int course = 0; course < 3; course++) {
            for (int i = 0; i < Lib.rate[course].length; i++) {
                for (int j = 0; j < Lib.rate[course][i].length; j++) {
                    maxRate[course] += Lib.rate[course][i][j];
                }
            }
        }
        super.onCreate(savedInstanceState);
        Lib.initActivity(this, R.layout.activity_main);
        prbBeginning = findViewById(R.id.prb_beginning);
        prbAlgorithms = findViewById(R.id.prb_algorithms);
        prbAdvanced = findViewById(R.id.prb_advanced);
        sPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        BegRateText = findViewById(R.id.BegRateText);
        AlgoRateText = findViewById(R.id.AlgoRateText);
        AdvRateText = findViewById(R.id.AdvRateText);
        prbCourses = new ArrayList<>();
        textCourses = new ArrayList<>();
        prbCourses.add(prbBeginning);
        prbCourses.add(prbAlgorithms);
        prbCourses.add(prbAdvanced);
        textCourses.add(BegRateText);
        textCourses.add(AlgoRateText);
        textCourses.add(AdvRateText);
        onResume();

        if (sPref.getString("lang", "default").equals("default")) {
            Intent intent = new Intent(this, SettingsActivity.class);
            intent.putExtra("first", 1);
            startActivity(intent);
            finish();
        } else if (!sPref.getString("lang", "default").equals(getResources().getConfiguration().locale.getLanguage())) {
            Lib.setLang(sPref.getString("lang", "default"), this);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateRatings(0);
        updateRatings(1);
        updateRatings(2);
        doubleClick = false;
    }

    private void updateRatings(int course)
    {
        int rate = sPref.getInt(Lib.courseName[course] + "_rating", 0);
        if(maxRate[course] == 0) maxRate[course] = 1;
        int perBegRate = rate * 100 / maxRate[course];
        perBegRate -= perBegRate % 5;
        switch (perBegRate)
        {
            case 0:
                prbCourses.get(course).setImageResource(R.drawable.prb0);
                break;
            case 5:
                prbCourses.get(course).setImageResource(R.drawable.prb5);
                break;
            case 10:
                prbCourses.get(course).setImageResource(R.drawable.prb10);
                break;
            case 15:
                prbCourses.get(course).setImageResource(R.drawable.prb15);
                break;
            case 20:
                prbCourses.get(course).setImageResource(R.drawable.prb20);
                break;
            case 25:
                prbCourses.get(course).setImageResource(R.drawable.prb25);
                break;
            case 30:
                prbCourses.get(course).setImageResource(R.drawable.prb30);
                break;
            case 35:
                prbCourses.get(course).setImageResource(R.drawable.prb35);
                break;
            case 40:
                prbCourses.get(course).setImageResource(R.drawable.prb40);
                break;
            case 45:
                prbCourses.get(course).setImageResource(R.drawable.prb45);
                break;
            case 50:
                prbCourses.get(course).setImageResource(R.drawable.prb50);
                break;
            case 55:
                prbCourses.get(course).setImageResource(R.drawable.prb55);
                break;
            case 60:
                prbCourses.get(course).setImageResource(R.drawable.prb60);
                break;
            case 65:
                prbCourses.get(course).setImageResource(R.drawable.prb65);
                break;
            case 70:
                prbCourses.get(course).setImageResource(R.drawable.prb70);
                break;
            case 75:
                prbCourses.get(course).setImageResource(R.drawable.prb75);
                break;
            case 80:
                prbCourses.get(course).setImageResource(R.drawable.prb80);
                break;
            case 85:
                prbCourses.get(course).setImageResource(R.drawable.prb85);
                break;
            case 90:
                prbCourses.get(course).setImageResource(R.drawable.prb90);
                break;
            case 95:
                prbCourses.get(course).setImageResource(R.drawable.prb95);
                break;
            case 100:
                prbCourses.get(course).setImageResource(R.drawable.prb100);
                break;
        }
        textCourses.get(course).setText(getResources().getString(R.string.rating) + ": " + rate + "/" + maxRate[course]);
    }

    public void onClick(View view)
    {
        Intent intent;
        switch (view.getId())
        {
            case R.id.beginning_course_btn:
                if(!doubleClick) {
                    doubleClick = true;
                    intent = new Intent(MainActivity.this, CourseActivity.class);
                    intent.putExtra("courseId", 0);
                    startActivity(intent);
                    break;
                }
            case R.id.algo_course_btn:
                if(!doubleClick) {
                    doubleClick = true;
                    intent = new Intent(MainActivity.this, CourseActivity.class);
                    intent.putExtra("courseId", 1);
                    startActivity(intent);
                    break;
                }
            case R.id.advanced_course_btn:
                if(!doubleClick) {
                    doubleClick = true;
                    intent = new Intent(MainActivity.this, CourseActivity.class);
                    intent.putExtra("courseId", 2);
                    startActivity(intent);
                    break;
                }
            case R.id.settings:
                if(!doubleClick) {
                    doubleClick = true;
                    startActivity(new Intent(this, SettingsActivity.class));
                    break;
                }
            default:
                break;
        }
    }
}
