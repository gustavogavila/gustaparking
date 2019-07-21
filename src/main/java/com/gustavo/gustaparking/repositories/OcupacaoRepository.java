package com.gustavo.gustaparking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gustavo.gustaparking.models.Ocupacao;
import com.gustavo.gustaparking.models.Veiculo;

@Repository
public interface OcupacaoRepository extends JpaRepository<Ocupacao, Long> {

	Ocupacao findByVeiculo(Veiculo veiculo);

}
