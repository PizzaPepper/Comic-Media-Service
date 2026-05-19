package com.pepper.Comic_Media_Service.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.pepper.Comic_Media_Service.DTO.ChapterVersionData;
import com.pepper.Comic_Media_Service.Entity.ChapterEntity;
import com.pepper.Comic_Media_Service.Entity.ChapterVersionEntity;

public interface ChapterVersionRepository extends CrudRepository<ChapterVersionEntity, UUID> {

    public Optional<ChapterVersionData> findByChapter(ChapterEntity chapter);

}
