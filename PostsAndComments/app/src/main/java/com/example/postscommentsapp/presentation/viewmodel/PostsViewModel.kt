package com.example.postscommentsapp.presentation.viewmodel

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
class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val searchPostsUseCase: SearchPostsUseCase
) : ViewModel() {

    val posts = MutableLiveData<List<Post>>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String?>()

    fun loadPosts() {
        viewModelScope.launch {
            loading.value = true
            try {
                posts.value = getPostsUseCase.execute()
            } catch (e: Exception) {
                error.value = "Error cargando publicaciones"
            }
            loading.value = false
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            loading.value = true
            try {
                posts.value = searchPostsUseCase.execute(query)
            } catch (e: Exception) {
                error.value = "Error buscando publicaciones"
            }
            loading.value = false
        }
    }
}
