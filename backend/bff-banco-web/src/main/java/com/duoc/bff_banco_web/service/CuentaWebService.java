package com.duoc.bff_banco_web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.duoc.bff_banco_web.client.CuentasMicroserviceClient;
import com.duoc.bff_banco_web.model.Cuenta;

@Service 
public class CuentaWebService {

    private final CuentasMicroserviceClient restClient;

    public CuentaWebService(CuentasMicroserviceClient restClient){
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

    public Cuenta updateCuenta(Long id, Cuenta cuenta, String jwt){
        return restClient.updateCuenta(id, cuenta, jwt);
    }

    public boolean deleteCuenta(Long id, String jwt){
        return restClient.deleteCuenta(id, jwt);
    }

}
