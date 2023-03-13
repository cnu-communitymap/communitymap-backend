package com.swacademy.mapcommunity.data.mapper;

import com.swacademy.mapcommunity.data.entity.CommentDataEntity;
import com.swacademy.mapcommunity.data.entity.PostDataEntity;
import com.swacademy.mapcommunity.data.entity.UserDataEntity;
import com.swacademy.mapcommunity.domain.entity.Comment;
import com.swacademy.mapcommunity.domain.entity.Location;
import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.entity.User;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
   }

    /**
     * The User object has all the posts and comments it has.
     * @param user User
     * @return UserDataEntity
     */
    public UserDataEntity toDataEntity(User user) {
        UserDataEntity userDataEntity = modelMapper.map(user, UserDataEntity.class);

        //Get all posts and process them
        List<Post> posts = user.getPosts();
        List<PostDataEntity> postDataEntities = new ArrayList<>();
        for(Post post: posts) {
            Location location = post.getPosition();
            if (location != null) {
                PostDataEntity postDataEntity = new PostDataEntity();
                postDataEntity.setId(post.getId());
                postDataEntity.setTitle(post.getTitle());
                postDataEntity.setContent(post.getContent());
                postDataEntity.setPostLike(post.getPostLike());
                postDataEntity.setCreatedAt(post.getCreatedAt());
                postDataEntity.setUpdatedAt(post.getUpdatedAt());
                postDataEntity.setPosition(new GeometryFactory().createPoint(new Coordinate(location.longitude(), location.latitude())));
                postDataEntity.setUser(userDataEntity);

                // map comments for this post
                List<Comment> comments = post.getComments();
                List<CommentDataEntity> commentDataEntities = new ArrayList<>();
                for (Comment comment : comments) {
                    CommentDataEntity commentDataEntity = new CommentDataEntity();
                    commentDataEntity.setId(comment.getId());
                    commentDataEntity.setContent(comment.getContent());
                    commentDataEntity.setCreatedAt(comment.getCreatedAt());
                    commentDataEntity.setUpdatedAt(comment.getUpdatedAt());
                    commentDataEntity.setPost(postDataEntity);
                    commentDataEntity.setUser(userDataEntity);
                    commentDataEntities.add(commentDataEntity);
                }
                postDataEntity.setComments(commentDataEntities);

                postDataEntities.add(postDataEntity);
            }
        }
        userDataEntity.setPosts(postDataEntities);

        return userDataEntity;
    }

    /**
     * The User object has all the posts and comments it has.
     * @param userDataEntity UserDataEntity
     * @return User
     */
    public User toEntity(UserDataEntity userDataEntity) {
        User user = modelMapper.map(userDataEntity, User.class);
        List<PostDataEntity> postDataEntities = userDataEntity.getPosts();
        List<Post> posts = new ArrayList<>();
        for (PostDataEntity postDataEntity : postDataEntities) {
            Point position = postDataEntity.getPosition();
            if (position != null) {
                Post post = new Post();
                post.setId(postDataEntity.getId());
                post.setTitle(postDataEntity.getTitle());
                post.setContent(postDataEntity.getContent());
                post.setPostLike(postDataEntity.getPostLike());
                post.setCreatedAt(postDataEntity.getCreatedAt());
                post.setUpdatedAt(postDataEntity.getUpdatedAt());
                post.setPosition(new Location(position.getY(), position.getX()));
                post.setUser(user);

                //Process Comment object
                List<CommentDataEntity> commentDataEntities = postDataEntity.getComments();
                List<Comment> comments = new ArrayList<>();
                for (CommentDataEntity commentDataEntity : commentDataEntities) {
                    Comment comment = new Comment();
                    comment.setId(commentDataEntity.getId());
                    comment.setCommentLike(commentDataEntity.getCommentLike());
                    comment.setContent(commentDataEntity.getContent());
                    comment.setCreatedAt(commentDataEntity.getCreatedAt());
                    comment.setUpdatedAt(commentDataEntity.getUpdatedAt());
                    comment.setUser(user);
                    comments.add(comment);
                }
                post.setComments(comments);

                posts.add(post);
            }
        }
        user.setPosts(posts);

        return user;
    }
}
