package com.pepper.Comic_Media_Service.DTO;

import java.util.List;
import java.util.UUID;

import com.pepper.Comic_Media_Service.Entity.ChapterEntity;
import com.pepper.Comic_Media_Service.Entity.PageEntity;
import com.pepper.Comic_Media_Service.Entity.ScanGroupEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChapterVersionData {
    private UUID id;
    private ChapterEntity chapter;
    private ScanGroupEntity scanGroup;
    private List<PageEntity> pages;
}
