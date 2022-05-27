package com.teste.pedidos.repository;

import com.teste.pedidos.model.PedidoProdutos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PedidoProdutosRepository  extends JpaRepository<PedidoProdutos, UUID> {
}
