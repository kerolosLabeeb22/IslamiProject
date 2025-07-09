package com.example.islami_project.api

import com.example.islami_project.api.model.RadioResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("radios")
    fun getRadio(): Call<RadioResponse>
}