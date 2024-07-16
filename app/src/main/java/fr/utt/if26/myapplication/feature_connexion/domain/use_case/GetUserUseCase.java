package fr.utt.if26.myapplication.feature_connexion.domain.use_case;

import androidx.lifecycle.LiveData;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

import fr.utt.if26.myapplication.feature_connexion.data.UserEntity;
import fr.utt.if26.myapplication.feature_connexion.domain.model.User;
import fr.utt.if26.myapplication.feature_connexion.domain.repository.UserRepository;

public class GetUserUseCase {
    private UserRepository userRepository;

    public GetUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LiveData<List<UserEntity>> execute() {

        return userRepository.getAllUsers();
    }

    public ListenableFuture<UserEntity> execute(String email, String password) {
        return userRepository.getUser(email, password);
    }
}
