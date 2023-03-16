package com.swacademy.mapcommunity.data.mapper;

import com.swacademy.mapcommunity.data.entity.PostDataEntity;
import com.swacademy.mapcommunity.domain.entity.Location;
import com.swacademy.mapcommunity.domain.entity.Post;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

   private final ModelMapper modelMapper;

   @Autowired
   public PostMapper(ModelMapper modelMapper) {
       this.modelMapper = modelMapper;
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

        return post;
    }
}
