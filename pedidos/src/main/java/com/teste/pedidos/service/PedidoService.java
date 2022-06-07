package com.teste.pedidos.service;

import com.teste.pedidos.config.rabbitmq.Filas;
import com.teste.pedidos.config.rabbitmq.producer.RabbitMqProducer;
import com.teste.pedidos.exception.NotFoundException;
import com.teste.pedidos.model.Cliente;
import com.teste.pedidos.model.Pedido;
import com.teste.pedidos.model.PedidoProdutoNew;
import com.teste.pedidos.repository.ClienteRepository;
import com.teste.pedidos.repository.PedidoProdutoRepositoryNew;
import com.teste.pedidos.repository.PedidoRepository;
import com.teste.pedidos.repository.ProdutoRepository;
import com.teste.pedidos.repository.specification.PedidoSpecification;
import com.teste.pedidos.service.dto.PedidoNewDto;
import com.teste.pedidos.service.filter.PedidoFilter;
import com.teste.pedidos.service.form.PedidoForm;
import com.teste.pedidos.service.mapper.PedidoMapper;
import dto.EmailPedidoDto;
import dto.PedidoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
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

    //private final PedidoProdutosRepository pedidoProdutosRepository;
    private final PedidoProdutoRepositoryNew pedidoProdutoRepositoryNew;

    public Page<PedidoNewDto> buscarPedidodos(PedidoFilter filter, Pageable pageable) throws NotFoundException {
        Page<Pedido> pedidos = pedidoRepository.findAll(PedidoSpecification.of(filter), pageable);
        log.info("buscando pedidos..");
        if (!pedidos.hasContent()) {
            throw new NotFoundException("Nenhum pedido encontrado");
        }
        return new PageImpl<>(pedidoMapper.toDtos(pedidos.getContent()), pageable, pedidos.getTotalElements());
    }

    public PedidoNewDto buscarPedido(UUID idPedido) throws NotFoundException {
        Optional<Pedido> pedido = pedidoRepository.findById(idPedido);
        log.info("buscando pedidos..");
        if (!pedido.isPresent()) {
            throw new NotFoundException("Nenhum pedido encontrado");
        }
        return pedidoMapper.toDto(pedido.get());
    }

    @Transactional
    public PedidoNewDto gerarPedidoNew(PedidoForm pedidoForm) {
        Pedido pedido = new Pedido();
        pedido.setCodigoCliente(pedidoForm.getCodigoCliente());
        pedido.setEnderecoEntrega(pedidoForm.getEnderecoEntrega());
        pedido.setValorTotal(0D);
        pedido = pedidoRepository.save(pedido);

        Set<PedidoProdutoNew> produtosSalvos = new HashSet<>();
        double valorTotal = 0;
        for (Long produtId : pedidoForm.getProdutos()) {
            var produto = produtoRepository.findById(produtId).get();

            PedidoProdutoNew p = new PedidoProdutoNew();
            p.setPedido(pedido);
            p.setProduto(produto);
            produtosSalvos.add(p);
            pedidoProdutoRepositoryNew.save(p);
            valorTotal = valorTotal + produto.getPreco();
        }


        PedidoDto pedidoDto = new PedidoDto();
        pedidoDto.setIdPedido(pedido.getIdPedido());
        pedidoDto.setCodigoCliente(pedidoForm.getCodigoCliente());
        pedidoDto.setEnderecoEntrega(pedidoForm.getEnderecoEntrega());
        pedidoDto.setValorTotal(valorTotal);

        //atualizando pedido com o valor total
        pedido.setValorTotal(valorTotal);
        pedidoRepository.save(pedido);

        PedidoDto mensagem = pedidoDto;
        rabbitMqProducer.enviarMensagem(Filas.ENTREGA.toString(), mensagem);

        EmailPedidoDto emailPedidoDto = new EmailPedidoDto();

        emailPedidoDto.setCodigoPedido(pedido.getIdPedido());
        Optional<Cliente> cliente = clienteRepository.findById(pedido.getCodigoCliente());
        emailPedidoDto.setEmailDestinatario(cliente.get().getEmailCliente());
        emailPedidoDto.setAssunto("Novo Pedido Cadastrado");
        emailPedidoDto.setNomeCliente(cliente.get().getNomeCliente());
        emailPedidoDto.setEnderecoEntrega(pedido.getEnderecoEntrega());
        emailPedidoDto.setValorTotal(pedido.getValorTotal());

        EmailPedidoDto mensagemEmailPedido = emailPedidoDto;

        rabbitMqProducer.enviarMensagem(Filas.EMAIL_PEDIDO.toString(), mensagemEmailPedido);

        pedido.setItems(produtosSalvos);
        return pedidoMapper.toDto(pedido);
    }
}
