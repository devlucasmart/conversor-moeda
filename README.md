# Conversor de Moedas

![Java](https://img.shields.io/badge/Java-17-red.svg)
![Maven](https://img.shields.io/badge/Maven-3.8.5-yellow.svg)
![Gson](https://img.shields.io/badge/Gson-2.10.1-blue.svg)
![Dotenv](https://img.shields.io/badge/dotenv--java-3.0.0-green.svg)

Este é um **Conversor de Moedas** simples em linha de comando, desenvolvido em **Java 17** utilizando **Maven** e as bibliotecas **Gson** e **dotenv-java**. O sistema permite converter valores entre diferentes moedas utilizando uma API externa de câmbio.

## Funcionalidades Principais

- Conversão de valores entre moedas (USD, BRL, ARS, EUR, GBP, JPY, etc).
- Consulta de moedas disponíveis para conversão.
- Interface interativa via terminal.

## Pré-requisitos

- **JDK 17 ou superior**
- **Maven 3.8.5**
- **Chave de API** do [ExchangeRate-API](https://www.exchangerate-api.com/) (definida no arquivo `.env`)

## Instalação e Configuração

1. Clone este repositório:
   `git clone https://github.com/devlucasmart/conversor-moeda.git`
2. Navegue até o diretório do projeto:
   `cd conversor-moeda`
3. Crie um arquivo `.env` na raiz do projeto e adicione sua chave de API:

4. Compile o projeto com Maven:
   `mvn clean install`
5. Execute o projeto:
   `mvn exec:java -Dexec.mainClass="org.example.Main"`

## Como Usar

Ao iniciar, o sistema exibirá um menu no terminal para escolher o tipo de conversão ou inserir moedas manualmente. Basta seguir as instruções na tela para realizar as conversões.

### Exemplo de Uso