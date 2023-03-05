package com.swacademy.mapcommunity.domain.repository;

import com.swacademy.mapcommunity.domain.entity.Comment;
import com.swacademy.mapcommunity.domain.entity.Location;
import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.domain.exception.PersistenceInternalException;

import java.util.List;

/**
 * @author Taehyeon Kim
 * @reference <a href="https://www.baeldung.com/spring-dataIntegrityviolationexception">https://www.baeldung.com/spring-dataIntegrityviolationexception</a>
 * @reference <a href="https://developer-jang.tistory.com/24">https://developer-jang.tistory.com/24</a>
 */
public interface PostRepository {
    /**
     * Insert post to repository. The implementer should catch database exceptions and convert them into
     * custom exceptions that are defined in the domain layer or Java Standard Exceptions.
     * @param post Domain post entity.
     * @return Return inserted post id, Long.
     * @throws IllegalArgumentException When there is something wrong with given entity.
     * @throws PersistenceInternalException When an exception is thrown in the data layer.
     */
    Long insertPost(Post post) throws IllegalArgumentException, PersistenceInternalException;

    /**
     * Select post by post id. The implementer should catch database exceptions and convert them into
     * custom exceptions that are defined in the domain layer or Java Standard Exceptions.
     * @param postId post id
     * @return Return post entity. Not null.
     * @throws IllegalArgumentException When given id is wrong.
     * Include {@link org.springframework.dao.DataRetrievalFailureException DataRetrievalFailureException}.
     */
    Post selectPostById(Long postId) throws IllegalArgumentException;

    /**
     * Select post with user entity and comment entities by post id.
     * The implementer should catch database exceptions and convert them into
     * custom exceptions that are defined in the domain layer or Java Standard Exceptions.
     * @param postId post id
     * @param getUser Allow EAGER loading of user.
     * @param getComments Allow EAGER loading of comments.
     * @return Return the post entity with the entities specified by the given option. Not null.
     * @throws IllegalArgumentException When given id is wrong.
     * Include {@link org.springframework.dao.DataRetrievalFailureException DataRetrievalFailureException}.
     */
    Post selectPostById(Long postId, boolean getUser, boolean getComments) throws IllegalArgumentException;

    /**
     * Update post to given post entity. The implementer should catch database exceptions and convert them into
     * custom exceptions that are defined in the domain layer or Java Standard Exceptions.
     * @param updatedPost updated post entity
     * @return Return updated post id, Long.
     * @throws IllegalArgumentException When there is something wrong with given entity.
     * @throws PersistenceInternalException When an exception is thrown in the data layer.
     */
    Long updatePost(Post updatedPost) throws IllegalArgumentException, PersistenceInternalException;

    /**
     * Delete post by post id. The implementer should catch database exceptions and convert them into
     * custom exceptions that are defined in the domain layer or Java Standard Exceptions.
     * @param postId post id
     * @return Return the status of the deletion.
     * @throws IllegalArgumentException When there is something wrong with given entity.
     * @throws PersistenceInternalException When an exception is thrown in the data layer.
     */
    boolean deletePostById(Long postId) throws IllegalArgumentException, PersistenceInternalException;

    /**
     * Select comments on post with PostId. It can be replaced Post.getComments method.
     * The implementer should catch database exceptions and convert them into
     * custom exceptions that are defined in the domain layer or Java Standard Exceptions.
     * @param postId post id
     * @return Return list of comments.
     * @throws IllegalArgumentException When given id is wrong.
     * Include {@link org.springframework.dao.DataRetrievalFailureException DataRetrievalFailureException}.
     */
    List<Comment> selectCommentsByPostId(Long postId) throws IllegalArgumentException;

    /**
     * Select author of given post by postId. It can be replaced Post.getUser method.
     * The implementer should catch database exceptions and convert them into
     * custom exceptions that are defined in the domain layer or Java Standard Exceptions.
     * @param postId post id
     * @return Return Domain user entity. Not null.
     * @throws IllegalArgumentException When given id is wrong.
     * Include {@link org.springframework.dao.DataRetrievalFailureException DataRetrievalFailureException}.
     */
    User selectUserByPostId(Long postId) throws IllegalArgumentException;

    /**
     * Select posts by location.
     * The implementer should catch database exceptions and convert them into
     * custom exceptions that are defined in the domain layer or Java Standard Exceptions.
     * @param location Domain location value object.
     * @param allowRange Allow range based on location, meter.
     * @return Return list of posts around the given location.
     * @throws IllegalArgumentException When given id is wrong.
     * Include {@link org.springframework.dao.DataRetrievalFailureException DataRetrievalFailureException}.
     */
    List<Post> selectPostByLocation(Location location, double allowRange) throws IllegalArgumentException;

}
