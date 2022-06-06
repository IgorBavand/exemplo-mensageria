package com.teste.entregas.service;

import com.teste.entregas.model.Entrega;
import com.teste.entregas.model.enums.StatusPedido;
import com.teste.entregas.model.views_bd.ListagemProdutos;
import com.teste.entregas.repository.EntregaRepository;
import com.teste.entregas.repository.ListagemProdutosRepository;
import dto.PedidoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EntregaService {
    private final EntregaRepository entregaRepository;
    private final ListagemProdutosRepository listagemProdutosRepository;

    public List<ListagemProdutos> buscarProdutosDoPedido(UUID idPediddo){
        List<ListagemProdutos> p = listagemProdutosRepository.findProdutosPedido(idPediddo);

        p.forEach(pr -> {
            System.out.println(pr);
        });

        return p;
    }

}
