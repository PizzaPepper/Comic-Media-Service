package com.pepper.Comic_Media_Service.Mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pepper.Comic_Media_Service.DTO.PageData;
import com.pepper.Comic_Media_Service.Entity.PageEntity;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class PageMapper {
    
    public List<PageEntity> toEntity(List<PageData> data) {
        return data.stream().map(item -> PageEntity.builder()
            .id(item.getId())
            .imagePath(item.getImagePath())
            .pageNumber(item.getPageNumber())
            .build()).toList();
    }
}
