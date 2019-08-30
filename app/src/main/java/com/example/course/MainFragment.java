package com.example.course;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainFragment extends Fragment {

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        for (int course = 0; course < 3; course++) {
            maxRate[course] = 0;
            for (int i = 0; i < Lib.rate[course].length; i++) {
                for (int j = 0; j < Lib.rate[course][i].length; j++) {
                    maxRate[course] += Lib.rate[course][i][j];
                }
            }
        }
        prbBeginning = view.findViewById(R.id.prb_beginning);
        prbAlgorithms = view.findViewById(R.id.prb_algorithms);
        prbAdvanced = view.findViewById(R.id.prb_advanced);
        sPref = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        BegRateText = view.findViewById(R.id.BegRateText);
        AlgoRateText = view.findViewById(R.id.AlgoRateText);
        AdvRateText = view.findViewById(R.id.AdvRateText);
        prbCourses = new ArrayList<>();

        textCourses = new ArrayList<>();
        prbCourses.add(prbBeginning);
        prbCourses.add(prbAlgorithms);
        prbCourses.add(prbAdvanced);
        textCourses.add(BegRateText);
        textCourses.add(AlgoRateText);
        textCourses.add(AdvRateText);
        onResume();

        int[] btns = {R.id.beginning_course_btn, R.id.algo_course_btn, R.id.advanced_course_btn};
        for (int i = 0; i < btns.length; i++) {
            final int course = i;
            view.findViewById(btns[i]).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) getActivity()).showCourse(course);
                }
            });
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateRatings(0);
        updateRatings(1);
        updateRatings(2);
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
}
