package br.com.aforca.admin.api.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String message = error.getDefaultMessage();
      errors.put(fieldName, message);
    });

    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = { NoSuchElementException.class })
  protected ResponseEntity<Object> handleNoSuchElementException(RuntimeException ex, WebRequest request) {
    return handleExceptionInternal(ex, "Registro não encontrado", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler(value = { RuntimeException.class })
  protected ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
    return handleExceptionInternal(ex, "Erro interno. Contatar o desenvolvedor", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
  }
}
