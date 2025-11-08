package com.groupEight.TaskManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestControllerAdvice
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

  // --- Método utilitário para construir respostas padronizadas ---
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
}