package com.example.project00;


import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.HashMap;

public class Words {
    private HashMap<String, String> words;
    private LocalDatabase db;
    private Context ctx;

    public Words(Context context) {
        this.ctx = context;
        init();
    }

    private void init() {
        words = new HashMap<>();
        db = LocalDatabase.getInstance(ctx);
    }

    public HashMap<String, String> getWords() {
        return words;
    }

    @SuppressLint("Range")
    public HashMap<String, String> getNRandomWordsByCategory(int n, String category) {
        HashMap<String, String> selectWords = new HashMap<>();
        Cursor data = db.getNRandomTranslationByCategory(n, category);
        String rus, kal;
        while (!data.isAfterLast()){
            rus = data.getString(data.getColumnIndex(LocalDatabase.COL_RUS_WORDS));
            kal = data.getString(data.getColumnIndex(LocalDatabase.COL_KAL_WORDS));
            selectWords.put(rus, kal);
            data.moveToNext();
        };
        data.close();
        return selectWords;
    }
}
