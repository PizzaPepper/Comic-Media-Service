package com.pepper.Comic_Media_Service.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.pepper.Comic_Media_Service.Entity.ComicEntity;

public interface ComicRepository extends CrudRepository<ComicEntity, UUID> {
    public Optional<ComicEntity> findByTitle(String title);
}
