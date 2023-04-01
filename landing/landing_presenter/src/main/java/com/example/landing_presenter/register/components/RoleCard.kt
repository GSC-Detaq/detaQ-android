package com.example.landing_presenter.register.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.LocalGradient
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Neutral60
import com.example.landing_presenter.register.UserRole

@Composable
fun RoleCard(
    role: UserRole,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val localGradient = LocalGradient.current
    val borderModifier = Modifier
        .border(
            width = 1.dp,
            brush = localGradient.primary,
            shape = RoundedCornerShape(8.dp)
        )

    Card(
        modifier = modifier
            .size(150.dp)
            .then(
                if (isSelected) borderModifier else Modifier
            )
            .clickable {
                onClick()
            },
        elevation = 5.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = role.getIconRes()),
                contentDescription = stringResource(id = role.getRoleAsTextRes()) + " Icon",
                modifier = Modifier
                    .size(100.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = stringResource(id = role.getRoleAsTextRes()),
                style = MaterialTheme.typography.body2.copy(
                    color = Neutral60
                )
            )
        }
    }
}

@Preview
@Composable
fun RoleCardPreview() {
    DetaQTheme {
        RoleCard(
            role = UserRole.Family,
            isSelected = true,
            onClick = {  }
        )
    }
}