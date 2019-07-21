package com.gustavo.gustaparking.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustavo.gustaparking.models.Veiculo;
import com.gustavo.gustaparking.services.VeiculoService;

@RestController
@RequestMapping(value = "veiculos")
public class VeiculoController {

	@Autowired
	private VeiculoService service;

	@GetMapping
	public ResponseEntity<List<Veiculo>> listarCadastrados() {
		List<Veiculo> veiculos = service.listarCadastrados();
		return ResponseEntity.ok(veiculos);
	}

}
