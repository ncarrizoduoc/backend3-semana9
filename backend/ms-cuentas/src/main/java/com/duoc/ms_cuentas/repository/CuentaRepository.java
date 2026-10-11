package com.duoc.ms_cuentas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duoc.ms_cuentas.model.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Long>{
    
    Optional<Cuenta> findByCuentaIdAndNombreAndTipo(Long cuentaId, String nombre, String tipo);

    List<Cuenta> findByCuentaId(Long cuentaId);

    void deleteByCuentaIdAndNombreAndTipo(Long cuentaId, String nombre, String tipo);
}
