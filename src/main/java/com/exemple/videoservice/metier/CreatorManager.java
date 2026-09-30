package com.exemple.videoservice.metier;

import com.exemple.videoservice.dao.entities.Creator;
import com.exemple.videoservice.dto.CreatorRequest;
import com.exemple.videoservice.dao.repositories.CreatorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreatorManager implements IcreatorManager {

    private final CreatorRepository creatorRepository;

    @Override
    public Creator saveCreator(Creator creator) {
        return creatorRepository.save(creator);
    }

    @Override
    public Creator saveCreator(CreatorRequest request) {
        Creator creator = Creator.builder()
                .name(request.getName())
                .email(request.getEmail())
                .build();
        return creatorRepository.save(creator);
    }
}