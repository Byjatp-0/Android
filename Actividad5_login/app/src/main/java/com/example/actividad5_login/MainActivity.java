package com.example.actividad5_login;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText tCorreo;
    private EditText tContraseña;
    private Button botonContinuar;
    private Switch switchRecordar;
    private TextView tMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tCorreo = findViewById(R.id.tCorreo);
        tContraseña = findViewById(R.id.tContraseña);
        botonContinuar = findViewById(R.id.botonContinuar);
        switchRecordar = findViewById(R.id.switchRecordar);
        tMensaje = findViewById(R.id.tMensaje);

        botonContinuar.setOnClickListener(e -> {

            String correo = tCorreo.getText().toString();
            String contraseña = tContraseña.getText().toString();
            Boolean recordar = switchRecordar.isChecked();

            if(correo.equals("correo@correo.com") && contraseña.equals("123")){
                tMensaje.setText("Contraseña correcta");
                tContraseña.setText("");
                tCorreo.setText("");

                if(recordar){
                    tCorreo.setText("correo@correo.com");
                    tContraseña.setText("123");

                }

            }else{
                tMensaje.setText("Correo o contraseña incorrectos");
            }
                }
                );

    }
}