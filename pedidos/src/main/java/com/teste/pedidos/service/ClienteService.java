package com.teste.pedidos.service;

import com.teste.pedidos.exception.BadRequestException;
import com.teste.pedidos.exception.NotFoundException;
import com.teste.pedidos.model.Cliente;
import com.teste.pedidos.model.Produto;
import com.teste.pedidos.repository.ClienteRepository;
import com.teste.pedidos.repository.specification.ClienteSpecification;
import com.teste.pedidos.service.dto.ClienteDto;
import com.teste.pedidos.service.dto.ProdutoDto;
import com.teste.pedidos.service.filter.ClienteFilter;
import com.teste.pedidos.service.form.ClienteForm;
import com.teste.pedidos.service.form.ProdutoForm;
import com.teste.pedidos.service.mapper.ClienteMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public Page<ClienteDto> bucarClientes(ClienteFilter filter, Pageable pageable) throws NotFoundException {
        Page<Cliente> clientes = clienteRepository.findAll(ClienteSpecification.of(filter), pageable);
        log.info("buscando clientes..");
        if(!clientes.hasContent()){
            throw new NotFoundException("Nenhum cliente encontrado");
        }
        return new PageImpl<>(clienteMapper.toDtos(clientes.getContent()), pageable, clientes.getTotalElements());
    }



    public ClienteDto cadastrarCliente(ClienteForm clienteForm){

        Cliente cliente = new Cliente();
        cliente.setNomeCliente(clienteForm.getNomeCliente());
        log.info("Salavdno cliente...");
        System.out.println(clienteForm);
        System.out.println(cliente);

        try {
            clienteRepository.save(cliente);
        }catch (BadRequestException e){
            throw new BadRequestException("Não foi possivel salval o cliente");
        }

        return clienteMapper.toDto(cliente);
    }


}
