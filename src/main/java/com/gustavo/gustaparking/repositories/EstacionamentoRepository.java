package com.gustavo.gustaparking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gustavo.gustaparking.models.Estacionamento;

@Repository
public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Long> {

}
