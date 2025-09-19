package com.sprindao.jwt.repo;

import com.sprindao.jwt.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State,Long> {
    List<State> findByCountry_Id(Long countryId);
}
