package com.buenoezandro.algalog.domain.service;

import org.springframework.stereotype.Service;

import com.buenoezandro.algalog.domain.exception.EntidadeNaoEncontradaException;
import com.buenoezandro.algalog.domain.model.Entrega;
import com.buenoezandro.algalog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {

	private final EntregaRepository entregaRepository;

	public Entrega buscar(Long entregaId) {
		return this.entregaRepository.findById(entregaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada!"));
	}

}