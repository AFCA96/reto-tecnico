package com.retoTecnico.cuentaMovimiento.mq.publisher;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublisherConfig {

    @Value("${spring.rabbitmq.queue}")
    private String message;

    @Bean
    public Queue queue() {
        return new Queue(this.message, true);
    }
}
