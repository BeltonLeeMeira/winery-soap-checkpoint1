package br.com.fiap.winery;

import jakarta.jws.WebService;

@WebService(endpointInterface = "br.com.fiap.winery.WineStockService")
public class WineStockServiceImplementation implements WineStockService {

    @Override
    public String getMenu() {
        return """
               === Carta de Vinhos - Winery ===
               Tintos:
                 - Cabernet Sauvignon
                 - Merlot
                 - Malbec
                 - Pinot Noir
                 - Syrah
               Brancos:
                 - Chardonnay
                 - Sauvignon Blanc
                 - Riesling
               Roses:
                 - Grenache
               """;
    }

    @Override
    public String placeOrder(String name, int quantity) {
        System.out.println("Pedido recebido -> Vinho: " + name + " | Quantidade: " + quantity);
        return "Pedido confirmado!";
    }
}
