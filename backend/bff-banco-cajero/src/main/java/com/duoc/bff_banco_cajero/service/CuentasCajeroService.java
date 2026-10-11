package com.duoc.bff_banco_cajero.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.duoc.bff_banco_cajero.client.CuentasMicroserviceClient;
import com.duoc.bff_banco_cajero.model.Cuenta;

@Service 
public class CuentasCajeroService {

    private final CuentasMicroserviceClient restClient;

    public CuentasCajeroService(CuentasMicroserviceClient restClient){
        this.restClient = restClient;
    }

    public List<Cuenta> findAll(String jwt){
        return restClient.findAll(jwt);
    }

    public Cuenta findCuenta(Long id, String jwt){
        return restClient.findCuenta(id, jwt);
    }

}
