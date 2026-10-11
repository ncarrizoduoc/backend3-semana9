package com.duoc.bff_banco_web.client;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.duoc.bff_banco_web.model.EstadoCuenta;
import com.duoc.bff_banco_web.model.MovimientoCuenta;
import com.duoc.bff_banco_web.model.TransferenciaRequest;


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

    public MovimientoCuenta realizarMovimiento(MovimientoCuenta movimiento, String jwt) {
        return restClient.post()
            .uri("/api/banco/movimientos")
            .body(movimiento)
            .header(HttpHeaders.AUTHORIZATION, jwt)
            .retrieve()
            .body(MovimientoCuenta.class);
    }

    public List<MovimientoCuenta> verMovimientos(String tipoMovimiento, LocalDate fecha, String jwt) {
        if (tipoMovimiento == null && fecha != null){
            return restClient.get()
                .uri(uriBuilder -> uriBuilder
                    .path("/api/banco/movimientos")
                    .queryParam("fecha", fecha)
                    .build())
                .header(HttpHeaders.AUTHORIZATION, jwt)
                .retrieve()
                .body(List.class);

        } else if (tipoMovimiento != null && fecha == null){
            return restClient.get()
                .uri(uriBuilder -> uriBuilder
                    .path("/api/banco/movimientos")
                    .queryParam("tipoMovimiento", tipoMovimiento)
                    .build())
                .header(HttpHeaders.AUTHORIZATION, jwt)
                .retrieve()
                .body(List.class);

        } else if (tipoMovimiento == null && fecha == null){
            return restClient.get()
                .uri("/api/banco/movimientos")
                .header(HttpHeaders.AUTHORIZATION, jwt)
                .retrieve()
                .body(List.class);

        }
        return restClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/api/banco/movimientos")
                .queryParam("tipoMovimiento", tipoMovimiento)
                .queryParam("fecha", fecha)
                .build())
            .header(HttpHeaders.AUTHORIZATION, jwt)
            .retrieve()
            .body(List.class);
    }

}
