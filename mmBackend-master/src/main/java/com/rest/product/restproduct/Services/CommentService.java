package com.rest.product.restproduct.Services;

import com.rest.product.restproduct.entities.Comment;

import java.util.List;

public interface CommentService {

    Comment createComment(Long postId, String postedBy, String content);

    List<Comment> getCommentsByPostId(Long postId);
}