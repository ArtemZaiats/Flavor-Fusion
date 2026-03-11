package com.flavorfusion.flavorfusion.main

import android.os.Bundle
import android.graphics.Color
import android.widget.Toast
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flavorfusion.common_ui.compose.EffectHandler
import com.flavorfusion.common_ui.theme.FlavorFusionTheme
import com.flavorfusion.flavorfusion.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
        )
        setContent {
            val state = viewModel.state.collectAsStateWithLifecycle().value

            EffectHandler(viewModel = viewModel) {
                when (it) {
                    is MainContract.Effect.ShowErrorDialog -> {
                        Toast.makeText(
                            this@MainActivity,
                            "Error! ${state.errorMessage}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            FlavorFusionTheme(appTheme = state.appTheme.theme.type) {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AppNavigation()
                }
            }
        }
    }
}