package com.example.project00;


import androidx.room.Room;

import java.util.HashMap;
import java.util.List;

public class Words {
    private HashMap<String, String> words;

    public Words() {
        init();
    }

    private void init() {
        words = new HashMap<>();
        AppDatabase db = Room.databaseBuilder(MainActivity.getInstance(),
                AppDatabase.class, "example").createFromAsset("database/example.db").build();
        TranslationDao translationDao = db.translationDao();
        List<Translation> translations = translationDao.getAll();


    }

    public HashMap<String, String> getWords() {
        return words;
    }
}
