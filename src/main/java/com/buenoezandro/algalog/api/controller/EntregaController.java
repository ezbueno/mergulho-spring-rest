package com.buenoezandro.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.buenoezandro.algalog.api.mapper.EntregaMapper;
import com.buenoezandro.algalog.api.model.dto.EntregaDTO;
import com.buenoezandro.algalog.api.model.dto.input.EntregaInputDTO;
import com.buenoezandro.algalog.domain.repository.EntregaRepository;
import com.buenoezandro.algalog.domain.service.FinalizacaoEntregaService;
import com.buenoezandro.algalog.domain.service.SolicitacaoEntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/entregas")
public class EntregaController {

	private final EntregaRepository entregaRepository;
	private final FinalizacaoEntregaService finalizacaoEntregaService;
	private final SolicitacaoEntregaService solicitacaoEntregaService;
	private final EntregaMapper entregaMapper;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public EntregaDTO solicitar(@Valid @RequestBody EntregaInputDTO entregaInputDTO) {
		var novaEntrega = this.entregaMapper.toEntity(entregaInputDTO);
		var entregaSolicitada = this.solicitacaoEntregaService.solicitar(novaEntrega);
		return this.entregaMapper.toDTO(entregaSolicitada);
	}
	
	@PutMapping(value = "/{id}/finalizacao", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long id) {
		this.finalizacaoEntregaService.finalizar(id);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EntregaDTO> listar() {
		return this.entregaMapper.toCollectionDTO(this.entregaRepository.findAll());
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntregaDTO> buscar(@PathVariable Long id) {
		return this.entregaRepository.findById(id).map(entrega -> ResponseEntity.ok(this.entregaMapper.toDTO(entrega)))
				.orElse(ResponseEntity.notFound().build());
	}

}