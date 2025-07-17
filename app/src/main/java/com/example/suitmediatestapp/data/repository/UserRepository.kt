package com.example.suitmediatestapp.data.repository

import com.example.suitmediatestapp.data.api.ApiService
import com.example.suitmediatestapp.data.response.Response

class UserRepository(private val apiService: ApiService) {
    suspend fun getUsers(page: Int): Response {
        return apiService.getUsers(page)
    }



}