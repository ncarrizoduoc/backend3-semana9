package com.duoc.ms_cuentas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Cuenta> findAll(){
        return cuentaRepo.findAll();
    }

    public Cuenta findCuenta(Long id){
        Optional<Cuenta> cuenta = cuentaRepo.findById(id);
        if (cuenta.isEmpty()){
            return null;
        } else {
            return cuenta.get();
        }
    }

    public Cuenta save(Cuenta cuenta){
        return cuentaRepo.save(cuenta);
    }

    @Transactional
    public void deleteCuenta(Long id){
        cuentaRepo.deleteById(id);
    }

}