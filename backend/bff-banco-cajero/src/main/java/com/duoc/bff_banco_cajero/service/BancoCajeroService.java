package com.duoc.bff_banco_cajero.service;

import org.springframework.stereotype.Service;

import com.duoc.bff_banco_cajero.client.BancoMicroserviceClient;
import com.duoc.bff_banco_cajero.model.EstadoCuenta;
import com.duoc.bff_banco_cajero.model.MovimientoCuenta;
import com.duoc.bff_banco_cajero.model.MovimientoCuentaRequest;

@Service 
public class BancoCajeroService {

    private final BancoMicroserviceClient bancoMicroserviceClient;

    public BancoCajeroService(BancoMicroserviceClient bancoMicroserviceClient) {
        this.bancoMicroserviceClient = bancoMicroserviceClient;
    }

    public EstadoCuenta getEstadoCuenta(Long id, String jwt){
        EstadoCuenta estadoCuenta = bancoMicroserviceClient.getEstadoCuenta(id, jwt);
        if (estadoCuenta == null){
            return null;
        }
        return estadoCuenta;
        
    }

    // Metodo para realizar un movimiento desde un cajero automático (solo permite retiro o depósito)
    public MovimientoCuenta realizarMovimiento(MovimientoCuentaRequest request, String jwt){
        String descripcion = (
            request.getTransaccion().equals("deposito")? "Depósito realizado en cajero" : "Retiro realizado desde cajero"
        );

        // Si es retiro, el monto debe ser negativo
        if (request.getTransaccion().equals("retiro")){
            request.setMonto(request.getMonto() * -1);
        }
        
        MovimientoCuenta movimiento = MovimientoCuenta.builder()
            .id(Long.valueOf(0))
            .cuentaId(request.getCuentaId())
            .fecha(request.getFecha())
            .transaccion(request.getTransaccion())
            .monto(request.getMonto())
            .descripcion(descripcion)
            .build();
        
        MovimientoCuenta movimientoCreado = bancoMicroserviceClient.realizarMovimiento(movimiento, jwt);
        return movimientoCreado;
    }

}
