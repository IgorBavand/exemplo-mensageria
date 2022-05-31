package com.teste.pedidos.service.form;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class ProdutoForm {

    @NotBlank
    private String nomeProduto;

    @Min(value = 1)
    private double preco;

}
