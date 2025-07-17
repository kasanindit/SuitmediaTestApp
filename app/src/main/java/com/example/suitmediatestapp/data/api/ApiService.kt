package com.example.suitmediatestapp.data.api

import com.example.suitmediatestapp.data.response.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/users")
    suspend fun getUsers(
        @Query ("page") page: Int
    ) : Response

}