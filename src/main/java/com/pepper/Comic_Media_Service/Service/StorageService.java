package com.pepper.Comic_Media_Service.Service;

import java.util.List;

import com.pepper.Comic_Media_Service.DTO.MultiPartPageData;
import com.pepper.Comic_Media_Service.DTO.PageData;

public interface StorageService {
    
    public List<PageData> storeChapter(List<MultiPartPageData> pages);

}
