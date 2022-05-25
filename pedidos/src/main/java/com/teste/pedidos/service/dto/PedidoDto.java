package com.teste.pedidos.service.dto;

import com.teste.pedidos.model.Produto;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.util.List;

@Data
public class PedidoDto {

    private Long codigoCliente;
    private double valorTotal;
    private String enderecoEntrega;
    private List<Produto> produtos;

}
