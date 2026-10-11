package com.duoc.bff_banco_cajero.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import com.duoc.bff_banco_cajero.model.Cuenta;


@Component 
public class CuentasMicroserviceClient {

    private final RestClient restClient;

    public CuentasMicroserviceClient(@Qualifier("cuentaRestClient") RestClient restClient){
        this.restClient = restClient;
    }

    public List<Cuenta> findAll(String jwt){
        return restClient.get()
            .uri("/api/banco/cuentas")
            .header(HttpHeaders.AUTHORIZATION, jwt)
            .retrieve()
            .body(new ParameterizedTypeReference<List<Cuenta>>() {});
    }

    public Cuenta findCuenta(Long id, String jwt){
        try {
            return restClient.get()
                .uri("/api/banco/cuentas/{id}", id)
                .header(HttpHeaders.AUTHORIZATION, jwt)
                .retrieve()
                .body(Cuenta.class);
        } catch (HttpClientErrorException.NotFound ex) {
            return null;
        }
    }

}
