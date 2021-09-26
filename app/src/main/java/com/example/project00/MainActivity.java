package com.example.project00;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Random rand;

    private HashMap<String, String> allPairWords;
    private String answer, kalm;
    private List<String> rusWords;

    private RadioGroup radioGroup;
    private Button answerBtn;
    private RadioButton var1, var2, var3;
    private TextView kalmTxt;
    private TextView counterTxt;
    public static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_main);
        init();
        loadWords();
        createQuest();
    }

    public static MainActivity getInstance(){
        return instance;
    }

    private void showMsg(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    private void updateView() {
        RadioButton varAnswer,var;
        counterTxt.setText(String.format("%d/%d",rusWords.size()-allPairWords.size(),rusWords.size()));
        kalmTxt.setText(kalm);
        kalmTxt.setAlpha(0.0f);
        kalmTxt.animate().alpha(1.0f).setDuration(300);
        int numVar = rand.nextInt(3);
        varAnswer = (RadioButton) radioGroup.getChildAt(numVar);
        varAnswer.setText(answer);
        for(int i = 0; i < radioGroup.getChildCount(); i++){
            var = (RadioButton) radioGroup.getChildAt(i);
            if(var.getId() != varAnswer.getId()){
                var.setText(getRandStr());
            }
        }
    }

    private String getRandStr(){
        return rusWords.get(rand.nextInt(rusWords.size()));
    }

    private void checkWords() {
        RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId()) ;
        if(radioButton.getText() == answer){
            showMsg("Правильно!");
            radioButton.setChecked(false);
            createQuest();
        }
        else {
            showMsg("Не правильно");
            radioButton.setChecked(false);
        }
    }

    private void createQuest() {
        if(allPairWords.size() == 0){
            finishTest();
        }
        else {
            int numRandPair = rand.nextInt(allPairWords.size());
            answer = (String) allPairWords.keySet().toArray()[numRandPair];
            kalm = allPairWords.get(answer);
            allPairWords.remove(answer);
            updateView();
        }
    }

    private void finishTest() {
        Intent intent = new Intent(this,ResultActivity.class);
        startActivity(intent);
        finish();
    }

    private void loadWords() {
        Words words = new Words();
        allPairWords = words.getWords();
        rusWords = new ArrayList<>(allPairWords.keySet());
    }

    private void init(){
        rand = new Random();
        answerBtn = findViewById(R.id.answer);
        answerBtn.setOnClickListener(view -> checkWords());
        radioGroup = findViewById(R.id.radioGroup);
        var1 = findViewById(R.id.var1);
        var2 = findViewById(R.id.var2);
        var3 = findViewById(R.id.var3);
        kalmTxt = findViewById(R.id.kalm);
        counterTxt = findViewById(R.id.counter);
    }


}