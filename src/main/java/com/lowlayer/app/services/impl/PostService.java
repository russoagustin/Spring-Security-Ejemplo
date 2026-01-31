package com.lowlayer.app.services.impl;

import java.time.Instant;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lowlayer.app.dto.PostRequestDTO;
import com.lowlayer.app.dto.PostResponseDTO;
import com.lowlayer.app.exceptions.NotFoundException;
import com.lowlayer.app.mappers.PostMapper;
import com.lowlayer.app.model.AppUser;
import com.lowlayer.app.model.Category;
import com.lowlayer.app.model.Post;
import com.lowlayer.app.repositories.CategoryRepository;
import com.lowlayer.app.repositories.PostRepository;
import com.lowlayer.app.services.interfaces.IpostService;


@Service
@Transactional
public class PostService implements IpostService{

    private final PostRepository repo;
    private final CategoryRepository categoryRepo;
    private final PostMapper postMapper;

    public PostService(PostRepository repo, PostMapper postMapper, CategoryRepository categoryRepo){
        this.repo = repo;
        this.categoryRepo = categoryRepo;
        this.postMapper = postMapper;

    }

    @Override
    public PostResponseDTO createPost(PostRequestDTO postDto, AppUser owner) {
        Post post = postMapper.toEntity(postDto);
        post.setAuthor(owner);
        post.setCategory(
            findCategoryOrThrow(
                postDto.catName()
            )
        );
        post.setCreatedAt(Instant.now());
        post.setUpdatedAt(Instant.now());

        return postMapper.toResponseDto(repo.save(post));
    }

    @Override
    @Transactional(readOnly = true)
    public PostResponseDTO findPostById(Integer id) {
        Post post = findPostOrThrow(id);

        return postMapper.toResponseDto(post);
    }

    @Override
    public PostResponseDTO updatePost(Integer id, PostRequestDTO post) {
        Post actualPost = findPostOrThrow(id);

        //the creation date should not be updated by users

        actualPost.setTitle(post.title());
        actualPost.setContent(post.content());
        actualPost.setTags(post.tags());
        actualPost.setCategory(
            findCategoryOrThrow(
                post.catName()
            )
        );
        actualPost.setUpdatedAt(Instant.now());

        return postMapper.toResponseDto(repo.save(actualPost));
    }

    @Override
    public void deletePost(Integer id) {
        Post post = findPostOrThrow(id);
        repo.delete(post);
    }

    private Post findPostOrThrow(Integer id) {
        return repo.findById(id)
            .orElseThrow(() -> new NotFoundException("No se encontró un post con el id: " + id));
    }

    private Category findCategoryOrThrow(String name) {
        return categoryRepo.findByName(name)
            .orElseThrow(() -> new NotFoundException("Categoría no existe"));
    }

}
