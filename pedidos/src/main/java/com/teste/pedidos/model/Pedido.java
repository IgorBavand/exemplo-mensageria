package com.teste.pedidos.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "pedidos")
@AllArgsConstructor
@NoArgsConstructor

public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Setter(AccessLevel.PRIVATE)
    @Column(columnDefinition = "BINARY(16)", name = "id_pedido", unique = true, nullable = false, updatable = false)
    private UUID idPedido;

    @Column(name = "codigo_cliente")
    private Long codigoCliente;

    @Column(name = "valor_total")
    private double valorTotal;

    @Column(name = "endereco_entrega")
    private String enderecoEntrega;

    @OneToMany
    @JoinTable(name = "pedido_produtos",
                    joinColumns = @JoinColumn(name = "pedido_id"),
                    inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> produtos;

}


//código do cliente, código dos produtos, valor total e endereço de entrega