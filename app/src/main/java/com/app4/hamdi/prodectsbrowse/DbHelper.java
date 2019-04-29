package com.app4.hamdi.prodectsbrowse;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users (" +
                "_id integer primary key autoincrement not null, " +
                "name text unique not null, " +
                "password text not null , " +
                "isAdmin boolean default 0)");

        db.execSQL("create table prodects (" +
                "_id integer primary key autoincrement not null, " +
                "name text, " +
                "type text default 'empty', " +
                "price real default 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
        db.execSQL("drop table if exists prodects");
        onCreate(db);
    }
}
