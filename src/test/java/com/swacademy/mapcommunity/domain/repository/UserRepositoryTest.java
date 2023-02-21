package com.swacademy.mapcommunity.domain.repository;

import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.domain.vo.Gender;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    EntityManagerFactory emf;

    @Autowired
    UserRepository repository;

    @BeforeEach
    void setUp(){
        repository.deleteAll();
    }

    @Test
    @DisplayName("insert test")
    void testUser() {
        // Given
        User user = new User();
        user.setEmail("hong@gmail.com");
        user.setPassword("1234!");
        user.setNickName("나는 도라에몽");
        user.setGender(Gender.NONE);

        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        entityManager.persist(user);

        transaction.commit();
    }

    @Test
    @DisplayName("insert test, Use SpringDataJPA")
    void springDataJpaUser() {
        // Given
        User user = new User();
        user.setEmail("king@gmail.com");
        user.setPassword("1234!");
        user.setNickName("나는 도라에몽");
        user.setGender(Gender.NONE);

        repository.save(user);
    }
}
