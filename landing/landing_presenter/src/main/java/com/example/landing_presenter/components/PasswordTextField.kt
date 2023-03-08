package com.example.landing_presenter.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.utils.errors.ValidationError
import com.example.core_ui.ui.theme.DetaQTheme

@Composable
fun PasswordTextField(
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordVisible: Boolean,
    showPassword: () -> Unit,
    error: ValidationError?,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = password,
        onValueChange = { text ->
            onPasswordChange(text)
        },
        label = { Text(text = "Password") },
        isError = error != null,
        trailingIcon = {
            val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            val description = if (passwordVisible) "Hide password" else "Show password"

            Row {
                IconButton(
                    onClick = showPassword
                ){
                    Icon(
                        imageVector  = image,
                        contentDescription = description
                    )
                }

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
            }
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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
fun PasswordTextFieldPreview() {
    DetaQTheme {
        PasswordTextField(
            password = "Password",
            onPasswordChange = { },
            passwordVisible = false,
            showPassword = {  },
            error = ValidationError("There is Error")
        )
    }
}