package com.example.trend_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trend_compose.ui.theme.Trend_ComposeTheme
import com.example.trend_compose.ui.theme.components.ProjectCard
import com.example.trend_compose.viewmodel.ProjectViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Trend_ComposeTheme {
                MainScreen()

            }
        }
    }

    @Composable
    fun MainScreen() {
        val vm: ProjectViewModel = viewModel()

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
                    .fillMaxWidth(),
                viewModel = vm
            )
        }
    }

    @Composable
    fun PorjectListScreen(modifier: Modifier, viewModel: ProjectViewModel) {
        val projects by viewModel.projectList.collectAsState()
        LazyColumn(modifier = Modifier.padding(16.dp)) {
            items(projects) { project ->
                ProjectCard(
                    name = project.name,
                    imageUrl = project.imageUrl,
                    onClick = {
                        println("点击了 ${project.name}")
                    }
                )
            }
        }

    }
}
