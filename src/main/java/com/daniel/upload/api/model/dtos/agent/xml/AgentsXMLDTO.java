package com.daniel.upload.api.model.dtos.agent.xml;

import java.util.Collection;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "agentes")
public class AgentsXMLDTO {
	
	@JacksonXmlProperty(localName = "versao", isAttribute = true)
	private String version;
	
	@JacksonXmlProperty(localName = "agente")
	@JacksonXmlElementWrapper(useWrapping = false)
	private Collection<AgentXMLDTO> agents;
	
}
