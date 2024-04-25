import java.net.*;
import java.io.*;
import com.google.gson.*;

public class ApiConnection {
    public static double getCurrencyValue(String inputCurrency, String outputCurrency) throws IOException {
        // Construir la URL con la moneda de entrada
        String apiUrl = "https://v6.exchangerate-api.com/v6/5bfaf3a1aaed9b29067c977f/latest/" + inputCurrency;
        URL url = new URL(apiUrl);

        // Crear conexión HTTP
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Leer la respuesta
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Convertir la respuesta a un objeto JSON
        JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();

        // Obtener el valor de la moneda de salida
        return jsonResponse.getAsJsonObject("conversion_rates").get(outputCurrency).getAsDouble();
    }
}
