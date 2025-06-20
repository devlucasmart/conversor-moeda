import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConversorMoeda {

    private ExchangeRateResponse obterCotacao(String base) {
        URI cambio = URI.create("https://v6.exchangerate-api.com/v6/1fd2fd874e34ad219d7cecec/latest/" + base);
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

    public Double converter(double valor, String moedaOrigem, String moedaDestino) {
        ExchangeRateResponse response = obterCotacao(moedaOrigem);
        Double taxa = response.conversion_rates().get(moedaDestino);
        if (taxa != null) {
            return valor * taxa;
        } else {
            throw new IllegalArgumentException("Moeda de destino não encontrada.");
        }
    }
}