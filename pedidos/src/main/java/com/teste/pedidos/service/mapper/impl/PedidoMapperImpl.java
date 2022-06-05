package com.teste.pedidos.service.mapper.impl;

import com.teste.pedidos.model.Cliente;
import com.teste.pedidos.model.Pedido;
import com.teste.pedidos.repository.ClienteRepository;
import com.teste.pedidos.service.dto.PedidoNewDto;
import com.teste.pedidos.service.mapper.PedidoMapper;
import dto.PedidoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PedidoMapperImpl implements PedidoMapper {

    @Autowired
    ClienteRepository clienteRepository;


    @Override
    public Pedido toModel(PedidoDto pedidoDto) {
        return null;
    }

    @Override
    public PedidoNewDto toDto(Pedido pedido) {
        if ( pedido == null ) {
            return null;
        }


        PedidoNewDto pedidoNewDto = new PedidoNewDto();

        pedidoNewDto.setIdPedido( pedido.getIdPedido() );
        pedidoNewDto.setCodigoCliente( pedido.getCodigoCliente() );
        Optional<Cliente> c = clienteRepository.findById(pedido.getCodigoCliente());
        pedidoNewDto.setNomeCliente(c.get().getNomeCliente());
        pedidoNewDto.setValorTotal( pedido.getValorTotal() );
        pedidoNewDto.setEnderecoEntrega( pedido.getEnderecoEntrega() );
        pedidoNewDto.setProdutos( pedido.getItems() );

        return pedidoNewDto;
    }

    @Override
    public List<PedidoNewDto> toDtos(List<Pedido> pedidos) {
        if ( pedidos == null ) {
            return null;
        }

        List<PedidoNewDto> list = new ArrayList<PedidoNewDto>( pedidos.size() );
        for ( Pedido pedido : pedidos ) {
            list.add( toDto( pedido ) );
        }

        return list;
    }

    @Override
    public Pedido copiar(Pedido cliente) {
        return null;
    }
}
