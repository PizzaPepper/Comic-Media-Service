package com.pepper.Comic_Media_Service.Entity;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "page")
public class PageEntity {
    
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "page_id", updatable = false, nullable = false)
    private UUID id;

    private Integer pageNumber;

    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "chapter_version_id")
    private ChapterVersionEntity chapterVersion;
}
