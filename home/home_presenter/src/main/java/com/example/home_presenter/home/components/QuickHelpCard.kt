package com.example.home_presenter.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.home_presenter.R

@Composable
fun QuickHelpCard(
    onFirstAidClick: () -> Unit,
    onAloneClick: () -> Unit,
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
            Image(
                painter = painterResource(id = R.drawable.quickhelp_icon),
                contentDescription = "Quick Help Icon",
                modifier = Modifier
                    .size(80.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onFirstAidClick()
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.first_aid_title),
                        style = MaterialTheme.typography.caption.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier
                            .weight(1f)
                    )

                    Icon(
                        imageVector = Icons.Rounded.ChevronRight,
                        contentDescription = null
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Divider()

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onAloneClick()
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.alone_title),
                        style = MaterialTheme.typography.caption.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier
                            .weight(1f)
                    )

                    Icon(
                        imageVector = Icons.Rounded.ChevronRight,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun QuickHelpCardPreview() {
    DetaQTheme {
        QuickHelpCard(
            onFirstAidClick = {  },
            onAloneClick = {  }
        )
    }
}