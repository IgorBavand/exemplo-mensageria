-- VIEW PARA INFORMAÇÕES DO PEDIDO
create view informacoes_pedido as SELECT id_pedido, nome_cliente, endereco_entrega, valor_total FROM xbrain_pedidos.pedidos p 
inner join xbrain_pedidos.clientes c
on p.codigo_cliente = c.codigo_cliente;


-- VIEW PARA LISTAGEM DOS PRODUTOS POR PEDIDO
create view listagem_produtos as select id_pedido, p.codigo_produto, nome_produto from xbrain_pedidos.pedido_produtos p 
inner join xbrain_pedidos.produtos prod on p.codigo_produto = prod.codigo_produto