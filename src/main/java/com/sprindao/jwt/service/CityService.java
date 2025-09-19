package com.sprindao.jwt.service;

import com.sprindao.jwt.dto.CityDto;
import com.sprindao.jwt.entity.City;
import com.sprindao.jwt.entity.District;
import com.sprindao.jwt.exception.BusinessException;
import com.sprindao.jwt.repo.CityRepository;
import com.sprindao.jwt.repo.DistrictRepository;
import com.sprindao.jwt.repo.StateRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private final DistrictRepository districtRepository;
    private final CityRepository cityRepository;

    public CityService(DistrictRepository districtRepository, CityRepository cityRepository) {
        this.districtRepository = districtRepository;
        this.cityRepository = cityRepository;
    }

    public City findCityByCityId(Long cityId){
        Optional<City> optionalCity = cityRepository.findById(cityId);
        return optionalCity.orElseThrow(()->new BusinessException("No city available with the id: "+cityId));
    }

    public List<City> getCitiesForDistrict(Long districtId){
      List<City> cities = cityRepository.findByDistrict_Id(districtId);
        if(CollectionUtils.isEmpty(cities)){
            throw new BusinessException("No cities available for districtId:"+districtId);
        }
        return cities;
    }

    public List<City> getCitiesForState(Long stateId){
        List<City> cities = cityRepository.findByDistrict_State_Id(stateId);
        if(CollectionUtils.isEmpty(cities)){
            throw new BusinessException("No cities available for stateId:"+stateId);
        }
        return cities;
    }

    public City saveCity(CityDto cityDto,Long districtId){
        Optional<District> optionalDistrict = districtRepository.findById(districtId);
        District district = optionalDistrict.orElseThrow(()->new BusinessException("No District available with the Id:"+districtId));

        City city = new City();
        city.setName(cityDto.getName());
        city.setCode(cityDto.getCode());
        city.setDistrict(district);
        return cityRepository.save(city);
    }
}
