package com.salmakhd.android.arsastyleadmin.screen.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.salmakhd.android.arsastyleadmin.common.model.ArsaOperationStatus

@Composable
fun RootGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    //topAppBarViewModel: TopBarViewModel,
    isUserLoggedIn:Boolean = false
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        route = ArsaAppDestination.RootGraph.route,
        // show app logo screen first always
        startDestination = "SPLASH_SCREEN"
    ) {
        composable(route = "SPLASH_SCREEN") {
            LaunchedEffect(Unit) {
                if(isUserLoggedIn) {
                    navController.navigate(ArsaAppDestination.AdminGraphDestination.route)
                } else {
                    navController.navigate(ArsaAppDestination.LoginGraph.route)
                }
            }
        }
        composable(route = ArsaAppDestination.LoginGraph.route) {
            LoginGraph()
        }
        composable(route = ArsaAppDestination.AdminGraphDestination.route) {
            AdminGraph()
        }
    }
}