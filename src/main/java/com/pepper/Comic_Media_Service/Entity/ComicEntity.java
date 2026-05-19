package com.pepper.Comic_Media_Service.Entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor 
@Entity
@Builder
@Table(name = "comic")
public class ComicEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "manga_id", updatable = false, nullable = false)
    private UUID id;

    private String title;
    private String author;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "comic", cascade = CascadeType.ALL)
    private List<ChapterEntity> chapters;

}
