package com.sprindao.jwt.service;

import com.sprindao.jwt.dto.StateDto;
import com.sprindao.jwt.entity.City;
import com.sprindao.jwt.entity.Country;
import com.sprindao.jwt.entity.District;
import com.sprindao.jwt.entity.State;
import com.sprindao.jwt.exception.BusinessException;
import com.sprindao.jwt.repo.CountryRepository;
import com.sprindao.jwt.repo.StateRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StateService {

    private final StateRepository stateRepository;

    private final CountryRepository countryRepository;

    public StateService(StateRepository stateRepository, CountryRepository countryRepository) {
        this.stateRepository = stateRepository;
        this.countryRepository = countryRepository;
    }

    public List<State> getAllStatesForCountry(Long countryId){
        List<State> states =   stateRepository.findByCountry_Id(countryId);
        if(CollectionUtils.isEmpty(states)){
            throw new BusinessException("No states available for the country with id: "+countryId);
        }
        return states;
    }

    public State getStateByStateId(Long stateId){
        Optional<State> optionalState =  stateRepository.findById(stateId);
        return optionalState.orElseThrow(()-> new BusinessException("No state available with the id: "+stateId));
    }

    public State saveState(StateDto stateDto,Long countryId){
         Optional<Country> optionalCountry =  countryRepository.findById(countryId);
         Country country =  optionalCountry.orElseThrow(()->new BusinessException("No country available with the mentioned id: "+countryId));

         State state = new State();
         state.setName(stateDto.getName());
         state.setCode(stateDto.getCode());
         state.setCountry(country);
         List<District> districts =  new ArrayList<>();
         stateDto.getDistricts().forEach(districtDto -> {
             List<City> cities = new ArrayList<>();
             District district = new District();
             district.setName(districtDto.getName());
             district.setCode(districtDto.getCode());
             district.setState(state);
             districtDto.getCities().forEach(cityDto -> {
                 City city = new City();
                 city.setName(cityDto.getName());
                 city.setCode(cityDto.getCode());
                 city.setDistrict(district);
                 cities.add(city);
             });
             district.setCities(cities);
             districts.add(district);
         });
        state.setDistricts(districts);
        return stateRepository.save(state);
    }
}
