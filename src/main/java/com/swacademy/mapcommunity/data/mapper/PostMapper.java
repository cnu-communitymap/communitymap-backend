package com.swacademy.mapcommunity.data.mapper;

import com.swacademy.mapcommunity.data.entity.CommentDataEntity;
import com.swacademy.mapcommunity.data.entity.PostDataEntity;
import com.swacademy.mapcommunity.domain.entity.Comment;
import com.swacademy.mapcommunity.domain.entity.Location;
import com.swacademy.mapcommunity.domain.entity.Post;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostMapper {

   private final ModelMapper modelMapper;
   private final CommentMapper commentMapper;

   @Autowired
   public PostMapper(ModelMapper modelMapper, CommentMapper commentMapper) {
       this.modelMapper = modelMapper;
       this.commentMapper = commentMapper;
   }

    /**
     * Additional definition is required because the types of the position field of the Post object and PostDataEntity are different.
     * @param post Post
     * @return PostDataEntity
     */
    public PostDataEntity toDataEntity(Post post) {
        PostDataEntity postDataEntity = modelMapper.map(post, PostDataEntity.class);

        Location position = post.getPosition();
        if (position != null) {
            postDataEntity.setPosition(new GeometryFactory().createPoint(new Coordinate(position.longitude(), position.latitude())));
        }
        List<CommentDataEntity> commentDataEntities = new ArrayList<>();
        for (Comment comment : post.getComments()) {
            CommentDataEntity commentDataEntity = commentMapper.toDataEntity(comment);
            commentDataEntities.add(commentDataEntity);
        }
        postDataEntity.setComments(commentDataEntities);

        return postDataEntity;
    }

    /**
     * Additional definition is required because the types of the position field of the Post object and PostDataEntity are different.
     * @param postDataEntity PostDataEntity
     * @return Post
     */
    public Post toEntity(PostDataEntity postDataEntity) {
        Post post = modelMapper.map(postDataEntity, Post.class);  //mapping

        Point point = postDataEntity.getPosition();
        if(point != null) {
            post.setPosition(new Location(point.getY(), point.getX()));
        }

        List<Comment> comments = new ArrayList<>();
        for (CommentDataEntity commentDataEntity : postDataEntity.getComments()) {
            Comment comment = commentMapper.toEntity(commentDataEntity);
            comments.add(comment);
        }
        post.setComments(comments);

        return post;
    }
}
