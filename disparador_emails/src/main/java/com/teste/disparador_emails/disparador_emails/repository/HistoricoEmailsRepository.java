package com.teste.disparador_emails.disparador_emails.repository;

import com.teste.disparador_emails.disparador_emails.model.HistoricoEmails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HistoricoEmailsRepository extends JpaRepository<HistoricoEmails, UUID> {
}
