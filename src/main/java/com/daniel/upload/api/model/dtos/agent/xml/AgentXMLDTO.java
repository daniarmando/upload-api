package com.daniel.upload.api.model.dtos.agent.xml;

import java.util.Collection;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "code", "date", "region" })
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AgentXMLDTO {
	
	@EqualsAndHashCode.Include
	@JacksonXmlProperty(localName = "codigo")
	private Long code;
	
	@JacksonXmlProperty(localName = "data")
	private Date date;
	
	@JacksonXmlProperty(localName = "regiao")
	@JacksonXmlElementWrapper(useWrapping = false)
	private Collection<RegionXMLDTO> regions;
	
}
