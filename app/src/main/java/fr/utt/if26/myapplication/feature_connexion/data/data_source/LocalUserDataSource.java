package fr.utt.if26.myapplication.feature_connexion.data.data_source;

import androidx.lifecycle.LiveData;

import java.util.List;
import kotlinx.coroutines.flow.Flow;
import android.os.AsyncTask;

import com.google.common.util.concurrent.ListenableFuture;

import fr.utt.if26.myapplication.feature_connexion.data.UserDao;
import fr.utt.if26.myapplication.feature_connexion.data.UserEntity;

public class LocalUserDataSource {
    private UserDao userDao;

    public LocalUserDataSource(UserDao userDao) {
        this.userDao = userDao;
    }

    public LiveData<List<UserEntity>> getAllUsers() {
        return userDao.getAllUsers();
    }

    //public void insert(UserEntity user) {

        //userDao.insert(user);
    //}

    public ListenableFuture<Long> insertUser(UserEntity user) {
        return userDao.insertUser(user);
    }

    public ListenableFuture<UserEntity> getUser(String email, String password) {
        return userDao.getUser(email, password);
    }


}


