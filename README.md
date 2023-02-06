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

## Execução da aplicação
> **AVISO:** A propriedade **`finalName`** do **`pom.xml`** é importante para que o Docker identifique a partir de qual
> executável será gerado a imagem da aplicação (vide arquivos **`Dockerfile`** e **`docker-compose.yml`**). Então, caso queira
> alterar o nome do executável, verificar onde mais é utilizado.

Com o repositório baixado e as tecnologias requisitas instaladas, vamos executar a aplicação utilizando o Docker. Para
gerar o executável da aplicação (.jar), acesse a pasta raiz do projeto e execute o comando com argumento para pular os testes:

```shell
./mvnw install -DskipTests
```

Então será gerado o `aforca-admin-api-docker.jar` (caso a configuração original não seja alterada) na pasta pasta `target`.

Ainda na pasta raiz, para gerar a imagem Docker, execute:
```shell
docker build -t aforca-admin-api-docker.jar .
```

A imagem pode ser identifica a partir do seguinte comando e o resultado esperado como no **Output**:
```shell
docker images

Output:
REPOSITORY                    TAG       IMAGE ID       CREATED        SIZE
aforca-admin-api-docker.jar   latest    485b54d9d342   10 days ago    692MB
```

Para subir o container, na pasta raiz:
```shell
docker compose up -d
```

O resultado espera pode ser observado pelo comando:
```shell
docker ps -a

Output:
CONTAINER ID   IMAGE                         COMMAND                  CREATED          STATUS                    PORTS                                       NAMES
057ba67db5dd   aforca-admin-api-docker.jar   "java -jar aforca-ad…"   19 seconds ago   Up 7 seconds              0.0.0.0:8080->8080/tcp, :::8080->8080/tcp   aforca-admin-api-API-1
2bba7430fb0e   postgres                      "docker-entrypoint.s…"   19 seconds ago   Up 18 seconds (healthy)   0.0.0.0:5432->5432/tcp, :::5432->5432/tcp   aforca-admin-api-PostgreSQL-1
```

Para desligar o container, na pasta raiz:
```shell
docker compose down
```
