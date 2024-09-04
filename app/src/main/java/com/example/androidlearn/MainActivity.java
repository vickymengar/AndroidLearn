package com.example.androidlearn;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView bienvenidaTextView;
    private ImageButton btnLogout;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar componentes
        bienvenidaTextView = findViewById(R.id.bienvenidaTextView);
        btnLogout = findViewById(R.id.btnLogout);

        // Obtener SharedPreferences
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        // Obtener nombre de usuario de SharedPreferences y mostrarlo
        String nombreUsuario = sharedPreferences.getString("username", null);

        if (nombreUsuario != null) {
            bienvenidaTextView.setText("¡Bienvenido, " + nombreUsuario + "!");
        } else {
            bienvenidaTextView.setText("¡Bienvenido!");
        }

        // Configurar el listener para el botón de cerrar sesión
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Borrar los datos de usuario y redirigir al menú de usuarios
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Configurar listeners para cada ImageView
        configurarImageViewListeners();
    }

    private void configurarImageViewListeners() {
        // Configuración para cada cuadrito que redirige a su actividad correspondiente
        findViewById(R.id.cuadrito1).setOnClickListener(view -> redirigirAVista(Vista1Activity.class));
        findViewById(R.id.cuadrito2).setOnClickListener(view -> redirigirAVista(Vista2Activity.class));
        findViewById(R.id.cuadrito3).setOnClickListener(view -> redirigirAVista(Vista3Activity.class));
        findViewById(R.id.cuadrito4).setOnClickListener(view -> redirigirAVista(Vista4Activity.class));
        findViewById(R.id.cuadrito5).setOnClickListener(view -> redirigirAVista(Vista5Activity.class));
        findViewById(R.id.cuadrito6).setOnClickListener(view -> redirigirAVista(Vista6Activity.class));
        findViewById(R.id.cuadrito7).setOnClickListener(view -> redirigirAVista(Vista7Activity.class));
        findViewById(R.id.cuadrito8).setOnClickListener(view -> redirigirAVista(Vista8Activity.class));
        findViewById(R.id.cuadrito9).setOnClickListener(view -> redirigirAVista(Vista9Activity.class));
        findViewById(R.id.cuadrito10).setOnClickListener(view -> redirigirAVista(Vista10Activity.class));
        findViewById(R.id.cuadrito11).setOnClickListener(view -> redirigirAVista(Vista11Activity.class));
        findViewById(R.id.cuadrito12).setOnClickListener(view -> redirigirAVista(Vista12Activity.class));
        findViewById(R.id.cuadrito13).setOnClickListener(view -> redirigirAVista(Vista13Activity.class));
        findViewById(R.id.cuadrito14).setOnClickListener(view -> redirigirAVista(Vista14Activity.class));
    }

    private void redirigirAVista(Class<?> vistaActivity) {
        Intent intent = new Intent(MainActivity.this, vistaActivity);
        startActivity(intent);
    }
}
