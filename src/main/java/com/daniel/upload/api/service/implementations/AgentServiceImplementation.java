package com.daniel.upload.api.service.implementations;

import java.util.Collection;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.daniel.upload.api.converter.XmlConverter;
import com.daniel.upload.api.exception.InvalidDataException;
import com.daniel.upload.api.model.dtos.agent.RegionDTO;
import com.daniel.upload.api.model.dtos.agent.xml.AgentsXMLDTO;
import com.daniel.upload.api.model.entities.Agent;
import com.daniel.upload.api.repository.AgentRepository;
import com.daniel.upload.api.service.interfaces.AgentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AgentServiceImplementation implements AgentService {
	
	private final AgentRepository repository;
	private final XmlConverter<AgentsXMLDTO> xmlConverter;	

	@Override
	public void processUpload(MultipartFile file) {
		
		var agentsXmlDTO = xmlConverter.toObject(file, AgentsXMLDTO.class);
		
		save(agentsXmlDTO)
			.forEach(agent -> System.out.println(String.format("Agent code => %s", agent.getCode())));				
	}

	private Collection<Agent> save(AgentsXMLDTO agentsDTO) {
		
		if (Objects.isNull(agentsDTO) || Objects.isNull(agentsDTO.getAgents())) {
			throw new InvalidDataException("The uploaded file is not valid");
		}
		
		var agents = Agent.fromAgentsDTO(agentsDTO);
		return repository.saveAll(agents);
	}

	@Override
	public Collection<RegionDTO> getRegionValuesByAcronym(String acronym) {
		
		var agents = repository.findByRegionAcronym(acronym);
		
		return agents.stream().map(RegionDTO::fromAgent).toList();				
	}

}
