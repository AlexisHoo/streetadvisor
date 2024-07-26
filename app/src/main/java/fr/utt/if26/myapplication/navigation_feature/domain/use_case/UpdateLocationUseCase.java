package fr.utt.if26.myapplication.navigation_feature.domain.use_case;

import android.location.Location;

import fr.utt.if26.myapplication.navigation_feature.data.repository.NavigationRepository;

public class UpdateLocationUseCase {
    private final NavigationRepository repository;

    public UpdateLocationUseCase(NavigationRepository repository) {
        this.repository = repository;
    }

    public void execute(Location currentLocation) {
        repository.updateCurrentLocation(currentLocation);
    }
}
