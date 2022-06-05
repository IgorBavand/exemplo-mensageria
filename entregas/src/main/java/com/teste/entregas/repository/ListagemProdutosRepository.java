package com.teste.entregas.repository;

import com.teste.entregas.model.chaves_compostas.ListagemProdutosPK;
import com.teste.entregas.model.views_bd.ListagemProdutos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListagemProdutosRepository extends JpaRepository<ListagemProdutos, ListagemProdutosPK> {

}
