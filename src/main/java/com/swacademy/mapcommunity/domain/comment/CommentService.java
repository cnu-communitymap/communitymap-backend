package com.swacademy.mapcommunity.domain.comment;

import com.swacademy.mapcommunity.domain.repository.CommentRepository;
import com.swacademy.mapcommunity.domain.entity.Comment;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
@Service
public class CommentService {
//    private final CommentRepository commentRepository;
//
//    public CommentService(CommentRepository commentRepository) {
//        this.commentRepository = commentRepository;
//    }
//
//    /**
//     *
//     * @param commentId  UUID
//     * @param registerdPostId UUID
//     * @param userId UUID
//     * @param commentContent not null. repository에서 not null처리
//     * @param commentDate Date
//     * @param like int, not negative
//     * @return Comment
//     * @return If failed ? return null : return Comment
//     */
//    @Nullable
//    public Comment registerComment(UUID commentId, UUID registerdPostId, UUID userId, String commentContent, Date commentDate, int like){
//        Comment comment = new Comment(commentId, registerdPostId, userId, commentContent, commentDate, like);
//        try{
//            return commentRepository.insert(comment);
//        } catch (RuntimeException e) {  //@Todo 예외 정의
//            return null;
//        }
//    }
//
//    @Nullable
//    public Comment getComment(UUID commentId){
//        try{
//            var comment = commentRepository.getCommentById(commentId);
//            if (comment.isPresent()) return comment.get();
//            else return null;
//        } catch (RuntimeException e) { //@Todo 예외 정의
//            return null;
//        }
//    }
//
//    Comment updateComment(Comment comment){
//        return comment;
//    }
//
//    //@Todo 반환 type void...? boolean...? 다른 것...?
//    void deleteCommentById(UUID commentId){
//        commentRepository.deleteById(commentId);
//    }
//
//    //@Todo 반환 type 다시 생각
//    /**
//     *
//     * @param commentId 존재유무 판별은 repository에서 throw던지도록 처리
//     * @return void로 하고 upLike를 repository에서 try catch 처리
//     */
//    void upLike(UUID commentId){
//        commentRepository.upLike(commentId);
//    }

}
