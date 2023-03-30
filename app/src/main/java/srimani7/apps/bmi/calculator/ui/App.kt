@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package srimani7.apps.bmi.calculator.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import srimani7.apps.bmi.calculator.BmiViewModel
import srimani7.apps.bmi.calculator.format
import srimani7.apps.bmi.calculator.ui.theme.BmiTheme

@Composable
fun App(viewModel: BmiViewModel = viewModel()) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("BMI Calculator", fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onTertiaryContainer
                )
            )
        },
        //contentWindowInsets = WindowInsets.ime,
        modifier = Modifier.animateContentSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = viewModel.bmi.format(2),
                    style = MaterialTheme.typography.headlineSmall,
                )
                Divider(modifier = Modifier.fillMaxWidth(.7f), thickness = 2.5.dp)
                Text(text = viewModel.message, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.secondary)
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ModeSelector(viewModel.selectedMode, updateMode = viewModel::updateMode)
                CustomTextField(viewModel.heightState,ImeAction.Next, viewModel::updateHeight)
                CustomTextField(viewModel.weightState,ImeAction.Done, viewModel::updateWeight)
            }
            Spacer(modifier = Modifier)

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .padding(16.dp, 12.dp)
                    .fillMaxWidth(),
            ) {
                ActionButton(text = "Clear", viewModel::clear)
                ActionButton(text = "Calculate", viewModel::calculate)
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

@Composable
fun RowScope.ActionButton(text: String, onClick: () -> Unit) {
    val focusManager = LocalFocusManager.current
    Button(
        onClick = { focusManager.clearFocus(); onClick() },
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.weight(1f),
        contentPadding = PaddingValues(18.dp)
    ) {
        Text(text)
    }
}

@Preview(showSystemUi = true)
@Composable
fun AppPreview() {
    BmiTheme {
        App()
    }
}