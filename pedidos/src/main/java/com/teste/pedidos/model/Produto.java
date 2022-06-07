package com.teste.pedidos.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "produtos")
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_produto", nullable = false, updatable = false)
    private Long codigoProduto;

    @Column(name = "nome_produto")
    private String nomeProduto;

    @Column(name = "preco")
    private double preco;

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
