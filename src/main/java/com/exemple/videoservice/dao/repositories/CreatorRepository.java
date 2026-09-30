package com.exemple.videoservice.dao.repositories;

import com.exemple.videoservice.dao.entities.Creator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatorRepository extends JpaRepository<Creator, Integer> {
}
