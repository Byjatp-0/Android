package com.example.actividad7_sonidos;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button botonPlay, botonStop, botonPause, botonCargar, botonSeekTo, botonRelease, botonColor;
    private TextView txtTiempo;
    private SeekBar lineaTiempo;
    private MediaPlayer mediaPlayer;
    private EditText txtSeekTo;
    private ConstraintLayout layout; //Tiene que ser Constraint porque es el tipo de layout que he usado
    private boolean negro = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        botonPlay = findViewById(R.id.botonPlay);
        botonStop = findViewById(R.id.botonStop);
        botonPause = findViewById(R.id.botonPause);
        botonCargar = findViewById(R.id.botonCargar);
        botonSeekTo = findViewById(R.id.botonSeekTo);
        botonRelease = findViewById(R.id.botonRelease);
        botonColor = findViewById(R.id.botonColor);
        layout = findViewById(R.id.main);
        txtTiempo = findViewById(R.id.txtTiempo);
        lineaTiempo = findViewById(R.id.lineaTiempo);
        mediaPlayer = MediaPlayer.create(this, R.raw.atlantis);

        botonPlay.setOnClickListener(v -> {
            mediaPlayer.start();
        });

        botonStop.setOnClickListener(v ->{
            mediaPlayer.stop();
        });

        botonPause.setOnClickListener(v ->{
            mediaPlayer.pause();
        });

        botonRelease.setOnClickListener(v ->{
            mediaPlayer.release();
        });

        botonCargar.setOnClickListener(v ->{
            mediaPlayer = MediaPlayer.create(this, R.raw.atlantis);
        });

        botonColor.setOnClickListener(v ->{
            if(negro){
                layout.setBackgroundColor(Color.WHITE);
                negro = false;

            }else{
                layout.setBackgroundColor(Color.BLACK);
                negro = true;

            }
        });

        txtSeekTo = findViewById(R.id.txtSeekTo);
        botonSeekTo = findViewById(R.id.botonSeekTo);
        botonSeekTo.setOnClickListener(v ->{
            int p = Integer.parseInt(txtSeekTo.getText().toString());
            mediaPlayer.seekTo(p);
        });

        //Creación barra progreso
        lineaTiempo = findViewById(R.id.lineaTiempo);
        txtTiempo = findViewById(R.id.txtTiempo);

        mediaPlayer.setOnPreparedListener(mp ->{
            lineaTiempo.setMax(mediaPlayer.getDuration());
            txtTiempo.setText("00:00");
        });

        //Esto actualiza la barra
        Handler handler = new Handler();
        Runnable actualizar = new Runnable() {
            @Override
            public void run() {
                lineaTiempo.setProgress(mediaPlayer.getCurrentPosition());

                //Para poner el tiempo en el textView
                int minutos = mediaPlayer.getCurrentPosition() / 1000 / 60;
                int segundos = mediaPlayer.getCurrentPosition() / 1000 % 60; //Se parte entre sesenta porque de los segundos se quiere el resto
                String tiempo = String.format("%02d:%02d", minutos, segundos);
                txtTiempo.setText(tiempo);

                //Se actualiza cada segundo
                handler.postDelayed(this,1000);

            }
        };

        handler.post(actualizar);

        //Permitimos que el usuario arrastre la barra
        lineaTiempo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mediaPlayer.seekTo(progress);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}