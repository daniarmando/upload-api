package com.daniel.upload.api.model.dtos.agent.xml;

import java.math.BigDecimal;
import java.util.Collection;

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
public class RegionValueDTO {

	private Collection<BigDecimal> value;
	
}
