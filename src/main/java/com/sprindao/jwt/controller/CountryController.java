package com.sprindao.jwt.controller;

import com.sprindao.jwt.aop.TimeEvaluator;
import com.sprindao.jwt.dto.CountryDto;
import com.sprindao.jwt.entity.Country;
import com.sprindao.jwt.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/country/countries")
    @TimeEvaluator
    public ResponseEntity<List<Country>> getAllCountries(){
        List<Country> countries = countryService.getAllCountries();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @GetMapping("/country/{countryId}")
    @TimeEvaluator
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Country> getCountryByCountryId(@PathVariable(name = "countryId") Long countryId){
       Country country =  countryService.getCountryForCountryId(countryId);
       return new ResponseEntity<>(country,HttpStatus.OK);
    }

    @PostMapping("/country")
    @TimeEvaluator
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Country> saveCountry(@RequestBody CountryDto countryDto){
        Country country = countryService.saveCountry(countryDto);
        return new ResponseEntity<>(country,HttpStatus.OK);
    }
}
