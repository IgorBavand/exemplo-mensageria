package com.teste.pedidos.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "produtos")
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @Column(name = "codigo_produto", unique = true, nullable = false, updatable = false)
    private Long codigoProduto;

    @Column(name = "nome_produto")
    private String nomeProduto;



}
