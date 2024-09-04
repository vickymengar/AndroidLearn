package com.example.androidlearn;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 3000; // 3 segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Se obtienen las SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE);

        // Revisa si es la primera vez que se lanza la aplicación
        boolean isFirstLaunch = sharedPreferences.getBoolean("isFirstLaunch", true);

        // Manejo del tiempo de espera en la pantalla de splash
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (isFirstLaunch) {
                    // Si es la primera vez, muestra el MenuPrincipalActivity
                    intent = new Intent(SplashActivity.this, MenuPrincipalActivity.class);

                    // Guarda que ya no es la primera vez que se lanza
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isFirstLaunch", false);
                    editor.apply();
                } else {
                    // Si no es la primera vez, muestra el MenuDeUsuariosActivity
                    intent = new Intent(SplashActivity.this, MenuDeUsuariosActivity.class);
                }

                // Inicia la actividad correspondiente
                startActivity(intent);
                finish(); // Cierra la actividad de splash
            }
        }, SPLASH_TIME_OUT);
    }
}
