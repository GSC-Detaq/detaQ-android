package com.example.home_presenter.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.home_presenter.R
import com.example.home_presenter.model.QuickHelp

@Composable
fun QuickHelpItem(
    quickHelp: QuickHelp,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = 5.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = quickHelp.number.toString(),
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 32.sp,
                        color = MaterialTheme.colors.secondary
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = stringResource(id = quickHelp.titleRes),
                    style = MaterialTheme.typography.h4.copy(
                        color = MaterialTheme.colors.secondary
                    ),
                    textAlign = TextAlign.Start
                )
            }

            Text(
                text = stringResource(id = quickHelp.descriptionRes),
                style = MaterialTheme.typography.caption.copy(
                    color = MaterialTheme.colors.secondary
                ),
                modifier = Modifier
                    .padding(start = 21.dp)
            )
        }
    }
}

@Preview
@Composable
fun QuickHelpItemPreview() {
    DetaQTheme {
        QuickHelpItem(
            quickHelp = QuickHelp(
                number = 1,
                titleRes = R.string.conscious_title_1,
                descriptionRes = R.string.conscious_description_1
            )
        )
    }
}