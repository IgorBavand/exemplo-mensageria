package com.teste.pedidos.model;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private Double valorTotal;

    @Column(name = "endereco_entrega")
    private String enderecoEntrega;

    @OneToMany(mappedBy = "id.pedido")
    private Set<PedidoProdutoNew> items = new HashSet<>();

    @Column(name = "data", columnDefinition = "TIMESTAMP")
    private LocalDate data;

    @Column(name = "hora" , columnDefinition = "VARCHAR(5)")
    private String hora;

    @PrePersist
    public void prePersist(){
        LocalDateTime date = LocalDateTime.now();
        data = LocalDate.of(date.getYear(),date.getMonthValue(),date.getDayOfMonth());
        hora = date.getHour() + ":" + date.getMinute();
    }

}
