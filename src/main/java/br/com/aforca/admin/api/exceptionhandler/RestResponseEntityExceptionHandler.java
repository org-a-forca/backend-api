package br.com.aforca.admin.api.exceptionhandler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
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
    Map<String, Object> bodyOfResponse = new HashMap<>();
    bodyOfResponse.put("mensagem", "Registro não encontrado");

    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<Object> handleDataIntegrityViolationException() {
    Map<String, Object> bodyOfResponse = new HashMap<>();
    bodyOfResponse.put("mensagem", "Não é possível excluir o registro porque está sendo utilizado por outro");

    return new ResponseEntity<>(bodyOfResponse, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(value = { RuntimeException.class })
  protected ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
    Map<String, Object> bodyOfResponse = new HashMap<>();
    bodyOfResponse.put("mensagem", "Erro interno. Contatar o desenvolvedor");

    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
  }
}
