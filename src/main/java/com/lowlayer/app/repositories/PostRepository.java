package com.lowlayer.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lowlayer.app.model.Post;

@Repository
public interface PostRepository extends CrudRepository<Post,Integer>{

}
