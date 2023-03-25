package com.example.home_presenter.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.utils.extensions.formattedDate
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Neutral50
import com.example.home_presenter.R
import java.time.LocalDateTime

@Composable
fun ArticleSection(
    modifier: Modifier = Modifier,
    articles: List<Article> = dummyArticles
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(
            items = articles,
            key = { article -> article.title }
        ) { article ->
            ArticleCard(
                article = article
            )
        }
    }
}

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article
) {
    Card(
        modifier = modifier
            .width(304.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = article.imageRes),
                contentDescription = article.title + "image",
                modifier = Modifier
                    .width(304.dp)
                    .height(120.dp)
            )

            Column(
                modifier = Modifier
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.h5
                )

                Text(
                    text = article.date.formattedDate(),
                    style = MaterialTheme.typography.caption.copy(
                        color = Neutral50
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun ArticleCardPreview() {
    DetaQTheme {
        ArticleCard(article = dummyArticles[0])
    }
}

@Preview
@Composable
fun ArticleSectionPreview() {
    DetaQTheme {
        ArticleSection()
    }
}

data class Article(
    val imageRes: Int, // temporary
    val title: String,
    val date: LocalDateTime
)

private val dummyArticles = listOf(
    Article(
        imageRes = R.drawable.article_sample,
        title = "One in Three Deaths Caused by the Heart. Let's Prevent Heart Attacks!",
        date = LocalDateTime.now()
    ),
    Article(
        imageRes = R.drawable.article_sample_2,
        title = "Hidden Symptoms of a Heart Attack, Take Note!",
        date = LocalDateTime.now()
    )
)