// Arquivo: LerArquivo.java

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;

void main() throws IOException {
    Path arquivo = Path.of("saida", "notas.txt");

    // Prepara um arquivo de exemplo para o programa funcionar sozinho
    Files.createDirectories(arquivo.getParent());
    Files.write(arquivo, List.of("Maria;8.5", "Joao;7.0", "Ana;9.5", "Pedro;6.0"));

    // readString: o arquivo inteiro em um único texto
    String tudo = Files.readString(arquivo);
    IO.println("Caracteres lidos: " + tudo.length());

    // readAllLines: uma lista com uma String por linha
    List<String> linhas = Files.readAllLines(arquivo);
    double soma = 0;
    for (String linha : linhas) {
        String[] partes = linha.split(";");           // separa nome e nota
        String nome = partes[0];
        double nota = Double.parseDouble(partes[1]);  // texto -> número
        IO.println(nome + " tirou " + nota);
        soma += nota;
    }
    IO.println("Media: " + soma / linhas.size());

    // Arquivo que não existe lança NoSuchFileException
    try {
        Files.readAllLines(Path.of("saida", "nao-existe.txt"));
    } catch (NoSuchFileException e) {
        IO.println("Arquivo nao encontrado: " + e.getMessage());
    }
}
