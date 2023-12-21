package com.mini.project.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mini.project.entity.CarEntity;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, UUID>{

	List<CarEntity> findByBrand_BrandName(String brandName);
}
