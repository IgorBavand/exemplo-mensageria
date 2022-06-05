package com.teste.entregas.repository;

import com.teste.entregas.model.chaves_compostas.ListagemProdutosPK;
import com.teste.entregas.model.views_bd.ListagemProdutos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ListagemProdutosRepository extends JpaRepository<ListagemProdutos, ListagemProdutosPK> {

    @Query("select p from ListagemProdutos p where p.id.idPedido = :idPedido")
    List<ListagemProdutos> findProdutosPedido(UUID idPedido);

}
