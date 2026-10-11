package com.duoc.bff_banco_movil.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.duoc.bff_banco_movil.client.CuentasMicroserviceClient;
import com.duoc.bff_banco_movil.model.Cuenta;

@Service 
public class CuentasMovilService {

    private final CuentasMicroserviceClient restClient;

    public CuentasMovilService(CuentasMicroserviceClient restClient){
        this.restClient = restClient;
    }

    public List<Cuenta> findAll(String jwt){
        return restClient.findAll(jwt);
    }

    public Cuenta findCuenta(Long id, String jwt){
        return restClient.findCuenta(id, jwt);
    }

    public Cuenta saveCuenta(Cuenta cuenta, String jwt){
        return restClient.saveCuenta(cuenta, jwt);
    }

    public boolean deleteCuenta(Long id, String jwt){
        return restClient.deleteCuenta(id, jwt);
    }

}
