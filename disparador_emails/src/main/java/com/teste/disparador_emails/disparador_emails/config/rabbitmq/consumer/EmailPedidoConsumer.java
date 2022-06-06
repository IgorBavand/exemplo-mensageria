package com.teste.disparador_emails.disparador_emails.config.rabbitmq.consumer;

import com.teste.disparador_emails.disparador_emails.service.EmailService;
import dto.EmailPedidoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailPedidoConsumer{

    private final EmailService emailService;

    @RabbitListener(queues = "EMAIL_PEDIDO")
    private void enviarEmailPedido(EmailPedidoDto emailPedidoDto){
        System.out.println("---------------------------------");
        System.out.println(emailPedidoDto);
        log.info("Novo pedido recebido, enviando email para fornecedor...");
        emailService.enviarEmailPedido(emailPedidoDto);
    }
}
