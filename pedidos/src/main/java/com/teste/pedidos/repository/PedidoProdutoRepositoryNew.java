package com.teste.pedidos.repository;

import com.teste.pedidos.model.PedidoProdutoNew;
import com.teste.pedidos.model.PedidoProdutoPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoProdutoRepositoryNew extends JpaRepository<PedidoProdutoNew, PedidoProdutoPK> {
}
