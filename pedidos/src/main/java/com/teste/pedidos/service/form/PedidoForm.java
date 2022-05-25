package com.teste.pedidos.service.form;

import lombok.Data;

@Data
public class PedidoForm {

    private Long codigoCliente;
    private double valorTotal;
    private String enderecoEntrega;
}
