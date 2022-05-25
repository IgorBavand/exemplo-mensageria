package com.teste.pedidos.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "clientes")
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @Column(name = "codigo_cliente", unique = true, nullable = false, updatable = false)
    private Long codigoCliente;

    @Column(name = "nome_cliente")
    private String nomeCliente;
}
