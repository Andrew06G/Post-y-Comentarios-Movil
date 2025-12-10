package com.example.postscommentsapp.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PostsViewModel extends ViewModel {

    private final GetPostsUseCase getPostsUseCase;
    private final SearchPostsUseCase searchPostsUseCase;

    private final MutableLiveData<List<Post>> postsLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();

    @Inject
    public PostsViewModel(GetPostsUseCase getPostsUseCase,
                          SearchPostsUseCase searchPostsUseCase) {
        this.getPostsUseCase = getPostsUseCase;
        this.searchPostsUseCase = searchPostsUseCase;
    }

    public LiveData<List<Post>> getPosts() { return postsLiveData; }
    public LiveData<Boolean> isLoading() { return loading; }
    public LiveData<String> getError() { return error; }

    public void loadPosts() {
        loading.postValue(true);

        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                List<Post> posts = getPostsUseCase.execute();
                postsLiveData.postValue(posts);
            } catch (Exception e) {
                error.postValue("Error al cargar publicaciones: " + e.getMessage());
            } finally {
                loading.postValue(false);
            }
        });
    }

    public void searchPosts(String query) {
        loading.postValue(true);

        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                List<Post> posts = searchPostsUseCase.execute(query);
                postsLiveData.postValue(posts);
            } catch (Exception e) {
                error.postValue("Error buscando publicaciones.");
            } finally {
                loading.postValue(false);
            }
        });
    }
}
