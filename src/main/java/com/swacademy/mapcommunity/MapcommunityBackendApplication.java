package com.swacademy.mapcommunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration;
import org.springframework.context.annotation.Profile;

@SpringBootApplication(exclude = { PersistenceExceptionTranslationAutoConfiguration.class })
@Profile("local")
public class MapcommunityBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MapcommunityBackendApplication.class, args);
    }

}
