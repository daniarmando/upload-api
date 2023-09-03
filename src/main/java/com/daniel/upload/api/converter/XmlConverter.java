package com.daniel.upload.api.converter;

import java.io.IOException;
import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.daniel.upload.api.exception.XmlConverterException;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class XmlConverter<T> {
	
	private XmlMapper mapper;
	
	public XmlConverter() {
		mapper = new XmlMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);		
	}
	
	public String toXml(T value) {
		
		try {
			
			return mapper.writeValueAsString(value);
			
		} catch (JacksonException e) {
			
			throw new XmlConverterException(String.format(
					"An error occurred while trying to convert %s to xml", value.getClass().getSimpleName()), e);
		}
		
    }
	
	public T toObject(MultipartFile file, Class<T> type) {
	
		try {
			
			if (Objects.isNull(file)) {
				return null;
			}
			
			log.info("Converting {} to {}", file.getOriginalFilename(), type.getSimpleName());
			
			return mapper.readValue(file.getBytes(), type);
			
		} catch (IOException ex) {
			
			var messageError = String.format("An error occurred while trying to convert file %s to %s", 
					file.getOriginalFilename(), type.getSimpleName());
			
			throw new XmlConverterException(messageError, ex);
		}
		
	}

}
