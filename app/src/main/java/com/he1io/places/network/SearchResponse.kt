package com.he1io.places.network

import com.he1io.places.model.Place

/* This class has the same structure as the JSON given by the Foursquare API venues search endpoint "venues/search":
    "response": {
        "venues": [
            {
                ...
            }
        ]
    }
 */

data class SearchResponse(
    val response: SearchResponseObject
)

data class SearchResponseObject(
    val venues: List<Place>
)