package com.buenoezandro.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.buenoezandro.algalog.api.mapper.OcorrenciaMapper;
import com.buenoezandro.algalog.api.model.dto.OcorrenciaDTO;
import com.buenoezandro.algalog.api.model.dto.input.OcorrenciaInputDTO;
import com.buenoezandro.algalog.domain.service.BuscaEntregaService;
import com.buenoezandro.algalog.domain.service.RegistroOcorrenciaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

	private final RegistroOcorrenciaService registroOcorrenciaService;
	private final OcorrenciaMapper ocorrenciaMapper;
	private final BuscaEntregaService buscaEntregaService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public OcorrenciaDTO registrar(@PathVariable Long entregaId,
			@Valid @RequestBody OcorrenciaInputDTO ocorrenciaInputDTO) {
		var ocorrenciaRegistrada = this.registroOcorrenciaService.registrar(entregaId,
				ocorrenciaInputDTO.getDescricao());
		return this.ocorrenciaMapper.toDTO(ocorrenciaRegistrada);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OcorrenciaDTO> listar(@PathVariable Long entregaId) {
		var entrega = this.buscaEntregaService.buscar(entregaId);
		return this.ocorrenciaMapper.toCollectionDTO(entrega.getOcorrencias());
	}

}