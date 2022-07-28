package com.example.frutiapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

// para ayuda de como crear un listado https://www.youtube.com/watch?v=4WUwnxrSAuU

public class Scores extends AppCompatActivity {

    private RecyclerView recyclerViewScores;
    private RecyclerViewAdaptador adaptadorScores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        //RELACIONAMOS LOS ELEMENTOS DEL ACTIVITY
        recyclerViewScores= (RecyclerView) findViewById(R.id.recycle_todos_score);
        //LO COLOCAMOS TIPO LISTA
        recyclerViewScores.setLayoutManager(new LinearLayoutManager(this));

        adaptadorScores = new RecyclerViewAdaptador(obtener_scores());
        recyclerViewScores.setAdapter(adaptadorScores);

    }

    public List<ScoreModelo>obtener_scores(){

        String temp_nombre="", temp_score="";
        String temp_imagen;


        List<ScoreModelo> score = new ArrayList<>();

        //CONEXION A LA BASE DE DATOS
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "BD",null,1);
        SQLiteDatabase BD= admin.getWritableDatabase();
        //CONSULTA EL SCORE MAS ALTO
        Cursor consulta=BD.rawQuery(
                "SELECT * FROM puntaje order by score desc",null);


        if (consulta.moveToFirst()){
            do {
                temp_nombre = consulta.getString(0);
                temp_score = consulta.getString(1);
                temp_imagen= consulta.getString(2);
                int ruta=getResources().getIdentifier(temp_imagen, "drawable",getOpPackageName());
                score.add(new ScoreModelo(temp_nombre, temp_score, ruta ));
            }while (consulta.moveToNext());
        }

        BD.close();
        return score;

    }

    public void onBackPressed(){
        // el boton de atraz

        Intent intent =new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

}