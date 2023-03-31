package com.example.home_presenter.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.home_presenter.R
import com.example.home_presenter.model.QuickHelp

@Composable
fun QuickHelpSection(
    modifier: Modifier = Modifier,
    quickHelps: List<QuickHelp>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement
            .spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(
            items = quickHelps,
            key = { help -> help.number }
        ) { help ->
            QuickHelpItem(
                quickHelp = help
            )
        }
    }
}

@Preview
@Composable
fun ConsciousSectionPreview() {
    DetaQTheme {
        QuickHelpSection(
            quickHelps = listOf(
                QuickHelp(
                    number = 1,
                    titleRes = R.string.conscious_title_1,
                    descriptionRes = R.string.conscious_description_1
                ),
                QuickHelp(
                    number = 2,
                    titleRes = R.string.conscious_title_2,
                    descriptionRes = R.string.conscious_description_2
                )
            )
        )
    }
}