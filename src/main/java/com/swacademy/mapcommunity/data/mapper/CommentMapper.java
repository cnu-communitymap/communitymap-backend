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

@Component
public class CommentMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public CommentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
   }

    /**
     * Since it has a Post object as a field, it is necessary to additionally set a position with a different type.
     * @param comment Comment
     * @return CommentDataEntity
     */
    public CommentDataEntity toDataEntity(Comment comment) {
        CommentDataEntity commentDataEntity = modelMapper.map(comment, CommentDataEntity.class);
        Location position = comment.getPost().getPosition();
        if (position != null) {  //Since the type is different, it must be specified separately.
            PostDataEntity postDataEntity = commentDataEntity.getPost();
            postDataEntity.setPosition(new GeometryFactory().createPoint(new Coordinate(position.longitude(), position.latitude())));
            commentDataEntity.setPost(postDataEntity);
        }
        return commentDataEntity;
    }

    /**
     * Since it has a Post object as a field, it is necessary to additionally set a position with a different type.
     * @param commentDataEntity CommentDataEntity
     * @return Comment
     */
    public Comment toEntity(CommentDataEntity commentDataEntity) {
        Comment comment = modelMapper.map(commentDataEntity, Comment.class);
        Point point = commentDataEntity.getPost().getPosition();
        if(point != null) {
            Post post = comment.getPost();
            post.setPosition(new Location(point.getY(), point.getX()));
            comment.setPost(post);
        }
        return comment;
    }
}
