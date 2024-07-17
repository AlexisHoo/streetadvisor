package fr.utt.if26.myapplication.feature_connexion.presentation.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;

import fr.utt.if26.myapplication.R;
import fr.utt.if26.myapplication.feature_connexion.domain.model.User;
import fr.utt.if26.myapplication.feature_connexion.presentation.ViewModel.CreateUserViewModel;

public class CreateUserActivity extends AppCompatActivity {

    private CreateUserViewModel createUserViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);


        final EditText editTextFirstName = findViewById(R.id.editTextFirstName);
        final EditText editTextPassword = findViewById(R.id.editTextPassword);

        final EditText editTextLastName = findViewById(R.id.editTextLastName);
        final EditText editTextEmail = findViewById(R.id.editTextEmail);

        final EditText editTextAge = findViewById(R.id.editTextAge);
        final EditText editTextSexe = findViewById(R.id.editTextSexe);


        Button buttonCreateUser = findViewById(R.id.buttonCreateUser);

        createUserViewModel = new ViewModelProvider(this).get(CreateUserViewModel.class);

        //createUserViewModel.isUserCreated().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            //@Override
            //public void onChanged(Boolean isCreated) {
                //if (isCreated) {
              //      Toast.makeText(CreateUserActivity.this, "User created successfully!", Toast.LENGTH_SHORT).show();
                    // Navigate back or to another screen
                //}
            //}
        //});

        buttonCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = editTextFirstName.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                String lastname = editTextLastName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();

                //String age = editTextAge.getText().toString().trim();
                String sexe = editTextSexe.getText().toString().trim();


                if (!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(password)) {

                    String hashedPassword = Integer.toString(password.hashCode());
                    User user = new User(firstName,lastname,email,15,sexe, hashedPassword);
                    ListenableFuture<Long> future = createUserViewModel.createUser(user);
                    Futures.addCallback(future, new FutureCallback<Long>() {
                        @Override
                        public void onSuccess(Long result) {
                            // Handle success
                            System.out.println("User inserted successfully with ID: " + result);
                            Intent intent = new Intent(CreateUserActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            // Handle failure
                            t.printStackTrace();
                        }
                    }, MoreExecutors.directExecutor());
                } else {
                    Toast.makeText(CreateUserActivity.this, "Please enter all details", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}