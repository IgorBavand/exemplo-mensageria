package com.teste.entregas.controller;

import com.teste.entregas.repository.ListagemProdutosRepository;
import com.teste.entregas.service.EntregaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/entregas")
public class EntregaController {

    private final ListagemProdutosRepository listagemProdutosRepository;
    private final EntregaService entregaService;


}
