package com.mini.project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mini.project.dto.CarDto;
import com.mini.project.service.CarService;
import com.mini.project.util.GenericConstant;
import com.mini.project.util.SimpleResponse;

@RestController
@RequestMapping("/car")
public class CarController {

	@Autowired
	private CarService carService;
	
	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<SimpleResponse> addCar(@RequestBody CarDto dto) {
		SimpleResponse response = null;
		try {
			response = carService.addCar(dto);
		} catch(Exception e) {
			response = new SimpleResponse(GenericConstant.INTERNAL_ERROR, e.getMessage(), null);
		}
		return new ResponseEntity<SimpleResponse>(response, HttpStatus.OK);
	}
	
	@PostMapping("/detail")
	@ResponseBody
	public ResponseEntity<SimpleResponse> Cardetail(@RequestBody CarDto dto) {
		SimpleResponse response = null;
		try {
			response = carService.getCar(dto);
		} catch(Exception e) {
			response = new SimpleResponse(GenericConstant.INTERNAL_ERROR, e.getMessage(), null);
		}
		return new ResponseEntity<SimpleResponse>(response, HttpStatus.OK);
	}
	
    @GetMapping
    public ResponseEntity<SimpleResponse> getCars(@RequestParam Optional<String> brand) {
        SimpleResponse response = null;
        try {
        	 response = carService.getCars(brand);
        } catch(Exception e) {
        	response = new SimpleResponse(GenericConstant.INTERNAL_ERROR, e.getMessage(), null);
        }
        return new ResponseEntity<SimpleResponse>(response, HttpStatus.OK);
    }
}
