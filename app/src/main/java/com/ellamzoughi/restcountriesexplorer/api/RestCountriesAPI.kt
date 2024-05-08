package com.ellamzoughi.restcountriesexplorer.api

import com.ellamzoughi.restcountriesexplorer.model.CountryBean
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RESTCountriesAPI {
    @GET("all")
    fun getAllCountries(): Call<List<CountryBean>>

    @GET("name/{name}")
    fun getCountryByName(@Path("name") name: String, @Query("fullText") fullText: Boolean = false): Call<List<CountryBean>>

    companion object {
        fun create(): RESTCountriesAPI {
            return Retrofit.Builder()
                .baseUrl("https://restcountries.com/v3.1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RESTCountriesAPI::class.java)
        }
    }
}