package com.swacademy.mapcommunity.data;

import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.repository.PostRepository;
import com.swacademy.mapcommunity.domain.vo.Position;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.sql.Timestamp;
import java.util.*;

// @TODO Complete implementation.
@Repository
public class PostRepositoryImpl implements PostRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    // @TODO Spring JPA, Hibernate Spatial 알아보기.
    private final String INSERT_POST_SQL = "INSERT INTO post(" +
            "post_id, user_id, title, content, post_date, `like`, coordinate" +
            ") VALUES (:postId, :userId, :title, :content, :postDate, :like, ST_GeometryFromText('POINT(:latitude :longitude)'))";
    private final String UPDATE_POST_SQL = "UPDATE post SET set title = :title, content = :content, like = :like" +
            "WHERE post_id = :postId";
    private final String SELECT_POST_BY_ID_SQL = "SELECT * FROM post WHERE post_id = :postId";
    private final String SELECT_POST_BY_POSITION = null;
    private final String DELETE_POST = "DELETE FROM post WHERE post_id = :postId";


    public PostRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Post insertPost(Post post) throws DataAccessException {
        Map<String, Object> paramMap = new HashMap<>() {{
            put("postId", post.getPostId().toString().getBytes());
            put("userId", post.getUserId().toString().getBytes());
            put("title", post.getTitle());
            put("content", post.getContent());
            put("postDate", Timestamp.valueOf(post.getPostDate()));
            put("like", post.getLike());
            put("latitude", post.getPosition().latitude());
            put("longitude", post.getPosition().longitude());
        }};
        jdbcTemplate.update(INSERT_POST_SQL, paramMap);
        return null;
    }

    @Override
    public Post updatePost(Post post) {
        return null;
    }

    @Override
    public void deletePost(UUID postId) {

    }

    @Override
    public Optional<Post> getPostById(UUID postId) {
        return Optional.empty();
    }

    // @TODO Spring JPA, Hibernate Spatial 알아보기.
    @Override
    public List<Post> getPostsByPosition(Position position) {
        return null;
    }

    static UUID toUUID(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN);
        return new UUID(byteBuffer.getLong(), byteBuffer.getLong());
    }

    public static void main(String[] args) {
        HikariDataSource dataSource = DataSourceBuilder.create()
                .url("jdbc:mysql://localhost/post")
                .username("admin")
                .password("1q2w3e4r")
                .type(HikariDataSource.class)
                .build();
        PostRepositoryImpl postRepository = new PostRepositoryImpl(new NamedParameterJdbcTemplate(dataSource));
        Post newPost = new Post(UUID.randomUUID(), UUID.randomUUID(), "title", "content", new Position(20.0, 100.0));
        postRepository.insertPost(newPost);
    }
}
