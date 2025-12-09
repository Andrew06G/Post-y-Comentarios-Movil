package com.example.postscommentsapp.data.mapper;

import com.example.postscommentsapp.data.local.entity.PostEntity;
import com.example.postscommentsapp.data.remote.dto.PostDto;
import com.example.postscommentsapp.domain.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostMapper {

    public static PostEntity toEntity(PostDto dto) {
        return new PostEntity(
                dto.getId(),
                dto.getTitle(),
                dto.getBody()
        );
    }

    public static Post toDomain(PostEntity entity) {
        return new Post(
                entity.getId(),
                entity.getTitle(),
                entity.getBody()
        );
    }

    public static List<PostEntity> toEntityList(List<PostDto> list) {
        List<PostEntity> entities = new ArrayList<>();
        for (PostDto dto : list) {
            entities.add(toEntity(dto));
        }
        return entities;
    }

    public static List<Post> toDomainList(List<PostEntity> list) {
        List<Post> result = new ArrayList<>();
        for (PostEntity entity : list) {
            result.add(toDomain(entity));
        }
        return result;
    }
}
