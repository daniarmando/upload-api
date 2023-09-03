package com.daniel.upload.api.model.dtos.agent;

import java.math.BigDecimal;
import java.util.List;

import com.daniel.upload.api.model.dtos.agent.xml.RegionValueDTO;
import com.daniel.upload.api.model.entities.Agent;
import com.daniel.upload.api.model.entities.AgentRegion;
import com.daniel.upload.api.model.enums.AgentRegionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RegionDTO {
	
	@EqualsAndHashCode.Include
	private String id;
	
	private String acronym;
	
	private RegionValueDTO generations;	
	private RegionValueDTO purchase;
	
	@JsonIgnore
	private RegionValueDTO averagePrice;
	
	public static RegionDTO fromAgent(Agent agent) {
		
		return agent.getRegions().stream()
			.map(region -> RegionDTO.builder()
					.id(region.getId())
					.acronym(region.getAcronym())
					.generations(new RegionValueDTO(buildValues(region, AgentRegionType.GENERATION)))
					.purchase(new RegionValueDTO(buildValues(region, AgentRegionType.PURCHASE)))
					.averagePrice(new RegionValueDTO(buildValues(region, AgentRegionType.AVERAGE_PRICE)))
					.build())
			.toList()
			.get(0);
	}

	private static List<BigDecimal> buildValues(AgentRegion region, AgentRegionType type) {
		
		return region.getValues()
				.stream().filter(value -> value.getType() == type)
				.map(value -> value.getValue())
				.toList();
	}
	

}
