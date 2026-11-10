// Arquivo: LivroHandler.java

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Trata todas as requisições para /livros e /livros/{id}
class LivroHandler implements HttpHandler {

    private final RepositorioLivros repositorio;
    private final List<Livro> livros;

    LivroHandler(RepositorioLivros repositorio) throws IOException {
        this.repositorio = repositorio;
        this.livros = new ArrayList<>(repositorio.carregar());
    }

    @Override
    public void handle(HttpExchange troca) throws IOException {
        try {
            String metodo = troca.getRequestMethod();
            String caminho = troca.getRequestURI().getPath();       // /livros ou /livros/3
            String id = caminho.length() > "/livros/".length()
                    ? caminho.substring("/livros/".length()) : null;

            switch (metodo) {
                case "GET"     -> listarOuBuscar(troca, id);
                case "POST"    -> cadastrar(troca);
                case "DELETE"  -> remover(troca, id);
                case "OPTIONS" -> responder(troca, 204, "");         // resposta ao navegador (CORS)
                default        -> responder(troca, 405, Json.erro("Metodo nao permitido"));
            }
        } catch (Exception e) {
            responder(troca, 500, Json.erro("Erro interno do servidor"));
        }
    }

    // GET /livros  ou  GET /livros/{id}
    private void listarOuBuscar(HttpExchange troca, String id) throws IOException {
        if (id == null) {
            responder(troca, 200, Json.lista(livros));
            return;
        }
        Livro livro = buscarPorId(id);
        if (livro == null) {
            responder(troca, 404, Json.erro("Livro nao encontrado"));
        } else {
            responder(troca, 200, livro.paraJson());
        }
    }

    // POST /livros   corpo: {"titulo":"...","autor":"...","ano":1899}
    private void cadastrar(HttpExchange troca) throws IOException {
        String corpo = new String(troca.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
        Map<String, String> campos = Json.lerCampos(corpo);

        String titulo = campos.getOrDefault("titulo", "").trim();
        String autor = campos.getOrDefault("autor", "").trim();
        int ano;
        try {
            ano = Integer.parseInt(campos.getOrDefault("ano", ""));
        } catch (NumberFormatException e) {
            responder(troca, 400, Json.erro("Informe um ano valido"));
            return;
        }
        if (titulo.isEmpty() || autor.isEmpty()) {
            responder(troca, 400, Json.erro("Titulo e autor sao obrigatorios"));
            return;
        }

        int novoId = livros.stream().mapToInt(Livro::id).max().orElse(0) + 1;
        Livro novo = new Livro(novoId, titulo, autor, ano);
        livros.add(novo);
        repositorio.salvar(livros);
        responder(troca, 201, novo.paraJson());                     // 201 = criado
    }

    // DELETE /livros/{id}
    private void remover(HttpExchange troca, String id) throws IOException {
        Livro livro = id == null ? null : buscarPorId(id);
        if (livro == null) {
            responder(troca, 404, Json.erro("Livro nao encontrado"));
            return;
        }
        livros.remove(livro);
        repositorio.salvar(livros);
        responder(troca, 204, "");                                  // 204 = sem conteúdo
    }

    private Livro buscarPorId(String id) {
        try {
            int numero = Integer.parseInt(id);
            for (Livro l : livros) {
                if (l.id() == numero) return l;
            }
        } catch (NumberFormatException e) {
            // id que não é número: tratado como não encontrado
        }
        return null;
    }

    // Envia a resposta com status, cabeçalhos e corpo
    static void responder(HttpExchange troca, int status, String json) throws IOException {
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        var cabecalhos = troca.getResponseHeaders();
        cabecalhos.set("Content-Type", "application/json; charset=UTF-8");
        cabecalhos.set("Access-Control-Allow-Origin", "*");         // libera frontend em outra origem
        cabecalhos.set("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTIONS");
        cabecalhos.set("Access-Control-Allow-Headers", "Content-Type");
        // 204 não tem corpo: o tamanho é -1
        troca.sendResponseHeaders(status, status == 204 ? -1 : bytes.length);
        if (status != 204) {
            troca.getResponseBody().write(bytes);
        }
        troca.close();
    }
}
