package com.example.frutiapp;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


// SI ESCRIBE extends SQLiteOpenHelper puede importar la labreria y los dos metodos con ayuda de android stuido. entoces solo crear la tabla
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {


    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BD) {
        //CREA LA TABLA Y LOS ELEMENTOS, SI YA ESTA CREADA NO HACE NADA.
        BD.execSQL("CREATE TABLE puntaje(nombre text, score int, avatar text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase BD, int oldVersion, int newVersion) {

    }
}
