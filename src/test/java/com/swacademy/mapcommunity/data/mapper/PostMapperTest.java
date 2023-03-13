package com.swacademy.mapcommunity.data.mapper;

import com.swacademy.mapcommunity.MapcommunityBackendApplication;
import com.swacademy.mapcommunity.data.entity.PostDataEntity;
import com.swacademy.mapcommunity.data.entity.UserDataEntity;
import com.swacademy.mapcommunity.domain.entity.Comment;
import com.swacademy.mapcommunity.domain.entity.Location;
import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = MapcommunityBackendApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("local")
class PostMapperTest {

    private final ModelMapper modelMapper = new ModelMapper();
    private final PostMapper postMapper = new PostMapper(modelMapper);

    @Test
    @DisplayName("Object mapper test with User, Post")
    public void testToDataEntity() {
        //Given
        //Create a Post object to convert
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Test Title");
        post.setContent("Test Content");
        post.setPostLike(0);
        post.setPosition(new Location(37.123456, 127.123456));

        //Create a User Object to convert using FK
        User user = new User();
        user.setId(200010101L);
        user.setEmail("test@gmail.com");
        user.setPassword("test123");
        user.setNickname("tester");
        user.setGender(com.swacademy.mapcommunity.domain.entity.Gender.MALE);
        user.setBirth(LocalDate.of(2000, 1, 1));

        post.setUser(user);

        //When
        //Convert the Post object to a PostDataEntity object using the PostMapper
        PostDataEntity postDataEntity = postMapper.toDataEntity(post);

        //Then
        //Verify that the PostDataEntity object has the expected values
        assertEquals(post.getId(), postDataEntity.getId());
        assertEquals(post.getTitle(), postDataEntity.getTitle());
        assertEquals(post.getContent(), postDataEntity.getContent());
        assertEquals(post.getPostLike(), postDataEntity.getPostLike());
        assertEquals(postDataEntity.getPosition().getX(), post.getPosition().longitude()); //Point.X = Location.longitude
        assertEquals(postDataEntity.getPosition().getY(), post.getPosition().latitude());  //Point.Y = Location.latitude
        System.out.println("Post position: "+ post.getPosition()); //Location(Latitude, longitude)
        System.out.println("PostDataEntity position: "+ postDataEntity.getPosition()); //POINT(longitude, latitude)

        assertEquals(user.getId(), postDataEntity.getUser().getId());
    }

    @Test
    @DisplayName("Object mapper test with PostDataEntity, UserDataEntity")
    public void testToEntity() {
        //Given
        //Create a PostDataEntity object to convert
        PostDataEntity postDataEntity = new PostDataEntity();
        postDataEntity.setId(2L);
        postDataEntity.setTitle("Test Title");
        postDataEntity.setContent("Test Content");
        postDataEntity.setPostLike(0);
        postDataEntity.setPosition(new Location(37.123456, 127.123456).asPoint());

        //Create a UserDataEntity object to convert use FK
        UserDataEntity userDataEntity = new UserDataEntity();
        userDataEntity.setId(20200202L);
        userDataEntity.setEmail("test@test.com");
        userDataEntity.setPassword("password");
        userDataEntity.setNickname("nickname");
        userDataEntity.setGender(com.swacademy.mapcommunity.data.entity.Gender.MALE);
        userDataEntity.setBirth(LocalDate.of(2000, 1, 1));

        postDataEntity.setUser(userDataEntity);

        //When
        //Convert the PostDataEntity object to a Post object using the PostMapper
        Post post = postMapper.toEntity(postDataEntity);

        //Then
        //Verify that the Post object has the expected values
        assertEquals(postDataEntity.getId(), post.getId());
        assertEquals(postDataEntity.getTitle(), post.getTitle());
        assertEquals(postDataEntity.getContent(), post.getContent());
        assertEquals(postDataEntity.getPostLike(), post.getPostLike());
        assertEquals(postDataEntity.getPosition().getX(), post.getPosition().longitude()); //Point.X = Location.longitude
        assertEquals(postDataEntity.getPosition().getY(), post.getPosition().latitude());  //Point.Y = Location.latitude

        System.out.println("Post position: "+ post.getPosition()); //Location(Latitude, longitude)
        System.out.println("PostDataEntity position: "+ postDataEntity.getPosition()); //POINT(longitude, latitude)
        assertEquals(userDataEntity.getId(), post.getUser().getId());
    }

    @Test
    @DisplayName("Object mapper test with User, Post, Comment")
    public void toEntityHaveComment() {
        //Given
        //Create a Post object to convert
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Test Title");
        post.setContent("Test Content");
        post.setPostLike(0);
        post.setPosition(new Location(37.123456, 127.123456));

        //Create a User Object to convert using FK
        User user = new User();
        user.setId(200010101L);
        user.setEmail("test@gmail.com");
        user.setPassword("test123");
        user.setNickname("tester");
        user.setGender(com.swacademy.mapcommunity.domain.entity.Gender.MALE);
        user.setBirth(LocalDate.of(2000, 1, 1));

        post.setUser(user);

        Comment comment = new Comment();
        comment.setId(1L);
        comment.setContent("hi");
        comment.setUser(user);
        comment.setPost(post);
        comment.setCommentLike(0);

        //When
        //Convert the Post object to a PostDataEntity object using the PostMapper
        PostDataEntity postDataEntity = postMapper.toDataEntity(post);

        //Then
        //Verify that the PostDataEntity object has the expected values
        assertEquals(post.getId(), postDataEntity.getId());
        assertEquals(post.getTitle(), postDataEntity.getTitle());
        assertEquals(post.getContent(), postDataEntity.getContent());
        assertEquals(post.getPostLike(), postDataEntity.getPostLike());
        assertEquals(postDataEntity.getPosition().getX(), post.getPosition().longitude()); //Point.X = Location.longitude
        assertEquals(postDataEntity.getPosition().getY(), post.getPosition().latitude());  //Point.Y = Location.latitude
        System.out.println("Post position: "+ post.getPosition()); //Location(Latitude, longitude)
        System.out.println("PostDataEntity position: "+ postDataEntity.getPosition()); //POINT(longitude, latitude)

        assertEquals(user.getId(), postDataEntity.getUser().getId());
    }

}
