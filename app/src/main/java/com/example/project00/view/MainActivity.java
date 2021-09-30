package com.example.project00.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project00.R;
import com.example.project00.Words;

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
    private TextView kalmTxt;
    private TextView counterTxt;

    private final int DIALOG_EXIT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        createDialog();
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

    private void createDialog() {
        String[] alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя".split("");
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder
                .setTitle(R.string.title_dialog)
                .setItems(alphabet, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        loadWords(alphabet[i]);
                        createQuest();
                    }
                });

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

    private String getRandStr(){
        return rusWords.get(rand.nextInt(rusWords.size()));
    }

    private void checkAnswer() {
        RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId()) ;
        if(radioButton.getText() == answer){
            animAnswer(radioButton, true);
            radioButton.setChecked(false);
            createQuest();
        }
        else {
            animAnswer(radioButton, false);
            radioButton.setChecked(false);
        }
    }

    public void animAnswer(View view, boolean isAnswerCorrect) {
        ValueAnimator valueAnimator = new ValueAnimator();
        if (isAnswerCorrect) {
            valueAnimator.setIntValues(
                    getResources().getColor(R.color.green100),
                    Color.WHITE
            );
        }
        else {
            valueAnimator.setIntValues(
                    getResources().getColor(R.color.red100),
                    Color.WHITE
            );
        }
        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.addUpdateListener(valueAnimator1 -> {
            view.setBackgroundColor(((Integer) valueAnimator.getAnimatedValue()));
        });
        valueAnimator.setDuration(700);
        valueAnimator.start();

    }

    private void createQuest() {
        if(allPairWords.size() == 0){
            startFinishActivity();
        }
        else {
            int numRandPair = rand.nextInt(allPairWords.size());
            answer = (String) allPairWords.keySet().toArray()[numRandPair];
            kalm = allPairWords.get(answer);
            allPairWords.remove(answer);
            updateView();
        }
    }

    private void startFinishActivity() {
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
        finish();
    }

    private void loadWords(String category) {
        Words words = new Words(this);
        allPairWords = words.getNRandomWordsByCategory(10, category);
        rusWords = new ArrayList<>(allPairWords.keySet());
    }

    private void init(){
        rand = new Random();

        answerBtn = findViewById(R.id.answer);
        answerBtn.setOnClickListener(view -> checkAnswer());

        radioGroup = findViewById(R.id.radioGroup);
        kalmTxt = findViewById(R.id.kalm);
        counterTxt = findViewById(R.id.counter);
    }


}