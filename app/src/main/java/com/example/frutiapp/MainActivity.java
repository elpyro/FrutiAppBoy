package com.example.frutiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
//COLOCAR MUSICA, CAMBIIO DE IMAGEN, ABRIR ACTIVITY, COLOCAR ICONO,
public class MainActivity extends AppCompatActivity {

    public static String jugador;
    public static String avatar;
    private EditText nombre;
        private TextView score;
    private ImageView imagen;
    private MediaPlayer musica;
    //COLOCAR NUMER ALEATORIO
    int aleatorio= (int) (Math.random()*5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // COLOCAR EL ICONO EN EL MAINACTIVITY
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        //DECLARANDO LOS ELEMENTOS
        nombre=findViewById(R.id.editText_nombre);
        score=findViewById(R.id.textView_score);
        imagen=findViewById(R.id.imageView);

        //IMAGEN INICIAL ALEATORIA
        // ESTA RUTA DEBE GUARDARSE EN UNA VARIABLE ENTERA
        int ruta;
        if (aleatorio==0){
            ruta=getResources().getIdentifier("mango", "drawable",getOpPackageName());
            imagen.setImageResource(ruta);
            avatar="mango";
        }else if (aleatorio==1){
            ruta=getResources().getIdentifier("fresa", "drawable",getOpPackageName());
            imagen.setImageResource(ruta);
            avatar="fresa";
        }else if (aleatorio==2){
            ruta=getResources().getIdentifier("manzana", "drawable",getOpPackageName());
            imagen.setImageResource(ruta);
            avatar="manzana";
        }else if (aleatorio==3){
            ruta=getResources().getIdentifier("sandia", "drawable",getOpPackageName());
            imagen.setImageResource(ruta);
            avatar="sandia";
        } else if (aleatorio==4){
        ruta=getResources().getIdentifier("uva", "drawable",getOpPackageName());
        imagen.setImageResource(ruta);
        avatar="uva";
    }else if (aleatorio==5){
        ruta=getResources().getIdentifier("naranja", "drawable",getOpPackageName());
        imagen.setImageResource(ruta);
        avatar="naranja";
    }
        //CONEXION A LA BASE DE DATOS
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "BD",null,1);
        SQLiteDatabase BD= admin.getWritableDatabase();
        //CONSULTA EL SCORE MAS ALTO
        Cursor consulta=BD.rawQuery(
                "SELECT * FROM puntaje where score = (select max(score) from puntaje)",null);


        if (consulta.moveToFirst()){
            String temp_nombre=consulta.getString(0);
            String temp_score=consulta.getString(1);
            score.setText("Record: "+temp_nombre+" de "+temp_score );
        }

        BD.close();

        //COLOCAR PISTA MUSICAL
        musica= MediaPlayer.create(this,R.raw.alphabet_song);
        musica.start();
        //REPETIR AUDIO CICLADO
        musica.setLooping(true);

    }
    public void Jugar(View view){

        jugador=nombre.getText().toString().trim();
        if (jugador.equals("")){
            Toast.makeText(this, "Ingresa tu nombre", Toast.LENGTH_LONG).show();
            //FOCO A NOMBRE Y ABRIR TECLADO
            nombre.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            imm.showSoftInput(nombre, InputMethodManager.SHOW_IMPLICIT);
        }else{
            //DETENER LA MUSICA
            musica.stop();
            //DESTRUIR LA MUSICA PARA  LIBERAR RECURSOS
            musica.release();
            //ABRIR OTRO ACTIVITY
            Intent intent =new Intent(this, Nivel1.class);
            startActivity(intent);
            finish();
        }
    }
    
    @Override
    public void onBackPressed(){
    //este metodo sobre escrito en vacio es para la funcion que debe hacer al precionar la tecla back
    //se deja vacio para que no haga nada el boton de atraz
    }

    public void Salir(View view){
        //DETENER LA MUSICA
        musica.stop();
        //DESTRUIR LA MUSICA PARA  LIBERAR RECURSOS
        musica.release();
        finish();
    }
    public void Ver_score(View view){
        //DETENER LA MUSICA
        musica.stop();
        //DESTRUIR LA MUSICA PARA  LIBERAR RECURSOS
        musica.release();
        //ABRIR OTRO ACTIVITY
        Intent intent =new Intent(this, Scores.class);
        startActivity(intent);
        finish();
       }

    
    
}