package com.gustavo.gustaparking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gustavo.gustaparking.models.ParametroSistema;

@Repository
public interface ParametroSistemaRepository extends JpaRepository<ParametroSistema, Long> {

}
