package com.example.frutiapp;

public class ScoreModelo {
    private String nombre, score;
    private int foto;

    public ScoreModelo(String nombre, String score, int foto) {
        this.nombre = nombre;
        this.score = score;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
