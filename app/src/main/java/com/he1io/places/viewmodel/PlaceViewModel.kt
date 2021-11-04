package com.he1io.places.viewmodel

import androidx.lifecycle.ViewModel
import com.he1io.places.model.Place
import com.he1io.places.network.FoursquareApi
import java.text.SimpleDateFormat
import java.util.*

class PlaceViewModel : ViewModel() {

    private val foursquareApiVersion: String = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(Date())

    suspend fun getNearPlaces(
        location: String
    ): List<Place> {

        val placesList = FoursquareApi.retrofitService.getNearPlaces(
            location,
            foursquareApiVersion
        ).response.venues
        // Get details for every place retrieved
        val placesDetailsList = mutableListOf<Place>()
        for(place in placesList){
            placesDetailsList.add(getPlaceById(place.id))
        }

        return placesDetailsList
    }

    suspend fun getPlaceById(
        placeId: String
    ): Place {

        return FoursquareApi.retrofitService.getPlaceById(
            placeId,
            foursquareApiVersion
        ).response.venue
    }
}
