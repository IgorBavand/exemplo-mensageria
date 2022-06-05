package com.teste.entregas.model.chaves_compostas;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
public class ListagemProdutosPK implements Serializable {
    @Column(name = "id_pedido")
    private UUID idPedido;

    @Column(name = "codigo_produto")
    private Long codigoProduto;
}
