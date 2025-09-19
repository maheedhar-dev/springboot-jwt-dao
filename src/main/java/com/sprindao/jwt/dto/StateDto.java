package com.sprindao.jwt.dto;

import java.util.List;

public class StateDto {

    private String name;
    private String code;
    private List<DistrictDto> districts;

    public StateDto() {
    }

    public StateDto(String name, String code, List<DistrictDto> districts) {
        this.name = name;
        this.code = code;
        this.districts = districts;
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

    public List<DistrictDto> getDistricts() {
        return districts;
    }

    public void setDistricts(List<DistrictDto> districts) {
        this.districts = districts;
    }

    @Override
    public String toString() {
        return "StateDto{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", districts=" + districts +
                '}';
    }
}
