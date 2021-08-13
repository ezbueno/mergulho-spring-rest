package com.buenoezandro.algalog.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.buenoezandro.algalog.api.model.dto.OcorrenciaDTO;
import com.buenoezandro.algalog.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OcorrenciaMapper {

	private final ModelMapper modelMapper;

	public OcorrenciaDTO toDTO(Ocorrencia ocorrencia) {
		return this.modelMapper.map(ocorrencia, OcorrenciaDTO.class);
	}

	public List<OcorrenciaDTO> toCollectionDTO(List<Ocorrencia> ocorrencias) {
		return ocorrencias.stream().map(this::toDTO).collect(Collectors.toList());
	}

}