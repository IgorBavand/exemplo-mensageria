package com.teste.entregas.model;

import com.teste.entregas.model.enums.StatusPedido;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
