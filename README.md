# Multicast Chat

Este é um aplicativo Java simples para conduzir bate-papo multicast usando MulticastSocket.

## Descrição

O aplicativo Multicast Chat permite que os usuários se comuniquem uns com os outros em um grupo de multicast. É uma implementação básica de um sistema de bate-papo usando soquetes Java.

## Início

### Pré-requisitos

- Kit de Desenvolvimento Java (JDK)
- Qualquer ambiente de desenvolvimento Java (Eclipse, IntelliJ, etc.) ou ferramentas de linha de comando

### Uso

1. Clone o repositório em sua máquina local.

   ```bash
   git clone https://github.com/ana-athayde/multicast_chat_java
   ```

2. Compile o código-fonte Java.

   ```bash
   javac MulticastChat.java
   ```

3. Execute o aplicativo.

   ```bash
   java MulticastChat <multicast-host> <server_port>
   ```

4. Siga as instruções na tela para conversar com outros no grupo de multicast.

## Configuração do Host Multicast e Porta do Servidor

Para usar o aplicativo Multicast Chat, é importante configurar corretamente o host de multicast (`<multicast-host>`) e a porta do servidor (`<server_port>`). Esses valores determinam como o aplicativo se conecta a um grupo de multicast para enviar e receber mensagens.

### `<multicast-host>`

O valor de `<multicast-host>` representa o endereço IP do grupo de multicast ao qual você deseja que o aplicativo se junte. O endereço IP de multicast é um endereço especial reservado para comunicações de grupo. Certifique-se de usar um endereço IP de multicast válido dentro do intervalo reservado (por exemplo, entre 224.0.0.0 e 239.255.255.255).

### `<server_port>`

O valor de `<server_port>` é o número da porta em que o servidor de multicast estará ouvindo e no qual os clientes (incluindo o seu aplicativo) se conectam para enviar e receber mensagens. É essencial escolher uma porta disponível e que não entre em conflito com outras portas em uso na sua rede.


## Contribuição

Se desejar contribuir para este projeto, crie uma solicitação de pull (pull request). Agradecemos quaisquer melhorias ou novos recursos.

## Autor

- [Ana Athayde](https://github.com/ana-athayde)

## Agradecimentos

- Este projeto é um exemplo simples do uso de MulticastSocket em Java.
- Sinta-se à vontade para expandir e aprimorar este aplicativo conforme julgar apropriado.

## Notas

- O comando `Exit` pode ser usado para encerrar o bate-papo.

## Arquivos do Projeto

Aqui estão os principais arquivos do projeto:

### MulticastChat.java

O arquivo `MulticastChat.java` é a classe principal que inicia o aplicativo de bate-papo multicast. Ele lida com o envio de mensagens para o grupo multicast e recebe mensagens de outros participantes.

### ReadThread.java

O arquivo `ReadThread.java` contém a classe `ReadThread`, responsável por receber e processar as mensagens recebidas do grupo multicast. Ela assegura que as mensagens do próprio usuário não sejam repetidas.
