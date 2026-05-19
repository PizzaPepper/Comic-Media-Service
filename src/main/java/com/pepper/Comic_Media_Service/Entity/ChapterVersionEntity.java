package com.pepper.Comic_Media_Service.Entity;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor 
@Table(name = "chapterVersion")
public class ChapterVersionEntity {
    
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "chapter_version_id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private ChapterEntity chapter;

    @ManyToOne
    @JoinColumn(name = "scan_group_id")
    private ScanGroupEntity scanGroup;

    @OneToMany(mappedBy = "chapterVersion", cascade = CascadeType.ALL)
    private List<PageEntity> pages;

}
