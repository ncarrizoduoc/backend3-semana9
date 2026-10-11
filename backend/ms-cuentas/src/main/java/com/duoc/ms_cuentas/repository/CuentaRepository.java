package com.duoc.ms_cuentas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duoc.ms_cuentas.model.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Long>{

}
