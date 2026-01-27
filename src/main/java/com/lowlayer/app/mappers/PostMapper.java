package com.lowlayer.app.mappers;

import org.springframework.stereotype.Component;

import com.lowlayer.app.dto.PostRequestDTO;
import com.lowlayer.app.dto.PostResponseDTO;
import com.lowlayer.app.model.Post;

@Component
public class PostMapper {

    public PostResponseDTO toResponseDto(Post post){
        
        return new PostResponseDTO(
            post.getId(),
            post.getTitle(),
            post.getCategory().getName(),
            post.getContent(),
            post.getTags(),
            post.getCreatedAt(),
            post.getUpdatedAt()
        );
    }

    public Post toEntity(PostRequestDTO postDto){
        if (postDto == null) {
            return null;
        }

        Post post = new Post();
        post.setTitle(postDto.title());
        post.setContent(postDto.content());
        post.setTags(postDto.tags());

        return post;
    }

}
