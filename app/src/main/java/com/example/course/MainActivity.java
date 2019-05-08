package com.example.course;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView prbBeginning;
    ImageView prbAlgorithms;
    ImageView prbAdvanced;
    TextView BegRateText;
    SharedPreferences sPref;
    int maxBegRate = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        prbBeginning = findViewById(R.id.prb_beginning);
        prbAlgorithms = findViewById(R.id.prb_algorithms);
        prbAdvanced = findViewById(R.id.prb_advanced);
        sPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        BegRateText = findViewById(R.id.BegRateText);
        updateRatings();

        if(sPref.getString("lang", "default").equals("default"))
        {
            Intent intent = new Intent(this, SettingsActivity.class);
            intent.putExtra("first", 1);
            startActivity(intent);
            finish();
        }
        else if (!sPref.getString("lang", "default").equals(getResources().getConfiguration().locale.getLanguage()))
        {
            Lib.setLang(sPref.getString("lang", "default"), this);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateRatings();
    }

    private void updateRatings()
    {
        int rateBeginning = sPref.getInt("Beginning_rating", 0);
        Log.d("AAA", Integer.toString(sPref.getInt("A", 0)));
        int perBegRate = rateBeginning * 100 / maxBegRate;
        perBegRate -= perBegRate % 5;
        switch (perBegRate)
        {
            case 0:
                prbBeginning.setImageResource(R.drawable.prb0);
                break;
            case 5:
                prbBeginning.setImageResource(R.drawable.prb5);
                break;
            case 10:
                prbBeginning.setImageResource(R.drawable.prb10);
                break;
            case 15:
                prbBeginning.setImageResource(R.drawable.prb15);
                break;
            case 20:
                prbBeginning.setImageResource(R.drawable.prb20);
                break;
            case 25:
                prbBeginning.setImageResource(R.drawable.prb25);
                break;
            case 30:
                prbBeginning.setImageResource(R.drawable.prb30);
                break;
            case 35:
                prbBeginning.setImageResource(R.drawable.prb35);
                break;
            case 40:
                prbBeginning.setImageResource(R.drawable.prb40);
                break;
            case 45:
                prbBeginning.setImageResource(R.drawable.prb45);
                break;
            case 50:
                prbBeginning.setImageResource(R.drawable.prb50);
                break;
            case 55:
                prbBeginning.setImageResource(R.drawable.prb55);
                break;
            case 60:
                prbBeginning.setImageResource(R.drawable.prb60);
                break;
            case 65:
                prbBeginning.setImageResource(R.drawable.prb65);
                break;
            case 70:
                prbBeginning.setImageResource(R.drawable.prb70);
                break;
            case 75:
                prbBeginning.setImageResource(R.drawable.prb75);
                break;
            case 80:
                prbBeginning.setImageResource(R.drawable.prb80);
                break;
            case 85:
                prbBeginning.setImageResource(R.drawable.prb85);
                break;
            case 90:
                prbBeginning.setImageResource(R.drawable.prb90);
                break;
            case 95:
                prbBeginning.setImageResource(R.drawable.prb95);
                break;
            case 100:
                prbBeginning.setImageResource(R.drawable.prb100);
                break;
        }
        BegRateText.setText(getResources().getString(R.string.rating) + ": " + rateBeginning + "/" + maxBegRate);
    }

    public void onClick(View view)
    {
        Intent intent;
        switch (view.getId())
        {
            case R.id.beginning_course_btn:
                intent = new Intent(MainActivity.this, beginning_course.class);
                startActivity(intent);
                break;
            case R.id.algo_course_btn:
                intent = new Intent(MainActivity.this, algorithm_course.class);
                startActivity(intent);
                break;
            case R.id.advanced_course_btn:
                intent = new Intent(MainActivity.this, advanced_course.class);
                startActivity(intent);
                break;
            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            default:
                break;
        }
    }
}
