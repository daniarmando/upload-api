package com.daniel.upload.api.model.dtos.agent.xml;

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
public class RegionXMLDTO {
	
	@JacksonXmlProperty(localName = "sigla", isAttribute = true)
	private String acronym;
			
	@JacksonXmlProperty(localName="geracao")
	@JacksonXmlElementWrapper(useWrapping = true)
	private GenerationXMLDTO generation;
		
	@JacksonXmlProperty(localName="compra")
	@JacksonXmlElementWrapper(useWrapping = true)
	private PurchaseXMLDTO purchase;
	
	@JacksonXmlProperty(localName="precoMedio")
	@JacksonXmlElementWrapper(useWrapping = true)
	private AveragePriceXMLDTO averagePrice;
	
}
