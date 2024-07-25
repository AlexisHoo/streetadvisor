package fr.utt.if26.myapplication.navigation_feature.data.data_source;

import fr.utt.if26.myapplication.navigation_feature.data.repository.NavigationRepository;
import fr.utt.if26.myapplication.navigation_feature.domain.model.Location;
import fr.utt.if26.myapplication.navigation_feature.domain.model.Route;

public class GMapsRepository implements NavigationRepository {
    @Override
    public Route calculateRoute(Location origin, Location destination) {
        // Appeler l'API Google Maps pour calculer l'itinéraire
        // Transformer la réponse en objet Route
        return null; // Remplacer par l'implémentation réelle
    }

    @Override
    public void updateCurrentLocation(Location location) {
        // Mettre à jour la position actuelle
    }
}

