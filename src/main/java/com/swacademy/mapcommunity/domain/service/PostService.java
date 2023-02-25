package com.swacademy.mapcommunity.domain.service;

import com.swacademy.mapcommunity.domain.repository.PostRepository;
import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.vo.Position;
import com.swacademy.mapcommunity.dto.PostDto;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    ModelMapper modelMapper = new ModelMapper();

    // @TODO createPost 함수가 필요한지 생각해보기.

    /**
     * @param postDto PostDto
     */
    @Transactional
    public void registerPost(PostDto postDto) {
        // @TODO ADD Authorization logic

        //1. dto -> Entity (준영속 상태 객체)
        Post post = modelMapper.map(postDto, Post.class);

        Post entity = postRepository.save(post);
    }

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
//    @Nullable
//    public Post registerPost(UUID userId, String title, String content, LocalDateTime postDate, int like, Position position) {
//        // @TODO ADD Authorization logic
//        // @TODO 일단 Authorization은 고려하지 않고 구현한다.
//        // @TODO UUID.randomUUID -> 현재 UUID를 MySQL의 Primary Key에 맞춰서 생성해야 하는지 감이 잡히지 않아 임시 구현. 바꿔야 함.
//        // @TODO postRepository.registerPost의 구현도 다시 생각해봐야 함. Post를 생성해서 넘겨야 하는지 내용만 넘기는지.
//        Post newPost = new Post(UUID.randomUUID(), userId, title, content, postDate, like, position);
//        try {
//            return postRepository.insertPost(newPost);
//        } catch (DataAccessException e) {  // @TODO 예외 직접 정의하기.
//            return null;
//        }
//    }

    /**
     * @param postId UUID
     * @return Deletion is succeeded or not: boolean
     */
    public boolean deletePost(int postId) {
        // @TODO ADD Authorization logic
        try {
            postRepository.deleteById(postId);
            return true;
        } catch (DataAccessException e) {  // @TODO 예외 직접 정의하기.
            return false;
        }
    }
//
//    /**
//     * @param postId UUID
//     * @return If postId is invalid id, return null. Else return Post object.
//     */
//    @Nullable
//    public Post getPost(int postId) {
//        try {
//            var post = postRepository.findById(postId);
//            return post.orElse(null);
//        } catch (DataAccessException e) {  // @TODO 예외 직접 정의하기.
//            return null;
//        }
//    }

    /**
     * Returns the posts in a range based on the current location.
     * @param position Position
     * @return If failed, return null. Else returns list of Post.
     * @see Position
     */
    /*

    @Nullable
    public List<Post> getPostsByPosition(Position position) {
        // @TODO MySQL에서 제공하는 함수로 검색할지, 여기서 범위를 지정하여 검색할지 결정.
        try {
            return postRepository.findByPosition(position);  //Custom
        } catch (DataAccessException e) {  // @TODO 예외 직접 정의하기.
            return null;
        }
    }
    */

    /**
     * Add 1 like to the post with passed postId.
     * @param postId UUID
     * @return Like is successfully added or not.
     */

//    public Post upLike(int postId) {
//        // @TODO ADD Authorization logic
//        Post targetPost = this.getPost(postId);
//        if (targetPost == null) return null;
//        targetPost.upLike();    //@Todo Custom method 정의
//        try {
//            return postRepository.updatePost(targetPost);
//        } catch (DataAccessException e) {  // @TODO 예외 직접 정의하기.
//            return null;
//        }
//    }
}
