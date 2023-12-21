package com.mini.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mini.project.dto.BrandDto;
import com.mini.project.service.BrandService;
import com.mini.project.util.GenericConstant;
import com.mini.project.util.SimpleResponse;

@RestController
@RequestMapping("/brand")
public class BrandController {

	@Autowired
	private BrandService brandService;
	
	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<SimpleResponse> addBrand(@RequestBody BrandDto dto) {
		SimpleResponse response = null;
		try {
			response = brandService.addBrand(dto);
		} catch(Exception e) {
			response = new SimpleResponse(GenericConstant.INTERNAL_ERROR, e.getMessage(), null);
		}
		return new ResponseEntity<SimpleResponse>(response, HttpStatus.OK);
	}
}
