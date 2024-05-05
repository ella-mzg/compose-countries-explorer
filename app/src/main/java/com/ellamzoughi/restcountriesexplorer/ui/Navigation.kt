package com.ellamzoughi.restcountriesexplorer.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ellamzoughi.restcountriesexplorer.MainViewModel
import com.ellamzoughi.restcountriesexplorer.ui.screens.SearchScreen

@Composable
fun Navigation() {
    val navController: NavHostController = rememberNavController()
    val mainViewModel = MainViewModel()

    NavHost(navController = navController, startDestination = "search") {
        composable("search") {
            SearchScreen(mainViewModel)
        }
    }
}