package com.example.postscommentsapp.data.repository;

import com.example.postscommentsapp.data.local.dao.PostDao;
import com.example.postscommentsapp.data.local.entity.PostEntity;
import com.example.postscommentsapp.data.mapper.PostMapper;
import com.example.postscommentsapp.data.remote.RemoteDataSource;
import com.example.postscommentsapp.domain.model.Post;
import com.example.postscommentsapp.domain.repository.PostsRepository;

import java.util.List;

import javax.inject.Inject;

public class PostsRepositoryImpl implements PostsRepository {

    private final PostDao postDao;
    private final RemoteDataSource remote;

    @Inject
    public PostsRepositoryImpl(PostDao postDao, RemoteDataSource remote) {
        this.postDao = postDao;
        this.remote = remote;
    }

    @Override
    public List<Post> getPosts() throws Exception {

        // 1. Obtener local
        List<PostEntity> localData = postDao.getAllPosts();
        if (!localData.isEmpty()) {
            return PostMapper.toDomainList(localData);
        }

        // 2. Obtener remoto
        List<Post> remotePosts = remote.fetchPosts();

        // 3. Convertir a entidades
        List<PostEntity> remoteEntities = PostMapper.toEntityList(remotePosts);

        // 4. Guardar en BD
        postDao.insertPosts(remoteEntities);

        // 5. Devolver como lista de dominio
        return remotePosts;
    }

    @Override
    public List<Post> searchPosts(String query) throws Exception {
        List<PostEntity> filtered = postDao.getAllPosts();
        return PostMapper.toDomainList(
                filtered.stream()
                        .filter(p -> p.getTitle().toLowerCase().contains(query.toLowerCase()))
                        .toList()
        );
    }
}
