package com.swacademy.mapcommunity.domain.repository;

import com.swacademy.mapcommunity.domain.entity.Comment;
import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.domain.exception.InternalPersistenceException;

import java.util.List;

/**
 * @author Taehyeon Kim
 * @reference <a href="https://www.baeldung.com/spring-dataIntegrityviolationexception">https://www.baeldung.com/spring-dataIntegrityviolationexception</a>
 * @reference <a href="https://developer-jang.tistory.com/24">https://developer-jang.tistory.com/24</a>
 */
public interface UserRepository {
    /**
     * Insert user to repository. The implementer should catch database exceptions and convert them into
     * custom exceptions that are defined in the domain layer or Java Standard Exceptions.
     * @param user Domain user entity.
     * @return Return inserted user id, Long.
     * @throws IllegalArgumentException When there is something wrong with given entity.
     * @throws InternalPersistenceException When an exception is thrown in the data layer.
     */
    Long insertUser(User user) throws IllegalArgumentException, InternalPersistenceException;

    /**
     * Select user by user id. The implementer should catch database exceptions and convert them into
     * custom exceptions that are defined in the domain layer or Java Standard Exceptions.
     * @param userId
     * @return Return user entity. Not null.
     * @throws IllegalArgumentException When given id is wrong.
     * Include {@link org.springframework.dao.DataRetrievalFailureException DataRetrievalFailureException}.
     */
    User selectUserById(Long userId) throws IllegalArgumentException;

    /**
     * Select user with post entities and comment entities by user id.
     * The implementer should catch database exceptions and convert them into
     * custom exceptions that are defined in the domain layer or Java Standard Exceptions.
     * @param userId user id
     * @param getPosts Allow EAGER loading of posts.
     * @param getComments Allow EAGER loading of comments.
     * @return Return user entity with the entities specified by the given option. Not null.
     * @throws IllegalArgumentException When given id is wrong.
     * Include {@link org.springframework.dao.DataRetrievalFailureException DataRetrievalFailureException}.
     */
    User selectUserById(Long userId, boolean getPosts, boolean getComments) throws IllegalArgumentException;

    /**
     * Update user to given user entity. The implementer should catch database exceptions and convert them into
     * custom exceptions that are defined in the domain layer or Java Standard Exceptions.
     * @param updatedUser
     * @return Return updated user id, Long.
     * @throws IllegalArgumentException When there is something wrong with given entity.
     * @throws InternalPersistenceException When an exception is thrown in the data layer.
     */
    Long updateUser(User updatedUser) throws IllegalArgumentException, InternalPersistenceException;

    /**
     * Delete user by user id. The implementer should catch database exceptions and convert them into
     * custom exceptions that are defined in the domain layer or Java Standard Exceptions.
     * @param userId
     * @return Return the status of the deletion.
     * @throws IllegalArgumentException When given id is wrong.
     * @throws InternalPersistenceException When an exception is thrown in the data layer.
     */
    boolean deleteUserById(Long userId) throws IllegalArgumentException, InternalPersistenceException;

    /**
     * Select posts created by user with userId. It can replace User.getPosts method.
     * The implementer should catch database exceptions and convert them into
     * custom exceptions that are defined in the domain layer or Java Standard Exceptions.
     * @param userId user id
     * @return Return list of posts.
     * @throws IllegalArgumentException When given id is wrong.
     * Include {@link org.springframework.dao.DataRetrievalFailureException DataRetrievalFailureException}.
     */
    List<Post> selectPostsByUserId(Long userId) throws IllegalArgumentException;

    /**
     * Select comments created by user with userId. It can replace User.getComments method.
     * The implementer should catch database exceptions and convert them into
     * custom exceptions that are defined in the domain layer or Java Standard Exceptions.
     * @param userId user id
     * @return Return list of comments.
     * @throws IllegalArgumentException When given id is wrong.
     * Include {@link org.springframework.dao.DataRetrievalFailureException DataRetrievalFailureException}.
     */
    List<Comment> selectCommentsByUserId(Long userId) throws IllegalArgumentException;

}
