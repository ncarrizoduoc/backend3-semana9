package com.duoc.ms_banco.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duoc.ms_banco.model.EstadoCuenta;

public interface EstadoCuentaRepository extends JpaRepository<EstadoCuenta, Long>{
    
    Optional<EstadoCuenta> findByCuentaId(Long cuentaId);

}
