package com.gustavo.gustaparking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gustavo.gustaparking.models.Vaga;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {

	List<Vaga> findBySituacao(int i);

	@Query(value = "select count('id') as total_vagas from vaga", nativeQuery = true)
	Long countById();

	Long countBySituacao(Integer integer);

}
