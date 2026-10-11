package com.duoc.bff_banco_movil.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration 
public class RestClientConfig {

    @Bean("bancoRestClient")
    public RestClient bancoRestClient(){
        return RestClient.builder()
            .baseUrl("http://ms-banco:8090")
            .build();
    }

    @Bean("cuentasRestClient")
    public RestClient cuentasRestClient(){
        return RestClient.builder()
            .baseUrl("http://ms-cuentas:8092")
            .build();
    }

}
