package com.sprindao.jwt.controller;

import com.sprindao.jwt.dto.StateDto;
import com.sprindao.jwt.entity.State;
import com.sprindao.jwt.service.StateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StateController {

    private final StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @GetMapping("/state/{countryId}")
    public ResponseEntity<List<State>> getAllStatesForCountry(@PathVariable(name = "countryId") Long countryId){
        List<State> states= stateService.getAllStatesForCountry(countryId);
        return new ResponseEntity<>(states, HttpStatus.OK);
    }

    @GetMapping("/{stateId}")
    public ResponseEntity<State> getStateForStateId(@PathVariable(name = "stateId") Long stateId){
        State state = stateService.getStateByStateId(stateId);
        return new ResponseEntity<>(state,HttpStatus.OK);
    }

    @PostMapping("/state/{countryId}")
    public ResponseEntity<State> saveState(@RequestBody StateDto stateDto,@PathVariable(name = "countryId") Long countryId){
        State state = stateService.saveState(stateDto,countryId);
        return new ResponseEntity<>(state,HttpStatus.OK);
    }
}
