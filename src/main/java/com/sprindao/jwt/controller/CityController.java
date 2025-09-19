package com.sprindao.jwt.controller;

import com.sprindao.jwt.dto.CityDto;
import com.sprindao.jwt.entity.City;
import com.sprindao.jwt.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<City> getCityByCityId(@PathVariable(name = "cityId") Long cityId){
        City city = cityService.findCityByCityId(cityId);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @GetMapping("/city/district/{districtId}")
    public ResponseEntity<List<City>> getCitiesByDistrictId(@PathVariable("districtId")Long districtId){
        List<City> cities = cityService.getCitiesForDistrict(districtId);
        return new ResponseEntity<>(cities,HttpStatus.OK);
    }

    @GetMapping("/city/state/{stateId}")
    public ResponseEntity<List<City>> getCitiesByState(@PathVariable("stateId")Long stateId){
        List<City> cities = cityService.getCitiesForState(stateId);
        return new ResponseEntity<>(cities,HttpStatus.OK);
    }

    @PostMapping("/city/{districtId}")
    public ResponseEntity<City> saveCity(@RequestBody CityDto cityDto, @PathVariable(name = "districtId") Long districtId){
        City city = cityService.saveCity(cityDto,districtId);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }
}
