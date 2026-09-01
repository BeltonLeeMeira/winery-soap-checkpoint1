package br.com.fiap.winery;

import jakarta.xml.ws.Endpoint;

public class Loader {

    public static void main(String[] args) {

        // Publica o serviço de estoque/pedidos
        WineStockServiceImplementation wineStock = new WineStockServiceImplementation();
        Endpoint.publish("http://localhost:8085/WineStockService", wineStock);
        System.out.println("Serviço publicado!");

        // Publica o serviço de aviso de estoque insuficiente
        WineWarningServiceImplementation wineWarning = new WineWarningServiceImplementation();
        Endpoint.publish("http://localhost:8086/WineWarningService", wineWarning);
        System.out.println("Serviço publicado!");
    }
}
