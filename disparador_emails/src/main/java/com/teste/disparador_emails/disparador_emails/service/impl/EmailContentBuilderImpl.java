package com.teste.disparador_emails.disparador_emails.service.impl;

import com.teste.disparador_emails.disparador_emails.service.EmailContentBuilder;
import dto.PedidoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import java.text.SimpleDateFormat;
import java.util.Date;


@Service
@Slf4j
@RequiredArgsConstructor
public class EmailContentBuilderImpl implements EmailContentBuilder {

    private Context contextThymeleaf;
    private final ITemplateEngine templateEngine;


    @Override
    public String buildEmailPedido(PedidoDto pedidoDto) {

        Date dataHoraAtual = new Date();
        String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
        String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);



        return null;
    }
}
