package com.teste.pedidos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Data
@Table(name = "pedido_produtos")
@AllArgsConstructor
@NoArgsConstructor
public class PedidoProdutos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "pedido_id")
    UUID pedidoId;

    @Column(name = "produto_id")
    Long produtoId;


}
