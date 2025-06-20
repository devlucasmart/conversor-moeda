package org.devlucas;

import com.google.gson.Gson;
import io.github.cdimascio.dotenv.Dotenv;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.Set;

public class ConversorMoeda {

    private static final Dotenv dotenv = Dotenv.load();
    private static final Optional<String> API_KEY = Optional.ofNullable(dotenv.get("API_KEY"));

    private ExchangeRateResponse obterCotacao(String base) {
        String apiKey = API_KEY.orElseThrow(() -> new RuntimeException("API_KEY não definida no .env"));
        URI cambio = URI.create("https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + base);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(cambio)
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), ExchangeRateResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Não consegui obter a cotação da moeda.");
        }
    }

    public Optional<Double> converter(double valor, String moedaOrigem, String moedaDestino) {
        ExchangeRateResponse response = obterCotacao(moedaOrigem);
        Double taxa = response.conversion_rates().get(moedaDestino);
        return taxa != null ? Optional.of(valor * taxa) : Optional.empty();
    }

    public Set<String> obterMoedasDisponiveis(String moedaOrigem) {
        ExchangeRateResponse response = obterCotacao(moedaOrigem);
        return response.conversion_rates().keySet();
    }
}