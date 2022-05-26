package com.teste.pedidos.service;

import com.teste.pedidos.exception.NotFoundException;
import com.teste.pedidos.model.Pedido;
import com.teste.pedidos.model.PedidoProdutos;
import com.teste.pedidos.model.Produto;
import com.teste.pedidos.repository.PedidoProdutosRepository;
import com.teste.pedidos.repository.PedidoRepository;
import com.teste.pedidos.repository.specification.PedidoSpecification;
import com.teste.pedidos.service.dto.PedidoDto;
import com.teste.pedidos.service.filter.PedidoFilter;
import com.teste.pedidos.service.form.PedidoForm;
import com.teste.pedidos.service.mapper.PedidoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PedidoService {

    private final PedidoMapper pedidoMapper;
    private final PedidoRepository pedidoRepository;

    private final PedidoProdutosRepository pedidoProdutosRepository;

    public Page<PedidoDto> buscarPedidods(PedidoFilter filter, Pageable pageable) throws NotFoundException {
        Page<Pedido> pedidos = pedidoRepository.findAll(PedidoSpecification.of(filter), pageable);
        log.info("buscando pedidos..");
        if(!pedidos.hasContent()){
            throw new NotFoundException("Nenhum pedido encontrado");
        }
        return new PageImpl<>(pedidoMapper.toDtos(pedidos.getContent()), pageable, pedidos.getTotalElements());
    }

    @Transactional
    public PedidoDto gerarPedido(PedidoForm pedidoForm){

        log.info("registrando pedido...");

        Pedido pedido = new Pedido();
        pedido.setCodigoCliente(pedidoForm.getCodigoCliente());
        pedido.setEnderecoEntrega(pedidoForm.getEnderecoEntrega());
        pedido.setValorTotal(pedidoForm.getValorTotal());

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        for(Long p : pedidoForm.getProdutos()){
            log.info("cadastrando produto do pedido...");
            PedidoProdutos pedidoProdutos = new PedidoProdutos();
            pedidoProdutos.setPedidoId(pedidoSalvo.getIdPedido());
            pedidoProdutos.setProdutoId(p);
            System.out.println(p);
            pedidoProdutosRepository.save(pedidoProdutos);
        }

        return pedidoMapper.toDto(pedidoSalvo);
    }



}
