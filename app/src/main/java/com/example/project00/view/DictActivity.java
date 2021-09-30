package com.example.project00.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import com.example.project00.LocalDatabase;
import com.example.project00.R;

public class DictActivity extends AppCompatActivity {

    private Cursor words;
    private LocalDatabase db;
    private ListView listViewWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dict);

        listViewWords= findViewById(R.id.words);

        db = new LocalDatabase(this);
        words = db.getAllTranslation();

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.word_item,
                words,
                new String[] {"rus", "kal"},
                new int[] {R.id.rus, R.id.kal});

        listViewWords.setAdapter(adapter);
    }
}