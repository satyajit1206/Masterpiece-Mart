package com.rest.product.restproduct.controllers;

import com.rest.product.restproduct.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("comments/create")
    public ResponseEntity<?> createComment(@RequestParam Long postId, @RequestParam String postedBy, @RequestParam String content) {
        try {
            return ResponseEntity.ok(commentService.createComment(postId, postedBy, content));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }


    @GetMapping("comments/{postId}")
    public ResponseEntity<?> getCommentsByPostId(@PathVariable Long postId){
        try{
            return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong");
        }
    }
}