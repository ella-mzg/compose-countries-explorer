package com.ellamzoughi.restcountriesexplorer.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ellamzoughi.restcountriesexplorer.model.CountryBean
import com.ellamzoughi.restcountriesexplorer.api.RESTCountriesAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call

class MainViewModel : ViewModel() {
    val countryList = mutableStateListOf<CountryBean>()
    var runInProgress = mutableStateOf(false)
    var errorMessage = mutableStateOf("")

    private val api = RESTCountriesAPI.create()

    fun loadAllCountries() {
        fetchCountriesFromCall(api.getAllCountries())
    }

    fun searchCountryByName(name: String) {
        runInProgress.value = true
        errorMessage.value = ""

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = api.getCountryByName(name, false).execute()
                if (response.isSuccessful) {
                    val countries = response.body() ?: emptyList()
                    countryList.clear()
                    if (countries.isEmpty()) {
                        errorMessage.value = "No results found. Check the spelling or try another search term."
                    } else {
                        countryList.addAll(countries)
                    }
                } else {
                    handleErrors(response.code())
                }
            } catch (e: Exception) {
                errorMessage.value = "Check your internet connection and try again."
            } finally {
                runInProgress.value = false
            }
        }
    }

    private fun fetchCountriesFromCall(call: Call<List<CountryBean>>) {
        runInProgress.value = true
        errorMessage.value = ""

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = call.execute()
                if (response.isSuccessful) {
                    val countries = response.body() ?: emptyList()
                    countryList.clear()
                    countryList.addAll(countries)
                } else {
                    handleErrors(response.code())
                }
            } catch (e: Exception) {
                errorMessage.value = "Check your internet connection and try again."
            } finally {
                runInProgress.value = false
            }
        }
    }

    private fun handleErrors(code: Int) {
        when (code) {
            404 -> errorMessage.value = "No countries found. Check the spelling and try again."
            500 -> errorMessage.value = "Server error. Please try again later."
            else -> errorMessage.value = "Unexpected error occurred. Error code: $code"
        }
    }
}
