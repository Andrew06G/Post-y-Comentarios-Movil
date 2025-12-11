package com.example.postscommentsapp.presentation.postlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postscommentsapp.domain.model.Post
import com.example.postscommentsapp.domain.usecase.GetPostsUseCase
import com.example.postscommentsapp.domain.usecase.SearchPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val searchPostsUseCase: SearchPostsUseCase
) : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    fun loadPosts() {
        viewModelScope.launch {
            val result = getPostsUseCase.execute()
            _posts.value = result
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            val trimmed = query.trim()
            val asNumber = trimmed.toIntOrNull()
            if (asNumber != null) {
                val post = getPostsUseCase.execute().find { it.id == asNumber }
                _posts.value = post?.let { listOf(it) } ?: emptyList()
                return@launch
            }

            val result = searchPostsUseCase.execute(trimmed)
            _posts.value = result
        }
    }
}
