# Winery – Checkpoint 1 (SOA e Web Services)

Webservice SOAP (JAX-WS) com publicação (Publisher) e dois consumidores (Consumer),
desenvolvido para o Checkpoint 1 da disciplina **Arquitetura SOA e Web Services**.

## Integrantes do grupo
- Belton Lee Carr De Muzio Meira - RM560760

## Estrutura do repositório

```
Winery/
├── Publisher/
│   └── WinerySys/                # Publica os serviços SOAP
│       └── src/main/java/br/com/fiap/winery/
│           ├── WineStockService.java                  (interface)
│           ├── WineStockServiceImplementation.java     (implementação)
│           ├── WineWarningService.java                 (interface)
│           ├── WineWarningServiceImplementation.java    (implementação)
│           └── Loader.java                             (classe driver que publica os endpoints)
└── Consumer/
    ├── WineStockClient/          # Consome getMenu()
    │   └── src/main/java/br/com/fiap/winery/ApplicationClient1.java
    └── WineOrderClient/          # Consome placeOrder() e sendWarn()
        └── src/main/java/br/com/fiap/winery/ApplicationClient2.java
```

## Serviços publicados

| Serviço            | Endpoint                                     | Método      |
|---------------------|-----------------------------------------------|-------------|
| WineStockService    | http://localhost:8085/WineStockService        | getMenu, placeOrder |
| WineWarningService  | http://localhost:8086/WineWarningService       | sendWarn    |

## Como executar

1. **Publicar os serviços**
   ```
   cd Publisher/WinerySys
   mvn compile
   mvn exec:java -Dexec.mainClass="br.com.fiap.winery.Loader"
   ```
   (ou rode a classe `Loader` diretamente pela IDE)

   Confirme no console as mensagens "Serviço publicado!" e verifique os WSDLs no navegador:
   - http://localhost:8085/WineStockService?wsdl
   - http://localhost:8086/WineWarningService?wsdl

2. **Rodar o cliente do menu (WineStockClient)** — com o Publisher no ar:
   ```
   cd Consumer/WineStockClient
   mvn generate-sources
   mvn compile
   mvn exec:java -Dexec.mainClass="br.com.fiap.winery.ApplicationClient1"
   ```

3. **Rodar o cliente de pedido/aviso (WineOrderClient)** — com o Publisher no ar:
   ```
   cd Consumer/WineOrderClient
   mvn generate-sources
   mvn compile
   mvn exec:java -Dexec.mainClass="br.com.fiap.winery.ApplicationClient2"
   ```


## Mapeamento do roteiro

| Item do roteiro | Onde está |
|---|---|
| Projeto Maven `WinerySys` em `Publisher` | `Publisher/WinerySys` |
| `pom.xml` com packaging `jar` + `jaxws-rt` | `Publisher/WinerySys/pom.xml` |
| Interface `WineStockService` (`getMenu`, `placeOrder`) | `WineStockService.java` |
| `@WebService`, `@SOAPBinding(style)`, `@WebMethod` | `WineStockService.java` / `WineWarningService.java` |
| `WineStockServiceImplementation` + `@Override` + `endpointInterface` | `WineStockServiceImplementation.java` |
| Classe driver `Loader` com `Endpoint.publish` | `Loader.java` |
| Projeto `WineStockClient` em `Consumer` | `Consumer/WineStockClient` |
| `pom.xml` com `jaxws-maven-plugin` / `wsimport` | `Consumer/WineStockClient/pom.xml` |
| `ApplicationClient1` (`URL`, `QName`, `Service.create`, `getPort`, `getMenu`) | `ApplicationClient1.java` |
| Projeto `WineOrderClient` em `Consumer` | `Consumer/WineOrderClient` |
| `ApplicationClient2` (`placeOrder`) | `ApplicationClient2.java` |
| Interface `WineWarningService` + `sendWarn` | `WineWarningService.java` |
| `WineWarningServiceImplementation` ("Estoque insuficiente!") | `WineWarningServiceImplementation.java` |
| `Loader` publicando `wineWarning` em `:8086` | `Loader.java` |
| `pom.xml` do `WineOrderClient` reconfigurado com o WSDL do aviso | execução `wsimport-warning` |
| `url2`, `qName2`, `service2`, `wineWarningService`, `warn` | `ApplicationClient2.java` |

## Requisitos
- Java 17+
- Maven 3.8+

