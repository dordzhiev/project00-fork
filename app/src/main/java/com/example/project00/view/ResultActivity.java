package com.example.project00.view;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.project00.R;
import com.example.project00.view.MainActivity;

public class ResultActivity extends AppCompatActivity {
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textView = findViewById(R.id.textView);
        test(textView);
    }

    public void startTest(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void test(TextView view){
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(Color.WHITE,getResources().getColor(R.color.priatniy));
        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.addUpdateListener(valueAnimator1 -> {
            view.setTextColor(((Integer) valueAnimator.getAnimatedValue()));
        });
        valueAnimator.setDuration(500);
        valueAnimator.start();
    }
}