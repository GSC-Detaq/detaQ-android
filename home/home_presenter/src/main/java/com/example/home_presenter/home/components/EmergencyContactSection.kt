package com.example.home_presenter.home.components

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.domain.model.Contact
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Negative60
import com.example.core_ui.ui.theme.Neutral10

@Composable
fun EmergencyContactSection(
    modifier: Modifier = Modifier,
    contacts: List<Contact>,
    isDeleteVisible: Boolean,
    onAddClick: () -> Unit,
    onDelete: (String) -> Unit
) {
    LazyRow(
        modifier = modifier
            .height(100.dp), // optional
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        item {
            Card(
                modifier = modifier
                    .size(60.dp)
                    .clickable {
                        onAddClick()
                    },
                elevation = 5.dp,
                shape = RoundedCornerShape(8.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "+",
                        style = MaterialTheme.typography.h1
                    )
                }
            }
        }

        items(
            items = contacts,
            key = { contact -> contact.id }
        ) { contact ->
            EmergencyContactCard(
                contact = contact,
                isDeleteVisible = isDeleteVisible,
                onDelete = { onDelete(contact.id) }
            )
        }
    }
}

@Composable
private fun EmergencyContactCard(
    modifier: Modifier = Modifier,
    contact: Contact,
    isDeleteVisible: Boolean,
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

            if (isDeleteVisible) {
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
}

@Preview
@Composable
fun EmergencyContactCardPreview() {
    DetaQTheme {
        EmergencyContactCard(
            contact = Contact(
                id = "1",
                name = "Adam",
                number = "08"
            ),
            isDeleteVisible = true,
            onDelete = {  }
        )
    }
}

@Preview
@Composable
fun EmergencyContactSectionPreview() {
    DetaQTheme {
        EmergencyContactSection(
            contacts = dummyListOfContacts,
            onAddClick = {  },
            isDeleteVisible = true,
            onDelete = {  }
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