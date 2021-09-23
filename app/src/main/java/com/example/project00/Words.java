package com.example.project00;

import java.util.HashMap;

public class Words {
    private HashMap<String,String> words;

    public Words() {
        init();
    }

    private void init() {
        words = new HashMap<>();
        words.put("Яблоко","альмн");
        words.put("Груша","кедмн");
        words.put("Суп","шөлн");
        words.put("Сахар","шикр");
        words.put("Помидор","адамч");
        words.put("Соль","давсн");
        words.put("Рыба","бершг");
        words.put("Молоко","үсн");
        words.put("Баранка","тоhш");
        words.put("Банан","hадль");
    }

    public HashMap<String, String> getWords() {
        return words;
    }
}
