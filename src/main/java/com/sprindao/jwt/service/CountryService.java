package com.sprindao.jwt.service;

import com.sprindao.jwt.dto.CountryDto;
import com.sprindao.jwt.entity.City;
import com.sprindao.jwt.entity.Country;
import com.sprindao.jwt.entity.District;
import com.sprindao.jwt.entity.State;
import com.sprindao.jwt.exception.BusinessException;
import com.sprindao.jwt.repo.CountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountries(){
      List<Country> countries =  countryRepository.findAll();
      if(CollectionUtils.isEmpty(countries)){
          throw new BusinessException("No countries available, please add few companies");
      }
      return countries;
    }

    public Country getCountryForCountryId(Long countryId){
        Optional<Country> optionalCountry = countryRepository.findById(countryId);
        return optionalCountry.orElseThrow(()->new BusinessException("No country available with the countryId: "+countryId));
    }

    public Country saveCountry(CountryDto countryDto){
        Country country = new Country();
        country.setName(countryDto.getName());
        country.setCode(countryDto.getCode());
        List<State> states = new ArrayList<>();
        countryDto.getStates().forEach(stateDto -> {
                List<District> districts = new ArrayList<>();
                State state = new State();
                state.setName(stateDto.getName());
                state.setCode(stateDto.getCode());
                stateDto.getDistricts().forEach(districtDto -> {
                    List<City>cities = new ArrayList<>();
                    District district = new District();
                    district.setName(districtDto.getName());
                    district.setCode(districtDto.getCode());
                    districtDto.getCities().forEach(cityDto -> {
                        City city = new City();
                        city.setName(cityDto.getName());
                        city.setCode(cityDto.getCode());
                        city.setDistrict(district);
                        cities.add(city);
                    });
                    district.setState(state);
                    district.setCities(cities);
                    districts.add(district);
                }
                );
                state.setCountry(country);
                state.setDistricts(districts);
                states.add(state);
        });
        country.setStates(states);
      return  countryRepository.save(country);
    }

}
