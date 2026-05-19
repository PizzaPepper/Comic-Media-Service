package com.pepper.Comic_Media_Service.Repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.pepper.Comic_Media_Service.Entity.ChapterEntity;
import com.pepper.Comic_Media_Service.Entity.ComicEntity;

public interface ChapterRepository extends CrudRepository<ChapterEntity, UUID> {
    public Optional<ChapterEntity> findByComicAndNumber(ComicEntity comic, BigDecimal number);
}
