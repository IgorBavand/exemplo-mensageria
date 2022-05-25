package com.teste.pedidos.service.filter;

import com.teste.pedidos.model.Produto;
import lombok.Data;

import java.util.List;

@Data
public class PedidoFilter {


    private Long codigoCliente;
    private String enderecoEntrega;


}
