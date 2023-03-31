package com.example.sos_presenter.sos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.CommonHeader
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.sos_presenter.R
import com.example.sos_presenter.sos.components.SosButton

@Composable
fun SosScreen(
    onBackClick: () -> Unit,
    onSosClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CommonHeader(
                title = stringResource(id = R.string.emergency),
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(64.dp))

            Text(
                text = stringResource(id = R.string.need_help),
                style = MaterialTheme.typography.h1
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(id = R.string.need_help_desc),
                style = MaterialTheme.typography.caption.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF808080),
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.height(128.dp))

            SosButton(
                onClick = onSosClick
            )
        }
    }
}

@Preview
@Composable
fun SosScreenPreview() {
    DetaQTheme {
        SosScreen(
            onBackClick = {  },
            onSosClick = {  }
        )
    }
}