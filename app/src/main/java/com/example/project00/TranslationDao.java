package com.example.project00;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TranslationDao {
    @Query("SELECT * FROM translation")
    List<Translation> getAll();

    @Query("SELECT * FROM translation WHERE id IN (:translationIds)")
    List<Translation> loadAllByIds(int[] translationIds);

    @Query("SELECT * FROM translation WHERE rus LIKE :word LIMIT 1")
    Translation findByWord(String word);

    @Query("SELECT * FROM translation WHERE kal LIKE :translation LIMIT 1")
    Translation findByTranslation(String translation);

    @Insert
    void insertAll(Translation... translations);

    @Delete
    void delete(Translation translation);

}
