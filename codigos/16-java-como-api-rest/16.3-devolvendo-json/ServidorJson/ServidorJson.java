// Arquivo: ServidorJson.java

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;

void main() throws IOException {
    List<Livro> livros = List.of(
            new Livro(1, "Dom Casmurro", "Machado de Assis", 1899),
            new Livro(2, "Vidas Secas", "Graciliano Ramos", 1938));

    HttpServer servidor = HttpServer.create(new InetSocketAddress(8080), 0);

    servidor.createContext("/livros", troca -> {
        byte[] corpo = Json.lista(livros).getBytes(StandardCharsets.UTF_8);

        troca.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        troca.sendResponseHeaders(200, corpo.length);
        troca.getResponseBody().write(corpo);
        troca.close();
    });

    servidor.start();
    IO.println("Servidor no ar: http://localhost:8080/livros");
}
