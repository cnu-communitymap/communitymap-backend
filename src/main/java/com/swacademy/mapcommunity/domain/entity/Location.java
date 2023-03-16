package com.swacademy.mapcommunity.domain.entity;

/**
 * Value object that express coordinate.
 * @param latitude -90.0 <= latitude <= 90.0
 * @param longitude -180.0 <= longitude <= 180.0
 */
public record Location(
        double latitude,
        double longitude
) {
    public static final double MAX_LATITUDE = 90d;
    public static final double MIN_LATITUDE = -90d;
    public static final double MAX_LONGITUDE = 180d;
    public static final double MIN_LONGITUDE = -180d;

    public Location {
        if (latitude <= MIN_LATITUDE || latitude >= MAX_LATITUDE) throw new IllegalArgumentException(
                "Latitude value should be in range %s to %s.".formatted(MIN_LATITUDE, MAX_LATITUDE));
        if (longitude <= MIN_LONGITUDE || longitude >= MAX_LONGITUDE) throw new IllegalArgumentException(
                "Longitude value should be in range %s to %s.".formatted(MIN_LONGITUDE, MAX_LONGITUDE));
    }

}
