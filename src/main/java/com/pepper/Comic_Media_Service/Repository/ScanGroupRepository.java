package com.pepper.Comic_Media_Service.Repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.pepper.Comic_Media_Service.Entity.ScanGroupEntity;

public interface ScanGroupRepository extends CrudRepository<ScanGroupEntity, UUID> {}
