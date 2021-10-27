package com.he1io.places

data class FoursquareResponse(
    val response: ResponseObject
)

data class ResponseObject(
    val venues: List<Place>
)