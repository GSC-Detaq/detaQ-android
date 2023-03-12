package com.example.home_presenter.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.home_presenter.R
import com.example.home_presenter.home.components.*

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onFirstAidClick: () -> Unit,
    onAloneClick: () -> Unit
) {
    val state = viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            HomeHeader(
                onNotificationClick = {  }
            )
        }
    ) {paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            item {
                HomeBanner()
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(id = R.string.quick_help),
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                QuickHelpCard(
                    onFirstAidClick = onFirstAidClick,
                    onAloneClick = onAloneClick,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.emergency_contact),
                        style = MaterialTheme.typography.h3
                    )

                    Text(
                        text = stringResource(id = R.string.edit),
                        style = MaterialTheme.typography.caption.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.secondary
                        ),
                        modifier = Modifier
                            .clickable {  }
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                EmergencyContactSection()
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.nearby_health_facility),
                        style = MaterialTheme.typography.h3
                    )

                    Text(
                        text = stringResource(id = R.string.show_all),
                        style = MaterialTheme.typography.caption.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.secondary
                        ),
                        modifier = Modifier
                            .clickable {  }
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                HealthFacilitiesSection()
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.article),
                        style = MaterialTheme.typography.h3
                    )

                    Text(
                        text = stringResource(id = R.string.show_all),
                        style = MaterialTheme.typography.caption.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.secondary
                        ),
                        modifier = Modifier
                            .clickable {  }
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                ArticleSection()
            }
        }
    }
}