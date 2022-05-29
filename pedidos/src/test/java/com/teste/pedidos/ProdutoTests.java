package com.teste.pedidos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teste.pedidos.model.Cliente;
import com.teste.pedidos.model.Produto;
import com.teste.pedidos.service.PedidoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoTests {


    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void verificarCadastroProdutoOk() throws Exception {
        Produto produto = new Produto();

        produto.setNomeProduto("bicicleta");

        mockMvc.perform(post("/produtos/cadastrar-produto")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(produto)))
                .andExpect(status().isOk());
    }
}
