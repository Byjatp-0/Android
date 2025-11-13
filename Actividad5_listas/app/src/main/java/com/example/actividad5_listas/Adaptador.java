package com.example.actividad5_listas;

import androidx.recyclerview.widget.RecyclerView;

public class Adaptador extends RecyclerView.Adapter<Adaptador.VideojuegosViewHolder> {

    public Videojuego[] listaVideojuegos;

    public Adaptador(Videojuego[] listaVideojuegos){
        this.listaVideojuegos = listaVideojuegos;
    }
}
