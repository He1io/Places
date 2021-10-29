package com.he1io.places

data class SearchResponse(
    val response: SearchResponseObject
)

data class SearchResponseObject(
    val venues: List<Place>
)