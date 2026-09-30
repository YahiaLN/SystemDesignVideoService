package com.exemple.videoservice;


import com.exemple.videoservice.dao.entities.Creator;
import com.exemple.videoservice.dao.entities.Video;
import com.exemple.videoservice.dao.repositories.CreatorRepository;
import com.exemple.videoservice.dao.repositories.VideoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.List;

@Configuration
public class DatabaseConfig {

    @Bean
    CommandLineRunner start(CreatorRepository creatorRepository, VideoRepository videoRepository) {
        return args -> {
            // 1. Création et sauvegarde des Creators
            List<Creator> creators = List.of(
                    Creator.builder().name("Youssef").email("youssef@example.com").build(),
                    Creator.builder().name("Amina").email("amina@example.com").build(),
                    Creator.builder().name("Karim").email("karim@example.com").build()
            );
            creatorRepository.saveAll(creators);

            // 2. Création et sauvegarde des Videos (associées aux creators)
            List<Video> videos = List.of(
                    Video.builder()
                            .name("Spring Boot Tutorial")
                            .url("http://youtube.com/v1")
                            .description("Apprendre Spring Boot de zéro")
                            .datePublication(String.valueOf(new Date()))
                            .creator(creators.get(0)) // Youssef
                            .build(),
                    Video.builder()
                            .name("GraphQL API Guide")
                            .url("http://youtube.com/v2")
                            .description("Introduction à Spring for GraphQL")
                            .datePublication(String.valueOf(new Date()))
                            .creator(creators.get(0)) // Youssef
                            .build(),
                    Video.builder()
                            .name("JPA and Hibernate")
                            .url("http://youtube.com/v3")
                            .description("Les bases des bases de données avec Java")
                            .datePublication(String.valueOf(new Date()))
                            .creator(creators.get(1)) // Amina
                            .build()
            );
            videoRepository.saveAll(videos);
        };
    }
}
