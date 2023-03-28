package com.example.landing_presenter.register.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Neutral40
import com.example.core_ui.ui.theme.Neutral50
import com.example.landing_presenter.R
import com.example.landing_presenter.register.Role

@Composable
fun RoleDropDown(
    role: Role?,
    isOpen: Boolean,
    onClick: () -> Unit,
    onDismiss: () -> Unit,
    onSelectRole: (Role) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        DropdownMenu(
            expanded = isOpen,
            onDismissRequest = onDismiss
        ) {
            Role.values().forEach { role ->
                DropdownMenuItem(
                    onClick = { onSelectRole(role) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = role.name
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .border(
                    width = 1.dp,
                    color = Neutral40,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = role?.name ?: stringResource(id = R.string.role),
                style = MaterialTheme.typography.body1.copy(
                    color = Neutral50
                )
            )

            Icon(
                imageVector = if(isOpen) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                contentDescription = if(isOpen) stringResource(id = R.string.close) else stringResource(id = R.string.open),
                tint = Neutral50
            )
        }
    }
}

@Preview
@Composable
fun RoleDropDownPreview() {
    DetaQTheme {
        RoleDropDown(
            role = null,
            isOpen = false,
            onClick = { },
            onDismiss = {  },
            onSelectRole = {  }
        )
    }
}