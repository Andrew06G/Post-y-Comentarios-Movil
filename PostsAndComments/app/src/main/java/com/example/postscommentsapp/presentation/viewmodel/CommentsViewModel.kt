package com.example.postscommentsapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postscommentsapp.domain.model.Comment
import com.example.postscommentsapp.domain.usecase.GetCommentsUseCase
import com.example.postscommentsapp.domain.usecase.InsertCommentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    private val getCommentsUseCase: GetCommentsUseCase,
    private val insertCommentUseCase: InsertCommentUseCase
) : ViewModel() {

    val comments = MutableLiveData<List<Comment>>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String?>()

    fun loadComments(postId: Int) {
        viewModelScope.launch {
            loading.value = true
            try {
                comments.value = getCommentsUseCase.execute(postId)
            } catch (e: Exception) {
                error.value = "Error cargando comentarios"
            }
            loading.value = false
        }
    }

    fun addComment(postId: Int, text: String) {
        viewModelScope.launch {
            try {
                insertCommentUseCase.execute(
                    Comment(
                        id = 0,
                        postId = postId,
                        comment = text,
                        createdAt = System.currentTimeMillis()
                    )
                )
                loadComments(postId)
            } catch (e: Exception) {
                error.value = "No se pudo insertar el comentario"
            }
        }
    }
}
