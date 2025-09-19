package com.sprindao.jwt.repo;

import com.sprindao.jwt.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District,Long> {

    List<District> findByState_Id(Long stateId);
}
