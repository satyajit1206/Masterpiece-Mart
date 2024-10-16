package com.rest.product.restproduct.Services;

import com.rest.product.restproduct.entities.Post;

import java.util.List;

public interface PostService {

    Post savePost(Post post);

    List<Post> getAllPost();

    Post getPostById(Long postId);

    void likePost(Long postId);

    List<Post> searchByName(String name);

}