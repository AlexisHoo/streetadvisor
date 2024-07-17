package fr.utt.if26.myapplication.feature_connexion.presentation.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fr.utt.if26.myapplication.R;
import fr.utt.if26.myapplication.feature_connexion.presentation.MainActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView userIdTextView = findViewById(R.id.textView);
        Button login = findViewById(R.id.login);
        Button signup = findViewById(R.id.signup);
        Button without = findViewById(R.id.without);

        userIdTextView.setText("Welcome to StreetAdvisor !");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        without.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                //startActivity(intent);
                System.out.println("Not created yet");

            }
        });
    }
}