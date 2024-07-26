package fr.utt.if26.myapplication.navigation_feature.domain.model;

import android.location.Location;

import java.util.List;

public class Route {
    private List<Location> path;
    private String duration;
    private String distance;

    public Route(List<Location> path, String duration, String distance) {
        this.path = path;
        this.duration = duration;
        this.distance = distance;
    }

    public List<Location> getPath() {
        return path;
    }

    public String getDuration() {
        return duration;
    }

    public String getDistance() {
        return distance;
    }
}

