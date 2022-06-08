package com.teste.disparador_emails.disparador_emails.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "historico_emails")
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoEmails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Setter(AccessLevel.PRIVATE)
    @Column(name = "id_historico_email", unique = true, nullable = false, updatable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID idHistoricoEmail;

    @Column(name = "remetente")
    private String remetente;

    @Column(name = "destinatario")
    private String destinatario;

    @Column(name = "assunto")
    private String assunto;

    @Column(name = "data", columnDefinition = "TIMESTAMP")
    private LocalDate data;

    @Column(name = "hora" , columnDefinition = "VARCHAR(5)")
    private String hora;

    @PrePersist
    public void prePersist(){
        LocalDateTime date = LocalDateTime.now();
        data = LocalDate.of(date.getYear(),date.getMonthValue(),date.getDayOfMonth());
        hora = date.getHour() + ":" + date.getMinute();
    }



}
