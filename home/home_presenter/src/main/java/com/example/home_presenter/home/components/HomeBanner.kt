package com.example.home_presenter.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.LocalGradient
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Neutral30
import com.example.home_presenter.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState


@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeBanner(
    modifier: Modifier = Modifier
) {
    val localGradient = LocalGradient.current
    val pageCount = bannerResList.size
    val state = rememberPagerState()

    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            count = pageCount,
            state = state,
            contentPadding = PaddingValues(horizontal = 16.dp),
            itemSpacing = 16.dp
        ) { page ->
            val painter = painterResource(id = bannerResList[page])

            Image(
                painter = painter,
                contentDescription = "Banner $page",
                modifier = Modifier
                    .aspectRatio(painter.intrinsicSize.width / painter.intrinsicSize.height)
                    .fillMaxWidth(),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pageCount) { page ->
                val colorModifier = if (state.currentPage == page) Modifier.background(localGradient.primary)
                else Modifier.background(Neutral30)

                val shapeModifier = if (state.currentPage == page) Modifier
                    .width(32.dp)
                    .height(8.dp)
                    .clip(RoundedCornerShape(26.dp))
                else Modifier
                    .size(8.dp)
                    .clip(CircleShape)

                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .then(shapeModifier)
                        .then(colorModifier)
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeBannerPreview() {
    DetaQTheme {
        HomeBanner()
    }
}

private val bannerResList = listOf(
    R.drawable.banner_1,
    R.drawable.banner_2,
    R.drawable.banner_3
)