package com.example.project00;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Translation.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TranslationDao translationDao();
}
