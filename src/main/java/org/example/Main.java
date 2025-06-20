package org.example;
import java.util.*;

public class Main {
      public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConversorMoeda conversor = new ConversorMoeda();

        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Dólar (USD) => Argentino (ARS)");
            System.out.println("2. Peso Argentino (ARS) => Dólar (USD)");
            System.out.println("3. Real (BRL) => Dólar (USD)");
            System.out.println("4. Inserir moedas manualmente");
            System.out.println("5. Sair");
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            String moedaOrigem = null;
            String moedaDestino = null;

            switch (opcao) {
                case 1 -> {
                    moedaOrigem = "USD";
                    moedaDestino = "ARS";
                }
                case 2 -> {
                    moedaOrigem = "ARS";
                    moedaDestino = "USD";
                }
                case 3 -> {
                    moedaOrigem = "BRL";
                    moedaDestino = "USD";
                }
                case 4 -> {
                    System.out.print("Digite a moeda de origem (ex: USD): ");
                    moedaOrigem = scanner.nextLine().trim().toUpperCase();
                    try {
                        Set<String> moedasDisponiveis = conversor.obterMoedasDisponiveis(moedaOrigem);
                        System.out.println("Moedas disponíveis: " + String.join(", ", moedasDisponiveis));
                    } catch (Exception e) {
                        System.out.println("Erro ao obter moedas disponíveis: " + e.getMessage());
                        continue;
                    }
                    System.out.print("Digite a moeda de destino: ");
                    moedaDestino = scanner.nextLine().trim().toUpperCase();
                }
                case 5 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> {
                    System.out.println("Opção inválida.");
                    continue;
                }
            }

            System.out.print("Digite o valor a ser convertido: ");
            double valor = scanner.nextDouble();

            try {
                double convertido = conversor.converter(valor, moedaOrigem, moedaDestino)
                        .orElseThrow(() -> new IllegalArgumentException("Moeda de destino não encontrada."));
                String simboloOrigem = SimbolosMoeda.MAPA.getOrDefault(moedaOrigem, moedaOrigem);
                String simboloDestino = SimbolosMoeda.MAPA.getOrDefault(moedaDestino, moedaDestino);
                System.out.printf("%.2f %s (%s) = %.2f %s (%s)%n",
                        valor, simboloOrigem, moedaOrigem,
                        convertido, simboloDestino, moedaDestino);
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
}