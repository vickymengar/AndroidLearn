package com.example.androidlearn;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MenuDeUsuariosActivity extends AppCompatActivity {

    private ListView usersListView;
    private ArrayList<String> userList;
    private ArrayAdapter<String> adapter;
    private TextView textViewRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_de_usuarios);

        usersListView = findViewById(R.id.usersListView);
        textViewRegistro = findViewById(R.id.textViewRegistro);
        userList = new ArrayList<>();

        // Obtén las SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE);

        // Carga los usuarios registrados
        Set<String> usersSet = sharedPreferences.getStringSet("registeredUsers", new HashSet<>());
        userList.addAll(usersSet);

        // Configura el ListView con los usuarios
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userList);
        usersListView.setAdapter(adapter);

        // Maneja la selección de un usuario
        usersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedUser = userList.get(position);

                // Redirige a LoginActivity con el usuario seleccionado
                Intent intent = new Intent(MenuDeUsuariosActivity.this, LoginActivity.class);
                intent.putExtra("username", selectedUser);
                startActivity(intent);
            }
        });

        // Maneja el clic en el TextView para registrarse
        textViewRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirige a la actividad de registro
                Intent intent = new Intent(MenuDeUsuariosActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Recarga la lista de usuarios en caso de que se haya registrado un nuevo usuario
        SharedPreferences sharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE);
        Set<String> usersSet = sharedPreferences.getStringSet("registeredUsers", new HashSet<>());

        userList.clear(); // Limpia la lista actual
        userList.addAll(usersSet); // Añade los usuarios actualizados
        adapter.notifyDataSetChanged(); // Notifica al adaptador que los datos han cambiado
    }
}
