package com.mx.sistemaRestaurante.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.sistemaRestaurante.model.Mesas;

public interface MesasDao extends JpaRepository<Mesas, Long> {

}
