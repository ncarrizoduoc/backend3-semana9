package com.duoc.msBanco.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duoc.msBanco.model.EstadoCuenta;

public interface EstadoCuentaRepository extends JpaRepository<EstadoCuenta, Long>{
    
    Optional<EstadoCuenta> findByCuentaId(Long cuentaId);

}
