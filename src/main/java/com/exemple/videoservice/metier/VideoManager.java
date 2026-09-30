package com.exemple.videoservice.metier;

import com.exemple.videoservice.dao.entities.Video;
import com.exemple.videoservice.dao.repositories.VideoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VideoManager implements IvideoManager {

    private final VideoRepository videoRepository;
    private final IcreatorManager creatorManager; // Injection pour gérer le créateur avant la vidéo

    @Override
    public Video saveVideo(Video video) {
        // Déplacement de la logique métier ici
        if (video.getCreator() != null && video.getCreator().getId() == null) {
            creatorManager.saveCreator(video.getCreator());
        }
        return videoRepository.save(video);
    }

    @Override
    public Video findById(Long id) {
        return videoRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new RuntimeException("Vidéo introuvable avec l'ID : " + id));
    }

    @Override
    public Video updateVideo(Video video) {
        return videoRepository.save(video);
    }
}