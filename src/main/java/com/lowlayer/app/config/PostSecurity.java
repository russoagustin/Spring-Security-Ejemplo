package com.lowlayer.app.config;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.lowlayer.app.repositories.PostRepository;

import lombok.AllArgsConstructor;

@Component("postSecurity")
@AllArgsConstructor
public class PostSecurity {

    private final PostRepository postRepository;

    public boolean isPostOwner(Integer postId, Authentication authentication){
        String username = authentication.getName();
        System.err.println("username: " + username + " postId: " + postId);
        boolean isOwner = postRepository.existsByIdAndAuthor_Username(postId, username);
        if (!isOwner) {
            throw new com.lowlayer.app.exceptions.NotOwnerException("User is not the owner of the post");
        }
        return isOwner;
    }
}
