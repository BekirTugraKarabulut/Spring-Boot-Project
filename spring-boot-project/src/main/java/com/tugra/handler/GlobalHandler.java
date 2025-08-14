package com.tugra.handler;

import com.tugra.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.InetAddress;

@ControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<ApiError> globalHandlerException(BaseException exception , WebRequest request) {

        return ResponseEntity.badRequest().body(createApiError(exception.getMessage() , request));

    }

    public <E> ApiError<E> createApiError(E message , WebRequest request){

        ApiError<E> apiError = new ApiError<>();
        apiError.setStatus(HttpStatus.BAD_REQUEST.value());

        Exception<E> exception = new Exception<>();
        exception.setPath(request.getDescription(false));
        exception.setHostName(getHostName());
        exception.setMessage(message);

        apiError.setException(exception);
        return apiError;
    }

    public String getHostName(){

        try {
            return InetAddress.getLocalHost().getHostName();
        }catch (java.lang.Exception e){
            e.printStackTrace();
        }

        return null;
    }

}
