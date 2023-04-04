package com.example.profile_presenter.my_family

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core_ui.CommonHeader
import com.example.core_ui.PrimaryButton
import com.example.core_ui.ui.theme.Neutral100
import com.example.profile_presenter.R
import com.example.profile_presenter.my_family.components.MyFamilyCard

@Composable
fun MyFamilyScreen(
    viewModel: MyFamilyViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            CommonHeader(
                title = stringResource(id = R.string.connect_title),
                onBackClick = onBackClick
            )
        },
        floatingActionButton = {
            PrimaryButton(
                textRes = if (state.isEditing) R.string.cancel else R.string.edit,
                textModifier = Modifier
                    .fillMaxWidth(),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                viewModel.onEvent(
                    event = MyFamilyEvent.Edit(!state.isEditing)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                if (state.patients.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = stringResource(id = R.string.patient),
                        style = MaterialTheme.typography.h3.copy(
                            color = Neutral100
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            items(
                items = state.patients,
                key = { family -> family.uid + "-patient" }
            ) { family ->
                MyFamilyCard(
                    username = family.email,
                    onDeleteVisible = state.isEditing,
                    onDelete = {
                        viewModel.onEvent(
                            event = MyFamilyEvent.OnDelete(family.uid)
                        )
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                if (state.family.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = stringResource(id = R.string.family),
                        style = MaterialTheme.typography.h3.copy(
                            color = Neutral100
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            items(
                items = state.family,
                key = { family -> family.uid + "-family" }
            ) { family ->
                MyFamilyCard(
                    username = family.email,
                    onDeleteVisible = state.isEditing,
                    onDelete = {
                        viewModel.onEvent(
                            event = MyFamilyEvent.OnDelete(family.uid)
                        )
                    }
                )
                
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}