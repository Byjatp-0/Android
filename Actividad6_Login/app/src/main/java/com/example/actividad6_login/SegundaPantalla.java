package com.example.actividad6_login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SegundaPantalla extends AppCompatActivity {

    private TextView txtCorreo;
    private Button botonDesconectar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_segunda_pantalla);

        txtCorreo = findViewById(R.id.txtCorreo);
        botonDesconectar = findViewById(R.id.botonDesconectar);

        Intent intent = getIntent();
        String correo = intent.getStringExtra("correo");
        txtCorreo.setText(correo);

        botonDesconectar.setOnClickListener(v ->{
            finish();
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}