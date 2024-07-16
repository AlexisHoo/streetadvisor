package fr.utt.if26.myapplication.feature_connexion.presentation.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;

import fr.utt.if26.myapplication.R;
import fr.utt.if26.myapplication.feature_connexion.data.UserEntity;
import fr.utt.if26.myapplication.feature_connexion.presentation.ViewModel.CreateUserViewModel;
import fr.utt.if26.myapplication.feature_connexion.presentation.ViewModel.UserViewModel;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText email = findViewById(R.id.editTextEmail);
        final EditText password = findViewById(R.id.editTextPassword);


        Button buttonLogin = findViewById(R.id.button);

        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailString = email.getText().toString().trim();
                String passwordString = password.getText().toString().trim();

                ListenableFuture<UserEntity> future = userViewModel.getUser(emailString, passwordString);

                Futures.addCallback(future, new FutureCallback<UserEntity>() {
                    @Override
                    public void onSuccess(UserEntity user) {
                        // Handle success
                        System.out.println("User fetched successfully: " + user);
                        Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                        intent.putExtra("userId", user.getFirstName());
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        // Handle failure
                        System.err.println("Error fetching user: " + t.getMessage());
                    }
                }, MoreExecutors.directExecutor());
            }
        });
    }
}