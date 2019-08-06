package com.example.course;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NoTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Lib.initActivity(this, R.layout.activity_no_task);
    }
}
