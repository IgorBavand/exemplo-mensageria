package com.teste.pedidos.model;

import lombok.*;

import javax.persistence.*;

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


}
