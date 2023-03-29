@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package srimani7.apps.dailyui.bmi

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import srimani7.apps.dailyui.format
import srimani7.apps.dailyui.ui.theme.DailyUITheme

@Composable
fun BmiCalculator(viewModel: BmiViewModel = viewModel()) {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "BMI Calculator",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Spacer(modifier = Modifier.weight(.4f))
        Text(
            text = viewModel.bmi.format(2),
            style = MaterialTheme.typography.headlineSmall,
        )

        Divider(modifier = Modifier.fillMaxWidth(.7f), thickness = 2.dp)
        Text(text = viewModel.message, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.secondary)
        Spacer(modifier = Modifier.height(8.dp))
        ModeSelector(viewModel.selectedMode, updateMode = viewModel::updateMode)
        CustomTextField(viewModel.heightState,ImeAction.Next, viewModel::updateHeight)
        CustomTextField(viewModel.weightState,ImeAction.Done, viewModel::updateWeight)

        Spacer(modifier = Modifier.weight(1f))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            TextButton(onClick = { focusManager.clearFocus(); viewModel.clear() }) {
                Text(text = "Clear")
            }
            Button(onClick = { focusManager.clearFocus(); viewModel.calculate() }) {
                Text(text = "Calculate")
            }
        }
    }
}

@Composable
private fun ModeSelector(selectedMode: BmiViewModel.Mode, updateMode: (BmiViewModel.Mode) -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        BmiViewModel.Mode.values().forEach {
            ElevatedFilterChip(selectedMode == it, onClick = { updateMode(it) },
                label = {
                    Text(it.name)
                }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun BmiPreview() {
    DailyUITheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.systemBarsPadding()) {
                BmiCalculator()
            }
        }
    }
}