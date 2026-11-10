// Arquivo: RepositorioLivros.java

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

// Guarda os livros em um arquivo CSV (mesma ideia do capítulo 15)
class RepositorioLivros {

    private final Path arquivo;

    RepositorioLivros(Path arquivo) {
        this.arquivo = arquivo;
    }

    List<Livro> carregar() throws IOException {
        var livros = new ArrayList<Livro>();
        if (!Files.exists(arquivo)) {
            return livros;
        }
        for (String linha : Files.readAllLines(arquivo)) {
            if (linha.isBlank()) continue;
            String[] p = linha.split(";");
            livros.add(new Livro(Integer.parseInt(p[0]), p[1], p[2], Integer.parseInt(p[3])));
        }
        return livros;
    }

    void salvar(List<Livro> livros) throws IOException {
        Files.createDirectories(arquivo.toAbsolutePath().getParent());
        var linhas = new ArrayList<String>();
        for (Livro l : livros) {
            // ponto e vírgula dentro do texto quebraria o CSV, por isso é trocado por vírgula
            linhas.add(l.id() + ";" + l.titulo().replace(';', ',') + ";"
                    + l.autor().replace(';', ',') + ";" + l.ano());
        }
        Files.write(arquivo, linhas);
    }
}
