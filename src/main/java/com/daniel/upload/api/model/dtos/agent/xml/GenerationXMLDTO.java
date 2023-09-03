package com.daniel.upload.api.model.dtos.agent.xml;

import java.math.BigDecimal;
import java.util.Collection;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GenerationXMLDTO {
		 
	@JacksonXmlProperty(localName = "valor")
	@JacksonXmlElementWrapper(useWrapping = false)
	private Collection<BigDecimal> values;
	
}
