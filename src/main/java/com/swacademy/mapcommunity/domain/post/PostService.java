package com.swacademy.mapcommunity.domain.post;

import com.swacademy.mapcommunity.domain.repository.PostRepository;
import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.vo.Position;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // @TODO createPost 함수가 필요한지 생각해보기.

    /**
     * @param userId UUId
     * @param title String, not null.
     * @param content String
     * @param postDate LocalDateTime
     * @param like int, cannot be negative.
     * @param position Position, value object.
     * @return If failed, return null. Else return Post object.
     * @see Position
     */
    @Nullable
    public Post registerPost(UUID userId, String title, String content, LocalDateTime postDate, int like, Position position) {
        // @TODO ADD Authorization logic
        // @TODO 일단 Authorization은 고려하지 않고 구현한다.
        // @TODO UUID.randomUUID -> 현재 UUID를 MySQL의 Primary Key에 맞춰서 생성해야 하는지 감이 잡히지 않아 임시 구현. 바꿔야 함.
        // @TODO postRepository.registerPost의 구현도 다시 생각해봐야 함. Post를 생성해서 넘겨야 하는지 내용만 넘기는지.
        Post newPost = new Post(UUID.randomUUID(), userId, title, content, postDate, like, position);
        try {
            return postRepository.insertPost(newPost);
        } catch (RuntimeException e) {  // @TODO 예외 직접 정의하기.
            return null;
        }
    }

    /**
     * @param userId UUId
     * @param title String, not null.
     * @param content String
     * @param position Position, value object.
     * @return If failed, return null. Else return Post object.
     */
    @Nullable
    public Post registerPost(UUID userId, String title, String content, Position position) {
        Post newPost = new Post(UUID.randomUUID(), userId, title, content, LocalDateTime.now(), 0, position);
        try {
            return postRepository.insertPost(newPost);
        } catch (RuntimeException e) {  // @TODO 예외 직접 정의하기.
            return null;
        }
    }

    /**
     * @param postId
     * @return Deletion is succeeded or not: boolean
     */
    public boolean deletePost(UUID postId) {
        // @TODO ADD Authorization logic
        try {
            postRepository.deletePost(postId);
            return true;
        } catch (RuntimeException e) {  // @TODO 예외 직접 정의하기.
            return false;
        }
    }

    /**
     * @param postId UUID
     * @return If postId is invalid id, return null. Else return Post object.
     */
    @Nullable
    public Post getPost(UUID postId) {
        try {
            var post = postRepository.getPostById(postId);
            if (post.isEmpty()) return null;
            else return post.get();
        } catch (RuntimeException e) {  // @TODO 예외 직접 정의하기.
            return null;
        }
    }

    /**
     * Returns the posts in a range based on the current location.
     * @param position Position
     * @return If failed, return null. Else returns list of Post.
     * @see Position
     */
    @Nullable
    public List<Post> getPostsByPosition(Position position) {
        // @TODO MySQL에서 제공하는 함수로 검색할지, 여기서 범위를 지정하여 검색할지 결정.
        try {
            return postRepository.getPostsByPosition(position);
        } catch (RuntimeException e) {  // @TODO 예외 직접 정의하기.
            return null;
        }
    }

    /**
     * Add 1 like to the post with passed postId.
     * @param postId UUID
     * @return Like is successfully added or not.
     */
    public Post upLike(UUID postId) {
        // @TODO ADD Authorization logic
        Post targetPost = this.getPost(postId);
        if (targetPost == null) return null;
        targetPost.upLike();
        try {
            return postRepository.updatePost(targetPost);
        } catch (RuntimeException e) {  // @TODO 예외 직접 정의하기.
            return null;
        }
    }
}
