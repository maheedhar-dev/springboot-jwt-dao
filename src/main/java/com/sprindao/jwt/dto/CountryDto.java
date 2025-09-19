package com.sprindao.jwt.dto;

import java.util.List;

public class CountryDto {
    private String name;
    private String code;
    private List<StateDto> states;

    public CountryDto() {
    }

    public CountryDto(String name, String code, List<StateDto> states) {
        this.name = name;
        this.code = code;
        this.states = states;
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

    public List<StateDto> getStates() {
        return states;
    }

    public void setStates(List<StateDto> states) {
        this.states = states;
    }

    @Override
    public String toString() {
        return "CountryDto{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", states=" + states +
                '}';
    }
}
