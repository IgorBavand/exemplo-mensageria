package com.teste.pedidos;

import com.teste.pedidos.exception.BadRequestException;
import com.teste.pedidos.model.Cliente;
import com.teste.pedidos.model.Pedido;
import com.teste.pedidos.model.Produto;
import com.teste.pedidos.repository.ClienteRepository;
import com.teste.pedidos.repository.ProdutoRepository;
import com.teste.pedidos.service.PedidoService;
import com.teste.pedidos.service.form.PedidoForm;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest

class PedidosApplicationTests {


	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	PedidoService pedidoService;



	@Test
	void VerificarExceptionClienteNaoExiste(){
		Produto p1 = new Produto();
		Produto p2 = new Produto();
		Produto p3 = new Produto();

		p1.setNomeProduto("bicicleta");
		p2.setNomeProduto("controle");
		p3.setNomeProduto("celular");

		produtoRepository.save(p1);
		produtoRepository.save(p2);
		produtoRepository.save(p3);

		Cliente cliente = new Cliente();

		cliente.setNomeCliente("Igor Guerreiro");
		clienteRepository.save(cliente);

		PedidoForm pedidoForm = new PedidoForm();
		pedidoForm.setCodigoCliente(3L);
		pedidoForm.setEnderecoEntrega("Parana");
		pedidoForm.setValorTotal(123);
		List<Long> p = new ArrayList<>();
		p.add(1L);
		p.add(3L);
		p.add(2L);
		pedidoForm.setProdutos(p);

		Assertions.assertThrows(BadRequestException.class, () -> pedidoService.gerarPedido(pedidoForm));
	}


	@Test
	void contextLoads() {

		Assertions.assertEquals(1,1);
	}

}
