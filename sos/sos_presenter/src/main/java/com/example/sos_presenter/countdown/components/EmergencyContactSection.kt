package com.example.sos_presenter.countdown.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.domain.model.Contact
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Negative60
import com.example.core_ui.ui.theme.Neutral10
import com.example.core_ui.ui.theme.Neutral100
import com.example.sos_presenter.R
import com.example.sos_presenter.countdown.CountDownEvent
import com.example.sos_presenter.countdown.CountDownState

@Composable
fun EmergencyContactSection(
    modifier: Modifier = Modifier,
    state: CountDownState,
    onEvent: (CountDownEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = 48.dp,
                    topEnd = 48.dp
                )
            )
            .background(Neutral10)
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.emergency_contact),
                style = MaterialTheme.typography.h3.copy(
                    color = Neutral100
                )
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

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            modifier = modifier
                .height(100.dp), // optional
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = state.contacts,
                key = { contact -> contact.id }
            ) { contact ->
                EmergencyContactCard(
                    contact = contact,
                    onDelete = {
                        onEvent(
                            CountDownEvent.RemoveContact(contact.id)
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = stringResource(id = R.string.call_ambulance_title),
            style = MaterialTheme.typography.h3.copy(
                color = Neutral100
            )
        )

        Spacer(modifier = Modifier.height(16.dp))
        
        CallAmbulanceCard(
            isChecked = state.isCallAmbulance,
            onChecked = {
                onEvent(
                    CountDownEvent.ToggleCallAmbulance(it)
                )
            }
        )
    }
}

@Composable
private fun EmergencyContactCard(
    modifier: Modifier = Modifier,
    contact: Contact,
    onDelete: () -> Unit
) {
    Card(
        modifier = modifier,
        elevation = 5.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Box {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.TopCenter)
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.secondary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = contact.name.first().uppercase(),
                        style = MaterialTheme.typography.h1.copy(
                            fontSize = 32.sp,
                            color = Neutral10
                        )
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = contact.name.substringBefore(" "),
                    style = MaterialTheme.typography.h5
                )
            }

            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Remove Contact",
                tint = Negative60,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .clickable {
                        onDelete()
                    }
            )
        }
    }
}

@Composable
fun CallAmbulanceCard(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    onChecked: (Boolean) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        elevation = 5.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = onChecked
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(id = R.string.call_ambulance_sub),
                    style = MaterialTheme.typography.body2.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = Neutral100
                    )
                )

                Text(
                    text = stringResource(id = R.string.call_ambulance_desc),
                    style = MaterialTheme.typography.caption.copy(
                        color = Color(0xFFB3B3B3)
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun CallAmbulanceCardPreview() {
    DetaQTheme {
        CallAmbulanceCard(
            isChecked = false,
            onChecked = {  }
        )
    }
}

@Preview
@Composable
fun EmergencyContactCardPreview() {
    DetaQTheme {
        EmergencyContactCard(
            contact = Contact(
                id = "1",
                name = "Fahmi",
                number = "08"
            ),
            onDelete = {  }
        )
    }
}

@Preview
@Composable
fun EmergencyContactSectionPreview() {
    DetaQTheme {
        EmergencyContactSection(
            state = CountDownState(
                contacts = dummyListOfContacts
            ),
            onEvent = {  }
        )
    }
}

private val dummyListOfContacts = listOf(
    Contact(
        id = "1",
        name = "Fahmi",
        number = "08"
    ),
    Contact(
        id = "2",
        name = "Dea",
        number = "08"
    ),
    Contact(
        id = "3",
        name = "Itsar",
        number = "08"
    )
)