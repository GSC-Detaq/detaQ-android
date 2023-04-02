package com.example.home_presenter.home

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.ContactsContract.CommonDataKinds.Phone
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.utils.UiEvent
import com.example.home_presenter.R
import com.example.home_presenter.home.components.*

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    showSnackBar: (String) -> Unit,
    onNotificationClick: () -> Unit,
    onFirstAidClick: () -> Unit,
    onAloneClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    val context = LocalContext.current
    val activity = LocalContext.current as Activity
    val contactPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val uri = result.data?.data

        uri?.let { data ->
            val cursor = context.contentResolver.query(
                data,
                null,
                null,
                null,
                null
            )

            if (cursor != null && cursor.moveToFirst()) {
                val nameIndex = cursor.getColumnIndexOrThrow(Phone.DISPLAY_NAME)
                val name = cursor.getString(nameIndex)

                val numberIndex = cursor.getColumnIndexOrThrow(Phone.NUMBER)
                val number = cursor.getString(numberIndex)

                cursor.close()

                viewModel.onEvent(
                    event = HomeEvent.AddContact(
                        number = number,
                        name = name
                    )
                )
            }
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Success -> Unit
                is UiEvent.ShowSnackBar -> {
                    showSnackBar(
                        event.message.asString(context)
                    )
                }
                else -> Unit
            }
        }
    }

    Scaffold(
        topBar = {
            HomeHeader(
                notificationCounts = state.notificationCount,
                onNotificationClick = onNotificationClick
            )
        }
    ) { paddingValues ->
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
                        text = stringResource(
                            id = if (state.isEditingContact) R.string.cancel else R.string.edit
                        ),
                        style = MaterialTheme.typography.caption.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.secondary
                        ),
                        modifier = Modifier
                            .clickable {
                                viewModel.onEvent(
                                    event = HomeEvent.ToggleEditContact
                                )
                            }
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                EmergencyContactSection(
                    contacts = state.emergencyContacts,
                    isDeleteVisible = state.isEditingContact,
                    onAddClick = {
                        if (hasContactPermission(context)) {
                            val intent = Intent(
                                Intent.ACTION_PICK,
                                Phone.CONTENT_URI
                            )

                            contactPicker.launch(intent)
                        } else {
                            requestContactPermission(context, activity)
                        }
                    },
                    onDelete = {  }
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

fun hasContactPermission(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS) ==
            PackageManager.PERMISSION_GRANTED
}

fun requestContactPermission(context: Context, activity: Activity) {
    if (!hasContactPermission(context)) {
        ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.READ_CONTACTS), 1)
    }
}