package com.swacademy.mapcommunity.domain.vo;

public record Position(
        double latitude,
        double longitude
) {
    public static final double MAX_LATITUDE = 90d;
    public static final double MIN_LATITUDE = -90d;
    public static final double MAX_LONGITUDE = 180d;
    public static final double MIN_LONGITUDE = -180d;

    public Position {
        if (latitude < MIN_LATITUDE || latitude > MAX_LATITUDE)
            throw new IllegalArgumentException("Latitude value should be in range %s to %s.".formatted(MIN_LATITUDE, MAX_LATITUDE));
        if (longitude < MIN_LONGITUDE || longitude > MAX_LONGITUDE)
            throw new IllegalArgumentException("Longitude value should be in range %s to %s.".formatted(MIN_LONGITUDE, MAX_LONGITUDE));
    }
}