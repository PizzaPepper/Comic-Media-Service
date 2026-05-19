package com.pepper.Comic_Media_Service.Mapper;

import org.springframework.stereotype.Component;

import com.pepper.Comic_Media_Service.DTO.ChapterData;
import com.pepper.Comic_Media_Service.DTO.ChapterVersionData;
import com.pepper.Comic_Media_Service.Entity.ChapterEntity;
import com.pepper.Comic_Media_Service.Entity.ChapterVersionEntity;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class ChapterMapper {

    public ChapterData toData(ChapterEntity entity) {
        return ChapterData.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .number(entity.getNumber())
                .build();
    }

    public ChapterVersionData toData(ChapterVersionEntity entity) {
        return ChapterVersionData.builder()
                .id(entity.getId())
                .chapter(entity.getChapter())
                .pages(entity.getPages())
                .scanGroup(entity.getScanGroup())
                .build();
    }
}
