package com.example.frutiapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// clase para llenar datos de lista de score
public class RecyclerViewAdaptador extends RecyclerView.Adapter<RecyclerViewAdaptador.ViewHolder>{

public static class ViewHolder extends RecyclerView.ViewHolder{
    //DECLARACION DE VARIABLES NECESARIAS
    private TextView score_nombre, score_score;
    private ImageView score_avatar;

    public ViewHolder(View itemView) {
        super(itemView);

        score_nombre=(TextView)itemView.findViewById(R.id.textView_score_nombre);
        score_score= (TextView)itemView.findViewById(R.id.textView_score_score);
        score_avatar=(ImageView) itemView.findViewById((R.id.imageView_imagen_score));

    }
    }
    public List<ScoreModelo> modeloLista;

    public RecyclerViewAdaptador(List <ScoreModelo>modeloLista){
        this.modeloLista=modeloLista;
    }

    //INFLA EL CONTENIDO
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.score_score.setText(modeloLista.get(position).getScore());
        holder.score_nombre.setText(modeloLista.get(position).getNombre());
        holder.score_avatar.setImageResource(modeloLista.get(position).getFoto());
    }

    //METODO QUE PERMITE INDICAR AL ADAPTADOR LA CANTIDAD DE ELEMENTOS QUE SE PROCESARAN
    @Override
    public int getItemCount() {
        return modeloLista.size();
    }
}
