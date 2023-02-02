package com.swacademy.mapcommunity.domain.comment;

import com.swacademy.mapcommunity.data.CommentRepository;
import com.swacademy.mapcommunity.domain.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    Comment registerComment(Comment comment){
        return commentRepository.registerComment(comment);   //수정 필요
    }

    Comment getComment(UUID commentId){
        return commentRepository.getComment(commentId);
    }

    boolean upLike(UUID commentId){
        return commentRepository.upLike(commentId);
    }

    //@Todo 나중에....void deleteComment(){} 추가
}
