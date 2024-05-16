package com.salmakhd.android.arsastyleadmin.screen.navigation

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.PhoneInTalk
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.BugReport
import androidx.compose.material.icons.outlined.FactCheck
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.salmakhd.android.arsastyleadmin.ui.designsystem.AllSizeDevicePreview
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaDropDownMenuState
import com.salmakhd.android.arsastyleadmin.ui.theme.ArsaStyleAdminTheme
import kotlinx.coroutines.launch

sealed class ArsaAppDestination(val route: String) {
    data object RootGraph: ArsaAppDestination(route="ROOT_GRAPH_DESTINATION")
    data object LoginGraph: ArsaAppDestination(route= "LOGIN_GRAPH_DESTINATION")
    data object CustomerGraph: ArsaAppDestination(route= "CUSTOMER_GRAPH_DESTINATION")
    data object StylistGraph: ArsaAppDestination(route = "STYLIST_GRAPH_DESTINATION")
    data object AdminGraphDestination: ArsaAppDestination(route="ADMIN_GRAPH_DESTINATION")
}

sealed class ArsaBottomBarItem(
    val route: String,
    val title: String,
    val imageVector: ImageVector
) {
    // for Customer
    data object ActiveUsers: ArsaBottomBarItem(
        route = AdminGraphDestination.ActiveUsersScreen.route,
        title = "کاربران فعال",
        imageVector = Icons.Outlined.AccountCircle
    )

    data object AdminWallet: ArsaBottomBarItem(
        route = AdminGraphDestination.Wallet.route,
        title = "حساب مالی",
        imageVector = Icons.Filled.Money
    )
    data object AdminUserVerification: ArsaBottomBarItem(
        route = AdminGraphDestination.Root.route,
        title = "تاییدیه",
        imageVector = Icons.Outlined.FactCheck
    )

    data object AdminUserSupport: ArsaBottomBarItem(
        route = AdminGraphDestination.UserSupportScreen.route,
        title = "رسیدگی",
        imageVector = Icons.Outlined.BugReport
    )
}

sealed class ArsaDrawerItem(
    val route: String,
    val title: String,
    val imageVector: ImageVector
) {
    object ContactUs: ArsaBottomBarItem(
        route = AdminGraphDestination.AboutUsScreen.route,
        title = "پشتیبانی",
        imageVector = Icons.Filled.PhoneInTalk
    )
    object AboutUs: ArsaBottomBarItem(
        route = AdminGraphDestination.AboutUsScreen.route,
        title = "درباره ما",
        imageVector = Icons.Filled.PhoneInTalk
    )
    object AppGuide: ArsaBottomBarItem(
        route = AdminGraphDestination.AboutUsScreen.route,
        title = "راهنمای استفاده از برنامه",
        imageVector = Icons.Filled.PhoneInTalk
    )
    object QAndA: ArsaBottomBarItem(
        route = AdminGraphDestination.AboutUsScreen.route,
        title = "سوالات متداول",
        imageVector = Icons.Filled.PhoneInTalk
    )
    object AppUpdate: ArsaBottomBarItem(
        route = AdminGraphDestination.AboutUsScreen.route,
        title = "نسخه و بروزرسانی",
        imageVector = Icons.Filled.PhoneInTalk
    )
    // can easily add other icons
}

@Composable
fun ArsaApp(
    bottomBarItems: List<ArsaBottomBarItem>,
    topBarActions: @Composable RowScope.() -> Unit = {},
    navController: NavHostController,
    content: @Composable (NavHostController, Modifier) -> Unit = {_,_->},
) {
    val snackbarHostState = remember { SnackbarHostState() }
    var title by remember {
        mutableStateOf("")
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val drawerItems = listOf(
        ArsaDrawerItem.ContactUs,
        ArsaDrawerItem.AboutUs,
        ArsaDrawerItem.AppGuide,
        ArsaDrawerItem.QAndA,
        ArsaDrawerItem.AppUpdate,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val isScaffoldVisible = (navBackStackEntry?.destination?.route) in (bottomBarItems.map{it.route})
    if(isScaffoldVisible) {
        ArsaProfileDrawer(
            drawerState = drawerState,
            drawerItems = drawerItems,
            navController = navController,
            screenContent = {
                Scaffold(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentWindowInsets = WindowInsets(0, 0, 0, 0),
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    },
                    bottomBar = {
                        ArsaBottomBar(
                            navController = navController,
                            screens = bottomBarItems
                        )
                    },
                    topBar = {
                        ArsaTopBar(
                            title = title, onAskUsButtonClicked = {
                                scope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            },
                            actions = topBarActions,
                        )
                    }
                ) {
                    content(
                        navController,
                        Modifier
                            .fillMaxSize()
                            .padding(it),
                    )
                    // topBarViewModel)
                }
            }
        )
    } else {
        content(
            navController,
            Modifier
                .fillMaxSize(),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArsaTopBar(
    modifier:Modifier = Modifier,
    title: String,
    onAskUsButtonClicked: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    CenterAlignedTopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = { onAskUsButtonClicked() }) {
                Icon(
                    imageVector = Icons.Filled.QuestionMark,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        actions = actions,
        scrollBehavior = scrollBehavior,
    )
}

@Composable
fun NotificationIconWithBadge(
    modifier: Modifier = Modifier,
    numberOfNotifications: Int,
    onClick: () -> Unit = {}
) {
    Box(modifier = modifier.padding(5.dp)) {
        val tertiaryColor = MaterialTheme.colorScheme.tertiary

        //IconButton(modifier= Modifier,onClick = { onClick() }) {
        Icon(
            modifier = Modifier
                .padding(top = 5.dp)
                .clickable { onClick() },
            imageVector = Icons.Filled.NotificationsActive,
            contentDescription = "Localized description",
            tint = MaterialTheme.colorScheme.primary
        )
        // }
        if(numberOfNotifications != 0) {
            Text(text = numberOfNotifications.toString(),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .drawWithContent {
                        drawCircle(
                            tertiaryColor,
                            radius = 7.dp.toPx(),
                            // This is based on the dimensions of the NavigationBar's "indicator pill";
                            // however, its parameters are private, so we must depend on them implicitly
                            // (NavigationBarTokens.ActiveIndicatorWidth = 64.dp)
                            center = Offset(center.x, center.y - 4.dp.toPx())
                        )
                        drawContent()
                    }
                    .padding(bottom = 5.dp),
                style = MaterialTheme.typography.titleSmall.copy(color = MaterialTheme.colorScheme.onPrimary))
        }
    }
}

/**
 * @param menuItems identifier: route, text: label
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun <T> ArsaMenu(
    menuItems: List<ArsaDropDownMenuState<T>>,
    modifier: Modifier = Modifier,
    onItemSelected: (T) -> Unit = {},
) {
    var isMenuExpanded by remember {
        mutableStateOf(false)
    }

    IconButton(onClick = {isMenuExpanded = !isMenuExpanded}) {
        Icon(
            modifier = Modifier,
            imageVector = Icons.Filled.MoreHoriz,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
    }

    DropdownMenu(
        expanded = isMenuExpanded,
        onDismissRequest = { isMenuExpanded = false },
        modifier = modifier
            .background(MaterialTheme.colorScheme.secondary, shape = MaterialTheme.shapes.medium)
            .clip(MaterialTheme.shapes.medium),
        properties = PopupProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        menuItems.forEach { item ->
            DropdownMenuItem(onClick = {
                onItemSelected(item.identifier)
            },text =  {
                Text(text = item.text, style = MaterialTheme.typography.titleMedium)
            })
        }
    }
}

@AllSizeDevicePreview
@Composable
fun ArsaMenuPreview() {
    ArsaStyleAdminTheme {
        ArsaMenu(
            menuItems = listOf(
                ArsaDropDownMenuState(
                    identifier = AdminGraphDestination.AboutUsScreen.route,
                    text = "افزودن خدمات"
                ),
                ArsaDropDownMenuState(
                    identifier = AdminGraphDestination.AboutUsScreen.route,
                    text = "تعیین وقت فعالیت"
                ),
                ArsaDropDownMenuState(
                    identifier = AdminGraphDestination.AboutUsScreen.route,
                    text = "خاموش کردن حساب"
                ),
            ),
            onItemSelected = {}
        )
    }
}

//@Preview
@Composable
fun NotificationIconWithBadgePreview() {
    ArsaStyleAdminTheme {
        NotificationIconWithBadge(numberOfNotifications = 2)
    }
}

@Composable
fun ArsaBottomBar(
    navController: NavHostController,
    screens: List<ArsaBottomBarItem>
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

//    val bottomBarShouldExist = !screens.any { it.route == currentDestination?.route }
//
//    if (bottomBarShouldExist) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        //   contentColor = MaterialTheme.colorScheme.tertiary,
        tonalElevation = 10.dp,
        contentPadding = PaddingValues(5.dp)
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
//            }
            /*
    AddItem(
        screen = screen,
        currentDestination = currentDestination,
        navController = navController
    )
     */
        }
    }
}

@Composable
fun RowScope.AddItem(
    modifier: Modifier = Modifier,
    screen: ArsaBottomBarItem,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    var iconSize by remember { mutableStateOf(24.dp) }
    LaunchedEffect(navController) {
        if(currentDestination?.hierarchy?.any {
                it.route == screen.route
            } == true) { 30.dp } else {24.dp}
    }

    BottomNavigationItem(
        modifier = modifier
            .weight(1f)
            .align(Alignment.CenterVertically)
            .border(
                width = 2.dp,
                if (currentDestination?.hierarchy?.any {
                        it.route == screen.route
                    } == true) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer,
                shape = CircleShape
            ),
        selectedContentColor = MaterialTheme.colorScheme.primary,
        unselectedContentColor = MaterialTheme.colorScheme.primary,
        label = {
            Text(
                modifier = Modifier.padding(top = 5.dp),
                text = screen.title,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSecondary
            )
        },
        icon = {
            IconButton(
                modifier = Modifier
                    .animateContentSize()
                    .size(iconSize),
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                }) {
                Icon(
                    imageVector = screen.imageVector,
                    contentDescription = "Localized description",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}

@Composable
fun ArsaProfileDrawer(
    modifier: Modifier = Modifier,
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Open),
    drawerItems: List<ArsaBottomBarItem> = emptyList(),
    navController: NavHostController,
    screenContent: @Composable () -> Unit = {}
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    ModalNavigationDrawer(
        drawerState = drawerState,
        modifier = modifier,
        drawerContent = {
            ModalDrawerSheet {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "راهنما",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
                Divider(color = MaterialTheme.colorScheme.tertiary)
                for(item in drawerItems) {
                    NavigationDrawerItem(
                        label = {
                            Text(text = item.title, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onPrimary)
                        },
                        selected =  currentDestination?.hierarchy?.any {
                            it.route == item.route
                        } == true,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) {
        screenContent()
    }
}

//@SmallDevicePreview
@Composable
fun ArsaProfileDrawerPreview() {
    ArsaStyleAdminTheme {
        //ArsaProfileDrawer(drawerState = rememberDrawerState(initialValue = DrawerValue.Open))
    }
}

//@SmallDevicePreview
@Composable
fun TopBarPreview() {
    ArsaStyleAdminTheme {
        ArsaTopBar(title = "")
    }
}

//@SmallDevicePreview
@Composable
fun BottomBarPreview() {
    ArsaStyleAdminTheme {
        // ArsaBottomBar()
    }
}

//@SmallDevicePreview
@Composable
fun ArsaAppPreview() {
    ArsaStyleAdminTheme {
        //ArsaApp()
    }
}


