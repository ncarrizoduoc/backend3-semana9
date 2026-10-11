package com.duoc.bff_banco_web.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.duoc.bff_banco_web.client.BancoMicroserviceClient;
import com.duoc.bff_banco_web.model.EstadoCuenta;
import com.duoc.bff_banco_web.model.MovimientoCuenta;
import com.duoc.bff_banco_web.model.TransferenciaRequest;

@Service 
public class BancoWebService {

    private final BancoMicroserviceClient bancoMicroserviceClient;

    public BancoWebService(BancoMicroserviceClient bancoMicroserviceClient) {
        this.bancoMicroserviceClient = bancoMicroserviceClient;
    }

    public EstadoCuenta getEstadoCuenta(Long id, String jwt){
        EstadoCuenta estadoCuenta = bancoMicroserviceClient.getEstadoCuenta(id, jwt);
        if (estadoCuenta == null){
            return null;
        }
        return estadoCuenta;
    }

    public MovimientoCuenta realizarTransferencia(TransferenciaRequest request, String jwt) {
        return bancoMicroserviceClient.realizarTransferencia(request, jwt);
    }

    public MovimientoCuenta realizarMovimiento(MovimientoCuenta movimiento, String jwt) {
        return bancoMicroserviceClient.realizarMovimiento(movimiento, jwt);
    }

    public List<MovimientoCuenta> verMovimientos(String tipoMovimiento, LocalDate fecha, String jwt) {
        return bancoMicroserviceClient.verMovimientos(tipoMovimiento, fecha, jwt);
    }

}
