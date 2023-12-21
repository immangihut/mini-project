package com.mini.project.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mini.project.entity.BrandEntity;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, UUID> {
	
	BrandEntity findByBrandId(UUID brandId);

}
