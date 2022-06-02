package com.teste.pedidos.service.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class ClienteForm {
    @NotBlank
    private String nomeCliente;
    @Email
    private String emailCliente;
}
