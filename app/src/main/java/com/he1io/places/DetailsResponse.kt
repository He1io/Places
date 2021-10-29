package com.he1io.places

data class DetailsResponse(
    val response: DetailsResponseObject
)

data class DetailsResponseObject(
    val venue: PlaceDetails
)