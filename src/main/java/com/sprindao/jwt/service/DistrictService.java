package com.sprindao.jwt.service;

import com.sprindao.jwt.dto.DistrictDto;
import com.sprindao.jwt.entity.City;
import com.sprindao.jwt.entity.District;
import com.sprindao.jwt.entity.State;
import com.sprindao.jwt.exception.BusinessException;
import com.sprindao.jwt.repo.CityRepository;
import com.sprindao.jwt.repo.DistrictRepository;
import com.sprindao.jwt.repo.StateRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DistrictService {

    private final DistrictRepository districtRepository;
    private final StateRepository stateRepository;

    public DistrictService(DistrictRepository districtRepository, StateRepository stateRepository) {
        this.districtRepository = districtRepository;
        this.stateRepository = stateRepository;
    }

    public District getDistrictForId(Long districtId){
     Optional<District> optionalDistrict =  districtRepository.findById(districtId);
     return optionalDistrict.orElseThrow(()->new BusinessException("No district found with id: "+districtId));
    }

    public List<District> getDistrictsForState(Long stateId){
       List<District> districts=  districtRepository.findByState_Id(stateId);
        if(CollectionUtils.isEmpty(districts)) {
            throw new BusinessException("No districts available for stateId: " + stateId);
        }
        return districts;
    }

    public District saveDistrict(DistrictDto districtDto, Long stateId){
          Optional<State> optionalState =   stateRepository.findById(stateId);
          State state =  optionalState.orElseThrow(()->new BusinessException("No state available for with id: "+stateId));

          District district = new District();
          district.setName(district.getName());
          district.setCode(district.getCode());
          List<City> cities = new ArrayList<>();
          districtDto.getCities().forEach(cityDto -> {
              City city = new City();
              city.setName(city.getName());
              city.setCode(city.getCode());
              city.setDistrict(district);
                cities.add(city);
          });
          district.setCities(cities);
          district.setState(state);
          return districtRepository.save(district);
    }

}
