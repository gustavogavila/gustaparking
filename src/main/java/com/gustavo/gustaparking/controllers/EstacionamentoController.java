package com.gustavo.gustaparking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustavo.gustaparking.models.Estacionamento;
import com.gustavo.gustaparking.services.EstacionamentoService;

@RestController
@RequestMapping(value = "estacionamentos")
public class EstacionamentoController {

	@Autowired private EstacionamentoService service;

	@PutMapping(value = "/{id}")
	public ResponseEntity<Estacionamento> update(@PathVariable Long id, @RequestBody Estacionamento estacionamento) {
		Estacionamento estacionamentoAtualizado = service.update(id, estacionamento);
		return ResponseEntity.ok(estacionamentoAtualizado);
	}
}
