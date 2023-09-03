package com.daniel.upload.api.converter;

import static com.daniel.upload.api.mocks.AgentsDTOMock.getAgentsDTOMock;
import static com.daniel.upload.api.mocks.XmlMock.getXmlMock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.http.MediaType.TEXT_XML_VALUE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import com.daniel.upload.api.exception.XmlConverterException;
import com.daniel.upload.api.model.dtos.agent.xml.AgentsXMLDTO;

class XmlConverterTest {
	
	private XmlConverter<AgentsXMLDTO> xmlConverter;
	
	@BeforeEach
	void testBefore() {
		xmlConverter = new XmlConverter<>();		
	}
	
	@Test
	void mustConvertObjectToXml() {
									
		var expected = getXmlMock();
		var xmlResult = xmlConverter.toXml(getAgentsDTOMock());
		
		assertEquals(expected, xmlResult);
	}
		
	@Test
	void mustConvertXmlToObject() {
		
		var expected = getAgentsDTOMock();
		var xmlFile = new MockMultipartFile(
				"file", "exemplo_01.xml", TEXT_XML_VALUE, getXmlMock().getBytes());
											
		var agentDTO = xmlConverter.toObject(xmlFile, AgentsXMLDTO.class);
		
		assertThat(agentDTO).isNotNull().isEqualTo(expected);
	}
	
	@Test
	void mustThrowExceptionWhenConvertXmlToObject() {
		
		var wrongFile = new MockMultipartFile(
				"file", "exemplo_01.xml", TEXT_XML_VALUE, "anything".getBytes());
		
		assertThrows(XmlConverterException.class, () -> xmlConverter.toObject(wrongFile, AgentsXMLDTO.class));				
	}

}
