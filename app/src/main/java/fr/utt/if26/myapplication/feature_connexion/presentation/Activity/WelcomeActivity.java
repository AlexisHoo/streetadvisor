package fr.utt.if26.myapplication.feature_connexion.presentation.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import fr.utt.if26.myapplication.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        // Récupérer l'ID de l'utilisateur à partir de l'Intent
        Intent intent = getIntent();
        String userId = intent.getStringExtra("userId");

        // Trouver le TextView et y afficher l'ID de l'utilisateur
        TextView userIdTextView = findViewById(R.id.textView);
        userIdTextView.setText("User ID: " + userId);
    }
}