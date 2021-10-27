package com.he1io.places

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.foursquare.com/v2/"

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
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("near") near: String,
        @Query("v") v: String = "20211027"
    ): FoursquareResponse
}

// Singleton Object - there will only be one instance of this object, saving and optimizing resources
object FoursquareApi {
    val retrofitService: FoursquareApiService by lazy {
        retrofit.create(FoursquareApiService::class.java)
    }
}