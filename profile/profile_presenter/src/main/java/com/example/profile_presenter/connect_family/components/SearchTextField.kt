package com.example.profile_presenter.connect_family.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.profile_presenter.R

@Composable
fun SearchTextField(
    text: String,
    onTextChange: (String) -> Unit,
    error: String?,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = text,
        onValueChange = { newText ->
            onTextChange(newText)
        },
        isError = error != null,
        trailingIcon = {
            error?.let {
                IconButton(
                    onClick = {  }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Error,
                        contentDescription = it,
                        tint = MaterialTheme.colors.error
                    )
                }
            }
        },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon"
            )
        },
        placeholder = {
            Text(text = stringResource(id = R.string.add_family_hint))
        },
        modifier = modifier
            .fillMaxWidth()
    )

    error?.let {
        Text(
            text = it,
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
        SearchTextField(
            text = "",
            onTextChange = {  },
            error = null
        )
    }
}