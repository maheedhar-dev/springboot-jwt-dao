package com.sprindao.jwt.controller;

import com.sprindao.jwt.dto.DistrictDto;
import com.sprindao.jwt.entity.District;
import com.sprindao.jwt.entity.State;
import com.sprindao.jwt.repo.DistrictRepository;
import com.sprindao.jwt.service.DistrictService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DistrictController {

  private final DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @GetMapping("/{districtId}")
    public ResponseEntity<District> getDistrictForId( @PathVariable(name = "districtId") Long districtId){
        District district =  districtService.getDistrictForId(districtId);
        return new ResponseEntity<>(district, HttpStatus.OK);
    }

    @GetMapping("/district/{stateId}")
    public ResponseEntity<List<District>> getAllDistrictsForStateId(@PathVariable(name = "stateId") Long stateId){
        List<District> districts = districtService.getDistrictsForState(stateId);
        return new ResponseEntity<>(districts,HttpStatus.OK);
    }

    @PostMapping("/district/{stateId}")
    public ResponseEntity<District> saveDistrictForStateId(@PathVariable(name = "stateId") Long stateId, @RequestBody DistrictDto districtDto){
        District district = districtService.saveDistrict(districtDto,stateId);
        return new ResponseEntity<>(district,HttpStatus.OK);
    }
}
