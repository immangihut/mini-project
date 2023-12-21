package com.mini.project.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mini.project.dto.BrandDto;
import com.mini.project.entity.BrandEntity;
import com.mini.project.repository.BrandRepository;
import com.mini.project.util.GenericConstant;
import com.mini.project.util.SimpleResponse;

@Transactional(readOnly = true)
@Service
public class BrandService {

	@Autowired
	private BrandRepository brandRepository;
	
	@Transactional(readOnly = false)
	public SimpleResponse addBrand(BrandDto dto) {
		
		if(dto.getBrandName().isEmpty() || dto.getBrandName() == null) {
			return new SimpleResponse(GenericConstant.FAILED, "Brand name is null", null);
		}
		
		Map<String, Object> response = new HashMap<>();
		
		BrandEntity newBrand = new BrandEntity();
		newBrand.setBrandId(UUID.randomUUID());
		newBrand.setBrandName(dto.getBrandName());	
		
		brandRepository.save(newBrand);
		
		response.put("brandId", newBrand.getBrandId());
		response.put("brandName", newBrand.getBrandName());
		
		return new SimpleResponse(GenericConstant.SUCCESS, "Successfully add new brand", response);
	}
}
