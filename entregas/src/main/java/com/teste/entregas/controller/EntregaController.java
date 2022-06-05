package com.teste.entregas.controller;

import com.teste.entregas.model.views_bd.ListagemProdutos;
import com.teste.entregas.repository.ListagemProdutosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/entregas")
public class EntregaController {

    private final ListagemProdutosRepository listagemProdutosRepository;


    @GetMapping("/listagem-produtos")
    public List<ListagemProdutos> teste(){
        List<ListagemProdutos> p = listagemProdutosRepository.findAll();

        p.forEach(pr -> {
            System.out.println(pr);
        });

        return p;
    }

}
