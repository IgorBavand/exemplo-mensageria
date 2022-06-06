package com.teste.disparador_emails.disparador_emails.service;

import dto.EmailPedidoDto;
import dto.PedidoDto;
import org.springframework.core.io.FileSystemResource;

public interface EmailService {

    void enviarEmailPedido(EmailPedidoDto pedidoDto);

    FileSystemResource createImage(String fileName, String ext);


}
