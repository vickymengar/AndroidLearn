package com.example.androidlearn;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidlearn.base.AndroidLearnDbHelper;

import java.util.HashSet;
import java.util.Set;

public class RegistroActivity extends AppCompatActivity {

    private EditText editTextNombre, editTextApP, editTextApM, editTextUsuario, editTextCorreo, editTextContrasena;
    private Button buttonRegistro;
    private TextView textViewLogin;
    private AndroidLearnDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Inicializar vistas
        editTextNombre = findViewById(R.id.editTextNombre);
        editTextApP = findViewById(R.id.editTextApP);
        editTextApM = findViewById(R.id.editTextApM);
        editTextUsuario = findViewById(R.id.editTextUsuario);
        editTextCorreo = findViewById(R.id.editTextCorreo);
        editTextContrasena = findViewById(R.id.editTextContrasena);
        buttonRegistro = findViewById(R.id.button_registro);
        textViewLogin = findViewById(R.id.textViewLogin);

        // Inicializar base de datos
        dbHelper = new AndroidLearnDbHelper(this);

        // Botón de registro
        buttonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });

        // Redirigir a la actividad de login
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí puedes redirigir a la actividad de inicio de sesión
                finish(); // Cierra la actividad actual
            }
        });
    }

    private void registrarUsuario() {
        String nombre = editTextNombre.getText().toString().trim();
        String apP = editTextApP.getText().toString().trim();
        String apM = editTextApM.getText().toString().trim();
        String usuario = editTextUsuario.getText().toString().trim();
        String correo = editTextCorreo.getText().toString().trim();
        String contrasena = editTextContrasena.getText().toString().trim();

        // Validar campos vacíos
        if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(apP) || TextUtils.isEmpty(usuario) ||
                TextUtils.isEmpty(correo) || TextUtils.isEmpty(contrasena)) {
            Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Insertar datos en la base de datos
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("apellido_paterno", apP);
        values.put("apellido_materno", apM);
        values.put("usuario", usuario);
        values.put("correo_electronico", correo);
        values.put("contrasena", contrasena);
        long newRowId = db.insert(AndroidLearnDbHelper.TABLE_USUARIOS, null, values);

        if (newRowId != -1) {
            Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();

            // Guardar el usuario en SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE);
            Set<String> usersSet = sharedPreferences.getStringSet("registeredUsers", new HashSet<>());

            usersSet.add(usuario); // Añadir el nuevo usuario al set

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putStringSet("registeredUsers", usersSet);
            editor.apply(); // Guardar cambios en SharedPreferences

            limpiarCampos(); // Limpiar campos después del registro

            // Redirigir a MenuDeUsuariosActivity para mostrar la lista actualizada
            Intent intent = new Intent(RegistroActivity.this, MenuDeUsuariosActivity.class);
            startActivity(intent);
            finish(); // Finalizar la actividad actual
        } else {
            Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
        }
    }

    private void limpiarCampos() {
        editTextNombre.setText("");
        editTextApP.setText("");
        editTextApM.setText("");
        editTextUsuario.setText("");
        editTextCorreo.setText("");
        editTextContrasena.setText("");
    }
}
