package com.teste.pedidos.service.mapper;

import com.teste.pedidos.model.Pedido;
import com.teste.pedidos.service.dto.PedidoNewDto;
import dto.PedidoDto;

import java.util.List;

//@Mapper(componentModel = "spring")
public interface PedidoMapper {

    Pedido toModel(PedidoDto pedidoDto);


   //@Mapping(target = "produtos", source = "items.id.produto")
   PedidoNewDto toDto(Pedido pedido);


    //Pedido toEntity(PedidoForm pedidoForm);
    List<PedidoNewDto> toDtos(List<Pedido> pedidos);



    Pedido copiar(Pedido cliente);
    //void atualizarPedido(PedidoForm form, @MappingTarget Pedido pedido);

}
