package com.he1io.places

import androidx.lifecycle.ViewModel

class PlaceViewModel : ViewModel() {

    suspend fun getNearPlaces(
        location: String
    ): List<Place> {

        return FoursquareApi.retrofitService.getNearPlaces(
            location
        ).response.venues
    }

    suspend fun getPlaceById(
        placeId: String
    ): PlaceDetails {

        return FoursquareApi.retrofitService.getPlaceById(
            placeId
        ).response.venue
    }
}
