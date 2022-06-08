package com.teste.disparador_emails.disparador_emails.service.impl;

import com.teste.disparador_emails.disparador_emails.model.HistoricoEmails;
import com.teste.disparador_emails.disparador_emails.repository.HistoricoEmailsRepository;
import com.teste.disparador_emails.disparador_emails.service.EmailContentBuilder;
import com.teste.disparador_emails.disparador_emails.service.EmailService;
import dto.EmailPedidoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender disparadorEmail;
    private final EmailContentBuilder emailContentBuilder;

    private final HistoricoEmailsRepository historicoEmailsRepository;
    private File fileTemp;
    @Value("${spring.mail.username}")
    private String emailFrom;


    @Override
    public void enviarEmailPedido(EmailPedidoDto emailPedidoDto) {
        try {
            MimeMessagePreparator emailPreparator = mimeMessage -> {
                MimeMessageHelper email = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                email.setTo("hosp.site1@gmail.com");
                //tenta com o segundo parametro se o primeiro n tiver setado
                email.setFrom(emailFrom, "disparador.teste@gmail.com");
                email.setSubject(emailPedidoDto.getAssunto());
                String content = emailContentBuilder.buildEmailPedido(emailPedidoDto);
                email.setText(content, true);
                FileSystemResource resCabecalho = this.createImage("cabecalho.png", "png");
                email.addInline("cabecalho", resCabecalho, "image/png");
            };

            disparadorEmail.send(emailPreparator);
            deleteImagem();
            log.info("O e-mail foi enviado com sucesso.");

            //SALVANDO NO HISTORICO DE EMAILS
            HistoricoEmails historicoEmails = new HistoricoEmails();
            historicoEmails.setRemetente(emailFrom);
            historicoEmails.setAssunto(emailPedidoDto.getAssunto());
            historicoEmails.setDestinatario("hosp.site1@gmail.com");
            historicoEmailsRepository.save(historicoEmails);
            log.info("Salvando no histórico de emails.");
        } catch (Exception exception) {
            log.info("Erro ao enviar e-mail. Motivo: " + exception.getMessage());
        }
    }

    private void deleteImagem() {
        if (fileTemp != null) {
            fileTemp.delete();
        }
    }

    public FileSystemResource createImage(String fileName, String ext) {
        FileSystemResource f = null;
        try {
            fileTemp = File.createTempFile(UUID.randomUUID().toString(), "." + ext);

            FileUtils.copyInputStreamToFile(this.getClass().getResourceAsStream("/static/image/" + fileName), fileTemp);

            f = new FileSystemResource(fileTemp);

        } catch (IOException e) {
            throw new Exception("Problema ao criar imagem do email. motivo: " + e.getMessage());
        } finally {
            return f;
        }
    }
}
