package com.swacademy.mapcommunity.domain.repository;

import com.swacademy.mapcommunity.domain.entity.Comment;
import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.domain.exception.InternalPersistenceException;

/**
 * @author Taehyeon Kim
 * @reference <a href="https://www.baeldung.com/spring-dataIntegrityviolationexception">https://www.baeldung.com/spring-dataIntegrityviolationexception</a>
 * @reference <a href="https://developer-jang.tistory.com/24">https://developer-jang.tistory.com/24</a>
 */
public interface CommentRepository {
    /**
     * Insert comment to repository. The implementer should catch database exceptions and convert them into
     * custom exceptions that are defined in the domain layer or Java Standard Exceptions.
     * @param comment Domain comment entity.
     * @return Return inserted comment id, Long.
     * @throws IllegalArgumentException When there is something wrong with given entity.
     * @throws InternalPersistenceException When an exception is thrown in the data layer.
     */
    Long insertComment(Comment comment) throws IllegalArgumentException, InternalPersistenceException;

    /**
     * Select comment by comment id. The implementer should catch database exceptions and convert them into
     * custom exceptions that are defined in the domain layer or Java Standard Exceptions.
     * @param commentId comment id
     * @return Return comment entity. Not null.
     * @throws IllegalArgumentException When given id is wrong.
     * Include {@link org.springframework.dao.DataRetrievalFailureException DataRetrievalFailureException}.
     */
    Comment selectCommentById(Long commentId) throws IllegalArgumentException;

    /**
     * Select comment with user and post entity by comment id.
     * The implementer should catch database exceptions and convert them into
     * custom exceptions that are defined in the domain layer or Java Standard Exceptions.
     * @param commentId comment id
     * @param getUser Allow EAGER loading of user.
     * @param getPost Allow EAGER loading of user.
     * @return Return the comment entity with the entities specified by the given option. Not null.
     * @throws IllegalArgumentException When given id is wrong.
     * Include {@link org.springframework.dao.DataRetrievalFailureException DataRetrievalFailureException}.
     */
    Comment selectCommentById(Long commentId, boolean getUser, boolean getPost) throws IllegalArgumentException;

    /**
     * Update comment to given comment entity.
     * The implementer should catch database exceptions and convert them into
     * custom exceptions that are defined in the domain layer or Java Standard Exceptions.
     * @param updatedComment
     * @return Return updated comment id, Long.
     * @throws IllegalArgumentException When there is something wrong with given entity.
     * @throws InternalPersistenceException When an exception is thrown in the data layer.
     */
    Long updateComment(Comment updatedComment) throws IllegalArgumentException, InternalPersistenceException;

    /**
     * Delete comment by comment id. The implementer should catch database exceptions and convert them into
     * custom exceptions that are defined in the domain layer or Java Standard Exceptions.
     * @param commentId
     * @return Return the status of the deletion.
     * @throws IllegalArgumentException When given id is wrong.
     * @throws InternalPersistenceException When an exception is thrown in the data layer.
     */
    boolean deleteCommentById(Long commentId) throws IllegalArgumentException, InternalPersistenceException;

    /**
     * Select author of given comment by commentId. It can replace Comment.getUser method.
     * The implementer should catch database exceptions and convert them into
     * custom exceptions that are defined in the domain layer or Java Standard Exceptions.
     * @param commentId comment id
     * @return Return Domain user entity.
     * @throws IllegalArgumentException When given id is wrong.
     * Include {@link org.springframework.dao.DataRetrievalFailureException DataRetrievalFailureException}.
     */
    User selectUserByCommentId(Long commentId) throws IllegalArgumentException;

    /**
     * Select post that contain given comment by commentId. It can replace Comment.getUser method.
     * The implementer should catch database exceptions and convert them into
     * custom exceptions that are defined in the domain layer or Java Standard Exceptions.
     * @param commentId comment id
     * @return Return Domain user entity.
     * @throws IllegalArgumentException When given id is wrong.
     * Include {@link org.springframework.dao.DataRetrievalFailureException DataRetrievalFailureException}.
     */
    Post selectPostByCommentId(Long commentId) throws IllegalArgumentException;
}
