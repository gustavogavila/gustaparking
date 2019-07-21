package com.gustavo.gustaparking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gustavo.gustaparking.models.Ocupacao;
import com.gustavo.gustaparking.models.Veiculo;
import com.gustavo.gustaparking.models.dtos.FinanceiroPeriodo;
import com.gustavo.gustaparking.models.dtos.PermanenciaMediaDiaria;
import com.gustavo.gustaparking.models.dtos.PermanenciaMediaPeriodo;
import com.gustavo.gustaparking.services.OcupacaoService;

@RestController
@RequestMapping(value = "ocupacoes")
public class OcupacaoController {

	@Autowired
	private OcupacaoService service;

	@PostMapping("/chegada")
	public ResponseEntity<Ocupacao> chegada(@RequestBody Veiculo veiculo) {
		Ocupacao arrive = service.arrive(veiculo.getPlaca());
		return ResponseEntity.ok(arrive);
	}

	@PostMapping("/saida")
	public ResponseEntity<Ocupacao> saida(@RequestBody Veiculo veiculo) {
		Ocupacao depart = service.depart(veiculo);
		return ResponseEntity.ok(depart);
	}

	@GetMapping("/permanenciamediadiaria")
	public ResponseEntity<PermanenciaMediaDiaria> relatorioPermanenciaMediaDiaria(@RequestParam String data) {
		PermanenciaMediaDiaria permanenciaMediaDiaria = service.getRelatorioPermanenciaMediaDiaria(data);
		return ResponseEntity.ok(permanenciaMediaDiaria);
	}

	@GetMapping("/permanenciamediaperiodo")
	public ResponseEntity<PermanenciaMediaPeriodo> relatorioPermanenciaMediaPeriodo(@RequestParam String dataInicio,
			@RequestParam String dataFinal) {
		PermanenciaMediaPeriodo permanenciaMediaPeriodo = service.getRelatorioPermanenciaMediaPeriodo(dataInicio,
				dataFinal);
		return ResponseEntity.ok(permanenciaMediaPeriodo);
	}

	@GetMapping("/financeiro")
	public ResponseEntity<FinanceiroPeriodo> relatorioFinanceiroPeriodo(@RequestParam String dataInicio,
			@RequestParam String dataFinal) {
		FinanceiroPeriodo financeiroPeriodo = service.getRelatorioFinanceiroPeriodo(dataInicio, dataFinal);
		return ResponseEntity.ok(financeiroPeriodo);
	}

}
