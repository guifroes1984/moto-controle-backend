package com.guifroes1984.moto_controle.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    private String getCurrentTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @ExceptionHandler(TransacaoNaoEncontradaException.class)
    public ResponseEntity<Map<String, Object>> handleTransacaoNaoEncontrada(
            TransacaoNaoEncontradaException e) {
        
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("timestamp", getCurrentTimestamp());
        resposta.put("status", HttpStatus.NOT_FOUND.value());
        resposta.put("erro", "Não Encontrado");
        resposta.put("mensagem", e.getMessage());
        resposta.put("caminho", "/api/transacoes");
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
    }
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException e) {
        
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("timestamp", getCurrentTimestamp());
        resposta.put("status", HttpStatus.BAD_REQUEST.value());
        resposta.put("erro", "Erro na Requisição");
        resposta.put("mensagem", e.getMessage());
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception e) {
        
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("timestamp", getCurrentTimestamp());
        resposta.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        resposta.put("erro", "Erro Interno do Servidor");
        resposta.put("mensagem", "Ocorreu um erro inesperado");
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resposta);
    }
}
