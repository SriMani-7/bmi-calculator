@file:OptIn(ExperimentalMaterial3Api::class)

package srimani7.apps.bmi.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.core.view.WindowCompat
import srimani7.apps.bmi.calculator.ui.App
import srimani7.apps.bmi.calculator.ui.theme.BmiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var useDarkTheme by remember { mutableStateOf(false) }

            BmiTheme(useDarkTheme = useDarkTheme) {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text("BMI Calculator", fontWeight = FontWeight.Bold)
                            },
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.onTertiaryContainer
                            ),
                            actions = {
                                IconToggleButton(checked = useDarkTheme, onCheckedChange = { useDarkTheme = it }) {
                                    val res = if (useDarkTheme) R.drawable.baseline_light_mode_24 else R.drawable.baseline_dark_mode_24
                                    Icon(painter = painterResource(res), contentDescription = "Theme")
                                }
                            }
                        )
                    },
                ) { paddingValues ->
                    App(modifier = Modifier.padding(paddingValues))
                }
            }
        }

        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}
