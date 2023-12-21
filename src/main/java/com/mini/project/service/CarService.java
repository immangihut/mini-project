package com.mini.project.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mini.project.dto.CarDto;
import com.mini.project.entity.BrandEntity;
import com.mini.project.entity.CarEntity;
import com.mini.project.repository.BrandRepository;
import com.mini.project.repository.CarRepository;
import com.mini.project.util.GenericConstant;
import com.mini.project.util.SimpleResponse;

@Transactional(readOnly = true)
@Service
public class CarService {

	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Transactional(readOnly = false)
	public SimpleResponse addCar(CarDto dto) {
		if(dto.getCarName() == null || dto.getBrandId() == null) {
			new SimpleResponse(GenericConstant.FAILED, "Car name or Brand name is null", null);
		}

		BrandEntity brand = brandRepository.findByBrandId(UUID.fromString(dto.getBrandId()));
		if(brand == null) {
			return new SimpleResponse(GenericConstant.FAILED, "Brand not found", null);
		}
		
		Map<String, Object> response = new HashMap<>();
		
		CarEntity newCar = new CarEntity();
		newCar.setCarId(UUID.randomUUID());
		newCar.setCarName(dto.getCarName());
		newCar.setBrand(brand);
		
		carRepository.save(newCar);
		
		response.put("carId", newCar.getCarId());
		response.put("carName", newCar.getCarName());
		response.put("brandName", newCar.getBrand().getBrandName());
		
		return new SimpleResponse(GenericConstant.SUCCESS, "Successfully add new car", response);
	}
	
	public SimpleResponse getCar(CarDto dto) {
		if(dto.getCarId() == null) {
			return new SimpleResponse(GenericConstant.FAILED, "Id is null", null);
		}
		
		Map<String, Object> response = new HashMap<>();
		
		CarEntity car = carRepository.findById(UUID.fromString(dto.getCarId())).orElseThrow();
		response.put("carId", car.getCarId());
		response.put("carName", car.getCarName());
		response.put("brandName", car.getBrand().getBrandName());
		
		return new SimpleResponse(GenericConstant.SUCCESS, "Car found", response);
	}
	
	public SimpleResponse getCars(Optional<String> brandName) {
		List<CarEntity> cars;
		
		if(brandName.isPresent()) {
			cars = carRepository.findByBrand_BrandName(brandName.get());
		} else {
			cars = carRepository.findAll();
		}
		
		List<CarDto> carDtos = cars.stream().map(car -> {
	        CarDto dto = new CarDto();
	        dto.setCarId(car.getCarId().toString());
	        dto.setCarName(car.getCarName());
	        dto.setBrandName(car.getBrand().getBrandName());
	        dto.setBrandId(car.getBrand().getBrandId().toString());
	        return dto;
	    }).collect(Collectors.toList());
		
		return new SimpleResponse(GenericConstant.SUCCESS, "Cars retrieved successfully", carDtos);
	}
}
