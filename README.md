# Desafio Xbrain

**Premissas:**

    Criar uma API usando Spring Boot;
    Utilizar um banco de dados MySQL;
    Apenas o back-end;


**Teste:**

    Desenvolver uma API REST que seja capaz de gerar um pedido. (código do cliente, código dos produtos, valor total e endereço de entrega)
    A api deve gravar o pedido no banco e enviar o pedido para a uma fila de mensagem de entregas, usando RabbitMQ.
    Criar um serviço para ler as entregas da fila e salvar na tabela Entrega (endereço de entrega e id do pedido)
    
##

## Criar container do RabbitMQ no docker

~~~docker
sudo docker run -d --hostname my-rabbit2 --name rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management
~~~
   
## URLs

### Serviço de Pedidos
~~~port
http://localhost:8050
~~~

### Serviço de Entregas
~~~port
http://localhost:8060
~~~

### Serviço de Disparador de Emails
~~~port
http://localhost:8070
~~~


