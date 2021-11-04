package com.he1io.places.model

// Class to parse the JSON given by the FoursquareApi
data class Place(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val categories: List<PlaceCategory> = listOf(PlaceCategory()),
    val location: PlaceLocation = PlaceLocation(),
    val hereNow: PlaceHereNow = PlaceHereNow(),
    val hours: PlaceHours = PlaceHours(),
    val rating: String = ""
) {
    data class PlaceCategory(
        val id: String = "",
        val name: String = "",
        val primary: Boolean = true,
        val icon: CategoryIcon = CategoryIcon()
    ) {

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
        val count: Int = 0,
        val summary: String = ""
    )

    data class PlaceHours(
        val status: String = "",
        val isOpen: Boolean = false,
        val timeframes: List<HoursTimeframe> = listOf(HoursTimeframe())
    ) {
        data class HoursTimeframe(
            val days: String = "",
            val open: List<TimeframeOpen> = listOf(TimeframeOpen())
        ) {
            data class TimeframeOpen(
                val renderedTime: String = ""
            )
        }


    }


}


