package fr.utt.if26.myapplication.navigation_feature.domain.use_case;

import android.location.Location;

import fr.utt.if26.myapplication.navigation_feature.data.repository.NavigationRepository;
import fr.utt.if26.myapplication.navigation_feature.domain.model.Route;

public class StartNavigationUseCase {
    private final NavigationRepository repository;

    public StartNavigationUseCase(NavigationRepository repository) {
        this.repository = repository;
    }

    public Route execute(Location origin, Location destination) {
        return repository.calculateRoute(origin, destination);
    }
}

