package com.teste.entregas.config.rabbitmq.consumer;

import com.teste.entregas.service.EntregaService;
import dto.PedidoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitMqConsumer {

    private final EntregaService entregaService;

    @RabbitListener(queues = "ENTREGA")
    private void consumidor(PedidoDto pedidoDto){
        //System.out.println(pedidoDto);
        //System.out.println("---------------------------------");
        log.info("Novo pedido recebido, chamando serviço de registro de entrega");
        entregaService.salvarEntrega(pedidoDto);
    }
}
