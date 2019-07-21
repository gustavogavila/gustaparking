package com.gustavo.gustaparking.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gustavo.gustaparking.models.Vaga;
import com.gustavo.gustaparking.models.dtos.VagaDTO;
import com.gustavo.gustaparking.services.VagaService;

@CrossOrigin
@RestController
@RequestMapping("vagas")
public class VagaController {

	@Autowired
	private VagaService service;

	@PostMapping
	public ResponseEntity<Vaga> save(@RequestBody Vaga vaga) {
		Vaga vagaSalva = service.save(vaga);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vagaSalva.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/relatoriodiario")
	public ResponseEntity<VagaDTO> relatorioDiario() {
		VagaDTO relatorioDiario = service.getRelatorioDiario();
		return ResponseEntity.ok(relatorioDiario);
	}
}
