package com.teste.pedidos.service.mapper;

import com.teste.pedidos.model.Cliente;
import com.teste.pedidos.service.dto.ClienteDto;
import com.teste.pedidos.service.form.ClienteForm;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    Cliente toModel(ClienteDto clienteDto);
    ClienteDto toDto(Cliente cliente);
    Cliente toEntity(ClienteForm clienteForm);
    List<ClienteDto> toDtos(List<Cliente> clientes);
    Cliente copiar(Cliente cliente);
    void atualizarClientes(ClienteForm form, @MappingTarget Cliente cliente);

}
