package com.example.frutiapp;

//COLOCAR IMAGENES, NUMEROS ALEATORIOS, SONIDOS, VARIABLES PUBLICAS

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Nivel1 extends AppCompatActivity {

    public static int score;
    private int aleatorio1, aleatorio2, resultado, resultado2;
    private int manzanas=3;
    int ruta;

    private TextView view_jugador, view_score;
    private EditText editText_resultado;
    private ImageView imagen_valor1, imagen_valor2, imagen_manzana;
    private MediaPlayer musica, sonido_bien, sonido_mal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel1);

        view_jugador=findViewById(R.id.textView_jugador);
        view_score=findViewById(R.id.textView_score_nivel);
        editText_resultado=findViewById(R.id.editText_resultado);
        imagen_valor1=findViewById(R.id.imageView_valor1);
        imagen_valor2=findViewById(R.id.imageView_valor2);
        imagen_manzana=findViewById(R.id.imageView_manzanas);

        view_jugador.setText("Jugador: "+ MainActivity.jugador);

        //COLOCAR PISTA MUSICAL
        musica= MediaPlayer.create(this,R.raw.goats);
        musica.start();
        //REPETIR AUDIO CICLADO
        musica.setLooping(true);

     Imagenes();


    }

    public void Imagenes(){
        aleatorio1= (int) (Math.random()*9);
        aleatorio2= (int) (Math.random()*9);

        //COLOCAR IMAGENES

        if (aleatorio1==1){
            ruta=getResources().getIdentifier("uno", "drawable",getOpPackageName());
            imagen_valor1.setImageResource(ruta);
        }else if(aleatorio1==2){
            ruta=getResources().getIdentifier("dos", "drawable",getOpPackageName());
            imagen_valor1.setImageResource(ruta);
        }else if(aleatorio1==3){
            ruta=getResources().getIdentifier("tres", "drawable",getOpPackageName());
            imagen_valor1.setImageResource(ruta);
        }else if(aleatorio1==4){
            ruta=getResources().getIdentifier("cuatro", "drawable",getOpPackageName());
            imagen_valor1.setImageResource(ruta);
        }else if(aleatorio1==5){
            ruta=getResources().getIdentifier("cinco", "drawable",getOpPackageName());
            imagen_valor1.setImageResource(ruta);
        }else if(aleatorio1==6){
            ruta=getResources().getIdentifier("seis", "drawable",getOpPackageName());
            imagen_valor1.setImageResource(ruta);
        }else if(aleatorio1==7){
            ruta=getResources().getIdentifier("siete", "drawable",getOpPackageName());
            imagen_valor1.setImageResource(ruta);
        }else if(aleatorio1==8){
            ruta=getResources().getIdentifier("ocho", "drawable",getOpPackageName());
            imagen_valor1.setImageResource(ruta);
        }else if(aleatorio1==9){
            ruta=getResources().getIdentifier("nueve", "drawable",getOpPackageName());
            imagen_valor1.setImageResource(ruta);
        }

        if (aleatorio2==1){
            ruta=getResources().getIdentifier("uno", "drawable",getOpPackageName());
            imagen_valor2.setImageResource(ruta);
        }else if(aleatorio2==2){
            ruta=getResources().getIdentifier("dos", "drawable",getOpPackageName());
            imagen_valor2.setImageResource(ruta);
        }else if(aleatorio2==3){
            ruta=getResources().getIdentifier("tres", "drawable",getOpPackageName());
            imagen_valor2.setImageResource(ruta);
        }else if(aleatorio2==4){
            ruta=getResources().getIdentifier("cuatro", "drawable",getOpPackageName());
            imagen_valor2.setImageResource(ruta);
        }else if(aleatorio2==5){
            ruta=getResources().getIdentifier("cinco", "drawable",getOpPackageName());
            imagen_valor2.setImageResource(ruta);
        }else if(aleatorio2==6){
            ruta=getResources().getIdentifier("seis", "drawable",getOpPackageName());
            imagen_valor2.setImageResource(ruta);
        }else if(aleatorio2==7){
            ruta=getResources().getIdentifier("siete", "drawable",getOpPackageName());
            imagen_valor2.setImageResource(ruta);
        }else if(aleatorio2==8){
            ruta=getResources().getIdentifier("ocho", "drawable",getOpPackageName());
            imagen_valor2.setImageResource(ruta);
        }else if(aleatorio2==9){
            ruta=getResources().getIdentifier("nueve", "drawable",getOpPackageName());
            imagen_valor2.setImageResource(ruta);
        }

    }

    public void Comprobar(View view){


        if (editText_resultado.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Ingresa Valor", Toast.LENGTH_LONG).show();
            //FOCO A NOMBRE Y ABRIR TECLADO
            editText_resultado.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText_resultado, InputMethodManager.SHOW_IMPLICIT);
            return;
        }
        resultado=aleatorio1+aleatorio2;
        resultado2= Integer.parseInt(editText_resultado.getText().toString().trim());


        if (resultado==resultado2){
            //ASIERTO
            sonido_bien= MediaPlayer.create(this,R.raw.wonderful);
            sonido_bien.start();

            Toast.makeText(this, "Exelente", Toast.LENGTH_LONG).show();
            editText_resultado.setText("");
            score=score+1;
            view_score.setText("Score: "+String.valueOf(score));

        }else{
            //DESASIERTO
            sonido_mal= MediaPlayer.create(this,R.raw.bad);
            sonido_mal.start();



            Toast.makeText(this, "Prueba otra vez", Toast.LENGTH_LONG).show();
            editText_resultado.setText("");
            view_score.setText("Score: "+String.valueOf(score));
            manzanas=manzanas-1;
         }

        Imagenes();

        if(manzanas==2){
            ruta=getResources().getIdentifier("dosvidas", "drawable",getOpPackageName());
            imagen_manzana.setImageResource(ruta);
        }else if (manzanas==1){
            ruta=getResources().getIdentifier("unavida", "drawable",getOpPackageName());
            imagen_manzana.setImageResource(ruta);
        }else if(manzanas==0){
            //CIERRA ACTIVITY Y VUELVE AL ANTERIOR Y GUARDA EL SCORE


            // CREAR OBJETO DE LA CLASE QUE CONECTA BD
            AdminSQLiteOpenHelper BD=new AdminSQLiteOpenHelper(this, "BD",null,1);
            //ABRIR LA BD EN MODO ESCRITURA y lectura
            SQLiteDatabase BaseDeDatos =BD.getWritableDatabase();

            //GUARMOS LOS VALORES ENN CONTENTVALUES
            ContentValues registro = new ContentValues();
            registro.put("nombre",MainActivity.jugador);
            registro.put("score", score);
            registro.put("avatar", MainActivity.avatar);



            // INSERTAMOS EN LA BASE DE DATOS UN CONTENTVALUES CON LA INFORMACION
            BaseDeDatos.insert("puntaje",null, registro);
            BaseDeDatos.close();

            sonido_bien.stop();
            sonido_bien.release();
            sonido_mal.stop();
            sonido_mal.release();
            musica.stop();
            musica.release();

            Toast.makeText(this, "Fin del juego", Toast.LENGTH_SHORT).show();

            Intent intent =new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onBackPressed(){
        //este metodo sobre escrito en vacio es para la funcion que debe hacer al precionar la tecla back
        //se deja vacio para que no haga nada el boton de atraz
    }
}


