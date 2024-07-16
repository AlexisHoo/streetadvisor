package fr.utt.if26.myapplication.feature_connexion.domain.repository;

import androidx.lifecycle.LiveData;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

import fr.utt.if26.myapplication.feature_connexion.data.UserEntity;
import fr.utt.if26.myapplication.feature_connexion.domain.model.User;

public interface UserRepository {

    LiveData<List<UserEntity>> getAllUsers();
    void saveUser(User user);

    ListenableFuture<Long> insertUser(UserEntity user);

    ListenableFuture<UserEntity> getUser(String email, String password);
}
