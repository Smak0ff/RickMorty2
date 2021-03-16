package com.example.rickmorty2.retrofit

import com.example.rickmorty2.model.Character
import com.example.rickmorty2.model.Episode
import com.example.rickmorty2.model.Results
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("api/character/")
    fun getAllCharacter(@Query("page") page: Int): Call<Character>
}