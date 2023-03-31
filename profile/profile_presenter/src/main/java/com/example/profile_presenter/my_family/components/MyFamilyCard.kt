package com.example.profile_presenter.my_family.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Negative60
import com.example.core_ui.ui.theme.Neutral10
import com.example.core_ui.ui.theme.Neutral100

@Composable
fun MyFamilyCard(
    username: String,
    onDeleteVisible: Boolean,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
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
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.secondary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = username.first().uppercase(),
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 32.sp,
                        color = Neutral10
                    )
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = username,
                style = MaterialTheme.typography.h5.copy(
                    color = Neutral100
                ),
                modifier = Modifier
                    .weight(1f)
            )

            if (onDeleteVisible) {
                IconButton(
                    onClick = onDelete
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Delete from My Family",
                        tint = Negative60
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MyFamilyCardPreview() {
    DetaQTheme {
        MyFamilyCard(
            username = "Darren#8900",
            onDeleteVisible = true,
            onDelete = {  }
        )
    }
}