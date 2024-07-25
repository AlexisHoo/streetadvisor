package fr.utt.if26.myapplication.navigation_feature.data.repository;

import fr.utt.if26.myapplication.navigation_feature.domain.model.Location;
import fr.utt.if26.myapplication.navigation_feature.domain.model.Route;

public interface NavigationRepository {
    Route calculateRoute(Location origin, Location destination);
    void updateCurrentLocation(Location location);
}

