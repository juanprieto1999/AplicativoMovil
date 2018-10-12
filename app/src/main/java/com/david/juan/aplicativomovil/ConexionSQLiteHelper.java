package com.david.juan.aplicativomovil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Juan on 11/10/2018.
 */

public class ConexionSQLiteHelper extends SQLiteOpenHelper {
    final String CreateTable="CREATE TABLE usuario (id INTEGER,nombre TEXT, telefono TEXT)";

    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL(CreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("DROP TABLE IF EXISTS usuarios ");
onCreate(db);
    }
}
