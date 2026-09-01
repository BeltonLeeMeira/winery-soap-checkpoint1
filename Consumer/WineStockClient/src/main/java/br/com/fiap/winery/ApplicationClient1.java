package br.com.fiap.winery;

import jakarta.xml.ws.Service;
import javax.xml.namespace.QName;
import java.net.URL;

public class ApplicationClient1 {

    public static void main(String[] args) throws Exception {

        URL url = new URL("http://localhost:8085/WineStockService?wsdl");

        // targetNamespace segue o pacote invertido (br.com.fiap.winery -> http://winery.fiap.com.br/)
        // localPart é o nome do serviço gerado pelo JAX-WS: <NomeDaClasse>Service.
        // Confira os valores exatos abrindo o WSDL publicado no navegador antes de rodar.
        QName qName = new QName("http://winery.fiap.com.br/", "WineStockServiceImplementationService");

        Service service = Service.create(url, qName);

        WineStockService wineStockService = service.getPort(WineStockService.class);

        String menu = wineStockService.getMenu();
        System.out.println(menu);
    }
}
