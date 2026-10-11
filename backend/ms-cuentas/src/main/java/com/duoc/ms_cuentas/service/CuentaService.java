package com.duoc.ms_cuentas.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import com.duoc.ms_cuentas.messaging.CuentaPublisher;
import com.duoc.ms_cuentas.model.Cuenta;
import com.duoc.ms_cuentas.repository.CuentaRepository;

@Service 
public class CuentaService {
    
    private final CuentaRepository cuentaRepo;
    private final CuentaPublisher movPublisher;

    public CuentaService(CuentaRepository cuentaRepo, CuentaPublisher movPublisher) {
        this.cuentaRepo = cuentaRepo;
        this.movPublisher = movPublisher;
    }

    public Cuenta findCuenta(Long id, String nombre, String tipo){
        Optional<Cuenta> cuenta = cuentaRepo.findByCuentaIdAndNombreAndTipo(id, nombre, tipo);
        if (cuenta.isEmpty()){
            return null;
        } else {
            return cuenta.get();
        }

    }

    public Cuenta save(Cuenta cuenta){
        return cuentaRepo.save(cuenta);
    }

    public void deleteCuenta(Long id, String nombre, String tipo){
        cuentaRepo.deleteByCuentaIdAndNombreAndTipo(id, nombre, tipo);
    }

}