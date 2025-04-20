package com.example.trend_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.trend_compose.ui.theme.Trend_ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Trend_ComposeTheme {
                Column(
                    modifier = Modifier
                        .padding(15.dp)
                        .padding(
                            top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
                        )
                ) {
                    Text(
                        text = "\uD83D\uDCE6 GitHub 项目",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    PorjectListScreen(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    )
                }
            }

        }
    }

    @Composable
    fun ProjectCard(
        name: String,
        imageUrl: String,
        onClick: () -> Unit
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onClick() },
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .padding(end = 16.dp)
                )
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )
            }
        }
    }

    @Composable
    fun PorjectListScreen(modifier: Modifier) {
        val fakeProjects = listOf(
            "Compose-Gallery",
            "JetNews",
            "NowInAndroid",
            "Kotlin-Coroutines",
            "Awesome-Android-UI"
        )
        LazyColumn(modifier = Modifier.padding(16.dp)) {
            items(fakeProjects) { projectName ->
                ProjectCard(
                    name = projectName,
                    imageUrl = "https://avatars.githubusercontent.com/u/9919",
                    onClick = {
                        println("点击了 $projectName")
                    }
                )
            }
        }

    }
}
