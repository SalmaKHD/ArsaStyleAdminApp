package com.salmakhd.android.arsastyleadmin.screen.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.salmakhd.android.arsastyleadmin.screen.routes.ActiveUsersScreen
import com.salmakhd.android.arsastyleadmin.screen.routes.UserSupportScreen
import com.salmakhd.android.arsastyleadmin.screen.routes.UserVerificationScreen
import com.salmakhd.android.arsastyleadmin.screen.routes.WalletScreen

@Composable
fun AdminGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    // topAppBarViewModel: TopBarViewModel,
) {
    //  val notifications by topAppBarViewModel.notificationSharedFlow.collectAsState(initial = emptyList())

    ArsaApp(
        navController = navController,
        bottomBarItems = listOf(
            ArsaBottomBarItem.ActiveUsers,
            ArsaBottomBarItem.AdminWallet,
            ArsaBottomBarItem.AdminUserVerification,
            ArsaBottomBarItem.AdminUserSupport
        ),
        topBarActions = {
//            NotificationIconWithBadge(
//                numberOfNotifications = notifications.size,
//                onClick = {navController.navigate( CustomerGraphDestination.NotificationScreen.route)}
//            )
        },
        content = { navHostController, modifier ->
            NavHost(
                modifier = modifier,
                navController = navHostController,
                route = ArsaAppDestination.CustomerGraph.route,
                // show app logo screen first always
                startDestination = AdminGraphDestination.Root.route
            ) {
                composable(route = AdminGraphDestination.Root.route) {
                    UserVerificationScreen()
                }

                composable(route = AdminGraphDestination.Wallet.route) {
                    WalletScreen()
                }

                composable(route = AdminGraphDestination.UserSupportScreen.route) {
                    UserSupportScreen()
                }

                composable(route = AdminGraphDestination.ActiveUsersScreen.route) {
                    ActiveUsersScreen()
                }
            }
        }
    )
}

sealed class AdminGraphDestination(val route: String) {
    data object Root: AdminGraphDestination(route = "ADMIN_GRAPH_DASHBOARD_DESTINATION")
    data object Wallet: AdminGraphDestination(route = "ADMIN_GRAPH_WALLET_DESTINATION")
    data object UserSupportScreen: AdminGraphDestination(route = "ADMIN_GRAPH_USER_SUPPORT")
    data object ActiveUsersScreen: AdminGraphDestination(route = "ADMIN_GRAPH_ACTIVE_USERS_SUPPORT")
    // drawer graph
    data object NotificationScreen: AdminGraphDestination(route = "ADMIN_GRAPH_NOTIFICATION_SCREEN_ROUTE")
    data object FAQScreen: AdminGraphDestination(route = "ADMIN_FAQ_SCREEN")
    data object AboutUsScreen: AdminGraphDestination(route = "ADMIN_ABOUT_US_SCREEN")
    data object VersionAndUpdateScreen: AdminGraphDestination(route = "ADMIN_VERSION_AND_UPDATE_SCREEN")
}