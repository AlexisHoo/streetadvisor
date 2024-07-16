package fr.utt.if26.myapplication.feature_connexion.data.repository;

import androidx.lifecycle.LiveData;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

import fr.utt.if26.myapplication.feature_connexion.data.UserEntity;
import fr.utt.if26.myapplication.feature_connexion.data.data_source.LocalUserDataSource;
import fr.utt.if26.myapplication.feature_connexion.domain.model.User;
import fr.utt.if26.myapplication.feature_connexion.domain.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {
    private LocalUserDataSource localUserDataSource;

    public UserRepositoryImpl(LocalUserDataSource localUserDataSource) {
        this.localUserDataSource = localUserDataSource;
    }

    @Override
    public LiveData<List<UserEntity>> getAllUsers() {
        return localUserDataSource.getAllUsers();
    }

    @Override
    public void saveUser(User user) {
        localUserDataSource.insertUser(new UserEntity(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAge(), user.getSexe(), user.getPassword()));
    }

    public ListenableFuture<Long> insertUser(UserEntity user) {
        return localUserDataSource.insertUser(user);
    }

    public ListenableFuture<UserEntity> getUser(String email, String password) {
        return localUserDataSource.getUser(email, password);
    }

}
