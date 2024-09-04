package com.example.androidlearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Button buttonIntro = findViewById(R.id.buttonintro);

        // Set onClickListener for the button
        buttonIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start MainActivity when the button is clicked
                Intent intent = new Intent(IntroActivity.this,MainActivity.class);
                startActivity(intent);
                // Optional: Finish the intro activity so the user cannot return to it
                finish();
            }
        });
    }
}