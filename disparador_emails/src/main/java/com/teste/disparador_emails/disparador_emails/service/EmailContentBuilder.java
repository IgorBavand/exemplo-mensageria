package com.teste.disparador_emails.disparador_emails.service;

import dto.EmailPedidoDto;
import dto.PedidoDto;

public interface EmailContentBuilder {
    String buildEmailPedido(EmailPedidoDto emailPedidoDto);
}
