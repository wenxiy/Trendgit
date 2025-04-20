package com.example.trend_compose.viewmodel

import androidx.lifecycle.ViewModel
import com.example.trend_compose.model.ProjectItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProjectViewModel : ViewModel() {
    private val _projectList = MutableStateFlow(
        listOf(
            ProjectItem(
                name = "Compose-Gallery",
                imageUrl = "https://avatars.githubusercontent.com/u/1",
                description = "一个展示 Jetpack Compose 的项目合集",
                stars = 1234
            ),
            ProjectItem(
                name = "NowInAndroid",
                imageUrl = "https://avatars.githubusercontent.com/u/2",
                description = "谷歌官方的现代 Android 架构项目",
                stars = 4567
            )
        )
    )
    val projectList: StateFlow<List<ProjectItem>> = _projectList
}