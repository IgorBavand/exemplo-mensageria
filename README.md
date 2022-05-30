# Desafio X-BRAIN

**Premissas:**

    Criar uma API usando Spring Boot, que faz parte da nossa stack.
    Utilizar um banco de dados em memória (HSQLDB ou H2)
    Não é necessário desenvolver front-end, apenas o back-end.


**Teste:**

    Desenvolver uma API REST que seja capaz de gerar um pedido. (código do cliente, código dos produtos, valor total e endereço de entrega)
    A api deve gravar o pedido no banco e enviar o pedido para a uma fila de mensagem de entregas, usando RabbitMQ.
    Criar um serviço para ler as entregas da fila e salvar na tabela Entrega (endereço de entrega e id do pedido)
    
##

## Criar container do RabbitMQ no docker

~~~docker
sudo docker run -d --hostname my-rabbit2 --name rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management
~~~
    
## Simulação realizada

**Cadastro do clente:**

![cadastro cliente](https://github.com/IgorBavand/teste-x-brain/blob/master/imagens-readme/cliente.png)

## 

**Cadastro dos produtos:**

![cadastro produto](https://github.com/IgorBavand/teste-x-brain/blob/master/imagens-readme/produto.png)

## 

**Gerando o pedido:**

![gerar pedido](https://github.com/IgorBavand/teste-x-brain/blob/master/imagens-readme/pedido.png)

## 

**Ao gerar o pedido é enviado a fila de entrega pelo RabbitMQ, é o serviço de entrega o registra na tabela de entregas**

![tabela entregas](https://github.com/IgorBavand/teste-x-brain/blob/master/imagens-readme/entrega.png)


##

### Alguns testes realizados: 
  
  ![testes](https://github.com/IgorBavand/teste-x-brain/blob/master/imagens-readme/testes.png)

  


