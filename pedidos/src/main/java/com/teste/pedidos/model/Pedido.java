package com.teste.pedidos.model;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Data
@Entity
@Table(name = "pedidos")
@AllArgsConstructor
@NoArgsConstructor

public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Setter(AccessLevel.PRIVATE)
    @Column(name = "id_pedido", unique = true, nullable = false, updatable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID idPedido;

    @Column(name = "codigo_cliente")
    private Long codigoCliente;

    @Column(name = "valor_total")
    private double valorTotal;

    @Column(name = "endereco_entrega")
    private String enderecoEntrega;

    /*@OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "pedido_produtos",
                    joinColumns = @JoinColumn(name = "pedido_id", referencedColumnName = "id_pedido"),
                    inverseJoinColumns = @JoinColumn(name = "produto_id", referencedColumnName = "codigo_produto")
    )
    private List<Produto> produtos = new ArrayList<>();
     */
    @OneToMany(mappedBy = "id.pedido")
    private Set<PedidoProdutoNew> items = new HashSet<>();

}
