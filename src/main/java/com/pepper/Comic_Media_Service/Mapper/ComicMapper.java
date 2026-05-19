package com.pepper.Comic_Media_Service.Mapper;

import org.springframework.stereotype.Component;

import com.pepper.Comic_Media_Service.DTO.ComicData;
import com.pepper.Comic_Media_Service.Entity.ComicEntity;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class ComicMapper {
    public ComicData toData(ComicEntity entity) {
        return ComicData.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .author(entity.getAuthor())
                .status(entity.getStatus())
                .build();
    }
}
