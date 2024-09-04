package com.example.androidlearn;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidlearn.base.AndroidLearnDbHelper;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsuario, editTextContrasena;
    private Button buttonIniciar;
    private TextView textViewRegistro;
    private AndroidLearnDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializar vistas
        editTextUsuario = findViewById(R.id.editTextUsuario);
        editTextContrasena = findViewById(R.id.editTextContrasena);
        buttonIniciar = findViewById(R.id.button_iniciar);
        textViewRegistro = findViewById(R.id.textViewRegistro);

        // Inicializar base de datos
        dbHelper = new AndroidLearnDbHelper(this);

        // Verificar si se recibió un usuario seleccionado desde MenuDeUsuariosActivity
        Intent intent = getIntent();
        String usuarioSeleccionado = intent.getStringExtra("usuarioSeleccionado");

        // Si se recibió un usuario, rellenar el campo de usuario
        if (usuarioSeleccionado != null) {
            Log.d("LoginActivity", "Usuario seleccionado: " + usuarioSeleccionado);
            editTextUsuario.setText(usuarioSeleccionado);
        } else {
            Log.d("LoginActivity", "No se recibió un usuario seleccionado.");
        }

        // Acción al hacer clic en el botón "Iniciar Sesión"
        buttonIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = editTextUsuario.getText().toString().trim();
                String contrasena = editTextContrasena.getText().toString().trim();

                if (usuario.isEmpty() || contrasena.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
                } else {
                    // Verificar credenciales
                    if (verificarUsuario(usuario, contrasena)) {
                        Toast.makeText(LoginActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                        // Ir a la siguiente actividad (por ejemplo, MainActivity)
                        Intent intent = new Intent(LoginActivity.this, IntroActivity.class);
                        startActivity(intent);
                        finish(); // Finalizar la actividad actual
                    } else {
                        Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Acción al hacer clic en el texto "Regístrate"
        textViewRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ir a la actividad de registro (por ejemplo, RegisterActivity)
                Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
    }

    // Este método se llama cuando la actividad ya está en ejecución y se recibe un nuevo Intent
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent); // Actualizar el Intent de la actividad
        String usuarioSeleccionado = intent.getStringExtra("usuarioSeleccionado");
        if (usuarioSeleccionado != null) {
            Log.d("LoginActivity", "Usuario seleccionado (onNewIntent): " + usuarioSeleccionado);
            editTextUsuario.setText(usuarioSeleccionado);
        } else {
            Log.d("LoginActivity", "No se recibió un usuario seleccionado (onNewIntent).");
        }
    }

    private boolean verificarUsuario(String usuario, String contrasena) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {"id"};
        String selection = "usuario = ? AND contrasena = ?";
        String[] selectionArgs = {usuario, contrasena};

        Cursor cursor = db.query(AndroidLearnDbHelper.TABLE_USUARIOS, columns, selection, selectionArgs, null, null, null);

        boolean usuarioEncontrado = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return usuarioEncontrado;
    }
}
