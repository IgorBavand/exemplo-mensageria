package dto;

import com.teste.entregas.model.Produto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PedidoDto implements Serializable {

    private Long codigoCliente;
    private double valorTotal;
    private String enderecoEntrega;
    private List<Produto> produtos;

}
