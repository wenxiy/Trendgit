package com.example.trend_compose.ui.theme

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.trend_compose.ui.theme.components.ProjectCard
import com.example.trend_compose.viewmodel.ProjectViewModel

@Composable
fun PorjectListScreen(
    modifier: Modifier,
    viewModel: ProjectViewModel,
    onLoadMore: () -> Unit
) {
    val projects by viewModel.projectList.collectAsState()
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = Modifier.padding(16.dp)
    ) {
        items(projects) { project ->
            ProjectCard(
                name = project.name,
                imageUrl = project.imageUrl,
                description = project.description,
                stars = project.stars,
                onClick = {
                    println("点击了 ${project.name}")
                }
            )
        }
        item {
            Button(
                onClick = onLoadMore,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) { Text(text = "加载更多") }
        }


    }
    val shouldLoadMore = remember {
        derivedStateOf {
            //获取最后一个可见的 item 的索引
            val lastVisible = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
            //数据列表的最后一个元素的索引
            lastVisible == projects.size - 1
        }
    }
    // shouldLoadMore.value == true 表示“该加载下一页了”
    LaunchedEffect(shouldLoadMore.value) {
        if (shouldLoadMore.value) {
            onLoadMore()
        }
    }

}