package com.buenoezandro.algalog.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.buenoezandro.algalog.api.model.dto.EntregaDTO;
import com.buenoezandro.algalog.api.model.dto.input.EntregaInputDTO;
import com.buenoezandro.algalog.domain.model.Entrega;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EntregaMapper {

	private final ModelMapper modelMapper;

	public EntregaDTO toDTO(Entrega entrega) {
		return this.modelMapper.map(entrega, EntregaDTO.class);
	}

	public List<EntregaDTO> toCollectionDTO(List<Entrega> entregas) {
		return entregas.stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	public Entrega toEntity(EntregaInputDTO entregaInputDTO) {
		return this.modelMapper.map(entregaInputDTO, Entrega.class);
	}

}