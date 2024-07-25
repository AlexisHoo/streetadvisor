package fr.utt.if26.myapplication.navigation_feature.presentation;

import fr.utt.if26.myapplication.navigation_feature.data.data_source.GMapsRepository;
import fr.utt.if26.myapplication.navigation_feature.data.repository.NavigationRepository;
import fr.utt.if26.myapplication.navigation_feature.domain.use_case.StartNavigationUseCase;
import fr.utt.if26.myapplication.navigation_feature.domain.use_case.UpdateLocationUseCase;

public class AppContainer {
    public NavigationRepository navigationRepository = new GMapsRepository();

    public StartNavigationUseCase startNavigationUseCase = new StartNavigationUseCase(navigationRepository);
    public UpdateLocationUseCase updateLocationUseCase = new UpdateLocationUseCase(navigationRepository);
}
