package com.daniel.upload.api.model.entities;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.daniel.upload.api.model.dtos.agent.xml.AgentXMLDTO;
import com.daniel.upload.api.model.dtos.agent.xml.AgentsXMLDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "agent")
public class Agent {
	
	@Id
	private String id;
	
	private Long code;
	
	@Temporal(TemporalType.DATE)
	private Date date;
		
	@Default
	@OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)	
	private Set<AgentRegion> regions = new HashSet<>();
	
	public static Collection<Agent> fromAgentsDTO(AgentsXMLDTO agentsXMLDTO) {
		
		return agentsXMLDTO.getAgents().stream().map(Agent::fromDTO).toList();
	}
	
	private static Agent fromDTO(AgentXMLDTO dto) {
		
		var newId = UUID.randomUUID().toString();
		
		return Agent.builder()
				.id(newId)
				.code(dto.getCode())
				.date(dto.getDate())
				.regions(AgentRegion.fromCollectionOfRegionDTO(newId, dto.getRegions()))
				.build();		
	}

}
