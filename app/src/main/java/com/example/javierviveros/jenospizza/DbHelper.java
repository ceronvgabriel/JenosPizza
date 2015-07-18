package com.example.javierviveros.jenospizza;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Javier Viveros on 14/07/2015.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "sedes.sqlite";
    private static final int DB_SCHEME_VERSION = 1;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataBaseManager.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public DbHelper (Context context) {
            super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }


}