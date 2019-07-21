package com.gustavo.gustaparking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustavo.gustaparking.models.ParametroSistema;
import com.gustavo.gustaparking.services.ParametroSistemaService;

@RestController
@RequestMapping(value = "parametrossistema")
public class ParametroSistemaController {

	@Autowired
	private ParametroSistemaService service;

	@PutMapping
	public ResponseEntity<ParametroSistema> update(@RequestBody ParametroSistema parametros) {
		ParametroSistema parametrosAtualizados = service.update(parametros);
		return ResponseEntity.ok(parametrosAtualizados);

	}
}
