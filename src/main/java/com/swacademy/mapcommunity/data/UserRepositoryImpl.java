package com.swacademy.mapcommunity.data;

import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.domain.repository.UserRepository;
import com.swacademy.mapcommunity.domain.vo.Gender;
import com.swacademy.mapcommunity.domain.vo.LoginInfo;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class UserRepositoryImpl implements UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final String INSERT_USER_SQL = "INSERT INTO user(user_id, email, `password`, `name`, gender, birth) " +
            "VALUES (:userId, :email, :password, :name, :gender, :birth)";
    private final String UPDATE_USER_SQL = "UPDATE user SET name = :name, gender = :gender, birth = :birth " +
            "WHERE user_id = :userId";
    private final String DELETE_USER_SQL = "DELETE FROM user WHERE user_id = :userId";
    private final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE user_id = :userId";
    private final String SELECT_USER_BY_EMAIL = "SELECT * FROM user WHERE email = :email";

    public UserRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<String, Object> userObjectToMap(User user) {
        return new HashMap<>() {{
            put("userId", user.getUserId().toString().getBytes());
            put("email", user.getLoginInfo().email());
            put("password", user.getLoginInfo().password().getBytes());  // @TODO byte[] 로 보내야 하는지 알아봐야 함. Hash 값을 넘겨야 함.
            put("name", user.getName());
            put("gender", user.getGender().name());
            put("birth", Timestamp.valueOf(user.getBirth()));
        }};
    }

    @Override
    public User login(LoginInfo loginInfo) {
        // @TODO Implement this method.
        return null;
    }

    @Override
    public boolean logout(LoginInfo loginInfo) {
        // @TODO Implement this method.
        return false;
    }

    @Override
    public User insertUser(User user) throws DataAccessException {
        Map<String, Object> paramMap = userObjectToMap(user);
        // @TODO UUID 생성 방법 다시 알아보고, 랜덤 생성이 최선이면 DataIntegrityViolationException 이거 예외처리 해줘야 할듯.
        jdbcTemplate.update(INSERT_USER_SQL, paramMap);
        return user;
    }

    @Override
    public User updateUser(User user) {
        Map<String, Object> paramMap = userObjectToMap(user);
        jdbcTemplate.update(UPDATE_USER_SQL, paramMap);
        return user;
    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public Optional<User> getUserById(UUID userId) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return Optional.empty();
    }

    public static void main(String[] args) {
        // @TODO Delete this example code.
        HikariDataSource dataSource = DataSourceBuilder.create()
                .url("jdbc:mysql://localhost/post")
                .username("admin")
                .password("1q2w3e4r")
                .type(HikariDataSource.class)
                .build();
        UserRepository userRepository = new UserRepositoryImpl(new NamedParameterJdbcTemplate(dataSource));
        LoginInfo loginInfo = new LoginInfo("email2@email.com", "1q2w3e4r");
        User newUser = new User(UUID.randomUUID(), loginInfo, "name", Gender.NONE, LocalDateTime.now());
        userRepository.insertUser(newUser);
    }
}
