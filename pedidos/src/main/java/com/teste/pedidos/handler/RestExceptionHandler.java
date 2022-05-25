package com.teste.pedidos.handler;

import java.lang.reflect.Method;

import com.teste.pedidos.exception.BadRequestException;
import com.teste.pedidos.exception.ExceptionResponse;
import com.teste.pedidos.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.ServiceUnavailableException;

@Slf4j
@ControllerAdvice
@RestController
public class RestExceptionHandler extends ResponseEntityExceptionHandler
        implements AsyncUncaughtExceptionHandler {
    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
        log.info("Erro async: " + throwable.getMessage());
        throwable.printStackTrace();
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ExceptionResponse> handlerBadRequest(
            BadRequestException ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handlerNotFound(
            NotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(ServiceUnavailableException.class)
    public final ResponseEntity<ExceptionResponse> handlerServiceUnavailable(
            ServiceUnavailableException ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(
            MissingServletRequestPartException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(
                        "Não foi possível identificar o arquivo na requisição", request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ExceptionResponse> handlerMaxUploadSizeExceeded(
            MaxUploadSizeExceededException exception, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(
                        "Só são permitidos arquivos com no máximo 200MB", request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handlerAllException(
            Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
