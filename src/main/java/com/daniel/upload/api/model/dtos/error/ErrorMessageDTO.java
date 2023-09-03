package com.daniel.upload.api.model.dtos.error;

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
public class ErrorMessageDTO {
	
    private String type;
    private String message;    

}
