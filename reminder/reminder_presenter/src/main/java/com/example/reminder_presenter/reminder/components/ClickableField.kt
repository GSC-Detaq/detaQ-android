package com.example.reminder_presenter.reminder.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.utils.extensions.asString
import com.example.core_ui.ui.theme.DetaQTheme
import java.time.LocalDate

@Composable
fun ClickableField(
    title: String,
    value: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = {  },
        label = { Text(text = title) },
        singleLine = true,
        readOnly = true,
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focus ->
                if (focus.isFocused) {
                    onClick()
                }
            }
    )
}

@Preview
@Composable
fun ClickableFieldPreview() {
    DetaQTheme {
        ClickableField(
            title = "Date Started",
            value = LocalDate.now().asString(),
            onClick = {  }
        )
    }
}