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
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("dataUserMapper")
public class UserMapper {

    private final ModelMapper modelMapper;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;

    /**
     * Using ModelMapper's createTypeMap() method, generating the rules used to map Post tp PostDataEntity.
     * Rules are created using the addMappings() method, and the position field is mapped using pointConverter()
     * @param modelMapper Constructor
     */
    @Autowired
    public UserMapper(ModelMapper modelMapper, PostMapper postMapper, CommentMapper commentMapper) {
        this.modelMapper = modelMapper;
        this.postMapper = postMapper;
        this.commentMapper = commentMapper;

//        modelMapper.createTypeMap(Post.class, PostDataEntity.class)
//                .addMappings(mapper -> mapper.using(pointConverter()).map(Post::getPosition, PostDataEntity::setPosition));
        this.modelMapper.createTypeMap(Location.class, Point.class);
   }

    /**
     * Using Converter -> to customize certain fields during the mapping process.
     * implements the Converter <Location, Point> interface,
     * which converts the Point object received as input into a Point object using a new GeometryFactory and Coordinate.
     * @return Point object
     */
    private Converter<Location, Point> pointConverter() {
        return ctx -> {
            Location location = ctx.getSource();
            GeometryFactory geometryFactory = new GeometryFactory();
            return geometryFactory.createPoint(new Coordinate(location.longitude(), location.latitude()));
        };
    }

    /**
     * The User object has all the posts and comments it has.
     * @param user User
     * @return UserDataEntity
     */
    public UserDataEntity toDataEntity(User user) {
        UserDataEntity userDataEntity = modelMapper.map(user, UserDataEntity.class);
        List<PostDataEntity> postDataEntities = new ArrayList<>();
        for (Post post : user.getPosts()) {
            PostDataEntity postDataEntity = postMapper.toDataEntity(post);
            postDataEntities.add(postDataEntity);
        }
        userDataEntity.setPosts(postDataEntities);

        List<CommentDataEntity> commentDataEntities = new ArrayList<>();
        for (Comment comment : user.getComments()) {
            CommentDataEntity commentDataEntity = commentMapper.toDataEntity(comment);
            commentDataEntities.add(commentDataEntity);
        }
        userDataEntity.setComments(commentDataEntities);

        return userDataEntity;
    }

    /**
     * The User object has all the posts and comments it has.
     * @param userDataEntity UserDataEntity
     * @return User
     */
    public User toEntity(UserDataEntity userDataEntity) {
        User user = modelMapper.map(userDataEntity, User.class);
        List<Post> posts = new ArrayList<>();
        for (PostDataEntity postDataEntity : userDataEntity.getPosts()) {
            Post post = postMapper.toEntity(postDataEntity);
            posts.add(post);
        }
        user.setPosts(posts);

        List<Comment> comments = new ArrayList<>();
        for (CommentDataEntity commentDataEntity : userDataEntity.getComments()) {
            Comment comment = commentMapper.toEntity(commentDataEntity);
            comments.add(comment);
        }
        user.setComments(comments);

        return user;
    }
}
