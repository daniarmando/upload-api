package com.daniel.upload.api.service.implementations;

import static com.daniel.upload.api.mocks.AgentMock.getAgentMock;
import static com.daniel.upload.api.mocks.AgentMock.getAgentMockWithOnlySERegion;
import static com.daniel.upload.api.mocks.XmlMock.getXmlMock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.http.MediaType.TEXT_XML_VALUE;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import com.daniel.upload.api.converter.XmlConverter;
import com.daniel.upload.api.exception.InvalidDataException;
import com.daniel.upload.api.model.dtos.agent.RegionDTO;
import com.daniel.upload.api.model.dtos.agent.xml.AgentsXMLDTO;
import com.daniel.upload.api.model.entities.Agent;
import com.daniel.upload.api.repository.AgentRepository;
import com.daniel.upload.api.service.interfaces.AgentService;

@ExtendWith(MockitoExtension.class)
class AgentServiceImplementationTest {
	
	private AgentService service;
	
	@Mock
	private AgentRepository repository;
	
	private XmlConverter<AgentsXMLDTO> xmlConverter;
	
	@BeforeEach
	void testBefore() {
		xmlConverter = new XmlConverter<>();
		service = new AgentServiceImplementation(repository, xmlConverter);
	}
	
	@Test
	void testContextLoads() {
		assertThat(this.service).isNotNull();
		assertThat(this.repository).isNotNull();		
		assertThat(this.xmlConverter).isNotNull();
	}
	
	@Test
	void testSaveAgents() {
		
		final var xmlFile = new MockMultipartFile(
				"file", "exemplo_01.xml", TEXT_XML_VALUE, getXmlMock().getBytes());
		
		given(repository.saveAll(ArgumentMatchers.<Collection<Agent>>any())).willReturn(List.of(getAgentMock()));
		
		service.processUpload(xmlFile);
		
		then(repository).should(times(1)).saveAll(ArgumentMatchers.<Collection<Agent>>any());		
	}
	
	@Test
	void testSaveAgentsWithInvalidDataException() {							
		
		assertThrows(InvalidDataException.class, () -> service.processUpload(null));
		
		then(repository).shouldHaveNoInteractions();		
	}
	
	@Test
	void testGetRegionValuesByAcronym() {	
		
		final var agent = getAgentMockWithOnlySERegion();
		final var expected = List.of(RegionDTO.fromAgent(agent));
		
		given(repository.findByRegionAcronym(anyString())).willReturn(List.of(agent));
		
		Collection<RegionDTO> entity = service.getRegionValuesByAcronym("SE");
		
		assertThat(entity).isNotNull().isEqualTo(expected);
		
		then(repository).should(times(1)).findByRegionAcronym(anyString());		
	}
		
}
