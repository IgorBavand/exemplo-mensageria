package com.teste.pedidos.service;

import com.teste.pedidos.exception.NotFoundException;
import com.teste.pedidos.model.Pedido;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class PedidoService {

    private final PedidoMapper pedidoMapper;
    private final PedidoRepository pedidoRepository;

    public Page<PedidoDto> buscarPedidods(PedidoFilter filter, Pageable pageable) throws NotFoundException {
        Page<Pedido> pedidos = pedidoRepository.findAll(PedidoSpecification.of(filter), pageable);
        log.info("buscando pedidos..");
        if(!pedidos.hasContent()){
            throw new NotFoundException("Nenhum pedido encontrado");
        }
        return new PageImpl<>(pedidoMapper.toDtos(pedidos.getContent()), pageable, pedidos.getTotalElements());
    }

    public PedidoDto gerarPedido(PedidoForm pedidoForm){

        Pedido pedido = pedidoMapper.toEntity(pedidoForm);

        System.out.println(pedido.getCodigoCliente());


        return null;
    }



}
