package com.daniel.upload.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.daniel.upload.api.model.dtos.error.ErrorMessageDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageDTO> handlerGenericException (Exception ex) {
		
        log.error("Generic Error - " + ex.getMessage(), ex);
        
        return buildResponseEntity("internal_error", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

	@ExceptionHandler(XmlConverterException.class)
	public ResponseEntity<ErrorMessageDTO> handleXmlConvertException(XmlConverterException ex) {
		
		log.error("XmlConvertException: " + ex.getMessage(), ex);				
		
		return buildResponseEntity("xml_unprocessable", "File not acceptable", HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(InvalidDataException.class)
	public ResponseEntity<ErrorMessageDTO> handleInvalidDataException(InvalidDataException ex) {
		
		log.error("InvalidDataException: " + ex.getMessage(), ex);
		
		String message = ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage();
		
		return buildResponseEntity("invalid_data", message, HttpStatus.BAD_REQUEST);
	}

	private ResponseEntity<ErrorMessageDTO> buildResponseEntity(String type, String message, HttpStatus status) {
		return new ResponseEntity<>(new ErrorMessageDTO(type, message), status);
	}

}
