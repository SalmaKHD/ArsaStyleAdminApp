package com.salmakhd.android.arsastyleadmin.screen.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.salmakhd.android.arsastyleadmin.screen.routes.LoginRoute

@Composable
fun LoginGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    //topAppBarViewModel: TopBarViewModel
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        route = ArsaAppDestination.LoginGraph.route,
        // show app logo screen first always
        startDestination = LoginGraphDestination.Root.route
    ) {
        // other nested graphs
        composable(
            route = LoginGraphDestination.Root.route
        ) {
            LoginRoute(
                navigateToDashboardScreen = {
                    navController.navigate(ArsaAppDestination.AdminGraphDestination.route) {
                        popUpTo(LoginGraphDestination.Root.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(route = ArsaAppDestination.AdminGraphDestination.route) {
            AdminGraph()
        }
    }
}

sealed class LoginGraphDestination(val route:String) {
    object Root: LoginGraphDestination(route = "LOGIN_GRAPH_LOGIN_OR_SIGN_UP_DESTINATION")
    object SignUpRoute: LoginGraphDestination(route = "SIGNUP_ROUTE")
}