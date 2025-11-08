package com.groupEight.TaskManagement.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.http.HttpServlet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

  // --- 404: Entidade não encontrada ---
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleEntityNotFound(
          EntityNotFoundException ex, HttpServletRequest request) {

    return buildErrorResponse(
            HttpStatus.NOT_FOUND,
            "Recurso não encontrado",
            ex.getMessage(),
            request.getRequestURI()
    );
  }

  // --- 404: Recurso não encontrado ---
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleResourceNotFound(
          ResourceNotFoundException ex, HttpServletRequest request) {

    return buildErrorResponse(
            HttpStatus.NOT_FOUND,
            "Recurso não encontrado",
            ex.getMessage(),
            request.getRequestURI()
    );
  }

  // --- 409: Conflito (recurso já existente) ---
  @ExceptionHandler(ResourceAlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> handleResourceAlreadyExists(
          ResourceAlreadyExistsException ex, HttpServletRequest request) {

    return buildErrorResponse(
            HttpStatus.CONFLICT,
            "Recurso já existente",
            ex.getMessage(),
            request.getRequestURI()
    );
  }

  // --- 400: Requisição inválida ---
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ErrorResponse> handleBadRequest(
          BadRequestException ex, HttpServletRequest request) {

    return buildErrorResponse(
            HttpStatus.BAD_REQUEST,
            "Requisição inválida",
            ex.getMessage(),
            request.getRequestURI()
    );
  }

  // --- 400: Erro de validação (Bean Validation) ---
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationException(
          MethodArgumentNotValidException ex, HttpServletRequest request) {

    String defaultMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();

    return buildErrorResponse(
            HttpStatus.BAD_REQUEST,
            "Erro de validação",
            defaultMessage,
            request.getRequestURI()
    );
  }

  // --- 401: Acesso não autorizado ---
  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<ErrorResponse> handleUnauthorizedException(
          UnauthorizedException ex, HttpServletRequest request) {

    return buildErrorResponse(
            HttpStatus.UNAUTHORIZED,
            "Acesso não autorizado",
            ex.getMessage(),
            request.getRequestURI()
    );
  }

  // --- 500: Erro interno no servidor ---
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGenericException(
          Exception ex, HttpServletRequest request) {

    return buildErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Erro interno no servidor",
            ex.getMessage(),
            request.getRequestURI()
    );
  }

  private ResponseEntity<ErrorResponse> buildErrorResponse(
          HttpStatus status, String error, String message, String path) {

    ErrorResponse response = new ErrorResponse(
            LocalDateTime.now(),
            status.value(),
            error,
            message,
            path
    );

    return ResponseEntity.status(status).body(response);
  }
  @ExceptionHandler(EquipeNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleEquipeNotFoundException(EquipeNotFoundException ex, HttpServletRequest request) {
      ErrorResponse dto = new ErrorResponse(LocalDateTime.now(),
              HttpStatus.NOT_FOUND.value(),
              "Entidade não encontrada",
              ex.getMessage(),request.getRequestURI());

    return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
  }
  @ExceptionHandler(UserTeamException.class)
  public ResponseEntity<ErrorResponse> userTeamHandler(UserTeamException ex, HttpServletRequest request) {
    ErrorResponse dto = new ErrorResponse(LocalDateTime.now(),
            HttpStatus.CONFLICT.value(),
            "Problemas ao editar equipe",
            ex.getMessage(),request.getRequestURI());

    return new ResponseEntity<>(dto, HttpStatus.CONFLICT);
  }
  @ExceptionHandler(InvalidFormatException.class)
  public ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex) {
    Map<String, Object> body = new HashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", HttpStatus.BAD_REQUEST.value());
    body.put("error", "Valor inválido");
    body.put("message", "Um dos campos possui um valor inválido. Verifique se o valor do enum está correto.");

    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Object> handleInvalidFormat(HttpMessageNotReadableException ex) {
    Map<String, Object> body = new HashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", HttpStatus.BAD_REQUEST.value());
    body.put("error", "Valor inválido");
    body.put("message", "Um dos campos possui um valor inválido. Verifique se o valor do enum está correto.");

    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }

}