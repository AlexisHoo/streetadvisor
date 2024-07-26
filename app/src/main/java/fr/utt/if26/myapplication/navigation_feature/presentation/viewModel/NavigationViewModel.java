package fr.utt.if26.myapplication.navigation_feature.presentation.viewModel;

import android.app.Application;
import android.location.Location;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import fr.utt.if26.myapplication.navigation_feature.domain.model.Route;
import fr.utt.if26.myapplication.navigation_feature.domain.use_case.StartNavigationUseCase;
import fr.utt.if26.myapplication.navigation_feature.domain.use_case.UpdateLocationUseCase;
public class NavigationViewModel extends AndroidViewModel {
    private final StartNavigationUseCase startNavigationUseCase;
    private final UpdateLocationUseCase updateLocationUseCase;

    private MutableLiveData<Route> routeLiveData = new MutableLiveData<>();

    public NavigationViewModel(Application app, StartNavigationUseCase startNavigationUseCase, UpdateLocationUseCase updateLocationUseCase) {
        super(app);
        this.startNavigationUseCase = startNavigationUseCase;
        this.updateLocationUseCase = updateLocationUseCase;
    }

    public LiveData<Route> getRoute() {
        return routeLiveData;
    }

    public void startNavigation(Location origin, Location destination) {
        Route route = startNavigationUseCase.execute(origin, destination);
        routeLiveData.setValue(route);
    }

    public void updateLocation(Location location) {
        updateLocationUseCase.execute(location);
    }
}
