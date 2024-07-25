package fr.utt.if26.myapplication.navigation_feature.presentation.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.List;

import android.Manifest;
import fr.utt.if26.myapplication.R;
import fr.utt.if26.myapplication.navigation_feature.domain.model.Location;
import fr.utt.if26.myapplication.navigation_feature.domain.model.Route;
import fr.utt.if26.myapplication.navigation_feature.presentation.MyApplication;
import fr.utt.if26.myapplication.navigation_feature.presentation.viewModel.NavigationViewModel;
import fr.utt.if26.myapplication.navigation_feature.presentation.viewModel.NavigationViewModelFactory;

public class NavigationActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private GoogleMap googleMap;
    private NavigationViewModel navigationViewModel;
    private EditText cityNameEditText;
    private Button startNavigationButton;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        //Import
        cityNameEditText = findViewById(R.id.city_name);
        startNavigationButton = findViewById(R.id.start_navigation_button);

        //Start navigation button
        startNavigationButton.setOnClickListener(v -> {
            String cityName = cityNameEditText.getText().toString();
            if (!cityName.isEmpty()) {
                startNavigationToCity(cityName);
            } else {
                Toast.makeText(this, "Please enter a city name", Toast.LENGTH_SHORT).show();
            }
        });

        // Initialiser la carte
        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.mapContainer, mapFragment)
                .commit();
        mapFragment.getMapAsync(this);

        // Initialiser le ViewModel
        MyApplication app = (MyApplication) getApplication();
        NavigationViewModelFactory factory = new NavigationViewModelFactory(
                app,
                app.appContainer.startNavigationUseCase,
                app.appContainer.updateLocationUseCase
        );

        navigationViewModel = new ViewModelProvider(this, factory).get(NavigationViewModel.class);

        // Observer les changements d'itinéraire
        navigationViewModel.getRoute().observe(this, route -> {
            // Mettre à jour l'itinéraire sur la carte
            updateMapWithRoute(route);
        });

        // Initialiser le client de localisation
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }



    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        //LatLng defaultLocation = new LatLng(48.8566, 2.3522);
        //googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 10));

        enableMyLocation();
    }

    private void enableMyLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }
        googleMap.setMyLocationEnabled(true);
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    if (location != null) {
                        LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15));
                    }
                });
    }

    private void updateMapWithRoute(Route route) {
        // Dessiner l'itinéraire sur la carte
        PolylineOptions polylineOptions = new PolylineOptions();
        for (Location location : route.getPath()) {
            polylineOptions.add(new LatLng(location.getLatitude(), location.getLongitude()));
        }
        googleMap.addPolyline(polylineOptions);
    }

    private void startNavigation(LatLng destination) {
        // Centrer la carte sur la destination
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(destination, 10));

        // Mettre à jour l'itinéraire sur la carte
        Location origin = new Location(48.8566, 2.3522); //Paris

        Location dest = new Location(destination.latitude, destination.longitude);

        navigationViewModel.startNavigation(origin, dest);
    }


    private void startNavigationToCity(String cityName) {
        Geocoder geocoder = new Geocoder(this);
        try {
            List<Address> addresses = geocoder.getFromLocationName(cityName, 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                LatLng cityLatLng = new LatLng(address.getLatitude(), address.getLongitude());
                startNavigation(cityLatLng);
            } else {
                Toast.makeText(this, "City not found", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error finding city", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                enableMyLocation();
            } else {
                Toast.makeText(this, "Location permission is required to use this feature", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
