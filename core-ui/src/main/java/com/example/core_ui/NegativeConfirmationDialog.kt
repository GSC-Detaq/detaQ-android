package com.example.core_ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Neutral100

@Composable
fun NegativeConfirmationDialog(
    message: String,
    onDismiss: () -> Unit,
    onClicked: () -> Unit,
    cancellationText: String,
    confirmationText: String
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        text = { Text(text = message) },
        buttons = {
            Column {
                Divider()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(
                        onClick = onDismiss,
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        Text(
                            text = cancellationText,
                            color = Neutral100
                        )
                    }

                    TextButton(
                        onClick = onClicked,
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        Text(
                            text = confirmationText
                        )
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun NegativeConfirmationDialogPreview() {
    DetaQTheme {
        NegativeConfirmationDialog(
            message = "Are you sure to log out?",
            onDismiss = {  },
            onClicked = {  },
            cancellationText = "Cancel",
            confirmationText = "Log Out"
        )
    }
}