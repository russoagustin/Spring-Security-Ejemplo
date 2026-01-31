package com.lowlayer.app.services.interfaces;

import com.lowlayer.app.dto.PostRequestDTO;
import com.lowlayer.app.dto.PostResponseDTO;
import com.lowlayer.app.model.AppUser;

public interface IpostService {
    PostResponseDTO createPost(PostRequestDTO post, AppUser owner);

    PostResponseDTO findPostById(Integer id);

    PostResponseDTO updatePost(Integer id, PostRequestDTO post);

    void deletePost(Integer id);
}
