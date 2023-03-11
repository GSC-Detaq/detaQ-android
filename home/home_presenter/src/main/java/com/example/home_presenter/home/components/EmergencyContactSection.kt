package com.example.home_presenter.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Neutral10
import com.example.core_ui.ui.theme.Red60

@Composable
fun EmergencyContactSection(
    modifier: Modifier = Modifier,
    contacts: List<String> = dummyListOfContacts
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(
            items = contacts,
            key = { contact -> contact }
        ) { contact ->
            EmergencyContactCard(
                contact = contact
            )
        }
    }
}

@Composable
private fun EmergencyContactCard(
    modifier: Modifier = Modifier,
    contact: String
) {
    Card(
        modifier = modifier,
        elevation = 5.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.secondary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = contact.first().uppercase(),
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 32.sp,
                        color = Neutral10
                    )
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = contact,
                style = MaterialTheme.typography.h5
            )
        }
    }
}

@Preview
@Composable
fun EmergencyContactCardPreview() {
    DetaQTheme {
        EmergencyContactCard(contact = "Adam")
    }
}

@Preview
@Composable
fun EmergencyContactSectionPreview() {
    DetaQTheme {
        EmergencyContactSection()
    }
}

private val dummyListOfContacts = listOf(
    "Mom",
    "Adam",
    "Grace"
)