package com.teste.pedidos.service.form;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class PedidoForm {

    @NotNull(message = "O cliente nao pode ser nulo")
    private Long codigoCliente;

    @Min(value = 1)
    private double valorTotal;

    @NotBlank(message = "o endereço nao pode ser nulo")
    private String enderecoEntrega;

    @NotNull(message = "Deve haver pelo menos um produto")
    private List<Long> produtos;
}
