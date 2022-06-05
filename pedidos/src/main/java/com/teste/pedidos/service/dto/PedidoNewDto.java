package com.teste.pedidos.service.dto;

import com.teste.pedidos.model.PedidoProdutoNew;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
public class PedidoNewDto implements Serializable {
    private UUID idPedido;
    private Long codigoCliente;
    private String nomeCliente;
    private double valorTotal;
    private String enderecoEntrega;
    private Set<PedidoProdutoNew> produtos;
}
