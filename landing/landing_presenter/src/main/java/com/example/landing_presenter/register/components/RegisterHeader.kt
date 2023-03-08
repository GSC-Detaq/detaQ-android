package com.example.landing_presenter.register.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.LocalGradient
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.landing_presenter.register.RegisterSection

@Composable
fun RegisterHeader(
    currentSection: RegisterSection,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val localGradient = LocalGradient.current
    val gradientModifier = Modifier.background(localGradient.primary)
    val neutralModifier = Modifier.background(Color(0xFFD9D9D9))

    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBackIos,
            contentDescription = "Back",
            modifier = Modifier
                .clickable { onBack() }
        )

        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .weight(1f)
                .height(8.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(localGradient.primary)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .weight(1f)
                .height(8.dp)
                .clip(RoundedCornerShape(10.dp))
                .then(
                    if (
                        currentSection == RegisterSection.NumberVerification
                        || currentSection == RegisterSection.SelectRole
                    ) gradientModifier else neutralModifier
                )
        )

        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .weight(1f)
                .height(8.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(localGradient.primary)
                .then(
                    if (currentSection == RegisterSection.SelectRole) gradientModifier else neutralModifier
                )
        )
    }
}

@Preview
@Composable
fun RegisterHeaderPreview() {
    DetaQTheme {
        RegisterHeader(
            currentSection = RegisterSection.NumberVerification,
            onBack = {  }
        )
    }
}