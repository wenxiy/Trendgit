package com.example.trend_compose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trend_compose.model.ProjectItem
import com.example.trend_compose.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProjectViewModel : ViewModel() {
    private val _projectList = MutableStateFlow<List<ProjectItem>>(
        emptyList()
    )
    val projectList: StateFlow<List<ProjectItem>> = _projectList

    private var currentPage = 1
    private var isLoading = false
    private val pageSize = 7

    init {
        loadNextPage()  // 默认加载第一页
    }

    fun loadNextPage() {
        if (isLoading) return  // 避免重复加载
        isLoading = true

        viewModelScope.launch {
            try {
                val response =
                    RetrofitInstance.api.searchTopRepos(
                        page = currentPage,
                        perPage = pageSize
                    )
                val nextItems = response.items.map {
                    ProjectItem(
                        name = it.name,
                        imageUrl = it.owner.avatar_url,
                        description = it.description ?: "无描述",
                        stars = it.stars
                    )
                }
                _projectList.value = _projectList.value + nextItems
                currentPage++
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading = false

            }
        }

    }
}