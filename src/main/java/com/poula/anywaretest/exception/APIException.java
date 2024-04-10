package com.poula.anywaretest.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@Getter
@Setter
public class APIException extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;
}
