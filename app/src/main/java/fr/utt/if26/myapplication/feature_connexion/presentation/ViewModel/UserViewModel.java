package fr.utt.if26.myapplication.feature_connexion.presentation.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

import fr.utt.if26.myapplication.feature_connexion.data.UserDatabase;
import fr.utt.if26.myapplication.feature_connexion.data.UserEntity;
import fr.utt.if26.myapplication.feature_connexion.data.data_source.LocalUserDataSource;
import fr.utt.if26.myapplication.feature_connexion.data.repository.UserRepositoryImpl;
import fr.utt.if26.myapplication.feature_connexion.domain.repository.UserRepository;
import fr.utt.if26.myapplication.feature_connexion.domain.use_case.GetUserUseCase;
import fr.utt.if26.myapplication.feature_connexion.domain.use_case.SaveUserUseCase;

public class UserViewModel extends AndroidViewModel {
    private GetUserUseCase getUserUseCase;
    private SaveUserUseCase saveUserUseCase;
    private LiveData<List<UserEntity>> allUsers;

    public UserViewModel(Application application) {
        super(application);
        UserRepository userRepository = new UserRepositoryImpl(new LocalUserDataSource(UserDatabase.getDatabase(application).userDao()));
        getUserUseCase = new GetUserUseCase(userRepository);
        saveUserUseCase = new SaveUserUseCase(userRepository);
        allUsers = getUserUseCase.execute();
    }

    public LiveData<List<UserEntity>> getAllUsers() {
        return allUsers;
    }

    public ListenableFuture<UserEntity> getUser(String email, String password) {
        return getUserUseCase.execute(email, password);
    }
}
