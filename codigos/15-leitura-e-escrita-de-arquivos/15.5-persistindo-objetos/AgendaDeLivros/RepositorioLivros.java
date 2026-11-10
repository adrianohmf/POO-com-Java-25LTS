// Arquivo: RepositorioLivros.java

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class RepositorioLivros {

    private final Path arquivo;

    RepositorioLivros(Path arquivo) {
        this.arquivo = arquivo;
    }

    void salvar(List<Livro> livros) throws IOException {
        Files.createDirectories(arquivo.toAbsolutePath().getParent());
        List<String> linhas = new ArrayList<>();
        for (Livro livro : livros) {
            linhas.add(livro.paraCsv());
        }
        Files.write(arquivo, linhas);
    }

    List<Livro> carregar() throws IOException {
        List<Livro> livros = new ArrayList<>();
        if (!Files.exists(arquivo)) {
            return livros;                       // primeira execução: ainda não há dados
        }
        for (String linha : Files.readAllLines(arquivo)) {
            if (!linha.isBlank()) {
                livros.add(Livro.deCsv(linha));
            }
        }
        return livros;
    }
}
