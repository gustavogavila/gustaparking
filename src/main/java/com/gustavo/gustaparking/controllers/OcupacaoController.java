package com.gustavo.gustaparking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustavo.gustaparking.models.Ocupacao;
import com.gustavo.gustaparking.models.Veiculo;
import com.gustavo.gustaparking.services.OcupacaoService;

@RestController
@RequestMapping(value = "ocupacoes")
public class OcupacaoController {

	@Autowired
	private OcupacaoService service;

	@PostMapping("/arrive")
	public ResponseEntity<Ocupacao> arrive(@RequestBody Veiculo veiculo) {
		Ocupacao arrive = service.arrive(veiculo.getPlaca());
		return ResponseEntity.ok(arrive);
	}

	@PostMapping("/depart")
	public ResponseEntity<Ocupacao> depart(@RequestBody Veiculo veiculo) {
		Ocupacao depart = service.depart(veiculo);
		return ResponseEntity.ok(depart);
	}
}
