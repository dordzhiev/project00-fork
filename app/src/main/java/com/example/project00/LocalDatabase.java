package com.example.project00;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class LocalDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "example.db";
    private static final int DATABASE_VERSION = 1;

    public static final String COL_RUS_WORDS = "rus";
    public static final String COL_KAL_WORDS = "kal";

    private static LocalDatabase instance = null;
    private Context ctx;

    public LocalDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.ctx = context;
    }

    public static LocalDatabase getInstance(Context ctx) {
        if (instance == null) {
            instance = new LocalDatabase(ctx);
        }
        return instance;
    }

    public Cursor getNRandomTranslationByCategory(int n, String category) {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();

        String [] sqlSelect = {"0 _id", "rus", "kal", "category"};
        String sqlTables = "stocks";
        String where = "category = '" + category + "'";
        String orderByRandom = "Random()";
        sqLiteQueryBuilder.setTables(sqlTables);

        Cursor c = sqLiteQueryBuilder.query(db, sqlSelect, where, null,
                null, null, orderByRandom, String.valueOf(n));

        c.moveToFirst();
        return c;
    }

    public Cursor getAllTranslation() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();

        String [] sqlSelect = {"0 _id", "rus", "kal", "category"};
        String sqlTables = "stocks";

        sqLiteQueryBuilder.setTables(sqlTables);
        Cursor c = sqLiteQueryBuilder.query(db, sqlSelect, null, null,
                null, null, null);

        c.moveToFirst();
        return c;
    }

}
