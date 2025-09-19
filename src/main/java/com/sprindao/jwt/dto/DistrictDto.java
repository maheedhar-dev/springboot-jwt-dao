package com.sprindao.jwt.dto;

import java.util.List;

public class DistrictDto {

    private String name;
    private String code;
    private List<CityDto> cities;

    public DistrictDto() {
    }

    public DistrictDto(String name, String code, List<CityDto> cities) {
        this.name = name;
        this.code = code;
        this.cities = cities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<CityDto> getCities() {
        return cities;
    }

    public void setCities(List<CityDto> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "DistrictDto{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", cities=" + cities +
                '}';
    }
}
