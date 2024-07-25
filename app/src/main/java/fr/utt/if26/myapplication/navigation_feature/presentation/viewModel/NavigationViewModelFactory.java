package fr.utt.if26.myapplication.navigation_feature.presentation.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import fr.utt.if26.myapplication.navigation_feature.domain.use_case.StartNavigationUseCase;
import fr.utt.if26.myapplication.navigation_feature.domain.use_case.UpdateLocationUseCase;

public class NavigationViewModelFactory implements ViewModelProvider.Factory {
    private final Application application;
    private final StartNavigationUseCase startNavigationUseCase;
    private final UpdateLocationUseCase updateLocationUseCase;

    public NavigationViewModelFactory(Application application, StartNavigationUseCase startNavigationUseCase, UpdateLocationUseCase updateLocationUseCase) {
        this.application = application;
        this.startNavigationUseCase = startNavigationUseCase;
        this.updateLocationUseCase = updateLocationUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NavigationViewModel.class)) {
            return (T) new NavigationViewModel(application, startNavigationUseCase, updateLocationUseCase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

