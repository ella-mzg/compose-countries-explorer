package com.ellamzoughi.restcountriesexplorer

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ellamzoughi.restcountriesexplorer.model.CountryBean
import com.ellamzoughi.restcountriesexplorer.model.RESTCountriesAPI
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

    fun loadIndependentCountries(status: Boolean = true) {
        fetchCountriesFromCall(api.getIndependentCountries(status))
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
                    errorMessage.value = "Error: ${response.code()} - ${response.message()}"
                }
            } catch (e: Exception) {
                errorMessage.value = "Exception: ${e.message}"
            } finally {
                runInProgress.value = false
            }
        }
    }
}