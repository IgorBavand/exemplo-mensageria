package com.teste.entregas.model;

import com.teste.entregas.model.enums.StatusPedido;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "entregas")
@AllArgsConstructor
@NoArgsConstructor
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Setter(AccessLevel.PRIVATE)
    @Column(columnDefinition = "BINARY(16)", name = "id_entrega", unique = true, nullable = false, updatable = false)
    private UUID idEntrega;

    @Column(name = "pedido_id")
    private UUID idPedido;

    @Column(name = "endereco_entrega")
    private String enderecoEntrega;

    @Column(name = "status_pedido")
    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;
}
