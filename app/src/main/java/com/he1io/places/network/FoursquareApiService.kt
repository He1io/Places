package com.he1io.places.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.foursquare.com/v2/"
private const val foursquareClientId = "GHDKFXUZUQPHIIWUSXOPSWM2XN4IYTS3BBLIEFKXZMBB50DC"
private const val foursquareClientSecret = "LKCSPMOFFT2UBE3JVYJ3IFFRYMATAXFCLSUFYFCDRLQTSHNJ"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface FoursquareApiService {
    @GET("venues/search")
    suspend fun getNearPlaces(
        @Query("near") near: String,
        @Query("v") v: String,
        @Query("client_id") clientId: String = foursquareClientId,
        @Query("client_secret") clientSecret: String = foursquareClientSecret,
        // I'm limiting this API request because it's Premium (50 requests/day)
        @Query("limit") limit: String = "2"
    ): SearchResponse

    @GET("venues/{venue_id}")
    suspend fun getPlaceById(
        @Path("venue_id") venueId: String,
        @Query("v") v: String,
        @Query("client_id") clientId: String = foursquareClientId,
        @Query("client_secret") clientSecret: String = foursquareClientSecret
    ): DetailsResponse
}

// Singleton Object - there will only be one instance of this object
object FoursquareApi {
    val retrofitService: FoursquareApiService by lazy {
        retrofit.create(FoursquareApiService::class.java)
    }
}