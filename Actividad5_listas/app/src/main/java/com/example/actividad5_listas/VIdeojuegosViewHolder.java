package com.example.actividad5_listas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import javax.crypto.Cipher;

public static class VideojuegosViewHolder extends RecyclerView.ViewHolder{

    public TextView tituloView1, tituloView2, tituloView3, tituloView4;

    public ImageView portadaView1, portadaView2, portadaView3, portadaView4;

    private Context context;

    public VideojuegosViewHolder(View view, Context context){
        super(view);
        this.context = context;
        tituloView1 = view.findViewById(R.id.lolTxt);
        tituloView2 = view.findViewById(R.id.zzzTxt);
        tituloView3 = view.findViewById(R.id.arTxt);
        tituloView4 = view.findViewById(R.id.dispatchTxt);
        portadaView1 = view.findViewById(R.id.lol);
        portadaView2 = view.findViewById(R.id.zzz);
        portadaView3 = view.findViewById(R.id.arc);
        portadaView4 = view.findViewById(R.id.dispatch);
    }
    @NonNull
    @Override
    public VideojuegosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.juegos_item, parent, false);

        return new VideojuegosViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull VideojuegosViewHolder holder, int position) {
        holder.BindVideojuego(this.listaVideojuegos[position]);
    }

    @Override
    public int getItemCount(){
        return this.listaVideojuegos.length;
    }

}
