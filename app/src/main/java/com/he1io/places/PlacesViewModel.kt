package com.he1io.places

import androidx.lifecycle.ViewModel

class PlacesViewModel : ViewModel() {

    suspend fun getNearPlaces(
        clientId: String,
        clientSecret: String,
        location: String
    ): List<Place> {

        return FoursquareApi.retrofitService.getNearPlaces(
            clientId,
            clientSecret,
            location
        ).response.venues
    }
}
