package com.example.profile_presenter.connect_wristband.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.utils.errors.ValidationError
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Neutral40

@Composable
fun WristbandTextField(
    modifier: Modifier = Modifier,
    codeText: String,
    codeCount: Int = 5,
    error: ValidationError?,
    onOtpTextChange: (String) -> Unit
) {
    BasicTextField(
        modifier = modifier,
        value = codeText,
        onValueChange = {
            if (it.length <= codeCount) {
                onOtpTextChange.invoke(it)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(codeCount) { index ->
                    CharView(
                        index = index,
                        text = codeText
                    )

                    if (index+1 != codeCount) {
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
        }
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

@Composable
private fun CharView(
    index: Int,
    text: String
) {
    val isFocused = text.length == index
    val char = when {
        index == text.length -> "0"
        index > text.length -> ""
        else -> text[index].toString()
    }
    Text(
        modifier = Modifier
            .size(56.dp)
            .border(
                1.dp,
                if (isFocused) MaterialTheme.colors.primary else Neutral40,
                RoundedCornerShape(8.dp)
            )
            .wrapContentHeight(Alignment.CenterVertically),
        text = char,
        style = MaterialTheme.typography.h1,
        color = if (isFocused) {
            MaterialTheme.colors.primary
        } else {
            Neutral40
        },
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
fun OtpTextFieldPreview() {
    DetaQTheme {
        WristbandTextField(
            codeText = "",
            onOtpTextChange = {  },
            error = null
        )
    }
}