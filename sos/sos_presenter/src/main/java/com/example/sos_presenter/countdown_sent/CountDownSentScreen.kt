package com.example.sos_presenter.countdown_sent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core_ui.CommonHeader
import com.example.core_ui.ui.theme.Neutral10
import com.example.core_ui.ui.theme.Neutral100
import com.example.core_ui.ui.theme.Red60
import com.example.sos_presenter.R
import com.example.sos_presenter.countdown_sent.components.SosSection
import com.google.maps.android.compose.GoogleMap

@Composable
fun CountDownSentScreen(
    viewModel: CountDownSentViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            CommonHeader(
                title = stringResource(id = R.string.emergency),
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            GoogleMap(
                modifier = Modifier
                    .fillMaxSize()
            )

            TextField(
                value = state.searchText,
                onValueChange = {
                    viewModel.onEvent(
                        CountDownSentEvent.OnSearchTextChange(it)
                    )
                },
                placeholder = {
                    Text(text = "Your Location...")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location Icon",
                        tint = Red60
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Neutral100,
                    backgroundColor = Neutral10,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.TopCenter)
            )

            SosSection(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            )
        }
    }
}