package com.buenoezandro.algalog.api.model.dto.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaInputDTO {

	@NotBlank
	private String descricao;

}