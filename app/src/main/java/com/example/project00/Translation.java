package com.example.project00;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Translation {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "rus")
    public String rus;

    @ColumnInfo(name = "kal")
    public String kal;

    @ColumnInfo(name = "category")
    public String category;
}
