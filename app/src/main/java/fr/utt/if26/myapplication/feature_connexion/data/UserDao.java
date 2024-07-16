package fr.utt.if26.myapplication.feature_connexion.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

import fr.utt.if26.myapplication.feature_connexion.data.UserEntity;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    ListenableFuture<Long> insertUser(UserEntity user);

    @Update
    void update(UserEntity user);

    @Delete
    void delete(UserEntity user);

    @Query("SELECT * FROM users")
    LiveData<List<UserEntity>> getAllUsers();

    @Query("SELECT * FROM users WHERE id = :userId")
    UserEntity getUserById(int userId);

    @Query("SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1")
    ListenableFuture<UserEntity> getUser(String email, String password);


}
