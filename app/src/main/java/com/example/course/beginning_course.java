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

    int[][] ids = {
            {},
            {},
            {1},
            {2, 3, 4}
    };

    int[][] rate = {
            {},
            {},
            {1},
            {1, 1, 2}
    };

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
                startActivity(intent);
                break;
            case R.id.article2:
                intent = new Intent(beginning_course.this, Article.class);
                intent.putExtra("site", "2-Installing_software.html");
                startActivity(intent);
                break;
            case R.id.article3:
                intent = new Intent(beginning_course.this, Article.class);
                intent.putExtra("site", "3-Simple_programm.html");
                startActivity(intent);
                break;
            case R.id.tasks2:
                intent = new Intent(beginning_course.this, TaskActivity.class);
                intent.putExtra("path", "Tasks/2/");
                intent.putExtra("ids", ids[2]);
                intent.putExtra("rate", rate[2]);
                startActivity(intent);
                break;
            case R.id.tasks3:
                intent = new Intent(beginning_course.this, TaskActivity.class);
                intent.putExtra("site", "Tasks/3/");
                intent.putExtra("ids", ids[3]);
                intent.putExtra("rate", rate[3]);
                startActivity(intent);
                break;
        }
    }
}
