package com.teste.disparador_emails.disparador_emails.config.rabbitmq.consumer;

import dto.PedidoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailPedidoConsumer{

    @RabbitListener(queues = "EMAIL_PEDIDO")
    private void consumidor(PedidoDto pedidoDto){
        //System.out.println(pedidoDto);
        //System.out.println("---------------------------------");
        log.info("Novo pedido recebido, chamando serviço de registro de entrega");
        //entregaService.salvarEntrega(pedidoDto);
    }
}
