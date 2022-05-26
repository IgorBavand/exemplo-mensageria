package com.teste.pedidos.service.form;

import com.teste.pedidos.model.Pedido;
import com.teste.pedidos.model.Produto;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class PedidoForm {

    private Long codigoCliente;
    private double valorTotal;
    private String enderecoEntrega;
    private List<Long> produtos;
}
