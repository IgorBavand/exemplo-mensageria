package com.teste.pedidos.service.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class ProdutoDto {

    private Long codigoProduto;
    private String nomeProduto;
}
