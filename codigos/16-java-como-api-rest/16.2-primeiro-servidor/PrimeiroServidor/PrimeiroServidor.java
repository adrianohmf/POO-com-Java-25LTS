// Arquivo: PrimeiroServidor.java

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

void main() throws IOException {
    // Cria o servidor na porta 8080 (0 = tamanho padrão da fila de espera)
    HttpServer servidor = HttpServer.create(new InetSocketAddress(8080), 0);

    // Registra uma rota: o que fazer quando alguém acessar /ola
    servidor.createContext("/ola", troca -> {
        byte[] corpo = "Ola, API do IFPE!".getBytes(StandardCharsets.UTF_8);

        troca.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8");
        troca.sendResponseHeaders(200, corpo.length);   // status 200 e tamanho da resposta
        troca.getResponseBody().write(corpo);           // corpo da resposta
        troca.close();
    });

    servidor.start();
    IO.println("Servidor no ar: http://localhost:8080/ola");
}
