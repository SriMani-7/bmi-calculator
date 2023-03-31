package srimani7.apps.bmi.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import srimani7.apps.bmi.calculator.ui.App
import srimani7.apps.bmi.calculator.ui.theme.BmiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BmiTheme {
                App()
            }
        }

        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}
