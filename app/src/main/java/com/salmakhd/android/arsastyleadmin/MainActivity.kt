package com.salmakhd.android.arsastyleadmin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.salmakhd.android.arsastyleadmin.classes.MySharedPreference
import com.salmakhd.android.arsastyleadmin.screen.navigation.RootGraph
import com.salmakhd.android.arsastyleadmin.ui.theme.ArsaStyleAdminTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var mySharedPreference: MySharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ArsaStyleAdminTheme {
                RootGraph(
                    isUserLoggedIn = mySharedPreference.getLogin(),
                    navController = rememberNavController()
                )
            }
        }
    }
}