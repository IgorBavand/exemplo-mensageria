package com.teste.pedidos.config.rabbitmq.producer;


import lombok.RequiredArgsConstructor;
import org.hibernate.envers.Audited;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void enviarMensagem(String nomeFila, Object mensagem){
        this.rabbitTemplate.convertAndSend(nomeFila, mensagem);
    }


}
