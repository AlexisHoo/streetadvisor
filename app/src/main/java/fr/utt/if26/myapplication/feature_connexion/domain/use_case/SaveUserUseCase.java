package fr.utt.if26.myapplication.feature_connexion.domain.use_case;

import fr.utt.if26.myapplication.feature_connexion.domain.model.User;
import fr.utt.if26.myapplication.feature_connexion.domain.repository.UserRepository;

public class SaveUserUseCase {
    private UserRepository userRepository;

    public SaveUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(User user) {
        userRepository.saveUser(user);
    }
}
