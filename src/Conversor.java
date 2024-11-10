import com.google.gson.Gson;
import entities.ExchangeRateResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Conversor {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scan = new Scanner(System.in);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/6fac5227fd58c132e130bcb8/latest/USD"))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        Gson gson = new Gson();
        ExchangeRateResponse exchangeRateResponse = gson.fromJson(json, ExchangeRateResponse.class);

        String sb = "Bienvenido al Conversor de monedas\n" +
                "1. Dolar -> Peso Argentino\n" +
                "2. Peso Argentino -> Dolar\n" +
                "3. Dolar -> Real Brasileño\n" +
                "4. Real Brasileño -> Dolar\n" +
                "5. Dolar -> Peso Colombiano\n" +
                "6. Peso Colombiano -> Dolar\n" +
                "7. Salir\n";

        System.out.println(sb + "\n");
        System.out.println("Ingrese una opcion:");

        int option = scan.nextInt();

        while (option <= 0 || option > 7) {
            System.out.println("Opción no válida...");
            System.out.print("Ingrese una opción: ");
            option = scan.nextInt();
        }

        System.out.println("Ingresar valor a convertir: ");
        double valor = scan.nextDouble();

        switch (option) {
            case 1:
                double pesosARS= exchangeRateResponse.conversionRates.get("ARS") *valor;
                System.out.printf("%.2f",pesosARS);
                break;
            case 2:
                double dolarARS= valor/exchangeRateResponse.conversionRates.get("ARS");
                System.out.printf("%.2f",dolarARS);
                break;
            case 3:
                double real= exchangeRateResponse.conversionRates.get("BRL") *valor;
                System.out.printf("%.2f",real);
                break;
            case 4:
                double dolarBRL=valor/ exchangeRateResponse.conversionRates.get("BRL");
                System.out.printf("%.2f",dolarBRL);
                break;
            case 5:
                double pesosCOL= exchangeRateResponse.conversionRates.get("COP") /valor;
                System.out.printf("%.2f",pesosCOL);
                break;
            case 6:
                double dolarCOL= valor/exchangeRateResponse.conversionRates.get("COP") /valor;
                System.out.printf("%.2f",dolarCOL);
                break;
            case 7:
                System.out.println("Saliendo...");
                break;
        }


        /**System.out.println("Tasas de conversión:");
         System.out.println("Dolar -> Peso Argentino: " + exchangeRateResponse.conversionRates.get("ARS"));
         System.out.println("Dolar -> Real Brasileño: " + exchangeRateResponse.conversionRates.get("BRL"));
         System.out.println("Dolar -> Peso Colombiano: " + exchangeRateResponse.conversionRates.get("COP"));**/


    }
}
