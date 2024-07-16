package fr.utt.if26.myapplication.feature_connexion.domain.use_case;

import com.google.common.util.concurrent.ListenableFuture;

import fr.utt.if26.myapplication.feature_connexion.data.UserEntity;
import fr.utt.if26.myapplication.feature_connexion.domain.model.User;
import fr.utt.if26.myapplication.feature_connexion.domain.repository.UserRepository;

public class CreateUserUseCase {
    private UserRepository userRepository;

    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //public void execute(User user) {
        //userRepository.saveUser(user);
    //}

    public ListenableFuture<Long> execute(User user) {
        UserEntity userEntity = new UserEntity(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAge(), user.getSexe(), user.getPassword());
        return userRepository.insertUser(userEntity);
    }
}
