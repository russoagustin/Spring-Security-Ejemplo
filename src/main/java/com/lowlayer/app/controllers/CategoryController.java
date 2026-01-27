package com.lowlayer.app.controllers;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lowlayer.app.model.Category;
import com.lowlayer.app.repositories.CategoryRepository;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    private final CategoryRepository repo;

    public CategoryController(CategoryRepository repo){
        this.repo = repo;
    }

    @GetMapping
    public ResponseEntity<Set<Category>> getAll(){
        Set<Category> categories = StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toSet());

        return ResponseEntity.ok(categories);
    }
}
