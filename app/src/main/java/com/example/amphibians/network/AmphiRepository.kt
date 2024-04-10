package com.example.amphibians.network

import com.example.amphibians.model.AmphibiansData

interface AmphiRepository{
    suspend fun getDataAmphi():List<AmphibiansData>
}

class NetworkAmphiRepository(private val amphiApiService:AmphibiansApiService): AmphiRepository{
    override suspend fun getDataAmphi(): List<AmphibiansData>  = amphiApiService.getData()
}