package com.jdc.one.traders.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.one.traders.model.dto.output.DivisionDto;
import com.jdc.one.traders.model.dto.output.TownshipDto;
import com.jdc.one.traders.model.service.LocationService;

@RestController
@RequestMapping("member/location")
public class LocationApi {
	
	@Autowired
	private LocationService service;

	@GetMapping("division")
	List<DivisionDto> divisions() {
		return service.getAllDivisions();
	}
	
	@GetMapping("township/{division}")
	List<TownshipDto> townships(@PathVariable int division) {
		return service.findTownships(division);
	}
}
