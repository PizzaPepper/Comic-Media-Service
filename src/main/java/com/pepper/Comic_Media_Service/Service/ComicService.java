package com.pepper.Comic_Media_Service.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.pepper.Comic_Media_Service.DTO.ComicData;
import com.pepper.Comic_Media_Service.DTO.Request.CreateComicDataRequest;
import com.pepper.Comic_Media_Service.Entity.ComicEntity;
import com.pepper.Comic_Media_Service.Exception.ResourceAlreadyExistsException;
import com.pepper.Comic_Media_Service.Exception.ResourceNotFoundException;
import com.pepper.Comic_Media_Service.Mapper.ComicMapper;
import com.pepper.Comic_Media_Service.Repository.ComicRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ComicService {

    private final ComicRepository comicRepository;
    private final ComicMapper comicMapper;

    @Transactional
    public void createComicData(CreateComicDataRequest request) throws ResourceAlreadyExistsException {

        ComicEntity itExists = comicRepository.findByTitle(request.getTitle()).orElse(null);

        if(itExists != null) {
            throw new ResourceAlreadyExistsException("\'" + request.getTitle() + "\' already exists");
        }

        ComicEntity newComic = ComicEntity.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .createdAt(LocalDateTime.now())
                .build();

        comicRepository.save(newComic);
    }

    @Transactional
    public ComicData getComicById(UUID idComic) throws ResourceNotFoundException {
        ComicEntity entity = comicRepository.findById(idComic)
            .orElseThrow(() -> new ResourceNotFoundException("\'" + idComic + "\' Id Comic doesn\'t exists"));

        return comicMapper.toData(entity);
    }

}
