package com.teste.entregas.model.views_bd;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teste.entregas.model.chaves_compostas.ListagemProdutosPK;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "listagem_produtos")
@Data
public class ListagemProdutos {

    @EmbeddedId
    @JsonIgnore
    private ListagemProdutosPK id = new ListagemProdutosPK();

    @Column(name = "nome_produto")
    private String nomeProduto;


}
