package com.hantash.split_amount.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String,
    enabled: Boolean = true,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Next,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        modifier = modifier.padding(bottom = 10.dp, start = 10.dp, end = 10.dp),
        value = valueState.value,
        enabled = enabled,
        singleLine = isSingleLine,
        keyboardActions = keyboardActions,
        onValueChange = {
            valueState.value = it
        },
        label = {
            Text(text = labelId)
        },
        leadingIcon = {
            Icon(imageVector = Icons.Rounded.AttachMoney, contentDescription = "Money Icon")
        },
        textStyle = TextStyle(
            fontSize = 18.sp, color = Color.Black
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType, imeAction = imeAction
        )
    )
}