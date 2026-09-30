package com.exemple.videoservice.web;

import com.exemple.videoservice.dao.entities.Creator;
import com.exemple.videoservice.dao.entities.Video;
import com.exemple.videoservice.dao.repositories.CreatorRepository;
import com.exemple.videoservice.dao.repositories.VideoRepository;
import com.exemple.videoservice.dto.CreatorRequest;
import com.exemple.videoservice.metier.CreatorManager;
import com.exemple.videoservice.metier.VideoManager;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Controller
public class VideoGraphQlController {


    private final CreatorRepository creatorRepository;
    private final VideoRepository videoRepository;

    private final CreatorManager creatorManager;
    private final VideoManager videoManager;

    VideoGraphQlController(CreatorRepository creatorRepository,
                           VideoRepository videoRepository,
                           CreatorManager creatorManager,
                           VideoManager videoManager) {
        this.creatorRepository = creatorRepository;
        this.videoRepository = videoRepository;
        this.creatorManager = creatorManager;
        this.videoManager = videoManager;
    }

    @QueryMapping
    public List<Video> videoList() {
        return videoRepository.findAll();
    }

    @QueryMapping
    public List<Creator> creatorList() {
        return creatorRepository.findAll();
    }

    @MutationMapping
    public Creator saveCreator(@Argument Creator creatorDto) {
        return creatorManager.saveCreator(creatorDto);
    }

    @MutationMapping
    public Video saveVideo(@Argument Video video) {
        return videoManager.saveVideo(video);
    }

    @SubscriptionMapping
    public Flux<Video> notifyVideoChange() {
        return Flux.fromStream(
                Stream.generate(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    Random random = new Random();

                    CreatorRequest creatorRequest = CreatorRequest.builder()
                            .name("x" + random.nextInt())
                            .email("x@gmail.com")
                            .build();

                    Creator creator = creatorManager.saveCreator(creatorRequest);

                    Video video = videoManager.findById(1L);
                    video.setCreator(creator);
                    videoManager.updateVideo(video);

                    return video;
                }));
    }
}