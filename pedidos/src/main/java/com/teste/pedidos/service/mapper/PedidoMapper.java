package com.teste.pedidos.service.mapper;

import com.teste.pedidos.model.Pedido;
import dto.PedidoDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    Pedido toModel(PedidoDto pedidoDto);
    PedidoDto toDto(Pedido pedido);
    //Pedido toEntity(PedidoForm pedidoForm);
    List<PedidoDto> toDtos(List<Pedido> pedidos);
    Pedido copiar(Pedido cliente);
    //void atualizarPedido(PedidoForm form, @MappingTarget Pedido pedido);

}
