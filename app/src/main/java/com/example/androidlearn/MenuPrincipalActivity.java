package com.example.androidlearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MenuPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        Button buttonLogin = findViewById(R.id.button_login);
        Button buttonRegister = findViewById(R.id.button_registrar);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad de inicio de sesión
                Intent intent = new Intent(MenuPrincipalActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad de registro
                Intent intent = new Intent(MenuPrincipalActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
    }
}
