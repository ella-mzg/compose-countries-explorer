package com.ellamzoughi.restcountriesexplorer.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ellamzoughi.restcountriesexplorer.ui.screens.DetailScreen
import com.ellamzoughi.restcountriesexplorer.ui.screens.SearchScreen
import com.ellamzoughi.restcountriesexplorer.viewmodel.MainViewModel

@Composable
fun Navigation() {
    val navController: NavHostController = rememberNavController()
    val mainViewModel = MainViewModel()

    NavHost(navController = navController, startDestination = "search") {
        composable("search") {
            SearchScreen(mainViewModel, navController)
        }
        composable("detail/{countryIndex}") { backStackEntry ->
            val countryIndex = backStackEntry.arguments?.getString("countryIndex")?.toInt() ?: 0
            if (countryIndex >= 0 && countryIndex < mainViewModel.countryList.size) {
                DetailScreen(country = mainViewModel.countryList[countryIndex], navController = navController)
            } else {
                Text("Country details not available")
            }
        }
    }
}