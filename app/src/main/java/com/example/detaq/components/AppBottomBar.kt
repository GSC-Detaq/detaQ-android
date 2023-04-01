package com.example.detaq.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Neutral10
import com.example.core_ui.ui.theme.Neutral50
import com.example.core_ui.ui.theme.navigationTypography
import com.example.detaq.navigation.TopLevelDestination

@Composable
fun AppBottomBar(
    modifier: Modifier = Modifier,
    currentDestination: NavDestination?,
    onTabSelected: (TopLevelDestination) -> Unit,
) {
    val currentTopLevelDestination = TopLevelDestination.fromRoute(
        currentDestination?.route
    )

    BottomAppBar(
        modifier = modifier,
        backgroundColor = Neutral10,
        contentColor = Neutral50
    ) {
        BottomNavigation(
            backgroundColor = Neutral10,
            contentColor = Neutral50
        ) {
            TopLevelDestination.values().forEach { destination ->
                BottomNavigationItem(
                    icon = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            if (currentTopLevelDestination == destination) {
                                Spacer(modifier = Modifier.weight(1f))

                                Icon(
                                    painter = painterResource(id = destination.selectedIconRes),
                                    contentDescription = destination.name + " icon",
                                    modifier = Modifier
                                        .size(24.dp)
                                )
                            } else {
                                Icon(
                                    painter = painterResource(id = destination.unselectedIconRes),
                                    contentDescription = destination.name + " icon",
                                    modifier = Modifier
                                        .size(24.dp)
                                )
                            }

                            Text(
                                text = destination.name,
                                style = navigationTypography
                            )

                            if (currentTopLevelDestination == destination) {
                                Spacer(modifier = Modifier.weight(1f))

                                Box(
                                    modifier = Modifier
                                        .width(50.dp)
                                        .height(2.dp)
                                        .background(
                                            MaterialTheme.colors.secondary,
                                            RoundedCornerShape(
                                                topStart = 8.dp,
                                                topEnd = 8.dp
                                            )
                                        )
                                )

                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    },
                    selected = currentTopLevelDestination == destination,
                    onClick = {
                        onTabSelected(destination)
                    },
                    selectedContentColor = MaterialTheme.colors.secondary,
                    unselectedContentColor = Neutral50
                )

                if (destination == TopLevelDestination.History) {
                    BottomNavigationItem(
                        icon = {},
                        label = {  },
                        selected = false,
                        onClick = {  },
                        enabled = false
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun AppBottomBarPreview() {
    DetaQTheme {
        AppBottomBar(
            currentDestination = null,
            onTabSelected = {  }
        )
    }
}