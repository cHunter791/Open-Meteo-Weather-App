package com.chunter.openmeteoweather.features.locationsearch.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.chunter.openmeteoweather.R
import com.chunter.openmeteoweather.core.ui.theme.LocalDimensions
import com.chunter.openmeteoweather.core.ui.theme.OpenMeteoWeatherTheme

@Composable
fun LocationSearchField(
    modifier: Modifier = Modifier,
    location: String,
    onSearchSubmitted: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    var textFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                text = location,
                selection = TextRange(location.length)
            )
        )
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(LocalDimensions.current.searchFieldDimensions.height),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        TextField(
            modifier = Modifier.weight(3f),
            value = textFieldValue,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                focusManager.clearFocus()
                onSearchSubmitted(textFieldValue.text)
            }),
            singleLine = true,
            onValueChange = { value -> textFieldValue = value },
        )
        Button(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            onClick = {
                focusManager.clearFocus()
                onSearchSubmitted(textFieldValue.text)
            }
        ) {
            Text(text = stringResource(id = R.string.button_label_submit))
        }
    }
}

@Preview
@Composable
private fun LocationSearchFieldPreview() {
    OpenMeteoWeatherTheme {
        LocationSearchField(
            location = "Donegall Square N, Belfast BT1 5GS",
            onSearchSubmitted = {}
        )
    }
}