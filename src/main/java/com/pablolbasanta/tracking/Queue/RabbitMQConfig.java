package com.pablolbasanta.tracking.Queue;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.session_queue.name}")
    private String session_queue;

    @Value("${rabbitmq.events_queue.name}")
    private String events_queue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.session_routing.key}")
    private String session_routingKey;

    @Value("${rabbitmq.events_routing.key}")
    private String events_routingKey;

    @Bean
    public Queue session_queue(){
        return new Queue(session_queue, false);
    }

    @Bean
    public Queue events_queue(){
        return new Queue(events_queue, false);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding session_binding(){
        return BindingBuilder
                .bind(session_queue())
                .to(exchange())
                .with(session_routingKey);
    }

    @Bean
    public Binding events_binding(){
        return BindingBuilder
                .bind(events_queue())
                .to(exchange())
                .with(events_routingKey);
    }

// Spring boot autoconfiguration provides following beans
    // ConnectionFactory
    // RabbitTemplate
    // RabbitAdmin
}
