package com.teste.pedidos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pedido_produtos")
public class PedidoProdutoNew {

    @EmbeddedId
    @JsonIgnore
    private PedidoProdutoPK id = new PedidoProdutoPK();

    public void setProduto(Produto produto) {
        id.setProduto(produto);
    }
    public Produto getProduto() {
        return id.getProduto();
    }
    public void setPedido(Pedido pedido) {
        id.setPedido(pedido);
    }
    @JsonIgnore
    public Pedido getPedido() {
        return id.getPedido();
    }

}
