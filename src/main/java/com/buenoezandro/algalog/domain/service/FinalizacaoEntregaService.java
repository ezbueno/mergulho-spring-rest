package com.buenoezandro.algalog.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buenoezandro.algalog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {

	private final EntregaRepository entregaRepository;
	private final BuscaEntregaService buscaEntregaService;

	@Transactional
	public void finalizar(Long entregaId) {
		var entrega = this.buscaEntregaService.buscar(entregaId);

		entrega.finalizar();

		this.entregaRepository.save(entrega);
	}

}