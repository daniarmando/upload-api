package com.daniel.upload.api.model.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.daniel.upload.api.model.dtos.agent.xml.RegionXMLDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "agent_region")
public class AgentRegion {
	
	@Id
	private String id;	
	
	private String acronym;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "agent_id", nullable = false)
	private Agent agent;
		
	@Default
	@OneToMany(mappedBy = "agentRegion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<AgentRegionValue> values = new HashSet<>();
	
	public static Set<AgentRegion> fromCollectionOfRegionDTO(String agentId, Collection<RegionXMLDTO> dtos) {
		
		return dtos.stream().map(regionDTO -> fromDTO(agentId, regionDTO))
				.collect(Collectors.toSet());
	}
	
	private static AgentRegion fromDTO(String agentId, RegionXMLDTO dto) {
		
		var newId = UUID.randomUUID().toString();
		
		return AgentRegion.builder()	
				.id(newId)
				.agent(Agent.builder().id(agentId).build())
				.acronym(dto.getAcronym())
				.values(AgentRegionValue.fromRegionDTO(newId, dto))
				.build();
	}

}
