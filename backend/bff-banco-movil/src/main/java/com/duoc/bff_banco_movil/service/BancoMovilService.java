package com.duoc.bff_banco_movil.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.duoc.bff_banco_movil.client.BancoMicroserviceClient;
import com.duoc.bff_banco_movil.model.EstadoCuenta;
import com.duoc.bff_banco_movil.model.MovimientoCuenta;
import com.duoc.bff_banco_movil.model.TransferenciaRequest;

@Service 
public class BancoMovilService {

    private final BancoMicroserviceClient bancoMicroserviceClient;

    public BancoMovilService(BancoMicroserviceClient bancoMicroserviceClient) {
        this.bancoMicroserviceClient = bancoMicroserviceClient;
    }

    public EstadoCuenta getEstadoCuenta(Long id, String jwt){
        EstadoCuenta estadoCuenta = bancoMicroserviceClient.getEstadoCuenta(id, jwt);
        if (estadoCuenta == null){
            return null;
        }

        // Simplificar estado de cuenta para dispositivos moviles
        List<MovimientoCuenta> movimientos = estadoCuenta.getMovimientos();
        
        // Ordenar lista de movimientos por fecha descendente
        movimientos.sort((m1, m2) -> m2.getFecha().compareTo(m1.getFecha()));
        // Mostrar solo ultimos 5 movimientos
        movimientos = movimientos.subList(0, Math.min(5, movimientos.size()));

        estadoCuenta.setMovimientos(movimientos);
        return estadoCuenta;
        
    }

    public MovimientoCuenta realizarTransferencia(TransferenciaRequest request, String jwt) {
        return bancoMicroserviceClient.realizarTransferencia(request, jwt);
    }

}
