package com.teste.pedidos.service;

import com.teste.pedidos.config.rabbitmq.Filas;
import com.teste.pedidos.config.rabbitmq.producer.RabbitMqProducer;
import com.teste.pedidos.exception.BadRequestException;
import com.teste.pedidos.exception.NotFoundException;
import com.teste.pedidos.model.Pedido;
import com.teste.pedidos.model.PedidoProdutos;
import com.teste.pedidos.model.Produto;
import com.teste.pedidos.repository.ClienteRepository;
import com.teste.pedidos.repository.PedidoProdutosRepository;
import com.teste.pedidos.repository.PedidoRepository;
import com.teste.pedidos.repository.ProdutoRepository;
import com.teste.pedidos.repository.specification.PedidoSpecification;
import com.teste.pedidos.service.filter.PedidoFilter;
import com.teste.pedidos.service.form.PedidoForm;
import com.teste.pedidos.service.mapper.PedidoMapper;
import dto.PedidoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PedidoService {

    private final PedidoMapper pedidoMapper;
    private final PedidoRepository pedidoRepository;
    private final RabbitMqProducer rabbitMqProducer;
    private final ProdutoRepository produtoRepository;

    private final ClienteRepository clienteRepository;

    private final PedidoProdutosRepository pedidoProdutosRepository;

    public Page<PedidoDto> buscarPedidodos(PedidoFilter filter, Pageable pageable) throws NotFoundException {
        Page<Pedido> pedidos = pedidoRepository.findAll(PedidoSpecification.of(filter), pageable);
        log.info("buscando pedidos..");
        if(!pedidos.hasContent()){
            throw new NotFoundException("Nenhum pedido encontrado");
        }
        return new PageImpl<>(pedidoMapper.toDtos(pedidos.getContent()), pageable, pedidos.getTotalElements());
    }

    public PedidoDto buscarPedido(UUID idPedido) throws NotFoundException {
        Optional<Pedido> pedido = pedidoRepository.findById(idPedido);
        log.info("buscando pedidos..");
        if(!pedido.isPresent()){
            throw new NotFoundException("Nenhum pedido encontrado");
        }
        return pedidoMapper.toDto(pedido.get());
    }


    @Transactional
    public PedidoDto gerarPedido(PedidoForm pedidoForm){


        if(!clienteRepository.findById(pedidoForm.getCodigoCliente()).isPresent()){
            throw new BadRequestException("o cliente informado nao foi encontrado.");
        }

        log.info("registrando pedido...");

        Pedido pedido = new Pedido();
        pedido.setCodigoCliente(pedidoForm.getCodigoCliente());
        pedido.setEnderecoEntrega(pedidoForm.getEnderecoEntrega());
        pedido.setValorTotal(pedidoForm.getValorTotal());

        Pedido pedidoSalvo;
        try {

             pedidoSalvo = pedidoRepository.save(pedido);

            List<Produto> produtosPedido = new ArrayList<>();
            for (Long p : pedidoForm.getProdutos()) {
                log.info("cadastrando produto do pedido...");
                PedidoProdutos pedidoProdutos = new PedidoProdutos();
                pedidoProdutos.setPedidoId(pedidoSalvo.getIdPedido());
                pedidoProdutos.setProdutoId(p);
                produtosPedido.add(produtoRepository.findById(p).get());
                pedidoProdutosRepository.save(pedidoProdutos);
            }

            pedidoSalvo.setProdutos(produtosPedido);

            //Enviando pedido via rabbitmq para ser registrado uma entrega

            PedidoDto mensagem = pedidoMapper.toDto(pedidoSalvo);
            rabbitMqProducer.enviarMensagem(Filas.ENTREGA.toString(), mensagem);

        }catch (BadRequestException e)
        {
            throw new BadRequestException("Algo deu errado ao gerar o pedido, tente novamente.");
        }

        return pedidoMapper.toDto(pedidoSalvo);
    }



}
