package com.example.project00;


import androidx.room.Room;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Words {
    private HashMap<String, String> words;

    public Words() {
        init();
    }

    private void init() {
        words = new HashMap<>();
        AppDatabase db = Room.databaseBuilder(MainActivity.getInstance(),
                AppDatabase.class, "stocks").createFromAsset("database/example.db").build();
        TranslationDao translationDao = db.translationDao();
        Random r = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++)
            arr[i] = r.nextInt();
        List<Translation> translations = translationDao.loadAllByIds(arr);
        for (int i = 0; i < 10; i++) {
            words.put(translations.get(i).rus, translations.get(i).kal);
        }

    }

    public HashMap<String, String> getWords() {
        return words;
    }
}
