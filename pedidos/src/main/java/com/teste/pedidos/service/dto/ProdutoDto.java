package com.teste.pedidos.service.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class ProdutoDto implements Serializable {

    private Long codigoProduto;
    private String nomeProduto;
    private double preco;

}
