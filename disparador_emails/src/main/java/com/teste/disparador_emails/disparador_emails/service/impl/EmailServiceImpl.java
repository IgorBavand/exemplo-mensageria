package com.teste.disparador_emails.disparador_emails.service.impl;

import com.teste.disparador_emails.disparador_emails.service.EmailContentBuilder;
import com.teste.disparador_emails.disparador_emails.service.EmailService;
import dto.PedidoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.File;


@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender disparadorEmail;
    private final EmailContentBuilder emailContentBuilder;
    private File fileTemp;
    @Value("${spring.mail.username}")
    private String emailFrom;
    @Override
    public void enviarEmailPedido(PedidoDto pedidoDto) {

    }

    @Override
    public FileSystemResource createImage(String fileName, String ext) {
        return null;
    }
}
