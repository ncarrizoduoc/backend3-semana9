package com.duoc.bff_banco_web.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.http.HttpHeaders;

import com.duoc.bff_banco_web.model.Cuenta;

@Component 
public class CuentasMicroserviceClient {

    private final RestClient restClient;

    public CuentasMicroserviceClient(@Qualifier("cuentasRestClient") RestClient restClient){
        this.restClient = restClient;
    }

    public List<Cuenta> findAll(String jwt){
        return restClient.get()
            .uri("/api/banco/cuentas/")
            .header(HttpHeaders.AUTHORIZATION, jwt)
            .retrieve()
            .body(new ParameterizedTypeReference<List<Cuenta>>() {});
    }

    public Cuenta findCuenta(Long id, String jwt){
        return restClient.get()
            .uri("/api/banco/cuentas/{id}", id)
            .header(HttpHeaders.AUTHORIZATION, jwt)
            .retrieve()
            .body(Cuenta.class);
    }

    public Cuenta saveCuenta(Cuenta cuenta, String jwt){
        return restClient.post()
            .uri("/api/banco/cuentas")
            .body(cuenta)
            .header(HttpHeaders.AUTHORIZATION, jwt)
            .retrieve()
            .body(Cuenta.class);
    }

    public Cuenta updateCuenta(Long id, Cuenta cuenta, String jwt){
        return restClient.put()
            .uri("/api/banco/cuentas/{id}", id)
            .body(cuenta)
            .header(HttpHeaders.AUTHORIZATION, jwt)
            .retrieve()
            .body(Cuenta.class);
    }

    public boolean deleteCuenta(Long id, String jwt) {
        var response = restClient.delete()
                .uri("/api/banco/cuentas/{id}", id)
                .header(HttpHeaders.AUTHORIZATION, jwt)
                .retrieve()
                .toBodilessEntity();

        return response.getStatusCode().value() == 204;
    }
    

}