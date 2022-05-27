package com.teste.pedidos.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClienteDto implements Serializable {

    private Long codigoCliente;
    private String nomeCliente;
}
