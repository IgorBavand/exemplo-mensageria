package com.teste.disparador_emails.disparador_emails.service;

import dto.PedidoDto;

public interface EmailContentBuilder {
    String buildEmailPedido(PedidoDto pedidoDto);
}
