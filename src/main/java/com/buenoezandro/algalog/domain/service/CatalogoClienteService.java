package com.buenoezandro.algalog.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buenoezandro.algalog.domain.exception.NegocioException;
import com.buenoezandro.algalog.domain.model.Cliente;
import com.buenoezandro.algalog.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CatalogoClienteService {

	private final ClienteRepository clienteRepository;

	public Cliente buscar(Long clienteId) {
		return this.clienteRepository.findById(clienteId)
				.orElseThrow(() -> new NegocioException("Cliente não encontrado!"));
	}

	@Transactional
	public Cliente salvar(Cliente cliente) {
		var emailExistente = this.clienteRepository.findByEmail(cliente.getEmail()).stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));

		if (emailExistente) {
			throw new NegocioException("Já existe um cliente cadastrado com este e-mail!");
		}

		return this.clienteRepository.save(cliente);
	}

	@Transactional
	public void excluir(Long id) {
		this.clienteRepository.deleteById(id);
	}

}