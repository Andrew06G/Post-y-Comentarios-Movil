package com.example.postscommentsapp.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CommentsViewModel extends ViewModel {

    private final GetCommentsUseCase getCommentsUseCase;
    private final InsertCommentUseCase insertCommentUseCase;

    private final MutableLiveData<List<Comment>> commentsLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();

    @Inject
    public CommentsViewModel(GetCommentsUseCase getCommentsUseCase,
                             InsertCommentUseCase insertCommentUseCase) {
        this.getCommentsUseCase = getCommentsUseCase;
        this.insertCommentUseCase = insertCommentUseCase;
    }

    public LiveData<List<Comment>> getComments() {
        return commentsLiveData;
    }

    public LiveData<Boolean> isLoading() { return loading; }

    public LiveData<String> getError() { return error; }

    public void loadComments(int postId) {
        loading.postValue(true);

        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                List<Comment> comments = getCommentsUseCase.execute(postId);
                commentsLiveData.postValue(comments);
            } catch (Exception e) {
                error.postValue("Error al cargar comentarios.");
            } finally {
                loading.postValue(false);
            }
        });
    }

    public void addComment(Comment comment) {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                insertCommentUseCase.execute(comment);
                loadComments(comment.getPostId());
            } catch (Exception e) {
                error.postValue("Error al insertar comentario.");
            }
        });
    }
}
