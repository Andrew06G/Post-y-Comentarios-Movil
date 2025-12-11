package com.example.postscommentsapp.presentation.postdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postscommentsapp.domain.model.Post
import com.example.postscommentsapp.domain.usecase.GetPostByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val getPostByIdUseCase: GetPostByIdUseCase
) : ViewModel() {

    private val _post = MutableLiveData<Post?>()
    val post: LiveData<Post?> = _post

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun loadPost(postId: Int) {
        viewModelScope.launch {
            _loading.value = true
            try {
                _post.value = getPostByIdUseCase.execute(postId)
                if (_post.value == null) {
                    _error.value = "Post no encontrado"
                }
            } catch (e: Exception) {
                _error.value = "Error cargando el post: ${e.message}"
            } finally {
                _loading.value = false
            }
        }
    }
}

