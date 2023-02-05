# A Força - Admin - API
API do modo admin da plataforma **A Força**.

## Sumário
1. [Tecnologias requisitadas](#tecnologias-requisitas)
   - [Instações e configurações](#instalaes-e-configuraes)
      - [Java](#java)
      - [Docker e Docker Compose](#docker-e-docker-compose)

## Tecnologias requisitas
  - Java 11
  - Docker (sugestão v20.10.23)
  - Docker Compose (sugestão v2.15.1)

### Instalações e configurações
#### Java
Java será utilizado para gerar o build da aplicação Spring Boot e auxiliar na construção da imagem Docker da nossa aplicação.
A instalação e configuração será através do gerenciador [**SDKMAN!**](https://sdkman.io/), segue [link da instalação](https://sdkman.io/install).

Após instalar o SDKMAN!, instale a versão 11 do Java. Para isso:

1. Verifique as versões disponíveis do JDK.
```shell
sdk list java
```

2. Copie o valor da coluna **Identifier** para instalar o JDK 11 da GraalVM.
```shell
sdk install java 21.3.2.r11-grl
```

3. Após a instalação, caso não configure essa versão como padrão (default), execute o seguinte comando para usar a versão instalada.
```shell
sdk use java 21.3.2.r11-grl
```

4. Execute o comando `java -version` para certificar que está usando o Java 11 através da mensagem exibida.
```shell
openjdk version "11.0.15" 2022-04-19
OpenJDK Runtime Environment GraalVM CE 21.3.2 (build 11.0.15+10-jvmci-21.3-b14)
OpenJDK 64-Bit Server VM GraalVM CE 21.3.2 (build 11.0.15+10-jvmci-21.3-b14, mixed mode, sharing)
```

#### Docker e Docker Compose
A instalação e configuração dessas duas tecnologias foi feita seguindo o tutorial da [DigitalOcean](https://www.digitalocean.com/).
É possível selecionar a partir do Sistema Operacional que você utiliza. Para ambos, basta seguir apenas o primeiro passo do tutorial.
Segue link do [Docker](https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-on-ubuntu-22-04#step-1-installing-docker) e do [Docker Compose](https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-compose-on-ubuntu-22-04#step-1-installing-docker-compose).