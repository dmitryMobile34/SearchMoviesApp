package com.example.filmplotonfragmentstest.api

import com.example.filmplotonfragmentstest.datamodel.Film
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {
//    @GET("?t=Pulp+Fiction&apikey=1982275d")
    @GET("?apikey=1982275d")
    fun getMovies(@Query("t") t: String) : Call<Film>

    companion object {

        var baseUrl = "https://www.omdbapi.com/"

        fun create() : RetrofitInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()

            return retrofit.create(RetrofitInterface::class.java)

        }
    }

}