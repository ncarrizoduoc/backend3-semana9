package com.duoc.bff_banco_movil.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.duoc.bff_banco_movil.model.EstadoCuenta;
import com.duoc.bff_banco_movil.model.MovimientoCuenta;
import com.duoc.bff_banco_movil.model.TransferenciaRequest;

@Component
public class BancoMicroserviceClient {
    private final RestClient restClient;

    public BancoMicroserviceClient(@Qualifier("bancoRestClient") RestClient restClient) {
        this.restClient = restClient;
    }

    public EstadoCuenta getEstadoCuenta(Long id, String jwt){
        return restClient.get()
            .uri("/api/banco/estado-cuenta/{id}", id)
            .header(HttpHeaders.AUTHORIZATION, jwt)
            .retrieve()
            .body(EstadoCuenta.class);
    }

    public MovimientoCuenta realizarTransferencia(TransferenciaRequest request, String jwt) {
        return restClient.post()
            .uri("/api/banco/transferencias")
            .body(request)
            .header(HttpHeaders.AUTHORIZATION, jwt)
            .retrieve()
            .body(MovimientoCuenta.class);
    }

}
