package com.buenoezandro.algalog.domain.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buenoezandro.algalog.domain.model.Entrega;
import com.buenoezandro.algalog.domain.model.StatusEntrega;
import com.buenoezandro.algalog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

	private final CatalogoClienteService catalogoClienteService;
	private final EntregaRepository entregaRepository;

	@Transactional
	public Entrega solicitar(Entrega entrega) {
		var cliente = this.catalogoClienteService.buscar(entrega.getCliente().getId());

		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());

		return this.entregaRepository.save(entrega);
	}

}