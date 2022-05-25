package com.teste.pedidos.service;

import com.teste.pedidos.exception.NotFoundException;
import com.teste.pedidos.model.Pedido;
import com.teste.pedidos.repository.PedidoRepository;
import com.teste.pedidos.repository.specification.PedidoSpecification;
import com.teste.pedidos.service.dto.PedidoDto;
import com.teste.pedidos.service.filter.PedidoFilter;
import com.teste.pedidos.service.mapper.PedidoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
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

    public Page<PedidoDto> buscarPedidods(PedidoFilter filter, Pageable pageable) throws ChangeSetPersister.NotFoundException {
        Page<Pedido> clientes = pedidoRepository.findAll(PedidoSpecification.of(filter), pageable);
        log.info("buscando clientes..");
        if(!clientes.hasContent()){
            throw new NotFoundException("Nenhum cliente encontrado");
        }
        return new PageImpl<>(pedidoMapper.toDtos(clientes.getContent()), pageable, clientes.getTotalElements());
    }

}
