package com.example.course;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class beginning_course extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_beginning_course);
    }

    public void onClick(View view)
    {
        Intent intent;
        switch (view.getId())
        {
            case R.id.article1:
                intent = new Intent(beginning_course.this, Article.class);
                intent.putExtra("site", "1-Introduction.html");
                intent.putExtra("id", 1);
                startActivity(intent);
                break;
            case R.id.article2:
                intent = new Intent(beginning_course.this, Article.class);
                intent.putExtra("site", "2-Installing_software.html");
                intent.putExtra("id", 2);
                startActivity(intent);
                break;
            case R.id.article3:
                intent = new Intent(beginning_course.this, Article.class);
                intent.putExtra("site", "3-Simple_programm.html");
                intent.putExtra("id", 3);
                startActivity(intent);
                break;
            case R.id.article4:
                intent = new Intent(beginning_course.this, Article.class);
                intent.putExtra("site", "4-Variables.html");
                intent.putExtra("id", 4);
                startActivity(intent);
                break;
            case R.id.article5:
                intent = new Intent(beginning_course.this, Article.class);
                intent.putExtra("site", "5-Data_types.html");
                intent.putExtra("id", 5);
                startActivity(intent);
                break;
            case R.id.article6:
                intent = new Intent(beginning_course.this, Article.class);
                intent.putExtra("site", "6-Conditionals.html");
                intent.putExtra("id", 6);
                startActivity(intent);
                break;
            case R.id.tasks2:
                intent = new Intent(beginning_course.this, TaskActivity.class);
                intent.putExtra("id", 2);
                startActivity(intent);
                break;
            case R.id.tasks3:
                intent = new Intent(beginning_course.this, TaskActivity.class);
                intent.putExtra("id", 3);
                startActivity(intent);
                break;
            case R.id.tasks4:
                intent = new Intent(beginning_course.this, TaskActivity.class);
                intent.putExtra("id", 4);
                startActivity(intent);
                break;
            case R.id.tasks5:
                intent = new Intent(beginning_course.this, TaskActivity.class);
                intent.putExtra("id", 5);
                startActivity(intent);
                break;
            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
        }
    }
}
