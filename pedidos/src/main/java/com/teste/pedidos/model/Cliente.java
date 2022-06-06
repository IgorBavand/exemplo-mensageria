package com.teste.pedidos.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "clientes")
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_cliente", unique = true, nullable = false, updatable = false)
    private Long codigoCliente;

    @Column(name = "nome_cliente")
    private String nomeCliente;

    @Column(name = "email_cliente", unique = true)
    private String emailCliente;

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
