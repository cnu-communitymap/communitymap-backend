package com.swacademy.mapcommunity.data;

import com.swacademy.mapcommunity.data.entity.PostDataEntity;
import com.swacademy.mapcommunity.data.repository.PostJpaRepository;
import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.domain.entity.Comment;
import com.swacademy.mapcommunity.domain.entity.Location;
import com.swacademy.mapcommunity.domain.exception.PersistenceInternalException;
import com.swacademy.mapcommunity.domain.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepositoryImpl implements PostRepository {

    private final PostJpaRepository postRepository;

    public PostRepositoryImpl(PostJpaRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Override
    @Transactional
    public Long insertPost(Post post) throws IllegalArgumentException, PersistenceInternalException {
        try {
            PostDataEntity postDataEntity = new PostDataEntity();
            postRepository.save(postDataEntity);
            postRepository.findById(null);
            return 1L;
        } catch (DataAccessException dataAccessException) {
            throw new PersistenceInternalException("dataAccessException");
        }
//        throw new EntityNotFoundException();
//        return postRepository.getReferenceById(post.getId()).getId();
//        throw new IllegalArgumentException();
    }

    @Override
    public Post selectPostById(Long postId) {
        return null;
    }

    @Override
    public Post selectPostById(Long postId, boolean getUser, boolean getComments) {
        return null;
    }

    @Override
    public Long updatePost(Post updatedPost) {
        return null;
    }

    @Override
    public boolean deletePostById(Long postId) {
        return false;
    }

    @Override
    public List<Comment> selectCommentsByPostId(Long postId) {
        return null;
    }

    @Override
    public User selectUserByPostId(Long postId) {
        return null;
    }

    @Override
    public List<Post> selectPostByLocation(Location location, double allowRange) {
        return null;
    }
}
