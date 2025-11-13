package com.example.actividad5_listas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adaptador extends RecyclerView.Adapter<Adaptador.VideojuegosViewHolder> {

    public Videojuego[] listaVideojuegos;

    public Adaptador(Videojuego[] listaVideojuegos){
        this.listaVideojuegos = listaVideojuegos;
    }

    @NonNull
    @Override
    public VideojuegosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.juegos_item, parent, false);

        return new VideojuegosViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull VideojuegosViewHolder holder, int position) {
        holder.bindVideojuegos(listaVideojuegos);
    }

    @Override
    public int getItemCount(){
        return this.listaVideojuegos.length;
    }

    public static class VideojuegosViewHolder extends RecyclerView.ViewHolder {
        public TextView tituloView1, tituloView2, tituloView3, tituloView4;
        public ImageView portadaView1, portadaView2, portadaView3, portadaView4;

        public VideojuegosViewHolder(View view, Context context){
            super(view);
            tituloView1 = view.findViewById(R.id.lolTxt);
            tituloView2 = view.findViewById(R.id.zzzTxt);
            tituloView3 = view.findViewById(R.id.arTxt);
            tituloView4 = view.findViewById(R.id.dispatchTxt);
            portadaView1 = view.findViewById(R.id.lol);
            portadaView2 = view.findViewById(R.id.zzz);
            portadaView3 = view.findViewById(R.id.arc);
            portadaView4 = view.findViewById(R.id.dispatch);
        }

        public void bindVideojuegos(Videojuego[] lista) {
            tituloView1.setText(lista[0].nombre);
            tituloView2.setText(lista[1].nombre);
            tituloView3.setText(lista[2].nombre);
            tituloView4.setText(lista[3].nombre);
        }


    }
}
