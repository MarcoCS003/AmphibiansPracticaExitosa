package com.example.amphibians.data

import com.example.amphibians.model.AmphibiansData
import com.example.amphibians.network.AmphiRepository
import com.example.amphibians.network.AmphibiansApiService
import com.example.amphibians.network.NetworkAmphiRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

interface AppContainer {
    val amphibianDataRepository : AmphiRepository
}

class DefaultAppContainer : AppContainer{

    private val BASEURL =
        "https://android-kotlin-fun-mars-server.appspot.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASEURL)
        .build()


    private val retrofitService : AmphibiansApiService by lazy {
        retrofit.create(AmphibiansApiService::class.java)
    }


    override val amphibianDataRepository: AmphiRepository by lazy {
        NetworkAmphiRepository(retrofitService)
    }
}