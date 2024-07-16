package fr.utt.if26.myapplication.feature_connexion.presentation.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.common.util.concurrent.ListenableFuture;

import fr.utt.if26.myapplication.feature_connexion.data.UserDatabase;
import fr.utt.if26.myapplication.feature_connexion.data.data_source.LocalUserDataSource;
import fr.utt.if26.myapplication.feature_connexion.data.repository.UserRepositoryImpl;
import fr.utt.if26.myapplication.feature_connexion.domain.model.User;
import fr.utt.if26.myapplication.feature_connexion.domain.repository.UserRepository;
import fr.utt.if26.myapplication.feature_connexion.domain.use_case.CreateUserUseCase;

public class CreateUserViewModel extends AndroidViewModel {
    private CreateUserUseCase createUserUseCase;
    private MutableLiveData<Boolean> isUserCreated = new MutableLiveData<>();

    public CreateUserViewModel(Application application) {
        super(application);
        UserRepository userRepository = new UserRepositoryImpl(new LocalUserDataSource(UserDatabase.getDatabase(application).userDao()));
        createUserUseCase = new CreateUserUseCase(userRepository);
    }

    //public void createUser(String firstName, String password, String lastname, String email, int age, String sexe) {
        // Simple password hashing example, in real-world applications use more secure methods
        //String hashedPassword = Integer.toString(password.hashCode());
        //User user = new User(1, firstName,lastname,email,age,sexe, hashedPassword);
        //createUserUseCase.execute(user);
        //isUserCreated.setValue(true);
    //}

    public ListenableFuture<Long> createUser(User user) {

        //createUserUseCase.execute(user);
        isUserCreated.setValue(true);

        return createUserUseCase.execute(user);
    }

    public LiveData<Boolean> isUserCreated() {
        return isUserCreated;
    }
}
