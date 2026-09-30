package com.exemple.videoservice.metier;

import com.exemple.videoservice.dao.entities.Video;

public interface IvideoManager {
    Video saveVideo(Video video);
    Video findById(Long id);
    Video updateVideo(Video video);
}