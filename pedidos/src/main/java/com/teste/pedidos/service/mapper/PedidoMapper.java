package com.teste.pedidos.service.mapper;

import com.teste.pedidos.model.Pedido;
import com.teste.pedidos.service.dto.PedidoDto;
import com.teste.pedidos.service.form.PedidoForm;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    Pedido toModel(PedidoDto pedidoDto);

    PedidoDto toDto(Pedido cliente);

    Pedido toEntity(PedidoDto clienteForm);

    List<PedidoDto> toDtos(List<Pedido> clienteList);

    Pedido copiar(Pedido cliente);

    void atualizarPedido(PedidoForm form, @MappingTarget Pedido pedido);

}
