package com.sprindao.jwt.controller;

import com.sprindao.jwt.dto.ErrorMessageDto;
import com.sprindao.jwt.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorMessageDto> handleGlobalException(BusinessException ex){
        ErrorMessageDto errorMessageDto = new ErrorMessageDto();
        errorMessageDto.setMessage(ex.getMessage());
        errorMessageDto.setCode(HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(errorMessageDto,HttpStatus.OK);
    }
}
