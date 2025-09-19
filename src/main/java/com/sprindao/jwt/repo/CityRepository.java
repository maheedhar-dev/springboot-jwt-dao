package com.sprindao.jwt.repo;


import com.sprindao.jwt.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {
    List<City> findByDistrict_Id(Long districtId);
    List<City> findByDistrict_State_Id(Long stateId);
}
