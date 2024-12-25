package com.hantash.split_amount

import android.os.Bundle
import android.util.Log
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hantash.split_amount.components.InputField
import com.hantash.split_amount.ui.theme.SplitamountcomposeTheme
import com.hantash.split_amount.widgets.RoundIconButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp {
                MainContent()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    SplitamountcomposeTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            content()
        }
    }
}

@Preview
@Composable
fun MainContent() {
    Column {
        AmountContent()
        BillForm { bill ->
            Log.d("app-debug", "Bill: $bill")
        }
    }
}

@Composable
fun AmountContent(perPersonAmount: Double = 0.0) {
    Surface(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 8.dp)
            .fillMaxWidth()
            .height(150.dp)
            .clip(shape = RoundedCornerShape(corner = CornerSize(8.dp))),
        color = Color(color = 0xFFE9D7F7)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Total Per Person",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "$$perPersonAmount",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun BillForm(
    onValueChange: (String) -> Unit
) {
    val totalBillState = remember {
        mutableStateOf("")
    }
    val validationState = remember(totalBillState.value) {
        totalBillState.value.trim().isNotEmpty()
    }
    val sliderPositionState = remember {
        mutableFloatStateOf(0f)
    }

    Surface(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(width = 1.dp, color = Color.LightGray),
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            InputField(
                modifier = Modifier.fillMaxWidth(),
                valueState = totalBillState,
                labelId = "Enter Bill",
                keyboardActions = KeyboardActions {
                    if (!validationState) return@KeyboardActions

                    onValueChange(totalBillState.value.trim())
                }
            )

            if (true) {
                //Split
                Row(
                    modifier = Modifier.padding(4.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        modifier = Modifier.align(alignment = Alignment.CenterVertically),
                        text = "Split",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Spacer(modifier = Modifier.width(120.dp))
                    Row (
                        modifier = Modifier.padding(horizontal = 4.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        RoundIconButton(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            onClick = {

                            }
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 8.dp, end = 8.dp)
                                .align(alignment = Alignment.CenterVertically),
                            text = "1",
                        )
                        RoundIconButton(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            onClick = {

                            }
                        )
                    }
                }

                //Tip
                Row (
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 16.dp)
                ) {
                    Text(
                        modifier = Modifier.align(alignment = Alignment.CenterVertically),
                        text = "Tip",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.width(170.dp))
                    Text(
                        modifier = Modifier.align(alignment = Alignment.CenterVertically),
                        text = "$30.55",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                //Tip Slider
                Column(
                    modifier = Modifier.padding(all = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "$30.55",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Slider(
                        value = sliderPositionState.floatValue,
                        steps = 5,
                        valueRange = 0f..100f,
                        onValueChange = {
                            sliderPositionState.floatValue = it
                        },
                        onValueChangeFinished = {

                        }
                    )
                }
            }
        }
    }
}
















