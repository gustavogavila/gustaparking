package com.gustavo.gustaparking.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gustavo.gustaparking.models.Ocupacao;
import com.gustavo.gustaparking.models.Veiculo;

@Repository
public interface OcupacaoRepository extends JpaRepository<Ocupacao, Long> {

	Ocupacao findByVeiculo(Veiculo veiculo);

	@Query(value = "SELECT * FROM ocupacao o WHERE (o.data_hora_entrada BETWEEN :inicioDia AND :finalDia) AND (o.data_hora_saida BETWEEN :inicioDia AND :finalDia);",
			nativeQuery = true)
	List<Ocupacao> findByPeriod(@Param("inicioDia") LocalDateTime inicioDia, @Param("finalDia") LocalDateTime finalDia);

}
