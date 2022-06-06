package com.teste.entregas.config.rabbitmq.consumer;

import com.teste.entregas.model.Entrega;
import com.teste.entregas.model.enums.StatusPedido;
import com.teste.entregas.repository.EntregaRepository;
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

    private final EntregaRepository entregaRepository;


    @RabbitListener(queues = "ENTREGA")
    private void consumidor(PedidoDto pedidoDto){

        log.info("Novo pedido recebido, chamando serviço de registro de entrega");
        Entrega entrega = new Entrega();
        entrega.setEnderecoEntrega(pedidoDto.getEnderecoEntrega());
        entrega.setIdPedido(pedidoDto.getIdPedido());
        entrega.setStatusPedido(StatusPedido.PENDENTE);
        log.info("registrando entrega");
        entregaRepository.save(entrega);
    }
}
