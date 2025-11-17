package com.example.actividad7_sonidos;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.SoundPool;
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

    private Button botonPlay, botonPause, botonColor, botonPelicano, botonTwitter, botonDiscord, botonBufalo;
    private TextView txtTiempo, txtTiempoFinal;
    private SeekBar lineaTiempo;
    private MediaPlayer mediaPlayer;
    private ConstraintLayout layout; //Tiene que ser Constraint porque es el tipo de layout que he usado
    private boolean negro = false;
    private SoundPool soundPool;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        botonPlay = findViewById(R.id.botonPlay);
        botonPause = findViewById(R.id.botonPause);
        botonColor = findViewById(R.id.botonColor);
        botonBufalo = findViewById(R.id.botonBufalo);
        botonDiscord = findViewById(R.id.botonDiscord);
        botonPelicano = findViewById(R.id.botonPelicano);
        botonTwitter = findViewById(R.id.botonTwitter);
        layout = findViewById(R.id.main);
        txtTiempo = findViewById(R.id.txtTiempo);
        txtTiempoFinal = findViewById(R.id.txtTiempoFinal);
        lineaTiempo = findViewById(R.id.lineaTiempo);
        mediaPlayer = MediaPlayer.create(this, R.raw.atlantis);

        botonPlay.setOnClickListener(v -> {
            mediaPlayer.start();
        });

        botonPause.setOnClickListener(v ->{
            mediaPlayer.pause();
        });

        soundPool = new SoundPool.Builder().setMaxStreams(4).build();

        int sonidoBufalo = soundPool.load(this, R.raw.buffalo, 1);
        int sonidoDiscord = soundPool.load(this, R.raw.discord_notification, 1);
        int sonidoTwitter = soundPool.load(this, R.raw.twitter, 1);
        int sonidoPelicano = soundPool.load(this, R.raw.pelicano, 1);

        botonPelicano.setOnClickListener(v ->{
            soundPool.play(sonidoPelicano, 1, 1, 1, 0, 1);
        });

        botonTwitter.setOnClickListener(v ->{
            soundPool.play(sonidoTwitter, 1, 1, 1, 0, 1);
        });

        botonDiscord.setOnClickListener(v ->{
            soundPool.play(sonidoDiscord, 1, 1, 1, 0, 1);
        });

        botonBufalo.setOnClickListener(v ->{
            soundPool.play(sonidoBufalo, 1, 1, 1, 0, 1);
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

                //Para que muestre el tiempo total
                int tiempoRestante = mediaPlayer.getDuration() - mediaPlayer.getCurrentPosition();
                int minutosTotal = tiempoRestante / 1000 / 60;
                int segundosTotal = tiempoRestante / 1000 % 60;
                String tiempoTotal = String.format("-%02d:%02d", minutosTotal, segundosTotal);
                txtTiempoFinal.setText(tiempoTotal);

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