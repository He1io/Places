package com.he1io.places.model

// Class to parse the JSON given by the FoursquareApi
data class Place(
    val id: String,
    val name: String,
    val description: String = "",
    val categories: List<PlaceCategory>,
    val location: PlaceLocation,
    val hereNow: PlaceHereNow = PlaceHereNow(0,"")
){
    data class PlaceCategory(
        val id: String,
        val name: String,
        val primary: Boolean = true,
        val icon: CategoryIcon
    ){

        data class CategoryIcon(
            val prefix: String = "",
            val suffix: String = ""
        )
    }

    data class PlaceLocation(
        val distance: Int = 0,
        val lat: Double = 0.0,
        val lng: Double = 0.0
    )

    data class PlaceHereNow(
        val count: Int,
        val summary: String
    )
}


