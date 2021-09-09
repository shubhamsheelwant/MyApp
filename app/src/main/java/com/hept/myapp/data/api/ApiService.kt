package com.hept.myapp.data.api


import com.example.myapp.FormResponse
import retrofit2.http.GET

interface ApiService {

    @GET("hep-test.json")
    suspend fun getUsers(): FormResponse

}