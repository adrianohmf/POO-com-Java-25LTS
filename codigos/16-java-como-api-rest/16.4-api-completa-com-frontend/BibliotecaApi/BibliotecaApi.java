// Arquivo: BibliotecaApi.java

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;

void main() throws IOException {
    var repositorio = new RepositorioLivros(Path.of("dados", "livros.csv"));
    HttpServer servidor = HttpServer.create(new InetSocketAddress(8080), 0);

    // API: tudo que começa com /livros é tratado pelo LivroHandler
    servidor.createContext("/livros", new LivroHandler(repositorio));

    // Frontend: a página web/index.html é servida na raiz
    servidor.createContext("/", troca -> {
        Path pagina = Path.of("web", "index.html");
        if (!troca.getRequestURI().getPath().equals("/") || !Files.exists(pagina)) {
            LivroHandler.responder(troca, 404, Json.erro("Recurso não encontrado"));
            return;
        }
        byte[] html = Files.readAllBytes(pagina);
        troca.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        troca.sendResponseHeaders(200, html.length);
        troca.getResponseBody().write(html);
        troca.close();
    });

    servidor.start();
    IO.println("Biblioteca no ar: http://localhost:8080");
}
