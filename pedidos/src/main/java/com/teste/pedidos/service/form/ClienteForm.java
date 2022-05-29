package com.teste.pedidos.service.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ClienteForm {
    @NotBlank
    private String nomeCliente;

}
