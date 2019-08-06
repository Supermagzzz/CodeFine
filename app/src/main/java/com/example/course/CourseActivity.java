package com.example.course;

import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

public class CourseActivity extends AppCompatActivity {

    private RecyclerView menu;
    private MenuAdapter adapter;
    private int courseId;
    private boolean doubleClick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Lib.initActivity(this, R.layout.activity_course);
        courseId = getIntent().getIntExtra("courseId", 0);
        menu = findViewById(R.id.menu);
        menu.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MenuAdapter();
        menu.setAdapter(adapter);
        findViewById(R.id.settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!doubleClick) {
                    doubleClick = true;
                    Intent intent = new Intent(CourseActivity.this, SettingsActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private class MenuHolder extends RecyclerView.ViewHolder
    {
        private TextView name;
        private Button tasks;
        private ImageView back;
        private LinearLayout linearLayout;
        private Space space;
        private FrameLayout frameLayout;

        public MenuHolder(LayoutInflater inflater, ViewGroup parent)
        {
            super(inflater.inflate(R.layout.menu_item, parent, false));
            name = itemView.findViewById(R.id.name);
            tasks = itemView.findViewById(R.id.tasks);
            back = itemView.findViewById(R.id.back);
            linearLayout = itemView.findViewById(R.id.linLayout);
            space = itemView.findViewById(R.id.space);
            frameLayout = itemView.findViewById(R.id.frBack);
        }
    }

    private class MenuAdapter extends RecyclerView.Adapter<MenuHolder>
    {
        @NonNull
        @Override
        public MenuHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(CourseActivity.this);
            return new MenuHolder(layoutInflater, viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull MenuHolder menuHolder, int i) {
            for (int j = 0; j < Lib.headers_pos[courseId].length; j++)
            {
                if(Lib.headers_pos[courseId][j] == i)
                {
                    menuHolder.back.setImageResource(R.drawable.blue_name_glav);
                    menuHolder.back.setPadding(0, 0, 0, 0);
                    menuHolder.name.setText(Lib.headers_name[courseId][j]);
                    menuHolder.name.setTextSize(30);
                    menuHolder.name.setPadding(30, -30, 0, 0);
                    menuHolder.linearLayout.removeView(menuHolder.space);
                    return;
                }
                else if(Lib.headers_pos[courseId][j] < i)
                {
                    i--;
                }
                else
                {
                    break;
                }
            }
            final int pos = i;
            menuHolder.name.setText(Lib.names[courseId][pos]);
            if(Lib.ids[courseId][i+1].length != 0) {
                menuHolder.tasks.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!doubleClick) {
                            doubleClick = true;
                            Intent intent = new Intent(CourseActivity.this, TaskActivity.class);
                            intent.putExtra("courseId", courseId);
                            intent.putExtra("id", pos + 1);
                            startActivity(intent);
                        }
                    }
                });
            }
            else
            {
                menuHolder.tasks.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!doubleClick) {
                            doubleClick = true;
                            startActivity(new Intent(CourseActivity.this, NoTaskActivity.class));
                        }
                    }
                });
            }
            menuHolder.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!doubleClick) {
                        doubleClick = true;
                        Intent intent = new Intent(CourseActivity.this, Article.class);
                        intent.putExtra("id", pos + 1);
                        intent.putExtra("site", Lib.fileNames[courseId][pos]);
                        intent.putExtra("courseId", courseId);
                        startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return Lib.names[courseId].length + Lib.headers_pos[courseId].length;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        doubleClick = false;
    }
}
