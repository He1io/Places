package com.he1io.places.network

import com.he1io.places.model.Place

/* This class has the same structure as the JSON given by the Foursquare API venues details endpoint "venues/{venue_id}":
    "response": {
        "venue": {
            ...
        }
    }
 */

data class DetailsResponse(
    val response: DetailsResponseObject
)

data class DetailsResponseObject(
    val venue: Place
)