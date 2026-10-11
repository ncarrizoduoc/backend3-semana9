package com.duoc.ms_cuentas.messaging;

import java.util.Map;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.duoc.ms_cuentas.model.Cuenta;

@Component 
public class CuentaPublisher {
    private static final String TOPIC = "cuentas";
    private final JmsTemplate jmsTemplate;

    public CuentaPublisher(JmsTemplate jmsTemplate){
        this.jmsTemplate = jmsTemplate;
    }

    // Publicar evento con información de movimiento de cuenta
    public void publicarMovimientoCuenta(Cuenta cuenta, String tipo){
        Map<String, Object> event = Map.of(
            "message", (tipo.toLowerCase() == "eliminar") ? "Se ha creado una cuenta" : "Se ha eliminado una cuenta",
            "cuenta" , cuenta
        );
        jmsTemplate.convertAndSend(TOPIC, event);
    }

}
