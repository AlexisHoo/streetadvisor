package fr.utt.if26.myapplication.feature_connexion.presentation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;

import fr.utt.if26.myapplication.R;
import fr.utt.if26.myapplication.feature_connexion.domain.model.User;
import fr.utt.if26.myapplication.feature_connexion.presentation.ViewModel.CreateUserViewModel;

public class CreateUserFragment extends Fragment {
    private CreateUserViewModel createUserViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_user, container, false);

        final EditText editTextFirstName = view.findViewById(R.id.editTextFirstName);
        final EditText editTextPassword = view.findViewById(R.id.editTextPassword);

        final EditText editTextLastName = view.findViewById(R.id.editTextLastName);
        final EditText editTextEmail = view.findViewById(R.id.editTextEmail);

        final EditText editTextAge = view.findViewById(R.id.editTextAge);
        final EditText editTextSexe = view.findViewById(R.id.editTextSexe);


        Button buttonCreateUser = view.findViewById(R.id.buttonCreateUser);

        createUserViewModel = new ViewModelProvider(this).get(CreateUserViewModel.class);

        createUserViewModel.isUserCreated().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isCreated) {
                if (isCreated) {
                    Toast.makeText(getActivity(), "User created successfully!", Toast.LENGTH_SHORT).show();
                    // Navigate back or to another screen
                }
            }
        });

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
                    User user = new User(5, firstName,lastname,email,15,sexe, hashedPassword);
                    ListenableFuture<Long> future = createUserViewModel.createUser(user);
                    Futures.addCallback(future, new FutureCallback<Long>() {
                        @Override
                        public void onSuccess(Long result) {
                            // Handle success
                            System.out.println("User inserted successfully with ID: " + result);
                            Intent intent = new Intent(getActivity(), LoginActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            // Handle failure
                            t.printStackTrace();
                        }
                    }, MoreExecutors.directExecutor());
                } else {
                    Toast.makeText(getActivity(), "Please enter all details", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}

