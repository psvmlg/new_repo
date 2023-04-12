package com.example.gridviewapp.retrofit

import com.example.gridviewapp.model.GridImage
import retrofit2.Response
import retrofit2.http.GET

interface ImageApi {

    @GET("/v2/list")
    suspend fun getImageList(): Response<List<GridImage>>
}