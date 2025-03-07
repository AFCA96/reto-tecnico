package com.retoTecnico.clientePersona.consumer;

import com.retoTecnico.clientePersona.dto.SendMovimientoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @RabbitListener(queues = {"${spring.rabbitmq.queue}"})
    public void receive(@Payload SendMovimientoDto message){
        logger.info("Mensaje recibido {}", message);
    }
}
