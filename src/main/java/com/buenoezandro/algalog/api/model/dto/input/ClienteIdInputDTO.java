package com.buenoezandro.algalog.api.model.dto.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteIdInputDTO {

	@NotNull
	private Long id;
	
}