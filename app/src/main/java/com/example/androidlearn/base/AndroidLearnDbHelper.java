package com.example.androidlearn.base;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class AndroidLearnDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "AndroidLearn.db";
    public static final String TABLE_USUARIOS = "t_usuarios";

    public AndroidLearnDbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_USUARIOS + "("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nombre TEXT NOT NULL,"
                    + "apellido_paterno TEXT NOT NULL,"
                    + "apellido_materno TEXT,"
                    + "usuario TEXT NOT NULL,"
                    + "correo_electronico TEXT NOT NULL,"
                    + "contrasena TEXT NOT NULL");
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("Database Error","Error creating tables:" + e.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
        onCreate(db);
    }
}
