package com.buenoezandro.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.buenoezandro.algalog.domain.model.Cliente;
import com.buenoezandro.algalog.domain.repository.ClienteRepository;
import com.buenoezandro.algalog.domain.service.CatalogoClienteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

	private final ClienteRepository clienteRepository;
	private final CatalogoClienteService catalogoClienteService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cliente> listar() {
		return this.clienteRepository.findAll();
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> buscar(@PathVariable Long id) {
		return this.clienteRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return this.catalogoClienteService.salvar(cliente);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
		if (!this.clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		cliente.setId(id);
		return ResponseEntity.ok(this.catalogoClienteService.salvar(cliente));
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (!this.clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		this.catalogoClienteService.excluir(id);
		return ResponseEntity.noContent().build();
	}

}