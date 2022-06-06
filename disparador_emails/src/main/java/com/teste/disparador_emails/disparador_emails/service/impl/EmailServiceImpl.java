package com.teste.disparador_emails.disparador_emails.service.impl;

import com.teste.disparador_emails.disparador_emails.service.EmailContentBuilder;
import com.teste.disparador_emails.disparador_emails.service.EmailService;
import dto.EmailPedidoDto;
import dto.PedidoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
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
    public void enviarEmailPedido(EmailPedidoDto emailPedidoDto) {
        try {
            MimeMessagePreparator emailPreparator = mimeMessage -> {
                MimeMessageHelper email = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                email.setTo("hosp.site1@gmail.com");
                email.setFrom(emailFrom, "Pedido");
                email.setSubject("Pedido - Notificação de Pedido");
                String content = emailContentBuilder.buildEmailPedido(emailPedidoDto);
                email.setText(content, true);

                //FileSystemResource resCabecalho = this.createImage("logo.png", "png");
                //email.addInline("logo", resCabecalho, "image/png");
            };
            disparadorEmail.send(emailPreparator);
            log.info("O e-mail foi enviado com sucesso.");
        } catch (Exception exception) {
            log.info("Erro ao enviar e-mail de confirmação de conta. Motivo: " + exception.getMessage());
        }
    }

    @Override
    public FileSystemResource createImage(String fileName, String ext) {
        return null;
    }
}
