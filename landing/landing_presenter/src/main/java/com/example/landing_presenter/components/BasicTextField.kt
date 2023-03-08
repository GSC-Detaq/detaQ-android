package com.example.landing_presenter.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.utils.errors.ValidationError
import com.example.core_ui.ui.theme.DetaQTheme

@Composable
fun BasicTextField(
    label: String,
    text: String,
    onTextChange: (String) -> Unit,
    error: ValidationError?,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    OutlinedTextField(
        value = text,
        onValueChange = { newText ->
            onTextChange(newText)
        },
        label = { Text(text = label) },
        isError = error != null,
        trailingIcon = {
            error?.let {
                IconButton(
                    onClick = {  }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Error,
                        contentDescription = it.message ?: "Validation Error",
                        tint = MaterialTheme.colors.error
                    )
                }
            }
        },
        keyboardOptions = keyboardOptions,
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
    )

    error?.let {
        Text(
            text = it.message ?: "Validation Error",
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Start
        )
    }
}

@Preview
@Composable
fun BasicTextFieldPreview() {
    DetaQTheme {
        BasicTextField(
            label = "Email",
            text = "darrenthiores@gmail.com",
            onTextChange = {  },
            error = null
        )
    }
}