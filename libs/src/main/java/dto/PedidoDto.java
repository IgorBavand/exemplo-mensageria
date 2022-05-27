package dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
public class PedidoDto implements Serializable {

    private UUID idPedido;
    private Long codigoCliente;
    private double valorTotal;
    private String enderecoEntrega;
    private List<ProdutoDto> produtos;

}
