package com.example.project00;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {

    private TranslationDao dao;
    private  AppDatabase db;

    @Before
    public void createDb() {
        db = Room.databaseBuilder(ApplicationProvider.getApplicationContext(), AppDatabase.class, "example")
                .createFromAsset("databases/example.db").build();
        dao = db.translationDao();
    }

    @After
    public void closeDb() throws Exception {
        db.close();
    }

    @Test
    public void readAllWords() {
        List<Translation> list;
        list = dao.getAll();
        Assert.assertEquals(6693, list.size());
    }

}
