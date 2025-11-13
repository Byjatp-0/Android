package com.example.actividad5_listas;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recView);

        Videojuego[] videojuegos = new Videojuego[3];
        videojuegos[0] = new Videojuego("LoL",  "lol.jpg");
        videojuegos[1] = new Videojuego("Zenless Zone Zero", "zzz.png");
        videojuegos[2] = new Videojuego("Arc Raiders", "arc_riders.jpg");
        videojuegos[3] = new Videojuego("Dispatch", "dispatch.jpg");
        

        Adaptador adapter = new Adaptador(videojuegos);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);

    }
}