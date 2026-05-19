package com.pepper.Comic_Media_Service.Entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

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
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "scanGroup")
public class ScanGroupEntity {
    
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "scan_group_id", updatable = false, nullable = false)
    private UUID id;

    private String name;
    private String website;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "scanGroup")
    private List<ChapterVersionEntity> versions;
}
